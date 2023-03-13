package Controladores;

import UsuarioEmpresa.UsuarioEmpresa;
import Empresa.Empresa;
import java.io.IOException;
import java.sql.SQLException;

import BaseDeDatos.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Alertas.Alertas;


public class ControladorRegistro extends SQL  {
	private ControladorPrincipal Controlador = new ControladorPrincipal();
	//TODOS LOS FICHEROS TEXTFIELD Y BOTONES
	//UBICADOS EN EL ARCHIVO /VIEWREGISTRO/REGISTRO.FXML
	@FXML
	private TextField TF_NOMBRE_EMPRESA ; 
	@FXML
	private TextField TF_DIRECCION_EMPRESA ;
	@FXML 
	private TextField TF_NUMERO_EMPRESA ;
	@FXML
	private TextField TF_NOMBRE_USUARIO_REGISTRO ;
	@FXML
	private TextField TF_PUESTO_USUARIO_REGISTRO;
	@FXML
	private TextField TF_NOMBRE_USUARIO ;
	@FXML 
	private TextField TF_CORREO_ELECTRONICO ;
	@FXML 
	private PasswordField CONTRASENA ;
	@FXML
	private Button BotonVolver_VIEWREGISTRO ;
	@FXML
	private Button BotonREGISTRO ;
	//ATRIBUTOS 
	private String NombreEmpresa;
	private String DireccionEmpresa;	
	private String NumeroEmpresa;
	private String NombreUsuarioRegistro;
	private String PuestoUsuarioRegistro;
	private String NombreUsuario;
	private String CorreoElectronico;
	private String Contrasena;
	
	//DECLARACION DE USUARIO Y EMPRESA 
	private UsuarioEmpresa usuarioempresa ;
	private Empresa empresa ;
	//SE ALMACENARA TODOS LOS MENSAJES
	//COMO SI EL CORREO NO ES VALIDO O SI LA CONTRASEÑA 
	//NO TIENE LA LONGITUD DE 9 
	// SE ALMACENARA EN UN STRING Y LUEGO SE PASARA COMO PARAMETRO EN UNA ALERTA DEL PAQUETE ALERTAS
	private String mensaje="";	
	
