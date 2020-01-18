package com.example.demo;
/*
 * Pairing of a points and goal difference
 */
class Score {

	private Integer points = 0;
	private Integer goalDifference = 0;
	
	public Score() {
	}
	
	public Score(int points, int goalDifference) {
		this.points = points;
		this.goalDifference = goalDifference;
	}
	
	public Integer getPoints() {
		return points;
	}

	public Integer getGoalDifference() {
		return goalDifference;
	}

	public void accumulate(Score other) {
		this.points += other.points;
		this.goalDifference += other.goalDifference;
	}

	@Override
	public String toString() {
		return "Score [points=" + points + ", goalDifference=" + goalDifference + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goalDifference == null) ? 0 : goalDifference.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (goalDifference == null) {
			if (other.goalDifference != null)
				return false;
		} else if (!goalDifference.equals(other.goalDifference))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}
	
}