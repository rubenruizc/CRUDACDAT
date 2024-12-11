package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ent.Alumnado;
import ent.Matricula;
import ent.Profesores;


public class AccesoDatos {
	private static String useDB = "USE ad2425_rruiz;";

	private static List<Alumnado> getAlumnado() {
		List<Alumnado> alumnos = new ArrayList<>();

		alumnos.add(new Alumnado(1, "Ruben", "Ruiz", "15/04/2005"));
		alumnos.add(new Alumnado(2, "Amaro", "Suarez", "12/08/2004"));
		alumnos.add(new Alumnado(3, "Jenri", "Muñoz", "20/01/2005"));
		alumnos.add(new Alumnado(4, "Jota", "De Alba", "19/09/2003"));
		alumnos.add(new Alumnado(5, "Auri", "Miau", "15/04/2005"));

		return alumnos;
	}

	private static List<Profesores> getProfesores() {
		List<Profesores> profesores = new ArrayList<>();

		profesores.add(new Profesores(1, "Ruben", "Ruiz", "15/04/2005",5));
		profesores.add(new Profesores(2, "Amaro", "Suarez", "12/08/2004",10));
		profesores.add(new Profesores(3, "Jenri", "Muñoz", "20/01/2005",2));
		profesores.add(new Profesores(4, "Jota", "De Alba", "19/09/2003",7));
		profesores.add(new Profesores(5, "Auri", "Miau", "15/04/2005",30));

		return profesores;
	}

	private static List<Matricula> getMatricula() {
		List<Matricula> matriculas = new ArrayList<>();

		matriculas.add(new Matricula(1, 1, 1, "Matematicas",1));
		matriculas.add(new Matricula(2, 2, 2, "Matematicas",2));
		matriculas.add(new Matricula(3, 3, 3, "Matematicas",3));
		matriculas.add(new Matricula(4, 4, 4, "Matematicas",4));
		matriculas.add(new Matricula(5, 5, 5, "Matematicas",5));

		return matriculas;
	}

