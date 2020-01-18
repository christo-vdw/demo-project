package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueTableService {
	
	@Autowired
	private MatchResultRepository matchResultRepository;

	List<TeamScore> calculateScores(){
		return ScoreUtils.calculateScores(matchResultRepository.findAll());
	}

}
