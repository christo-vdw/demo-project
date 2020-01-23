package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TeamScore;
import com.example.demo.storage.MatchResultRepository;
import com.example.demo.utils.ScoreUtils;

@Service
public class LeagueTableService {
	
	@Autowired
	private MatchResultRepository matchResultRepository;

	public List<TeamScore> calculateScores(){
		return ScoreUtils.calculateScores(matchResultRepository.findAll());
	}

}
