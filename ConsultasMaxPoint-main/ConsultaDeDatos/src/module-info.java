module demo_jdbc {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens demo_jdbc to javafx.graphics, javafx.fxml;
	opens demo_jdbc.models to javafx.base;
}
