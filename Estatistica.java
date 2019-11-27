//package src;

import java.io.Serializable;
import java.util.GregorianCalendar;


public class Estatistica implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GregorianCalendar data;
	private String patamar;
	private Actividade modalidade;
	
	
	/*
	 * Contructor Vazio
	 */
	
	public Estatistica() {
		this.modalidade = null;
		this.data = new GregorianCalendar();
		this.patamar = "";
		
	}
	
	 /*
     * Construtor a partir das partes 
     */
	public Estatistica(Actividade modalidade, GregorianCalendar data, String patamar){
		this.modalidade = modalidade;
		this.data = data;
		this.patamar = patamar;
	}
	
	 /* 
     * Constructor Copia
     */
	public Estatistica (Estatistica m){
		this.modalidade = m.getModalidade();
		this.data = m.getData();
		this.patamar = m.getPatamar();
	}
	
	
	
	//METODOS
	
	public void addModalidade(Actividade m){
		this.modalidade = m;
	}
	
	
	//public void removeModalidade()
	
	/**
	 * SETS AND GETS
	 */

	public Actividade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Actividade modalidade) {
		this.modalidade = modalidade;
	}

	
	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	public String getPatamar() {
		return patamar;
	}

	public void setPatamar(String patamar) {
		this.patamar = patamar;
	}

	/*
	 * CLONE(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	
	public Estatistica clone(){
		return new Estatistica(this);
	}
	
	/*Equals
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
    	
    	if (this == o) return true;
    	if ((o==null)||(this.getClass() != o.getClass()))
    		return false;
    	else{	
    		Estatistica m=(Estatistica)o;
    			return(
    					this.modalidade.equals(m.getModalidade())
    					&& this.data.equals(m.getData())
    					&& this.patamar.equals(m.getPatamar()));
    	}
	}
    	/*toString
    	 * (non-Javadoc)
    	 * @see java.lang.Object#toString()
    	 */
    	public String toString(){
    		StringBuilder s = new StringBuilder();
    		s.append("\n"+ this.modalidade );
    		s.append("Data: " + this.data.getTime()+ "\n");
    		s.append("Patamar: "+this.patamar + "\n");
    		return s.toString();
    		
    	
    	
	}
	
    	
    	/**
    	 * METODOS
    	 */
}
