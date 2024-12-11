package ent;

public class Profesores {

	private int idProfesor;
	
	private String nombre;
	
	private String apellidos;
	
	private String fechaNac;
	
	private int antiguedad;
	
	public Profesores() {
		
	}
	
	public Profesores (int idProfesor, String nombre, String apellidos, String fechaNac, int antiguedad) {
		if (idProfesor > 0) {
			this.idProfesor = idProfesor;
		}
		
		if(nombre != null && !nombre.isEmpty()) {
			this.nombre = nombre;
		}
		
		if(apellidos != null && !apellidos.isEmpty()) {
			this.apellidos = apellidos;
		}
		
		if(fechaNac != null && !fechaNac.isEmpty()) {
			this.fechaNac = fechaNac;
		}
		
		if(antiguedad > 0) {
			this.antiguedad = antiguedad;
		}
	}

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		if (idProfesor > 0) {
			this.idProfesor = idProfesor;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre != null && !nombre.isEmpty()) {
			this.nombre = nombre;
		}
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		if(apellidos != null && !apellidos.isEmpty()) {
			this.apellidos = apellidos;
		}
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		if(fechaNac != null && !fechaNac.isEmpty()) {
			this.fechaNac = fechaNac;
		}
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		if(antiguedad > 0) {
			this.antiguedad = antiguedad;
		}
	}
	
}
