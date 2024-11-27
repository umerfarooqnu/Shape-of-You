module JavaFxProject1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.media;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens db to javafx.graphics, javafx.fxml;
	opens handlers to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens ui to javafx.graphics, javafx.fxml;
	opens repositories to javafx.graphics, javafx.fxml;
}