package Alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Alertas {
	
	//MENSAJE DE ERROR 
	
	// ESTE ICONO TENDRA LA OPCION DE 
	// TE MUESTRA QUE OCURRIO UN ERROR Y PARA VER 
	// LOS ERRORES EXACTAMENTE TENDRAS QUE DARLE A MOSTRAR DETALLES.
	public static void AlertError(String MensajePrimeraVista ,String MensajeDeError) {
	    Alert Mensaje = new Alert(Alert.AlertType.ERROR);
	    Mensaje.setTitle("Error.");
	    Mensaje.setHeaderText(null);
	    Mensaje.setContentText("Ocurrió un error.\n" + MensajePrimeraVista);
	    // Se crea un objeto de tipo TextArea, se le asigna un texto y se configura para
	    //que no sea editable y se ajuste al ancho y alto máximo disponible
	    TextArea textArea = new TextArea(MensajeDeError);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);
	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);
	    GridPane contenido = new GridPane();
	    contenido.setMaxWidth(Double.MAX_VALUE);
	    contenido.add(textArea, 0, 0);
	    Mensaje.getDialogPane().setExpandableContent(contenido);
	    Mensaje.showAndWait();
	}

	//MENSAJE DE INFORMACION 
	public static void Informacion(String MensajeDeInformacion) {
		Alert Mensaje = new Alert(Alert.AlertType.INFORMATION);
		Mensaje.setHeaderText(null);
		Mensaje.setTitle("Información.");
		Mensaje.setContentText(MensajeDeInformacion);
		Mensaje.showAndWait();
		}
	//MENSAJE DE ADVERTENCIA 
	public static void Advertencia(String MensajeDeAdvertencia) {
		Alert Mensaje = new Alert(Alert.AlertType.WARNING);
		Mensaje.setHeaderText(null);
		Mensaje.setTitle("Advertencia.");
		Mensaje.setContentText(MensajeDeAdvertencia);
		Mensaje.showAndWait();
		}
	}