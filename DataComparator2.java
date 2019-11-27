import java.io.Serializable;
import java.util.Comparator;


public class DataComparator2 implements Comparator<Estatistica>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(Calendar date, Estatistica est2){
		if(est1.getData().after(est2.getData())) return -1;
		if(est1.getData().before(est2.getData())) return 1;
		
		else return 0;
	}

}
