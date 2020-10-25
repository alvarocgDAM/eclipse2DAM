import java.util.ArrayList;
import java.util.LinkedList;

public class FIFO extends Planificador{

	//ATRIBUTOS
	
	// lista de procesos
	//ArrayList<Proceso> coleccionProcesos; de su padre Planificador
	
	//Constructor
	public FIFO(ArrayList<Proceso> listaProcesos) {
		
		super(listaProcesos);
		
	}
	
	
	/**
	 * Metodo que imprime por pantalla la planificacion de los procesos
	 * 
	 * 
	 */
	public void run() {
		
		int ciclosTotales = ciclosTotales(); //toma el array list del propio objeto
		
		//creo una cola donde se irán almacenando los procesos según "lleguen"
		LinkedList<Proceso> cola = new LinkedList<>();
		float sumIndiceP = 0; 
		
		//iteracion sobre los ciclos, desde el 0 hasta el total de ciclos necesarios menos 1 
		for (int cicloActual = 0; cicloActual < ciclosTotales;) {
			
			//quien ha llegado? lo meto en cola
			encolar(cola, coleccionProcesos, cicloActual);
			
			Proceso procesoEjecucion = cola.pollLast();
			
			while (!procesoEjecucion.terminado()) {
				
				procesoEjecucion.ejecutarCiclo();
				
				System.out.print("Ciclo " + cicloActual + " de " + ciclosTotales + ": PID " + procesoEjecucion.getPid() + " ciclos restantes=" + procesoEjecucion.getRafaga());
				
				
				//////////////////////////
				if (procesoEjecucion.terminado()) {
					
					//añado el 1 para tener en cuenta el final de este ciclo actual
					float indiceP = (1 + cicloActual - procesoEjecucion.getLlegada())/(procesoEjecucion.getRafagaSave());
					
					sumIndiceP += indiceP;
					
					System.out.println("  Índice de penalización: " + indiceP);
					
				} else {
					System.out.println("");
				}
				//////////////////////////
				
				cicloActual++;
				
				//seguimos comprobando quien ha llegado
				encolar(cola, coleccionProcesos, cicloActual);
				
			}
			
			
		}
		
		float indicePTotal = sumIndiceP/coleccionProcesos.size();
		
		System.out.println("Índice de penalización total: " + indicePTotal);
		
	}
	
	/**
	 * Devuelve el proceso que llega en el ciclo actual. Ahora mismo solo controla 1 proceso, no contempla empates 
	 * 
	 * @param coleccionProcesos
	 * @param cicloActual
	 * @return
	 */
	public Proceso quienLlega(ArrayList<Proceso> coleccionProcesos,int cicloActual) {
		
		Proceso recienLlegado = null;
		
		for (Proceso proceso : coleccionProcesos) {
			
			int llegadaPro = proceso.getLlegada();
			
			if (llegadaPro == cicloActual) {
				
				recienLlegado = proceso;
				
			}
			
		}
		
		return recienLlegado; //si no llega ningun proceso en este ciclo, devuelve null
	}
	
	/**
	 * Comprueba si ha llegado algun proceso nuevo y, si ha llegado, lo mete en cola
	 * 
	 * 
	 * @param cola
	 * @param coleccionProcesos
	 * @param cicloActual
	 */
	public void encolar(LinkedList<Proceso> cola,ArrayList<Proceso> coleccionProcesos,int cicloActual) {
		
		Proceso recienLlegado = quienLlega(coleccionProcesos, cicloActual);
		
		if (recienLlegado != null) {
			
			cola.addFirst(recienLlegado);
			
		}
		
	}
	
}
