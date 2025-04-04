package ejemploBD.dao.dao;

import java.util.List;

import ejemploBD.config.config.BDException;
import ejemploBD.modelo.modelo.Empleado;
import java.util.Scanner;

public class Principal {

	// Consulta los departamentos de la base de datos con la misma ubicaci�n y
	// ordenados por nombre de forma ascendente.
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		// Realiza la consulta
		System.out.println("Salario?");
		float salario = teclado.nextFloat();
		List<Empleado> empleados;
		try {
			empleados = AccesoEmpleado.consultarEmpleados(salario);
			
			empleados = AccesoEmpleado.consultarEmpleadosDepartamento(salario);
			if (empleados.size() == 0) {
				System.out.println("No se ha encontrado ningun empleado con un salario mayor al indicado.");
			} else {
				for (Empleado empleados2 : empleados) {
					System.out.println(empleados.toString());
				}
				System.out.println("Se han consultado " + empleados.size() + " departamentos de la base de datos.");
			}
		} catch (BDException e) {
			System.out.println(e.getMessage());
		}
	}
}