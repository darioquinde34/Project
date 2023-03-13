package UsuarioEmpresa;
public class UsuarioEmpresa {
	
	private int id ;
	private String Nombre ;
	private String Correo ;
	private String Contrasena ;
    private static String regex = "^[\\w._%+-]+@[\\w.-]+.[a-zA-Z]{2,}$";

	public UsuarioEmpresa(String Nombre , String correo , String contrasena ) {
			this.Nombre = Nombre ;
			this.Correo = correo ;
			this.Contrasena = contrasena ;
	}
	//GETTER Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getContrasena() {
		return Contrasena;
	}

	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}
	//METODO QUE COMPRUEBA EL CORREO 
	public static boolean compruebacorreo(String mail) {
		return mail.matches(regex);
	}
	//METODO QUE VA A COMPROBAR LA LOGNITUD DE LA CONTRASENA.
	public static boolean compruebacontrasena(String contrasena) {
		return contrasena.length() >= 9 ;
	}
	
	 // Override del método toString().
    @Override
    public String toString() {
        return "MENSAJE : Usuario registrado con | " +
                "ID=" + id +
                ", nombre='" + Nombre + '\'' +
                ", correoElectronico='" + Correo + "' + " +
                ", con contrasena='" + Contrasena + '\'' +
                '|';
    }
}
