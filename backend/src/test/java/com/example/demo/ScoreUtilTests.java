package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.model.MatchResult;
import com.example.demo.model.Score;
import com.example.demo.model.TeamScore;
import com.example.demo.utils.ScoreUtils;

public class ScoreUtilTests {

	@Test
	public void TestScoreCalculationSimple() {

		MatchResult[] matches = new MatchResult[] { new MatchResult("A", 2, "B", 0), };

		List<TeamScore> result = ScoreUtils.calculateScores(Arrays.asList(matches));
		result.stream().forEach(r -> System.out.println(r.toString()));

		assertEquals(result.size(), 2);

		assertEquals(result.get(0), new TeamScore("B", new Score(0, -2)));
		assertEquals(result.get(1), new TeamScore("A", new Score(3, 2)));
	}

	@Test
	public void TestScoreCalculation() {
		
		MatchResult[] matches = new MatchResult[]{
				new MatchResult("Manchester United", 2, "Chelsea", 0),
				new MatchResult("Manchester United", 1, "LIverpool", 1), 
				new MatchResult("Manchester United", 0, "Arsenal", 2),
				new MatchResult("Chelsea", 3, "Manchester United", 1),
				new MatchResult("Liverpool", 0, "Manchester United", 1),
				new MatchResult("Arsenal", 2, "Manchester United", 2),
				new MatchResult("Chelsea", 2, "Liverpool", 3),
				new MatchResult("Chelsea", 0, "Arsenal", 0),
				new MatchResult("Liverpool", 0, "Chelsea", 1),
				new MatchResult("Arsenal", 5, "Chelsea", 1),
				new MatchResult("Liverpool", 2, "Arsenal", 1),
				new MatchResult("Arsenal", 3, "Liverpool", 0)
		};
		
		List<TeamScore> result = ScoreUtils.calculateScores(Arrays.asList(matches));
		result.stream().forEach(r -> System.out.println(r.toString()));
		
		assertEquals(result.size(), 4);
	
		assertEquals(result.get(0), new TeamScore("ARSENAL", new Score(11,8)));
		assertEquals(result.get(1), new TeamScore("MANCHESTER UNITED", new Score(8, -1)));
		assertEquals(result.get(2), new TeamScore("LIVERPOOL", new Score(7,-3)));
		assertEquals(result.get(3), new TeamScore("CHELSEA", new Score(7,-4)));
	}

	@Test
	public void TestScoreCalculationTied() {

		MatchResult[] matches = new MatchResult[] { new MatchResult("A", 2, "B", 0), new MatchResult("B", 2, "C", 0),
				new MatchResult("C", 2, "A", 0) };

		List<TeamScore> result = ScoreUtils.calculateScores(Arrays.asList(matches));

		assertEquals(result.size(), 3);

		assertEquals(result.get(0), new TeamScore("A", new Score(3, 0)));
		assertEquals(result.get(1), new TeamScore("B", new Score(3, 0)));
		assertEquals(result.get(2), new TeamScore("C", new Score(3, 0)));
	}

	@Test
	public void TestScoreCalculationEmpty() {

		MatchResult[] matches = new MatchResult[] {};

		List<TeamScore> result = ScoreUtils.calculateScores(Arrays.asList(matches));
		result.stream().forEach(r -> System.out.println(r.toString()));

		assertEquals(result.size(), 0);
	}
}
