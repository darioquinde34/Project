module Project {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	
	opens Aplicacion to javafx.graphics, javafx.fxml;
	opens Controladores to javafx.fxml;	
}
