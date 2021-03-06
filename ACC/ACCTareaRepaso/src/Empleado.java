import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class Empleado {

	protected String empresa;
	protected String edad;
	protected String num_empleados;	
	
	public static final String FICHERO_EMPLEADOS_SAVE = "empleadosSave.txt";
	public static final String FICHERO_EMPLEADOS_BIN= "empleadosBin.txt";

	
	public Empleado(String nEmpresa, String nEdad, String nNum_empleados) {
		this.empresa = nEmpresa;
		this.edad = nEdad;
		this.num_empleados = nNum_empleados;
	}
	
	public Empleado(String lineaTexto) {
		
		String[] lineaTextoSplit = lineaTexto.split(",");
		
		String miEmpresa = lineaTextoSplit[0];
		String miEdad = lineaTextoSplit[1];
		String miNum_empleados = lineaTextoSplit[2];
		
		new Empleado(miEmpresa,miEdad,miNum_empleados);
		
	}
	
	
	
	
	public String toString() {
		return "Empresa: " + empresa + "\n Edad: " + edad + "\n Número de empleados: " + num_empleados + "\n";
	}


	public void toArrayEmpleados(ArrayList<Empleado> arrayEmpleados) {
		
		FileWriter archivo = null;
		try {
			
			archivo = new FileWriter(FICHERO_EMPLEADOS_SAVE);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		BufferedWriter escritor = new BufferedWriter(archivo);
		
		for (Empleado empleado : arrayEmpleados) {
			
			try {
				escritor.write(empleado.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void toArrayEmpleadosInverso(ArrayList<Empleado> arrayEmpleados) {
		
		Collections.reverse(arrayEmpleados);
		
		FileOutputStream archivo = null;
		try {
			archivo = new FileOutputStream(FICHERO_EMPLEADOS_BIN);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ObjectOutputStream escritor;
		try {
			escritor = new ObjectOutputStream(archivo);
			for (Empleado empleado : arrayEmpleados) {
				
				escritor.writeObject(empleado.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void leer_Empleados(String archivo) {
		
		String linea = null;
		FileReader archivoLectura = null;
		ArrayList<Empleado> arrayEmpleados = new ArrayList<Empleado>();
		
		try {
			archivoLectura = new FileReader(archivo+".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader lector = new BufferedReader(archivo);
		try {
			
		linea = lector.readLine();
		while (linea != null) {
			
			linea = lector.readLine();
				
			System.out.println(linea);
				
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 
	 * Toma el nombre de un fichero para abrirlo, leerlo y crear un ArrayList de empleados
	 * 
	 * @param nombreFichero
	 * @return ArrayList<Empleado>
	 */
	public ArrayList<Empleado> ficheroToArray(String nombreFichero) {
		
		String linea = null;
		FileReader archivo = null;
		ArrayList<Empleado> arrayEmpleados = new ArrayList<Empleado>();
		
		try {
			archivo = new FileReader(nombreFichero+".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader lector = new BufferedReader(archivo);
		try {
			
		linea = lector.readLine();
		while (linea != null) {
			
			linea = lector.readLine();
				
			Empleado empleadoTexto = new Empleado(linea);
				
			arrayEmpleados.add(empleadoTexto);
				
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		return arrayEmpleados;
		
	}
	
	
	

	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getEdad() {
		return edad;
	}
	
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	public String getNum_empleados() {
		return num_empleados;
	}
	
	public void setNum_empleados(String num_empleados) {
		this.num_empleados = num_empleados;
	}
	
}
