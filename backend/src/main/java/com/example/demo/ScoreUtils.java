package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreUtils {

	/*
	 * Method takes a hashmap of scores and adds score of a match result to it.
	 */

	// TODO CVDW - Remove logic duplication

	public static void accumulateScore(Map<String, Score> scores, Match matchResult) {

		String teamAName = matchResult.getTeamA().toUpperCase();
		String teamBName = matchResult.getTeamB().toUpperCase();

		int teamAPoints = 0;
		int teamBPoints = 0;

		if (matchResult.getTeamAGoals() == matchResult.getTeamBGoals()) {
			teamAPoints = 1;
			teamBPoints = 1;
		} else {
			teamAPoints = matchResult.getTeamAGoals() > matchResult.getTeamBGoals() ? 3 : 0;
			teamBPoints = matchResult.getTeamBGoals() > matchResult.getTeamAGoals() ? 3 : 0;
		}

		int teamAGoalDifference = matchResult.getTeamAGoals() - matchResult.getTeamBGoals();
		int teamBGoalDifference = matchResult.getTeamBGoals() - matchResult.getTeamAGoals();

		if (!scores.containsKey(teamAName)) {
			scores.put(teamAName, new Score());
		}

		if (!scores.containsKey(teamBName)) {
			scores.put(teamBName, new Score());
		}

		scores.get(teamAName).accumulate(new Score(teamAPoints, teamAGoalDifference));
		scores.get(teamBName).accumulate(new Score(teamBPoints, teamBGoalDifference));

	}

	public static List<TeamScore> calculateScores(Iterable<Match> matchResults) {

		Map<String, Score> teamScores = new HashMap<String, Score>();

		matchResults.forEach(matchResult -> {
			ScoreUtils.accumulateScore(teamScores, matchResult);
		});

		return teamScores.entrySet().stream().map(e -> new TeamScore(e.getKey(), e.getValue()))
				.sorted(new TeamScoreComparator()).collect(Collectors.toList());

	}

	// TODO CVDW - Not used
	private List<String> getUniqueTeamNames(List<Match> matchResults) {
		return matchResults.stream().flatMap(r -> Arrays.stream(new String[] { r.getTeamA(), r.getTeamB() })).distinct()
				.collect(Collectors.toList());
	}
}
