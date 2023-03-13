package Controladores;

import java.io.IOException;

import Aplicacion.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;

public class ControladorPrincipal {
	@FXML 
	private Hyperlink Texto_Superior_Home ;
	
	@FXML 
	private Hyperlink Texto_Superior_FUNCIONALIDADES;
	
	@FXML
    private Button Boton_Superior_REGISTRASE;
    
    @FXML
    private Button Boton_Superior_INICIARSESION;
    
    @FXML
    public void Home_PULSADO(ActionEvent event) throws IOException {
        // Cargar el archivo FXML y obtener el objeto Parent de la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.PaginaPrincipal()));
        Parent principalRoot = loader.load();

        Scene currentScene = ((Node) event.getSource()).getScene();
	    // Cambiamos la raíz de la escena por la vista de la pantalla anterior, eliminando así la pantalla actual
	    currentScene.setRoot(principalRoot);
	   /*
	     * currentScene.setRoot(principalRoot) establece el nodo raíz de la escena actual en 
	     * la nueva instancia de la pantalla principal, reemplazando así el contenido anterior.
	     */
    }
    @FXML
    public void REGISTRO_PULSADO(ActionEvent event) throws IOException {
   	 // Cargar el archivo FXML y obtener el objeto Parent de la ventana
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewRegistro/Registro.fxml"));
       Parent registroRoot = loader.load();

       // Agregar el objeto Parent de la ventana de registro como hijo del objeto BorderPane de la ventana principal
       BorderPane mainRoot = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
       mainRoot.setCenter(registroRoot);
   }
    @FXML
    public void FUNCIONALIDADES_PULSADO(ActionEvent event) throws IOException {
      	 // Cargar el archivo FXML y obtener el objeto Parent de la ventana
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewFuncionalidades/Funcionalidades.fxml"));
          Parent registroRoot = loader.load();

          // Agregar el objeto Parent de la ventana de registro como hijo del objeto BorderPane de la ventana principal
          BorderPane mainRoot = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
          mainRoot.setCenter(registroRoot);
      }									
      
    @FXML
    public void SESSION_PULSADO(ActionEvent event) throws IOException {
        System.out.println("Se ha pulsado el botón 'Iniciar sesión'");
      	 // Cargar el archivo FXML y obtener el objeto Parent de la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewInicioSesion/InicioSesion.fxml"));
        Parent registroRoot = loader.load();

        // Agregar el objeto Parent de la ventana de registro como hijo del objeto BorderPane de la ventana principal
        BorderPane mainRoot = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        mainRoot.setCenter(registroRoot);    }
      
    

    /*
     * 
     *EVENTO QUE REMUEVE TODO EL COTENIDO ACTUAL Y MUESTRA EL CONTENIDO
     *QUE SE LE ASIGNA
    public void REGISTRO_PULSADO(ActionEvent event) throws IOException {
        // Obtener el objeto Pane que contiene la ventana actual
        Pane ventanaActual = (Pane) ((Node) event.getSource()).getScene().getRoot();

        // Cargar el archivo FXML y obtener el objeto Parent de la nueva página
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewRegistro/Registro.fxml"));
        Parent nuevaPagina = loader.load();

        // Reemplazar el contenido de la ventana actual por el de la nueva página
        ventanaActual.getChildren().setAll(nuevaPagina);
    }
*/
    /*
     * EVENTO QUE CUANDO PINCHAS UN BOTON CIERRA LA VENTANA ANTERIOR
     * Y MUESTRE UNA NUEVA CON OTRO CONTENIDO
    @FXML
    public void REGISTRO_PULSADO(ActionEvent event) throws IOException {
    	    // Obtener el Stage actual a partir del evento de acción
    	    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    	    // Cargar el archivo FXML y obtener el objeto Parent de la nueva ventana
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewRegistro/Registro.fxml"));
    	    Parent nuevaVentanaParent = loader.load();

    	    // Crear la nueva Stage y establecer la escena con el objeto Parent cargado anteriormente
    	    Stage nuevaVentanaStage = new Stage();
    	    nuevaVentanaStage.setScene(new Scene(nuevaVentanaParent));

    	    // Mostrar la nueva ventana
    	    nuevaVentanaStage.show();

    	    // Cerrar la ventana anterior
    	    currentStage.close();
    	}
    	*/
}