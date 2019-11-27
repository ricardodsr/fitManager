import java.io.Serializable;


public class UtilizadorException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * Extends da class exception
 */
	public UtilizadorException ()
	{super();}
	
	public UtilizadorException (String s)
	{
		super (s);
	}

}