	public static boolean tablaExists(String nombreTabla) {
		Connection conector = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean exists = false;

		try {
			// Establecer conexión
			conector = ConexionBD.connect();

			// Crear el objeto Statement
			stmt = conector.createStatement();

			// Asegúrate de seleccionar la base de datos si es necesario
			// Este paso solo es necesario si no has establecido la base de datos
			// previamente
			stmt.executeUpdate(useDB); 

			// Consulta para verificar si la tabla existe
			String checkTableSql = "SELECT 1 FROM information_schema.tables WHERE table_name = '" + nombreTabla
					+ "' AND table_schema = 'ad2425_rruiz'";

			// Ejecutar la consulta SELECT
			rs = stmt.executeQuery(checkTableSql);

			// Si hay resultados, la tabla existe
			if (rs.next()) {
				exists = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar recursos
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
			if (conector != null) {
				try {
					conector.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
		}

		return exists;
	}

	/**
	 * Crea la tabla "Alumnado" en la base de datos si no existe.
 	*
 	* Este método se conecta a la base de datos, verifica si la tabla "Alumnado"
 	* ya existe, y si no es así, ejecuta la sentencia SQL para crearla. La tabla
 	* contiene las columnas: idAlumnado (clave primaria), nombre, apellidos y fechaNac.
 	* Gestiona y reporta cualquier error que pueda ocurrir durante la ejecución.
 	*/
	public static void CrearTablarALumnos() {
			Statement stmt = null;
			Connection conector = null;
			try {
				conector = ConexionBD.connect();
				// Paso 1. Creamos un nuevo objeto con la conexión
				stmt = conector.createStatement();

				// Paso 2. Seleccionar la base de datos
				stmt.executeUpdate(useDB);

				if (!tablaExists("Alumnado")) {
					// Paso 3. Definimos la sentencia para crear una nueva tabla
					String createTableSql = "CREATE TABLE Alumnado (" + "idAlumnado INT PRIMARY KEY," + "nombre VARCHAR(50),"
							+ "apellidos VARCHAR(50)," + "fechaNac VARCHAR(50)" + ");";

					// Paso 4. Ejecutar la sentencia de creación
					stmt.executeUpdate(createTableSql);

					System.out.println("Tabla Alumnado creada");
				} else {
					System.out.println("La tabla alumnado ya existe");
				}

			} catch (SQLException se) {
				// Gestionamos los posibles errores que puedan surgir durante la ejecución
				se.printStackTrace();
			} catch (Exception e) {
				// Gestionamos los posibles errores
				e.printStackTrace();
			} finally {
				// Paso 5. Cerrar el objeto en uso y la conexión
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						System.out.println("Ha ocurrido un error cerrando los recursos");
					}
				}
				if (conector != null) {
					try {
						conector.close();
					} catch (SQLException e) {
						System.out.println("Ha ocurrido un error cerrando los recursos");
					}
				}
			}
	}

	/**
	 * Crea la tabla "Profesores" en la base de datos si no existe.
	 * 
	 * Este método se conecta a la base de datos, verifica si la tabla "Profesores"
	 * ya existe, y si no es así, ejecuta la sentencia SQL para crearla. La tabla
	 * contiene las columnas: idProfesor (clave primaria), nombre, apellidos,
	 * fechaNac y antiguedad. Gestiona y reporta cualquier error que pueda ocurrir
	 * durante la ejecución.
	 */
	public static void CrearTablarProfesores() {
		Statement stmt = null;
		Connection conector = null;
		try {
			conector = ConexionBD.connect();
			// Paso 1. Creamos un nuevo objeto con la conexión
			stmt = conector.createStatement();

			// Paso 2. Seleccionar la base de datos
			stmt.executeUpdate(useDB);

			if (!tablaExists("Profesores")) {
				// Paso 3. Definimos la sentencia para crear una nueva tabla
				String createTableSql = "CREATE TABLE Profesores (" + "idProfesor INT PRIMARY KEY," + "nombre VARCHAR(50),"
						+ "apellidos VARCHAR(50)," + "fechaNac VARCHAR(50)," + "antiguedad INT" + ");";

				// Paso 4. Ejecutar la sentencia de creación
				stmt.executeUpdate(createTableSql);

				System.out.println("Tabla Profesores creada");
			} else {
				System.out.println("La tabla profesores ya existe");
			}

		} catch (SQLException se) {
			// Gestionamos los posibles errores que puedan surgir durante la ejecución
			se.printStackTrace();
		} catch (Exception e) {
			// Gestionamos los posibles errores
			e.printStackTrace();
		} finally {
			// Paso 5. Cerrar el objeto en uso y la conexión
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
			if (conector != null) {
				try {
					conector.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
		}
	}


	/**
 	* Crea la tabla "Matrícula" en la base de datos si no existe.
 	* 
 	* Este método se conecta a la base de datos, verifica si la tabla "Matrícula"
 	* ya existe, y si no es así, ejecuta la sentencia SQL para crearla. La tabla
 	* contiene las columnas: idMatricula (clave primaria), idProfesorado,
 	* idAlumnado, asignatura y curso. Gestiona y reporta cualquier error que
 	* pueda ocurrir durante la ejecución.
 	*/
	public static void CrearTablarMatricula() {
		Statement stmt = null;
		Connection conector = null;
		try {
			conector = ConexionBD.connect();
			// Paso 1. Creamos un nuevo objeto con la conexión
			stmt = conector.createStatement();

			// Paso 2. Seleccionar la base de datos
			stmt.executeUpdate(useDB);

			if (!tablaExists("Matricula")) {
				// Paso 3. Definimos la sentencia para crear una nueva tabla
				String createTableSql = "CREATE TABLE Matricula (" + "idMatricula INT PRIMARY KEY," + "idProfesorado INT,"
						+ "idAlumnado INT," + "asignatura VARCHAR(50)," + "curso INT" + ");";

				// Paso 4. Ejecutar la sentencia de creación
				stmt.executeUpdate(createTableSql);

				System.out.println("Tabla Matrícula creada");
			} else {
				System.out.println("La tabla matrícula ya existe");
			}

		} catch (SQLException se) {
			// Gestionamos los posibles errores que puedan surgir durante la ejecución
			se.printStackTrace();
		} catch (Exception e) {
			// Gestionamos los posibles errores
			e.printStackTrace();
		} finally {
			// Paso 5. Cerrar el objeto en uso y la conexión
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
			if (conector != null) {
				try {
					conector.close();
				} catch (SQLException e) {
					System.out.println("Ha ocurrido un error cerrando los recursos");
				}
			}
		}
	}
	
	
	
	/**
 	* Inserta los registros en la tabla "Alumnado" de la base de datos.
 	* 
 	* Este método se conecta a la base de datos, prepara una sentencia SQL para
 	* insertar registros en la tabla "Alumnado", y ejecuta la inserción de manera
 	* dinámica para cada objeto Alumnado en la lista obtenida de getAlumnado().
 	* Gestiona y reporta cualquier error que pueda ocurrir durante la ejecución.
 	* Finalmente, cierra los recursos utilizados.
 	*/
	public static void insertarAlumnado() {
		PreparedStatement stmt = null;
		Statement statement = null;
		Connection conector = null;

		try {
			conector = ConexionBD.connect();
			// Seleccionar la base de datos
			statement = conector.createStatement();
			statement.executeUpdate(useDB);

			String sql = "INSERT INTO Alumnado (idAlumnado, nombre, apellidos, fechaNac) VALUES (?, ?, ?, ?)";

			// Obtenemos las personas
			List<Alumnado> alumnos = getAlumnado();

			// Prepararemos la query para que coja los datos de manera dinamica.
			stmt = conector.prepareStatement(sql);
			for (Alumnado alumno : alumnos) {
				stmt.setInt(1, alumno.getIdAlumnado());
				stmt.setString(2, alumno.getNombre());
				stmt.setString(3, alumno.getApellidos());
				stmt.setString(4, alumno.getFechaNac());
				stmt.executeUpdate();

				System.out.println("Alumno/a " + alumno.getNombre() + " " + alumno.getApellidos() + " añadid@");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	/**
	 * Inserta los profesores en la tabla Profesores
	 * 
	 * Este método se conecta a la base de datos, selecciona la base de datos
	 * especificada y ejecuta una sentencia SQL para insertar los profesores en la
	 * tabla Profesores. La tabla contiene las columnas: idProfesor, nombre,
	 * apellidos, fechaNac y antiguedad. Gestiona y reporta cualquier error que
	 * pueda ocurrir durante la ejecución.
	 */
	public static void insertarProfesores() {
		PreparedStatement stmt = null;
		Statement statement = null;
		Connection conector = null;

		try {
			conector = ConexionBD.connect();
			// Seleccionar la base de datos
			statement = conector.createStatement();
			statement.executeUpdate(useDB);

			String sql = "INSERT INTO Profesores (idProfesor, nombre, apellidos, fechaNac, antiguedad) VALUES (?, ?, ?, ?, ?)";

			// Obtenemos las personas
			List<Profesores> profesores = getProfesores();

			// Prepararemos la query para que coja los datos de manera dinamica.
			stmt = conector.prepareStatement(sql);
			for (Profesores profesor : profesores) {
				stmt.setInt(1, profesor.getIdProfesor());
				stmt.setString(2, profesor.getNombre());
				stmt.setString(3, profesor.getApellidos());
				stmt.setString(4, profesor.getFechaNac());
				stmt.setInt(1, profesor.getAntiguedad());
				stmt.executeUpdate();

				System.out.println("Profesor/a " + profesor.getNombre() + " " + profesor.getApellidos() + " añadid@");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	/**
 	* Inserta registros en la tabla "Matricula" de la base de datos.
 	* 
 	* Este método se conecta a la base de datos, prepara una sentencia SQL para
 	* insertar registros en la tabla "Matricula", y ejecuta la inserción de manera
 	* dinámica para cada objeto Matricula en la lista obtenida de getMatricula().
 	* Gestiona y reporta cualquier error que pueda ocurrir durante la ejecución.
 	* Finalmente, cierra los recursos utilizados.
 	*/
	public static void insertarMatricula() {
		PreparedStatement stmt = null;
		Statement statement = null;
		Connection conector = null;

		try {
			conector = ConexionBD.connect();
			// Seleccionar la base de datos
			statement = conector.createStatement();
			statement.executeUpdate(useDB);

			String sql = "INSERT INTO Matricula (idMatricula, idProfesorado, idAlumnado, asignatura, curso) VALUES (?, ?, ?, ?, ?)";

			// Obtenemos las personas
			List<Matricula> matriculas = getMatricula();

			// Prepararemos la query para que coja los datos de manera dinamica.
			stmt = conector.prepareStatement(sql);
			for (Matricula matricula : matriculas) {
				stmt.setInt(1, matricula.getIdMatricula());
				stmt.setInt(2, matricula.getIdProfesorado());
				stmt.setInt(3, matricula.getIdAlumnado());
				stmt.setString(4, matricula.getAsignatura());
				stmt.setInt(1, matricula.getCurso());
				stmt.executeUpdate();

				System.out.println("Matricula " + matricula.getIdMatricula() + " añadida");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	public static void Listar() {
	
	}

	public static void Modificar() {
	
	}
	
	public static void Borrar() {
		
	}
	
	public static void EliminarTabla() {
		
	}
}
