package Aplicacion;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static String Logo() { //UBICACION DEL LOGO SUBIDO AL SERVIDOR
		return "https://facturpdr.000webhostapp.com/Imagenes/Logotipo/LogoFactur.png" ;
	}
	public static String PaginaPrincipal() {
		return "/ViewPrincipal/Principal.fxml"; //PAGINA PRINCIPAL QUE SE MOSTRARA AL ABRIR LA APLICACION
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(PaginaPrincipal()));
			Scene scene = new Scene(root,1200,700);
			primaryStage.setTitle("FACTURA PDR");
			// Icono logotipo
			primaryStage.getIcons().add(new Image(Logo())); 
			primaryStage.setResizable(false); // Hacer que la ventana no sea redimensionable
			primaryStage.setScene(scene);
			primaryStage.show();		 
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}