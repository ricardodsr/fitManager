import java.io.Serializable;


public class Futebol extends Actividade implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String nomeMod="Futebol";
	private boolean equipa;
	private String clube;
	private boolean profissional;
	
	/*
	 * Contructor Vazio
	 */
	
	public Futebol() {
		super();
		this.equipa = false;
		this.clube = "";
		this.profissional = false;
		
	}
	
	/*
     * Construtor a partir das partes 
     */
	
	public Futebol (double tempo, double distancia, boolean olimpica, boolean equipa, String clube, boolean profissional){
		super(tempo,distancia,olimpica,0);
		this.equipa = equipa;
		this.clube = clube;
		this.profissional = profissional;
	}
	
	/* 
     * Constructor Copia
     */
	
	public Futebol (Futebol m){
		super(m.getTempo(),m.getDistancia(),m.isOlimpica(),m.getAltimetria());
		this.equipa = m.isEquipa();
		this.clube = m.getClube();
		this.profissional = m.isProfissional();
	}

	
	/*
	 * GETS SETS
	 */

	public String getNomeMod() {
		return nomeMod;
	}

	public boolean isEquipa() {
		return equipa;
	}

	public void setEquipa(boolean equipa) {
		this.equipa = equipa;
	}

	public String getClube() {
		return clube;
	}

	public void setClube(String clube) {
		this.clube = clube;
	}

	public boolean isProfissional() {
		return profissional;
	}

	public void setProfissional(boolean profissional) {
		this.profissional = profissional;
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
    		Futebol m=(Futebol) o;
    			return(
    				    this.equipa == (m.isEquipa())
    					&& this.clube == (m.getClube())
    					&& this.profissional == (m.isProfissional()));
    					
    	}
    	
	}
	
	 /*
	 * CLONE(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Futebol clone(){
		return new Futebol(this);
	}
	
	/*toString
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\n"+"Nome Modalidade: " + Futebol.nomeMod + "\n");
		s.append("Tempo: " + super.getTempo() + "\n");
		s.append("Calorias: " + super.getCaloriasGastas() + "\n");
		s.append("Distancia: " + super.getDistancia() + "\n");
		s.append("E Olimpica: " + super.isOlimpica() + "\n");
		s.append("E de equipa: " + this.equipa + "\n");
		s.append("Clube: " + this.clube + "\n");
		s.append("Profissional: " + this.profissional +"\n");
		
		return s.toString();
	}
	

	@Override
	/**
	 * 
	 */
	public void caloriasGastas(Utilizador t) {
		double res;
		double peso = t.getPeso();
		if(super.getRitmo()<11)res = peso*super.getRitmo()*0.11;
		else res = peso*super.getRitmo()*0.16;
		this.caloriasGastas= res;
	}

	@Override
	public double calculaMelhorEstatistica() {
		return getDistancia()/getTempo();
	}
		

	
}
