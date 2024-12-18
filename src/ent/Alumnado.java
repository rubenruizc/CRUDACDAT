package ent;

public class Alumnado {

	private int idAlumnado;
	
	private String nombre;
	
	private String apellidos;
	
	private String fechaNac;
	
	public Alumnado() {
		
	}
	
	public Alumnado(int idAlumnado,String nombre,String apellidos,String fechaNac) {
		if(idAlumnado > 0) {
			this.idAlumnado = idAlumnado;
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
	}

	public int getIdAlumnado() {
		return idAlumnado;
	}

	public void setIdAlumnado(int idAlumnado) {
		if(idAlumnado > 0) {
			this.idAlumnado = idAlumnado;
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
	
	
	
}
