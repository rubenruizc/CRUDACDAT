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
		
	}
}