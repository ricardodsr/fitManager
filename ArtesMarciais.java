import java.io.Serializable;


public class ArtesMarciais extends Actividade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String nomeMod="Artes Marciais";
	private boolean equipa;
	private String cinturao;
	private String tecnica;
	
	
	/*
	 * Contructor Vazio
	 */
	
	public ArtesMarciais() {
		super();
		this.tecnica="";
		this.cinturao = "";
		this.equipa = false;
		
	}
	
	/*
     * Construtor a partir das partes 
     */
	
	public ArtesMarciais(double tempo, boolean olimpica,String tecnica,String cinturao, boolean equipa){
		super(tempo,0.0,olimpica,0);
		this.tecnica=tecnica;
		this.cinturao = cinturao;
		this.equipa = equipa;
	}
	
	/* 
     * Constructor Copia
     */
	
	public ArtesMarciais (ArtesMarciais m){
		super(m.getTempo(),m.getDistancia(),m.isOlimpica(),m.getAltimetria());
		this.tecnica = m.getTecnica();
		this.cinturao = m.getCinturao();
		this.equipa = m.isEquipa();
	}

	
	/*
	 * GETS SETS
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

	public String getCinturao() {
		return cinturao;
	}

	public void setCinturao(String cinturao) {
		this.cinturao = cinturao;
	}
	
	public void caloriasGastas(Utilizador t){
		double peso = t.getPeso();
		caloriasGastas = super.getTempo()*peso*0.2;
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
    		ArtesMarciais m=(ArtesMarciais) o;
    			return(
    					this.tecnica == (m.getTecnica())
    					&& this.cinturao == (m.getCinturao())
    					&& this.equipa == (m.isEquipa()));
    					
    	}
    	
	}
	
	 /*
	 * CLONE(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public ArtesMarciais clone(){
		return new ArtesMarciais(this);
	}
	
	/*toString
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\n"+"Nome Modalidade: " + ArtesMarciais.nomeMod + "\n");
		s.append("Tempo: " + super.getTempo() + "\n");
		s.append("Calorias: " + super.getCaloriasGastas() + "\n");
		s.append("Tecnica: " + this.tecnica + "\n");
		s.append("E Olimpica: " + super.isOlimpica() + "\n");
		s.append("Cinturao: " + this.cinturao + "\n");
		s.append("E de equipa: " + this.equipa + "\n");
		
		
		return s.toString();
	}

	@Override
	public double calculaMelhorEstatistica() {
		return getTempo();
	}

	
		
}
