package principal;

import java.util.Scanner;

import dal.AccesoDatos;

public class Principal {

	public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
			System.out.println("1. Modificar datos de un profesor");
			
            System.out.println("1. Modificar datos de un profesor");
            System.out.println("2. Modificar datos de un alumno");
            System.out.println("3. Borrar datos de matrícula");
            System.out.println("4. Borrar datos de un profesor");
            System.out.println("5. Borrar datos de un alumno");
            System.out.println("6. Eliminar tabla 'Matrícula'");
            System.out.println("7. Eliminar tabla 'Profesores'");
            System.out.println("8. Eliminar tabla 'Alumnado'");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    AccesoDatos.modificarDatosProfesor();
                    break;
                case 2:
                    modificarDatosAlumnado();
                    break;
                case 3:
                    borrarDatosMatricula();
                    break;
                case 4:
                    borrarDatosProfesor();
                    break;
                case 5:
                    borrarDatosAlumnado();
                    break;
                case 6:
                    borrarTablaMatricula();
                    break;
                case 7:
                    borrarTablaProfesores();
                    break;
                case 8:
                    borrarTablaAlumnado();
                    break;
                case 9:
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }

        scanner.close();
    }
}