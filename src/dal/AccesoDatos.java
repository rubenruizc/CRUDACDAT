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

	/**
	 * Verifica si la tabla especificada existe en la base de datos.
	 * 
	 * @param nombreTabla El nombre de la tabla a verificar.
	 * @return <code>true</code> si la tabla existe, <code>false</code> en caso
	 *         contrario.
	 */
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

			// Obtenemos los alumnos
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

			// Obtenemos los profesores
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

			// Obtenemos las matriculas
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

	/**
	 * Muestra los alumnos que cumplen los criterios de filtrado.
	 * 
	 * @param campo     Campo sobre el que se va a filtrar. Puede ser "nombre", "apellidos", "fechaNac".
	 * @param operador  Operador de comparación. Puede ser "=", "<", ">".
	 * @param valor     Valor sobre el que se va a filtrar.
	 * 
	 * @throws IllegalArgumentException si el campo no es reconocido o el operador no es válido.
	 * @throws SQLException             si se produce un error al ejecutar la consulta.
	 */
	public static void listarAlumnos(String campo, String operador, String valor) {
		PreparedStatement stmt = null;
		Connection conector = null;
		ResultSet rs = null;
	
		try {
			conector = ConexionBD.connect();
			String sqlBase = "SELECT * FROM Alumno";
			String sql = sqlBase;
	
			// Construcción dinámica del SQL según el filtro proporcionado
			if (campo != null && operador != null && valor != null) {
				switch (campo.toLowerCase()) {
					case "idAlumnado":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE idAlumnado " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para idAlumnado: " + operador);
						}
					case "nombre":
						sql += " WHERE nombre = ?";
						break;
					case "apellidos":
						sql += " WHERE apellido LIKE ?";
						valor = "%" + valor + "%"; // Para buscar con LIKE
						break;
					case "fechaNac":
						if (operador.equals("<") || operador.equals(">")) {
							sql += " WHERE fechaNacimiento " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para fechaNacimiento: " + operador);
						}
						break;
					default:
						throw new IllegalArgumentException("Campo no reconocido: " + campo);
				}
			}
	
			stmt = conector.prepareStatement(sql);
	
			if (campo != null && operador != null && valor != null) {
				stmt.setString(1, valor);
			}
	
			rs = stmt.executeQuery();
	
			// Imprimir los resultados
			while (rs.next()) {
				int idAlumnado = rs.getInt("idAlumnado");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellido");
				String fechaNac = rs.getString("fechaNac");
	
				System.out.println("ID: " + idAlumnado + ", Nombre: " + nombre + ", Apellido: " + apellidos + ", Fecha de Nacimiento: " + fechaNac);
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conector != null)
					conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	/**
 	* Muestra todos los registros de la tabla "Alumno" de la base de datos.
 	*
 	* Utiliza una sentencia SQL para seleccionar todos los registros de la tabla
 	* "Alumno" y los imprime en la consola. Gestiona y reporta cualquier error que
 	* pueda ocurrir durante la ejecución.
 	*/

	public static void listarTodosAlumnos() {
		Statement stmt = null;
		Connection conector = null;
		ResultSet rs = null;
	
		try {
			conector = ConexionBD.connect();
			String sql = "SELECT * FROM Alumno";
	
			stmt = conector.createStatement();
			rs = stmt.executeQuery(sql);
	
			// Imprimir los resultados
			while (rs.next()) {
				int idAlumnado = rs.getInt("idAlumnado");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellido");
				String fechaNac = rs.getString("fechaNac");
	
				System.out.println("ID: " + idAlumnado + ", Nombre: " + nombre + ", Apellido: " + apellidos + ", Fecha de Nacimiento: " + fechaNac);
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conector != null)
					conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}
	
	
	/**
	 * Muestra los profesores que cumplen los criterios de filtrado.
	 * 
	 * @param campo     Campo sobre el que se va a filtrar. Puede ser "nombre", "apellidos", "fechanac" o "antiguedad".
	 * @param operador  Operador de comparación. Puede ser "=", "<", ">".
	 * @param valor     Valor sobre el que se va a filtrar.
	 * 
	 * @throws IllegalArgumentException si el campo no es reconocido o el operador no es válido.
	 * @throws SQLException             si se produce un error al ejecutar la consulta.
	 */
	public static void listarProfesores(String campo, String operador, String valor) {
		PreparedStatement stmt = null;
		Connection conector = null;
		ResultSet rs = null;
	
		try {
			conector = ConexionBD.connect();
			String sqlBase = "SELECT * FROM Profesor";
			String sql = sqlBase;
	
			// Construcción dinámica del SQL según el filtro proporcionado
			if (campo != null && operador != null && valor != null) {
				switch (campo.toLowerCase()) {
					case "idProfesor":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE idProfesor " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para idProfesor: " + operador);
						}
						break;
					case "nombre":
						sql += " WHERE nombre = ?";
						break;
					case "apellidos":
						sql += " WHERE apellidos LIKE ?";
						valor = "%" + valor + "%"; // Para buscar con LIKE
						break;
					case "fechanac":
						if (operador.equals("<") || operador.equals(">")) {
							sql += " WHERE fechaNac " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para fechaNac: " + operador);
						}
						break;
					case "antiguedad":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE antiguedad " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para antiguedad: " + operador);
						}
						break;
					default:
						throw new IllegalArgumentException("Campo no reconocido: " + campo);
				}
			}
	
			stmt = conector.prepareStatement(sql);
	
			if (campo != null && operador != null && valor != null) {
				if (campo.equalsIgnoreCase("antiguedad")) {
					stmt.setInt(1, Integer.parseInt(valor));
				} else {
					stmt.setString(1, valor);
				}
			}
	
			rs = stmt.executeQuery();
	
			// Imprimir los resultados
			while (rs.next()) {
				int idProfesor = rs.getInt("idProfesor");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String fechaNac = rs.getString("fechaNac");
				int antiguedad = rs.getInt("antiguedad");
	
				System.out.println("ID: " + idProfesor + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Fecha de Nacimiento: " + fechaNac + ", Antigüedad: " + antiguedad);
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conector != null)
					conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	/**
	 * Muestra todos los profesores de la base de datos.
	 * 
	 * Este método se conecta a la base de datos, selecciona la base de datos
	 * especificada y ejecuta una sentencia SQL para obtener todos los profesores
	 * de la tabla "Profesores". La tabla contiene las columnas: idProfesor, nombre,
	 * apellidos, fechaNac y antiguedad. Imprime los resultados en la consola.
	 * 
	 */
	public static void listarTodosProfesores() {
		Statement stmt = null;
		Connection conector = null;
		ResultSet rs = null;
	
		try {
			conector = ConexionBD.connect();
			String sql = "SELECT * FROM Profesores";
	
			stmt = conector.createStatement();
			rs = stmt.executeQuery(sql);
	
			// Imprimir los resultados
			while (rs.next()) {
				int idProfesor = rs.getInt("idProfesor");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellido");
				String fechaNac = rs.getString("fechaNac");
				String antiguedad = rs.getString("antiguedad");
	
				System.out.println("ID: " + idProfesor + ", Nombre: " + nombre + ", Apellido: " + apellidos + ", Fecha de Nacimiento: " + fechaNac + ", Antiguedad: " +antiguedad);
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conector != null)
					conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	
	/**
	 * Muestra las matriculas que cumplen los criterios de filtrado.
	 * 
	 * @param campo     Campo sobre el que se va a filtrar. Puede ser "idmatricula", "idprofesorado", "idalumnado", "asignatura" o "curso".
	 * @param operador  Operador de comparación. Puede ser "=", "<", ">".
	 * @param valor     Valor sobre el que se va a filtrar.
	 * 
	 * @throws IllegalArgumentException si el campo no es reconocido o el operador no es válido.
	 * @throws SQLException             si se produce un error al ejecutar la consulta.
	 */
	public static void listarMatricula(String campo, String operador, String valor) {
		PreparedStatement stmt = null;
		Connection conector = null;
		ResultSet rs = null;
	
		try {
			conector = ConexionBD.connect();
			String sqlBase = "SELECT * FROM Matricula";
			String sql = sqlBase;
	
			// Construcción dinámica del SQL según el filtro proporcionado
			if (campo != null && operador != null && valor != null) {
				switch (campo.toLowerCase()) {
					case "idmatricula":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE idMatricula " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para idMatricula: " + operador);
						}
						break;
					case "idprofesorado":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE idProfesorado " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para idProfesorado: " + operador);
						}
						break;
					case "idalumnado":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE idAlumnado " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para idAlumnado: " + operador);
						}
						break;
					case "asignatura":
						sql += " WHERE asignatura LIKE ?";
						valor = "%" + valor + "%"; // Para buscar con LIKE
						break;
					case "curso":
						if (operador.equals("<") || operador.equals(">") || operador.equals("=")) {
							sql += " WHERE curso " + operador + " ?";
						} else {
							throw new IllegalArgumentException("Operador no válido para curso: " + operador);
						}
						break;
					default:
						throw new IllegalArgumentException("Campo no reconocido: " + campo);
				}
			}
	
			stmt = conector.prepareStatement(sql);
	
			if (campo != null && operador != null && valor != null) {
				if (campo.equalsIgnoreCase("idmatricula") || campo.equalsIgnoreCase("idprofesorado") || campo.equalsIgnoreCase("idalumnado") || campo.equalsIgnoreCase("curso")) {
					stmt.setInt(1, Integer.parseInt(valor));
				} else {
					stmt.setString(1, valor);
				}
			}
	
			rs = stmt.executeQuery();
	
			// Imprimir los resultados
			while (rs.next()) {
				int idMatricula = rs.getInt("idMatricula");
				int idProfesorado = rs.getInt("idProfesorado");
				int idAlumnado = rs.getInt("idAlumnado");
				String asignatura = rs.getString("asignatura");
				int curso = rs.getInt("curso");
	
				System.out.println("ID Matricula: " + idMatricula + ", ID Profesorado: " + idProfesorado + ", ID Alumnado: " + idAlumnado + ", Asignatura: " + asignatura + ", Curso: " + curso);
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conector != null)
					conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}

	/**
 	* Muestra todas las matriculas que se encuentran en la base de datos.
 	*
 	* Muestra todas las matriculas que se encuentran en la base de datos, con sus
 	* respectivos campos: idMatricula, idProfesorado, idAlumnado, asignatura y
 	* curso.
 	*
 	*/
	public static void listarTodasMatriculas() {
		Statement stmt = null;
		Connection conector = null;
		ResultSet rs = null;
	
		try {
			conector = ConexionBD.connect();
			String sql = "SELECT * FROM Matricula";
	
			stmt = conector.createStatement();
			rs = stmt.executeQuery(sql);
	
			// Imprimir los resultados
			while (rs.next()) {
				int idMatricula = rs.getInt("idMatricula");
				int idProfesor = rs.getInt("idProfesor");
				int idAlumnado = rs.getInt("idAlumnado");
				String asignatura = rs.getString("asignatura");
				int curso = rs.getInt("curso");
				
	
				System.out.println("ID Matricula: "+idMatricula+ ", IDProfesor: " + idProfesor + ", IDAlumnado: " + idAlumnado + ", Asignatura: " + asignatura + ", Curso: " + curso);
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conector != null)
					conector.close();
			} catch (SQLException se) {
				System.out.println("No se ha podido cerrar la conexión.");
			}
		}
	}
	
	

	public static void Modificar() {
	
	}
	
	public static void Borrar() {
		
	}
	
	public static void EliminarTabla() {
		
	}
}
