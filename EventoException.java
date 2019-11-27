import java.io.Serializable;


public class EventoException extends Exception implements Serializable{
	public EventoException ()
	{super();}
	
	public EventoException (String s)
	{
		super (s);
	}

}
