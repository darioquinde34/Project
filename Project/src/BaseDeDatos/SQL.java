package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Empresa.Empresa;
import UsuarioEmpresa.UsuarioEmpresa;

public class SQL {
	private static String bd="XE" ; //NOMBRE DE LA BDD
	private static String login="FACTURPDR";//USUARIO DE BBDD
	private static String password="FACTURPDR";//CONTRASEÑA

	//RUTA DEL SERVIDOR
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	static Connection conexion = null;
	static ResultSet rs = null;
	static Statement st = null;
	static PreparedStatement insertar = null;
	
	//CONEXION A LA BASE DE DATOS
	public static void Conexion() {
		try {
			//DRIVER PARA ORACLE	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url,login,password);
				if(conexion!=null) {
					System.out.println("Conexion realizada correctamente");
				}
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}
	//OBTIENE EL ID MAYOR DE USUARIOEMPRESA
	// Y DE LA EMPRESA 
	public static int ConsultaMAXID(String columna , String Tabla) throws SQLException {
	    int incremento = 0;
	    st = conexion.createStatement();
	    rs = st.executeQuery("SELECT MAX("+columna+") FROM " + Tabla );
	    if (rs.next()) {
	    	incremento = rs.getInt(1);
	    }
	    st.close();
	    return incremento+1;
	}
	//CONSULTA QUE COMPRUEBA EL CORREO EXISTENTE 
	public static int ConsultaExistenciaCorreo(String condicion) throws SQLException {
		int n = 0 ;
		// SE INTRODUCE UN ? , PARA MAS ADELANTE PONER LA CONDICION 
		// EVITAMOS ERRORES DE ESTE MODO AL INTRODUCIR DATOS.
		PreparedStatement ps = conexion.prepareStatement("SELECT COUNT(*) FROM USUARIOEMPRESA WHERE UPPER(CORREOELECTRONICO) = ?");
		// Se establece el valor del parámetro en la sentencia SQL
		ps.setNString(1, condicion.toUpperCase());
		
		// Se ejecuta la consulta y se obtiene el resultado
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			n = rs.getInt(1);
		}
		return n ; // RETORNA EL VALOR , EN ESTE CASO SOLO SERA 0 SI NO EXISTE O 1 , NUNCA VA A SER MAS DE 1
	}
	
	//CONSULTA QUE COMPRUEBA SI EXISTE USUARIO CON EL MISMO NOMBRE 
	public static int ConsultaExistenciaUsuario(String condicion) throws Exception {
		int n = 0 ;
		// SE INTRODUCE UN ? , PARA MAS ADELANTE PONER LA CONDICION 
		// EVITAMOS ERRORES DE ESTE MODO AL INTRODUCIR DATOS.
		PreparedStatement ps = conexion.prepareStatement("SELECT COUNT(*) FROM USUARIOEMPRESA WHERE UPPER(NOMBREUSUARIO) = ?");
		// Se establece el valor del parámetro en la sentencia SQL
		ps.setNString(1, condicion.toUpperCase());
		
		// Se ejecuta la consulta y se obtiene el resultado
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			n = rs.getInt(1);
		}
		return n ; // RETORNA EL VALOR , EN ESTE CASO SOLO SERA 0 SI NO EXISTE O 1 , NUNCA VA A SER MAS DE 1
	}
	//METODO QUE INSERTARA EL USUARIO EMPRESA
    public static void InsertarUsuarioEmpresa(UsuarioEmpresa usuario) throws ClassNotFoundException, SQLException {
    	
        try {
            // Crear una sentencia SQL
        	insertar = conexion.prepareStatement("INSERT INTO USUARIOEMPRESA VALUES (?,?,?,?)");
        	insertar.setInt(1, ConsultaMAXID("IDUSUARIOEMPRESA","USUARIOEMPRESA") );
        	insertar.setString(2, usuario.getNombre());
        	insertar.setString(3, usuario.getCorreo());
        	insertar.setString(4, usuario.getContrasena());
        	insertar.executeUpdate();
            System.out.println(usuario.toString());
            System.out.println("MENNSAJE : La operacion InsertarUsuarioEmpresa se ha completado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo insertar el usuario en la base de datos.");
        } 
        
    }
    //METODO QUE INSERTARA LA EMPRESA
   
    public static void InsertarEmpresa(Empresa empresa) throws ClassNotFoundException, SQLException {
    	try {
    		 	insertar = conexion.prepareStatement("INSERT INTO EMPRESA VALUES (?, ?, ?, ?, ?, ?, ?)");
    		    insertar.setInt(1, ConsultaMAXID("IDEMPRESA","EMPRESA") );
    		    insertar.setString(2, empresa.getNombre());
    		    insertar.setString(3, empresa.getDireccionFiscal());
    		    insertar.setString(4, empresa.getTelefono());
    		    insertar.setString(5, empresa.getNombreUsuarioRegistro());
    		    insertar.setString(6, empresa.getPuestoUsuarioRegistro());
    		    //AL HACER LA CONSULTA DE LA ID SE TIENE QUE EL USUARIO TIENE QUE EXISTIR
    		    //EL METODO LO QUE HACE ES INCREMENTAR EN 1 POR QUE ASI LO HEMOS DECLRADO
    		    //PARA LUEGO DECLARARLO EN INSERTARUSUARIODEMEPRESA
    		    //DE TAL MODO QUE AHORA LO QUE QUEREMOS ES SOLAMENTE SABER LA ID QUE TIENE
    		    //USUARIODEMEPRESA , POR ESO SE RESTA 1 POR QUE EL METODO INCREMENTA EL VALOR COUNT(*).
    		    insertar.setInt(7, ConsultaMAXID("IDUSUARIOEMPRESA","USUARIOEMPRESA")-1);
    		    insertar.executeUpdate();
    		    System.out.println(empresa.toString());
                System.out.println("MENSAJE : La operacion InsertarEmpresa se ha completado correctamente.");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    //SE CIERRA LA CONEXION.
    public static void CerrarConexion() throws SQLException {
    	if (rs != null) {
    	rs.close();
    	}
    	if (st != null) {
    	st.close();
    	}
    	if (insertar != null) {
    	insertar.close();
    	}
    	if (conexion != null) {
    	conexion.close();
    	}
    	System.out.println("Conexión cerrada correctamente.");
    	}
    	
	public static void main(String [] args) throws ClassNotFoundException, SQLException {

	}
}