	public void RegistrarUsuarioEmpresa() throws ClassNotFoundException, SQLException  {
		//SE ALMACENA LO QUE INTRODUCE EL USUARIO EN VARIABLES STRING.
		NombreUsuario = TF_NOMBRE_USUARIO.getText();
		//SI EL VALOR DE NOMBRE USUARIO ESTA VACIO
		// DEVUELVE VERDADERO Y LANZAMOS UN MENSAJE.
		if(NombreUsuario.isEmpty()) {
			mensaje+= "El Nombre de Usuario esta vacio.\n";
		}
		CorreoElectronico = TF_CORREO_ELECTRONICO.getText();
		//COMPRUEBA SI EL CORREO ES VALIDO.
		//EL METODO COMPRUEBA CORREO ESTA EN USUARIO EMPRESA
		if(!UsuarioEmpresa.compruebacorreo(CorreoElectronico)) {
			mensaje+= "Correo esta vacío o no es valido , recuerda que tiene que tener '@' y punto como mínimo.\n";
			//EN CASO DE ERROR SE VA ALMCENADO EL MENSAJE PARA LUEGO MOSTRARLO POR PANTALLA 
		}
		Contrasena = CONTRASENA.getText();
		if(!UsuarioEmpresa.compruebacontrasena(Contrasena) || Contrasena.isEmpty() ) {
			mensaje+= "La contrasena tiene que tener una longitud minima de 9 caracteres.\n";
		}
	    // Si hay algún mensaje de error, se muestra el mensaje y no se inserta nada
	    if (!mensaje.isEmpty()) {
	        return; // SE DETIENE EL PROGRAMA AQUI , SI NO AHI DATOS , NO SE CREARA EL OBJECTO USUARIO EMPRESA
	    }
		//LLAMADA AL CONSTRUCTOR DE USUARIO EMPRESA
		usuarioempresa = new UsuarioEmpresa(NombreUsuario,CorreoElectronico,Contrasena);
	}
	public void RegistrarEmpresa() throws ClassNotFoundException, SQLException {
		 // Validación de los campos de la empresa
	    NombreEmpresa = TF_NOMBRE_EMPRESA.getText();
	    if (NombreEmpresa.isEmpty()) {
	        mensaje += "El Nombre de Empresa esta vacio.\n";
	    }
	    DireccionEmpresa = TF_DIRECCION_EMPRESA.getText();
	    if (DireccionEmpresa.isEmpty()) {
	        mensaje += "La direccion de la empresa esta vacia.\n";
	    }
	    NumeroEmpresa = TF_NUMERO_EMPRESA.getText();
	    if (NumeroEmpresa.isEmpty() || !Empresa.Telefono(NumeroEmpresa)) {
	        mensaje += "El numero de la empresa esta vacio o no es un numero de teléfono.\n";
	    }
	    NombreUsuarioRegistro = TF_NOMBRE_USUARIO_REGISTRO.getText();
	    if (NombreUsuarioRegistro.isEmpty()) {
	        mensaje += "El nombre de usuario de Usuario registro esta vacio.\n";
	    }
		PuestoUsuarioRegistro = TF_PUESTO_USUARIO_REGISTRO.getText();
		if(PuestoUsuarioRegistro.isEmpty()) {
			mensaje+= "El puesto de registro esta vacio. \n" ;
		}
		if(!mensaje.isEmpty()) {
	        return; // SE DETIENE EL PROGRAMA AQUI , SI NO AHI DATOS , NO SE CREARA EL OBJECTO EMPRESA 
		}
		empresa = new Empresa(NombreEmpresa,DireccionEmpresa,NumeroEmpresa,NombreUsuarioRegistro,PuestoUsuarioRegistro);
	}
	@FXML
	public void VOLVER_VIEWREGISTRO(ActionEvent event) throws IOException {
		Controlador.Home_PULSADO(event);
	}
	@FXML
	public void BOTONREGISTRO_PULSADO(ActionEvent event) throws Exception{
		RegistrarUsuarioEmpresa();
		RegistrarEmpresa();
		//LOS OBJECTOS SOLO SE CRERAN SI NO SE DEVUELVE NINGUN MENSAJE DE ERROR.
		if(mensaje.isEmpty()) {
			SQL.Conexion(); 
			// CONSULTA EXISTENCIA CORREO SOLO TOMA 1 O 0 , NUNCA SER MAYOR A 1.
			if(SQL.ConsultaExistenciaCorreo(usuarioempresa.getCorreo().toUpperCase()) == 1 ) { // si la consulta devuelve 1 lanza un WARNING
				Alertas.Advertencia("Ya existe un usuario con el mismo correo electronico");
				System.out.println("MENSAJE : YA EXISTE UN USUARIO CON EL MISMO CORREO ELECTRONICO");
			}
			else if(SQL.ConsultaExistenciaUsuario(usuarioempresa.getNombre().toUpperCase()) == 1 )  {
				Alertas.Advertencia("Ya existe un usuario con el mismo usuario");
				System.out.println("MENSAJE : YA EXISTE UN USUARIO CON EL MISMO DE USUARIO");
			}
			else {
				SQL.InsertarUsuarioEmpresa(usuarioempresa);
				SQL.InsertarEmpresa(empresa);
				//MENSAJE DE INFORMACION PARA AVISAR AL USUARIO DE LA INTRODUCION DE DATOS
				Alertas.Informacion("Se ha registrado correctamente.\nBienvenido a FACTURPDR");
				System.out.println("USUARIO REGISTRADO EN FACTUR PDR");
				//Elimina los campos que ha escrito el usuario una vez le aceptar a la alerta de informacion
				TF_NOMBRE_EMPRESA.clear();
				TF_DIRECCION_EMPRESA.clear();
				TF_NUMERO_EMPRESA.clear();
				TF_NOMBRE_USUARIO_REGISTRO.clear();
				TF_PUESTO_USUARIO_REGISTRO.clear();
				TF_NOMBRE_USUARIO.clear(); ;
				TF_CORREO_ELECTRONICO.clear(); ;
				CONTRASENA.clear(); ;				
			}
			SQL.CerrarConexion();
		} else {
			// DE LO CONTRARIO SI LA VARIABLE MENSAJE TIENE CONTENIDO
			// SE MOSTRARA POR PANTALLA LOS ERRORES.
			Alertas.AlertError("Hay campos vacios.",mensaje);
			//RESTABLECE EL MENSAJE DE ERRORES 
			//SI QUITAMOS ESTA PARTE DEL CODIGO , UNA VEZ SE INTRODUCE EL CODIGO
			//QUEDA EN UN BUCLE , NO DJEA INTRODUCIR DATOS POR QUE COMO TAL
			//LA VARIABLE TIENE MENSAJES DE ERORES
			//POR ESO AHI QUE IGUALARLO A VACIO EL MENSAJE.
			//PARA QUE SI EL USUARIO DEJA EN VACIO Y QUIERO RETOMAR EL REGISTRO
			//PUEDA RETOMAR SIN TENER QUE VOLVER A INCIO Y VOLVER A REGISTRO
			mensaje="";
		}		
	}
}