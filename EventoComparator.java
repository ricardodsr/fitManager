import java.io.Serializable;
import java.util.Comparator;


public class EventoComparator implements Comparator<Evento>,Serializable{
	public int compare(Evento est1, Evento est2){
		if(est1.getDataInicio().before(est2.getDataInicio())) return -1;
		if(est1.getDataInicio().after(est2.getDataInicio())) return 1;
		
		else return 0;
	}
}
