package demo_jdbc.models;

public class ConstructorResult {

	private String name;
	private int wins;
	private int totalPoints;
	private int rank;
	

	public ConstructorResult(String name, int wins, int totalPoints, int rank) {
		super();
		this.name = name;
		this.wins = wins;
		this.totalPoints = totalPoints;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}