import java.util.ArrayList;

public abstract class Planificador {

	//ATRIBUTOS
	
	// lista de procesos
	ArrayList<Proceso> coleccionProcesos;

	
	
	//CONSTRUCTOR
	public Planificador(ArrayList<Proceso> coleccionProcesos) {
		
		this.coleccionProcesos = coleccionProcesos;
		
	}
		

	/**
	 * Devuelve el numero de ciclos totales que van a tardar todos los procesos en ejecutarse MENOS 1 (para usarlo directamente como iterador del for principal), tomando la arraylist del objeto
	 * 
	 * @param coleccionProcesos
	 * @return int
	 */
	public int ciclosTotales() {
		
		int ciclosSuma = 0;
		
		for (Proceso proceso : this.coleccionProcesos) {
			
			ciclosSuma += proceso.getRafaga();
			
		}
		
		return ciclosSuma-1; //le resto 1 para empezar en 0
	}
	
	/**
	 * Comprueba si ha llegado algún proceso, y lo devuelve
	 * 
	 * 
	 * @param cicloActual
	 * @return
	 */
	public Proceso procesoLlegado(int cicloActual) {
		
		
		for (Proceso proceso : this.coleccionProcesos) {
			
			if (proceso.getLlegada() == cicloActual) {
				
				return proceso;
				
			}
		}
		
		//si no ha llegado nadie, devuelvo null
		return null;
		
	}
			
}
