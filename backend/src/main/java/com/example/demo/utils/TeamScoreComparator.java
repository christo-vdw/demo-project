package com.example.demo.utils;

import java.util.Comparator;

import com.example.demo.model.TeamScore;

//TODO CVDW - Test
class TeamScoreComparator implements Comparator<TeamScore> {
	public int compare(TeamScore s1, TeamScore s2) {
		if (s1.getScore().getPoints() == s2.getScore().getPoints())
			return s2.getScore().getGoalDifference().compareTo(s1.getScore().getGoalDifference());
		else {
			return s2.getScore().getPoints().compareTo(s1.getScore().getPoints());
		}
	}
}