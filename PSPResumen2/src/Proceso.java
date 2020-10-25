
public class Proceso implements Comparable<Proceso>{

	//ATRIBUTOS
	
	//PID del proceso
	private int pid;
	
	// numero de ciclos que necesita el proceso para completarse
	//usamos 2 variables, una para almacenar y otra para modificar
	private int rafaga; 
	private int rafagaSave;
	
	//ciclo en el que llega el proceso
	private int llegada;
	
	//CONSTRUCTORES
	
	public Proceso() {
		this.pid = 0;
		this.llegada = 0;	
		this.rafaga = 0;
		this.rafagaSave = 0;
	}
	
	
	public Proceso(int pid,int llegada,int rafaga) {

		this.pid = pid;
		this.llegada = llegada;
		this.rafaga = rafaga;
		this.rafagaSave = rafaga;
		
	}
	//METODOS
	
	
	/**
	 * Devuelve true si la rafaga del proceso es 0, es decir, si ha terminado de ejecutarse
	 * 
	 * @return boolean
	 */
	public boolean terminado() {
		
		if (this.rafaga == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Ejecuta un ciclo del proceso, restándole 1 a su rafaga
	 * 
	 * @return boolean
	 */
	public void ejecutarCiclo() {
		
		this.rafaga--;
		
	}
	

	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public int getRafaga() {
		return rafaga;
	}

	public int getRafagaSave() {
		return rafagaSave;
	}

	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}


	public int getLlegada() {
		return llegada;
	}


	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}


	// Para la ordenación del Linked List
	@Override
	public int compareTo(Proceso o) {
		if (rafaga > o.rafaga) {
            return -1;
        }
        if (rafaga < o.rafaga) {
            return 1;
        }
        return 0;
	}
	
	
	
	
	
}
