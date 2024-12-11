package principal;

import java.util.Scanner;

import dal.AccesoDatos;

public class Principal {

	public static void main(String[] args) {
		int opcion  = 0;
		Scanner sc = new Scanner (System.in);
		
		System.out.println("¿Que desea hacer?"+ "\n1.Crear Tablas\r\n"
				+ "2.Insertar\r\n"
				+ "3.Listar\r\n"
				+ "4.Modificar\r\n"
				+ "5.Borrar\r\n"
				+ "6.Eliminar Tablas");
		opcion = sc.nextInt();
		
		switch (opcion) {
		case 1:
			AccesoDatos.CrearTabla();
			break;
		case 2:
			int opcionInsertar = 0;
			System.out.println("¿Sobre que tabla le gustaría insertar?" +"\n1.Profesores" + "\n2.Matrícula" + "\n3.Alumnado");
			opcionInsertar = sc.nextInt();
			AccesoDatos.Insertar();
			break;
		case 3:
			int opcionListar = 0;
			System.out.println("¿Qué tabla le gustaría listar?" +"\n1.Profesores" + "\n2.Matrícula" + "\n3.Alumnado");
			opcionListar = sc.nextInt();
			AccesoDatos.Listar();
			break;
		case 4:
			int opcionModificar = 0;
			System.out.println("¿Qué tabla le gustaría modificar?" +"\n1.Profesores" + "\n2.Matrícula" + "\n3.Alumnado");
			opcionModificar = sc.nextInt();
			AccesoDatos.Modificar();
			break;
		case 5:
			int opcionBorrar = 0;
			System.out.println("¿Qué tabla le gustaría borrar algún dato?" +"\n1.Profesores" + "\n2.Matrícula" + "\n3.Alumnado");
			opcionBorrar = sc.nextInt();
			AccesoDatos.Borrar();
			break;
		case 6:
			int opcionDropear = 0;
			System.out.println("¿Qué tabla le gustaría eliminar entera?" +"\n1.Profesores" + "\n2.Matrícula" + "\n3.Alumnado");
			opcionDropear = sc.nextInt();
			AccesoDatos.EliminarTabla();
			break;
		case 0:
			System.out.println("Un saludo, descanse.");
			break;
		}

	}

}
