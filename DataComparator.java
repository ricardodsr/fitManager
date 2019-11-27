import java.io.Serializable;
import java.util.Comparator;


public class DataComparator implements Comparator<Estatistica>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * Comparator
 */
	public int compare(Estatistica est1, Estatistica est2){
		if(est1.getData().before(est2.getData())) return -1;
		if(est1.getData().after(est2.getData())) return 1;
		
		else return 0;
	}
}
