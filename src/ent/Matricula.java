package ent;

public class Matricula {

	private int idMatricula;
	
	private int idProfesorado;
	
	private int idAlumnado;
	
	private String asignatura;
	
	private int curso;
	
	public Matricula() {
		
	}
	
	public Matricula(int idMatricula,int idProfesorado,int idAlumnado,String asignatura,int curso) {
		if(idMatricula > 0) {
			this.idMatricula = idMatricula;
		}
		
		if(idProfesorado > 0) {
			this.idProfesorado = idProfesorado;
		}
		
		if(idAlumnado > 0) {
			this.idAlumnado = idAlumnado;
		}
		
		if(asignatura != null && !asignatura.isEmpty()) {
			this.asignatura = asignatura;
		}
		
		if(curso > 0) {
			this.curso = curso;
		}
		
		
		
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		if(idMatricula > 0) {
			this.idMatricula = idMatricula;
		}
	}

	public int getIdProfesorado() {
		return idProfesorado;
	}

	public void setIdProfesorado(int idProfesorado) {
		if(idProfesorado > 0) {
			this.idProfesorado = idProfesorado;
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

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		if(asignatura != null && !asignatura.isEmpty()) {
			this.asignatura = asignatura;
		}
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		if(curso > 0) {
			this.curso = curso;
		}
	}
}
