package Empresa;

public class Empresa {
    private int ID;
    private String Nombre;
    private String DireccionFiscal;
    private String Telefono;
    private String NombreUsuarioRegistro;
    private String PuestoUsuarioRegistro;

    public Empresa(String nombre, String direccionFiscal, String numero, String nombreUsuarioRegistro, String puestoUsuarioRegistro) {
        this.Nombre = nombre;
        this.DireccionFiscal = direccionFiscal;
        this.Telefono = numero;
        this.NombreUsuarioRegistro = nombreUsuarioRegistro;
        this.PuestoUsuarioRegistro = puestoUsuarioRegistro;
    }
    //METODO QUE COMPBRARA SI EL VALOR PASADO ES NUMERICO.
    public static boolean Telefono(String numero) {
    	try {
    		Integer.parseInt(numero);
    		return true ;
    	}catch(Exception e) {
    		System.out.println("MENSAJE: EL TELEFONO INTRODUCIDO NO ES VALIDO");
    		return false ;
    	}
    }
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDireccionFiscal() {
		return DireccionFiscal;
	}

	public void setDireccionFiscal(String direccionFiscal) {
		DireccionFiscal = direccionFiscal;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getNombreUsuarioRegistro() {
		return NombreUsuarioRegistro;
	}

	public void setNombreUsuarioRegistro(String nombreUsuarioRegistro) {
		NombreUsuarioRegistro = nombreUsuarioRegistro;
	}

	public String getPuestoUsuarioRegistro() {
		return PuestoUsuarioRegistro;
	}

	public void setPuestoUsuarioRegistro(String puestoUsuarioRegistro) {
		PuestoUsuarioRegistro = puestoUsuarioRegistro;
	}
	  @Override
	    public String toString() {
	        return "MENSAJE : Empresa | con " +
	                ", nombre= " + Nombre + ' ' +
	                ", direccionFiscal= " + DireccionFiscal + ' ' +
	                ", numero= " + Telefono + ' ' +
	                ", nombreUsuarioRegistro= " + NombreUsuarioRegistro + ' ' +
	                ", puestoUsuarioRegistro= " + PuestoUsuarioRegistro + ' ' +
	                '|';
	    }
}