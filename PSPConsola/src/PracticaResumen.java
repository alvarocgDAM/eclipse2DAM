
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class PracticaResumen {

	public static void main(String args[]) {
		ProcessBuilder proceso = new ProcessBuilder();
		//String IP = leerIP();

		System.out.println("Elige una opción: ");
		System.out.println("1. Crear una carpeta dada una ruta y el nombre");
		System.out.println("2. Crear un fichero dada la ruta y el nombre");
		System.out.println("3. Listar todas las interfaces de red de nuestro ordenador");
		System.out.println("4. Mostrar la IP del ordenador dado el nombre de la interfaz de red");
		System.out.println("5. Mostrar la dirección MAC dado el nombre de la interfaz de red");
		System.out.println("6. Comprobar conectividad con internet");
		System.out.println("7. Salir");
		System.out.println();

		Scanner in = new Scanner(System.in);
		String opcion = in.nextLine();

		switch (opcion) {

		case "1":

			System.out.println("Cómo quieres que se llame la carpeta?");
			String nombreCarpeta = in.nextLine();

			System.out.println("En qué ruta quieres crear la carpeta?");
			String rutaCarpeta = in.nextLine();

			crearCarpeta(nombreCarpeta,rutaCarpeta,proceso);

			break;

		case "2":

			System.out.println("Cómo quieres que se llame el fichero?");
			String nombreFichero = in.nextLine();

			System.out.println("En qué ruta quieres crear el fichero?");
			String rutaFichero = in.nextLine();

			crearFichero(nombreFichero,rutaFichero,proceso);

			break; 
			
		case "3":

			listarInterfacesRed(proceso);

			break;
			
		case "4":

			System.out.println("Cómo se llama la interfaz cuya IP quieres comprobar?");
			String interfaz1 = in.nextLine();

			mostrarIPSegunInterfaz(interfaz1, proceso);

			break;
		case "5":

			System.out.println("Cómo se llama la interfaz cuyo MAC quieres comprobar?");
			String interfaz2 = in.nextLine();

			mostrarMACSegunInterfaz(interfaz2, proceso);

			break;
			
		case "6":

			comprobarConectividad(proceso);


			break;
			
		case "7":

			System.out.println("PO ADIO");
			System.exit(0);

			break;
		}
	}


	/**
	 * Crea una carpeta en la ruta que le indique el usuario, ejecutando el comando de cmd mkdir (OPCION 1)
	 * 
	 * @param nombreCarpeta
	 * @param rutaCarpeta
	 * @param proceso
	 */
	private static void crearCarpeta(String nombreCarpeta, String rutaCarpeta, ProcessBuilder proceso) {

		String param = rutaCarpeta + "\\" + nombreCarpeta;
		proceso.command("cmd.exe", "/c", "mkdir " + param );

		Process process;
		try {
			process = proceso.start();
			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			//Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			System.out.println(buffer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea un fichero en la ruta que le indique el usuario, ejecutando el comando de cmd goto >> 'ruta' (OPCION 2)
	 * 
	 * @param nombrefichero
	 * @param rutaFichero
	 * @param proceso
	 */
	private static void crearFichero(String nombrefichero, String rutaFichero, ProcessBuilder proceso) {

		String param = rutaFichero + "\\" + nombrefichero;
		proceso.command("cmd.exe", "/c", "goto >> " + param );

		Process process;
		try {
			process = proceso.start();
			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			//Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			System.out.println(buffer);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Lista todas las interfaces de red usando el comando de cmd ipconfig (OPCION 3)
	 * 
	 * @param proceso
	 */
	private static void listarInterfacesRed(ProcessBuilder proceso) {

		proceso.command("cmd.exe", "/c", "ipconfig" );

		Process process;
		try {
			process = proceso.start();
			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			//Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			System.out.println(buffer);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Muestra la IP del ordenador segun el nombre de la interfaz de red que indique el usuario (OPCION 4)
	 * 
	 * @param interfaz
	 * @param proceso
	 */
	private static void mostrarIPSegunInterfaz(String interfaz,ProcessBuilder proceso) {

		proceso.command("powershell", "/c", "Get-NetIPAddress -InterfaceAlias " + interfaz + "| select-object -property IPAddress" );

		Process process;
		try {
			process = proceso.start();
			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			//Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			System.out.println(buffer);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Muestra la MAC del ordenador segun el nombre de la interfaz de red que indique el usuario (OPCION 5)
	 * 
	 * @param interfaz
	 * @param proceso
	 */
	private static void mostrarMACSegunInterfaz(String interfaz,ProcessBuilder proceso) {

		proceso.command("cmd.exe", "/c", "getmac /V /FO CSV" ); //con este comando, obtenemos un csv de todas las macs de todas las interfaces

		Process process;

		try {
			process = proceso.start();
			HashMap<String,String> hashBuffer = new HashMap<String, String>(); //creamos un hashmap para luego poder localizar la mac que nos interesa

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));


			String line;
			while ((line = reader.readLine()) != null) {

				line = line.replace("\"", ""); //retiramos las comillas de la string CSV
				String[] columnas = line.split(",");

				String nombre = columnas[0];
				String mac = columnas[2];


				hashBuffer.put(nombre,mac);
			}
			System.out.println(hashBuffer.get(interfaz)); 

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Comprueba que no se pierda ningun paquete haciendo un ping a google.com, para comprobar la conectividad a internet 
	 * 
	 * @param proceso
	 */
	private static void comprobarConectividad(ProcessBuilder proceso) {

		proceso.command("cmd.exe", "/c", "ping www.google.com" ); 

		Process process;

		try {
			process = proceso.start();
			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			//Guardamos en un buffer la salida del proceso
			String line;
			
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			
			if (buffer.toString().contains("(0% perdidos)")) {
				
				System.out.println("Tienes conexión a Internet! (o al menos a Google, jeje)");
				
			} else {

				System.out.println("No tienes conexión a Internet, o se te ha caído algún paquetillo por el camino :(");
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

