package principal;

import java.util.Scanner;
import dal.AccesoDatos;

public class Principal {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean salir = false;
        String campo;
        String operador;
        String valor;

        while (!salir) {
            menu();
            
            int opcion = obtenerOpcion(7);  // Validación de opción

            switch (opcion) {
                case 1:
                    System.out.println("¿QUÉ TABLA LE GUSTARÍA CREAR?");
                    System.out.println("1.ALUMNADO");
                    System.out.println("2.PROFESORES");
                    System.out.println("3.MATRICULA");
                    System.out.println("4.MENU PRINCIPAL");

                    int opcionCrear = obtenerOpcion(4);  // Validación de opción

                    switch (opcionCrear) {
                        case 1:
                            AccesoDatos.crearTablarALumnos();
                            break;
                        case 2:
                            AccesoDatos.crearTablarProfesores();
                            break;
                        case 3:
                            AccesoDatos.crearTablarMatricula();
                            break;
                        case 4:
                            break;
                    }
                    break;

                case 2:
                    System.out.println("¿EN QUE TABLA QUIERE INSERTAR?");
                    System.out.println("1.ALUMNADO");
                    System.out.println("2.PROFESORES");
                    System.out.println("3.MATRICULA");
                    System.out.println("4.MENU PRINCIPAL");

                    int opcionInsertar = obtenerOpcion(4);  // Validación de opción

                    switch (opcionInsertar) {
                        case 1:
                            AccesoDatos.insertarAlumnado();
                            break;
                        case 2:
                            AccesoDatos.insertarProfesores();
                            break;
                        case 3:
                            AccesoDatos.insertarMatricula();
                            break;
                        case 4:
                            break;
                    }
                    break;

                case 3:
                    System.out.println("¿QUÉ TABLA LE GUSTARÍA LISTAR?");
                    System.out.println("1.ALUMNO");
                    System.out.println("2.TODOS LOS ALUMNOS");
                    System.out.println("3.PROFESOR");
                    System.out.println("4.TODOS LOS PROFESORES");
                    System.out.println("5.MATRICULA");
                    System.out.println("6.TODAS LAS MATRICULAS");
                    System.out.println("7.MENU PRINCIPAL");

                    int opcionListar = obtenerOpcion(7);  // Validación de opción

                    switch (opcionListar) {
                        case 1:
                            System.out.println("Introduzca el campo, el operador y el valor");
                            campo = scanner.nextLine();
                            System.out.println("Introduzca el operador");
                            operador = scanner.nextLine();
                            System.out.println("Introduzca el valor");
                            valor = scanner.nextLine();
                            AccesoDatos.listarAlumnos(campo, operador, valor);
                            break;
                        case 2:
                            AccesoDatos.listarTodosAlumnos();
                            break;
                        case 3:
                            System.out.println("Introduzca el campo, el operador y el valor");
                            campo = scanner.nextLine();
                            System.out.println("Introduzca el operador");
                            operador = scanner.nextLine();
                            System.out.println("Introduzca el valor");
                            valor = scanner.nextLine();
                            AccesoDatos.listarProfesores(campo, operador, valor);
                            break;
                        case 4:
                            AccesoDatos.listarTodosProfesores();
                            break;
                        case 5:
                            System.out.println("Introduzca el campo, el operador y el valor");
                            campo = scanner.nextLine();
                            System.out.println("Introduzca el operador");
                            operador = scanner.nextLine();
                            System.out.println("Introduzca el valor");
                            valor = scanner.nextLine();
                            AccesoDatos.listarMatricula(campo, operador, valor);
                            break;
                        case 6:
                            AccesoDatos.listarTodasMatriculas();
                            break;
                        case 7:
                            break;
                    }
                    break;

                case 4:
                    System.out.println("¿QUÉ TABLA LE GUSTARÍA MODIFICAR?");
                    System.out.println("1.ALUMNADO");
                    System.out.println("2.PROFESORES");
                    System.out.println("3.MATRICULA");
                    System.out.println("4.MENU PRINCIPAL");

                    int opcionModificar = obtenerOpcion(4);  // Validación de opción

                    switch (opcionModificar) {
                        case 1:
                            AccesoDatos.modificarDatosAlumnado();
                            break;
                        case 2:
                            AccesoDatos.modificarDatosProfesor();
                            break;
                        case 3:
                            AccesoDatos.modificarDatosMatricula();
                            break;
                        case 4:
                            break;
                    }
                    break;

                case 5:
                    System.out.println("¿DE QUE TABLA LE GUSTARÍA BORRAR DATOS?");
                    System.out.println("1.ALUMNADO");
                    System.out.println("2.PROFESORES");
                    System.out.println("3.MATRICULA");
                    System.out.println("4.MENU PRINCIPAL");

                    int opcionBorrar = obtenerOpcion(4);  // Validación de opción

                    switch (opcionBorrar) {
                        case 1:
                            AccesoDatos.borrarDatosAlumnado();
                            break;
                        case 2:
                            AccesoDatos.borrarDatosProfesor();
                            break;
                        case 3:
                            AccesoDatos.borrarDatosMatricula();
                            break;
                        case 4:
                            break;
                    }
                    break;

                case 6:
                    System.out.println("¿DE QUE TABLA LE GUSTARÍA BORRAR DATOS?");
                    System.out.println("1.ALUMNADO");
                    System.out.println("2.PROFESORES");
                    System.out.println("3.MATRICULA");
                    System.out.println("4.MENU PRINCIPAL");

                    int opcionBorrarTabla = obtenerOpcion(4);  // Validación de opción

                    switch (opcionBorrarTabla) {
                        case 1:
                            AccesoDatos.borrarTablaAlumnado();
                            break;
                        case 2:
                            AccesoDatos.borrarTablaProfesores();
                            break;
                        case 3:
                            AccesoDatos.borrarTablaMatricula();
                            break;
                        case 4:
                            break;
                    }
                    break;

                case 7:
                    System.out.println("SALIENDO...");
                    salir = true;
                    break;
            }
        }

        scanner.close();
    }

    public static void menu() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("1.CREAR TABLA");
        System.out.println("2.INSERTAR DATOS");
        System.out.println("3.LISTAR DATOS");
        System.out.println("4.MODIFICAR DATOS");
        System.out.println("5.BORRAR DATOS");
        System.out.println("6.BORRAR TABLA");
        System.out.println("7.SALIR");
    }

    // Función para manejar las opciones numéricas de manera segura
    public static int obtenerOpcion(int limite) {
        int opcion = -1;
        while (opcion < 1 || opcion > limite) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el salto de línea restante
                if (opcion < 1 || opcion > limite) {
                    System.out.println("Opción no válida. Por favor, elija una opción entre 1 y " + limite);
                }
            } else {
                scanner.nextLine();  
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        return opcion;
    }
}
