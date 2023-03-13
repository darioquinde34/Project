package Controladores;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class ControladorFuncionalidades extends ControladorPrincipal{
	
	@FXML
	private Button BotonVolver;

	public void VOLVER_PULSADO(ActionEvent event) throws IOException {
		super.Home_PULSADO(event);
	}
}