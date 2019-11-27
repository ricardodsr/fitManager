import java.io.Serializable;


public class Atletismo extends Actividade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String nomeMod = "Atletismo"; //corrida etc
	private String tecnica;//100m, 400m etc
	private boolean equipa;
	
	/*
	 * Contructor Vazio
	 */
	
	public Atletismo () {
		super();
		this.tecnica = "";
		this.equipa = false;
		
	}
	
	/*
     * Construtor a partir das partes 
     */
	
	public Atletismo (double tempo, double distancia, boolean olimpica,String tecnica, boolean equipa, double altimetria){
		super(tempo,distancia,olimpica,altimetria);
		this.tecnica = tecnica;
		this.equipa = equipa;
	}
	
	/* 
     * Constructor Copia
     */
	
	public Atletismo (Atletismo m){
		super(m.getTempo(),m.getDistancia(),m.isOlimpica(),m.getAltimetria());
		this.tecnica = m.getTecnica();
		this.equipa = m.isEquipa();
	}

	/*
	 * Sets and Gets
	 */
	
	
	
	public String getNomeMod() {
		return nomeMod;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	

	public boolean isEquipa() {
		return equipa;
	}

	public void setEquipa(boolean equipa) {
		this.equipa = equipa;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see Modalidade#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
    	
    	if (this == o) return true;
    	if ((o==null)||(this.getClass() != o.getClass()))
    		return false;
    	else{	
    		Atletismo m=(Atletismo) o;
    			return(
    					
    					 this.tecnica == (m.getTecnica())
    					&& this.equipa == (m.isEquipa()));
    					
    	}
    	
	}
	
	 /*
	 * CLONE(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Atletismo clone(){
		return new Atletismo (this);
	}
	
	/*toString
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\n"+"Nome Modalidade: " + Atletismo.nomeMod + "\n");
		s.append("Tempo: " + super.getTempo() + "\n");
		s.append("Calorias: " + super.getCaloriasGastas() + "\n");
		s.append("Distancia: " + super.getDistancia() + "\n");
		s.append("E Olimpica: " + super.isOlimpica() + "\n");
		s.append("Tecnica: " + this.tecnica + "\n");
		s.append("E de equipa: " + this.equipa + "\n");
		
		
		return s.toString();
	}
	

	@Override
	public void caloriasGastas(Utilizador t) {
		double res=0;
		double peso = t.getPeso();
		if(super.getRitmo()<11)res = peso*super.getRitmo()*0.16+(2*super.getAltimetria());
		else res = peso*this.getRitmo()*0.25+(2*super.getAltimetria());
		caloriasGastas = res;
	}

	@Override
	public double calculaMelhorEstatistica() {
		return getDistancia()/getTempo();
	}
		
	
}
