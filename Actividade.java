import java.io.Serializable;




public abstract class Actividade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double tempo;
	protected double caloriasGastas;
	private double distancia;
	private boolean olimpica;
	private double ritmo;
	private double altimetria;
	//private HashMap <String, Estatisticas> lEestatistica;
	
	/*
	 * Construtor Vazio
	 */
	public Actividade (){
		this.tempo = 0.0;
		this.caloriasGastas = 0.0;
		this.distancia = 0.0;
		this.olimpica = false;
		this.ritmo = 0.0;
		this.altimetria = 0.0;
	}
	
	/*
     * Construtor a partir das partes 
     */
	public Actividade (double tempo, double distancia, boolean olimpica, double altimetria){
		this.altimetria = altimetria;
		this.tempo = tempo;
		this.distancia = distancia;
		this.olimpica = olimpica;
		this.ritmo = distancia/tempo;
	}
	
	/* 
     * Constructor Copia
     */
	
	public Actividade (Actividade m){
		this.tempo = m.getTempo();
		this.caloriasGastas = m.getCaloriasGastas();
		this.distancia = m.getDistancia();
		this.olimpica = m.isOlimpica();
		this.ritmo = m.getDistancia()/m.getTempo();
		this.altimetria = m.getAltimetria();
	}
	
	/*
	 *GETS
     *SETS
     */

	
	
	public double getTempo() {
		return tempo;
	}

	public double getAltimetria() {
		return altimetria;
	}

	public void setAltimetria(double altimetria) {
		this.altimetria = altimetria;
	}

	public double getRitmo() {
		return ritmo;
	}

	

	public boolean isOlimpica() {
		return olimpica;
	}

	public void setOlimpica(boolean olimpica) {
		this.olimpica = olimpica;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public double getCaloriasGastas() {
		return caloriasGastas;
	}


	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	public abstract void caloriasGastas(Utilizador t);
	
	public abstract String getNomeMod();
	
	public abstract double calculaMelhorEstatistica();
	    /*
		 * CLONE(non-Javadoc)
		 * @see java.lang.Object#clone()
		 *N‹o existe clone, pois isto e uma abs.
		 */
	
	
	/*Equals
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
    	
    	if (this == o) return true;
    	if ((o==null)||(this.getClass() != o.getClass()))
    		return false;
    	else{	
    		Actividade m=(Actividade) o;
    			return(
    					this.tempo == (m.getTempo())
    					&& this.caloriasGastas ==(m.getCaloriasGastas())
    					&& this.distancia == (m.getDistancia())
    					&& this.olimpica == (m.isOlimpica()))
    					&& this.ritmo == (m.getRitmo())
    					&& this.altimetria == (m.getAltimetria());
    					
    	}
    	
	}
	
	/*toString
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("Tempo: " + this.tempo + "\n");
		s.append("Calorias: " + this.caloriasGastas + "\n");
		s.append("Distancia: " + this.distancia + "\n");
		s.append("E Olimpica: " + this.olimpica + "\n");
		s.append("Ritmo: " + this.ritmo + "\n");
		s.append("Ganho de altimetria: " + this.altimetria + "\n");
		
		return s.toString();
	}

}
