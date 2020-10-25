import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		ArrayList<Proceso> misProcesos = new ArrayList<Proceso>();
		
		//////////////////////
		// AQUÍ LOS PROCESOS//
		//////////////////////
		
		Proceso proceso1 = new Proceso(1001,0,5);
		Proceso proceso2 = new Proceso(1002,3,6);
		Proceso proceso3 = new Proceso(1003,2,2);
		Proceso proceso4 = new Proceso(1004,4,3);
		
		misProcesos.add(proceso1);
		misProcesos.add(proceso2);
		misProcesos.add(proceso3);
		misProcesos.add(proceso4);
		
		
		
		///////////////////////
		// DESCOMENTA EL QUE //
		//  QUIERAS USAR :)  //
		///////////////////////
		
		
		FIFO planificadorFIFO = new FIFO(misProcesos); 
		
		//planificadorFIFO.run();
		
		SJF planificadorSJF = new SJF(misProcesos);
		
		//planificadorSJF.run();
		
		SRT planificadorSRT = new SRT(misProcesos);
		
		//planificadorSRT.run();
		
	}

}
