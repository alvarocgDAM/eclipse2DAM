import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SJF  extends Planificador{

		//ATRIBUTOS
	
		// lista de procesos
		//ArrayList<Proceso> coleccionProcesos; de su padre
		
		//Constructor
		public SJF(ArrayList<Proceso> listaProcesos) {
			
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
				actualizarCola(cola,cicloActual);
				
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
					actualizarCola(cola,cicloActual);
					
				}
				
				
			}
			
			float indicePTotal = sumIndiceP/coleccionProcesos.size();
			
			System.out.println("Índice de penalización total: " + indicePTotal);
			
		}
		
		
		/**
		 *  Si en el ciclo actual ha llegado un proceso, lo añade a la cola y recalcula quién debe ir primero de nuevo
		 * 
		 * 
		 */
		public void actualizarCola(LinkedList<Proceso> cola,int cicloActual) {
			
			Proceso procesoNuevo = null;
			
			procesoNuevo = procesoLlegado(cicloActual);
			
			if (procesoNuevo != null) {
				
				cola.add(procesoNuevo);
				
				Collections.sort(cola);
				
			}
			
		}
		
		
		
		
		
	
}
