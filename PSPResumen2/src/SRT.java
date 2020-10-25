import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class SRT extends Planificador{

	//ATRIBUTOS
	
			// lista de procesos
			//ArrayList<Proceso> coleccionProcesos; de su padre
			
			//Constructor
			public SRT(ArrayList<Proceso> listaProcesos) {
				
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
					
					//debo comprobar si ha llegado alguien, y si ha llegado alguien, recalcular la cola y el proceso en ejecución					
					Proceso procesoEjecucion = recalcularEjecucion(cola,cicloActual);;
					
					while (true) {
						
						procesoEjecucion.ejecutarCiclo();
						
						System.out.print("Ciclo " + cicloActual + " de " + ciclosTotales + ": PID " + procesoEjecucion.getPid() + " ciclos restantes=" + procesoEjecucion.getRafaga());
						
						
						//////////////////////////
						if (procesoEjecucion.terminado()) {
							
							//añado el 1 para tener en cuenta el final de este ciclo actual
							float indiceP = (1 + cicloActual - procesoEjecucion.getLlegada())/(procesoEjecucion.getRafagaSave());
							
							sumIndiceP += indiceP;
							
							System.out.println("  Índice de penalización: " + indiceP);
							
							//adicionalmente, borro el proceso que ha terminado de ejecutarse
							cola.removeLast();							
							
						} else {
							System.out.println("");
						}
						//////////////////////////
						
						cicloActual++;
						
						//debo comprobar si ha llegado alguien, y si ha llegado alguien, recalcular la cola y el proceso en ejecución
						procesoEjecucion = recalcularEjecucion(cola,cicloActual);
						
						if (cicloActual > ciclosTotales) {
							
							break;
							
						}
						
					}
					
					
				}
				
				float indicePTotal = sumIndiceP/this.coleccionProcesos.size();
				
				System.out.println("Índice de penalización total: " + indicePTotal);
				
			}
			
			/**
			 * Comprueba si ha llegado un proceso nuevo, y los ordena por rafaga restante.
			 * Luego, devuelve el proceso que tenga la mayor prioridad (menor ráfaga restante)
			 * 
			 * @param cola
			 * @param cicloActual
			 */
			public Proceso recalcularEjecucion(LinkedList<Proceso> cola,int cicloActual) {
				
				Proceso procesoNuevo = null;
				
				//compruebo si ha llegado un proceso nuevo
				procesoNuevo = procesoLlegado(cicloActual);
				
				if (procesoNuevo != null) {
					
					cola.add(procesoNuevo);
					
					Collections.sort(cola);
					
				}
				
				//devuelvo el proceso con mayor prioridad, pero no lo elimino de la lista
				Proceso procesoAEjecutar = cola.peekLast();
				
				return procesoAEjecutar;
				
			}
	
}
