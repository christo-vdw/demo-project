package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
//TODO CVDW - Rename to match_result
public class Match {
	private long id;
	private String val;
	
	private String teamA;
	private String teamB;
	
	private Integer teamAGoals;
	private Integer teamBGoals;
	
	public Match(String teamA, Integer teamAGoals, String teamB, Integer teamBGoals) {
		
		this.teamA = teamA;
		this.teamB = teamB;
		this.teamAGoals = teamAGoals;
		this.teamBGoals = teamBGoals;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "val", nullable = false)
	public String getVal() {
		return val;
	}

	public void setVal(String value) {
		this.val = value;
	}
	
	@Column(name = "team_a", nullable = false)
	public String getTeamA() {
		return teamA;
	}

	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}

	@Column(name = "team_b", nullable = false)
	public String getTeamB() {
		return teamB;
	}

	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}
	
	@Column(name = "team_a_goals", nullable = false)
	public Integer getTeamAGoals() {
		return teamAGoals;
	}

	public void setTeamAGoals(Integer teamAGoals) {
		this.teamAGoals = teamAGoals;
	}

	@Column(name = "team_b_goals", nullable = false)
	public Integer getTeamBGoals() {
		return teamBGoals;
	}

	public void setTeamBGoals(Integer teamBGoals) {
		this.teamBGoals = teamBGoals;
	}

}