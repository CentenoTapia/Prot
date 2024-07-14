package demo_jdbc.models;

public class MaxPointConstructor {

	private String name;
	private int points;
	
	
	public MaxPointConstructor(String name,int points) {
		super();
		this.name = name;
		this.points = points;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
}
