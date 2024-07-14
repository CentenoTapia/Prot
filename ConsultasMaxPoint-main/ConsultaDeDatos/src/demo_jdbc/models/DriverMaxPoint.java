package demo_jdbc.models;

public class DriverMaxPoint {
	
	private String driverName;
	private float points;
	
	public DriverMaxPoint(String driverName, float points) {
		super();
		this.driverName = driverName;
		this.points = points;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}
	
	
}