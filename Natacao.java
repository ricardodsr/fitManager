import java.io.Serializable;


public class Natacao extends Actividade implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static String nomeMod="Natacao";
		private String tecnica; //crowl, brusus, mariposa, costas
		private boolean equipa;
		
		/*
		 * Construtor Vazio
		 */
		
		public Natacao () {
			super();
			this.tecnica = "";
			this.equipa = false;
			
		}
		
		/*
	     * Construtor a partir das partes 
	     */
		
		public Natacao (double tempo, double distancia, boolean olimpica, String tecnica, boolean equipa){
			super(tempo,distancia,olimpica,0);
			this.tecnica = tecnica;
			this.equipa = equipa;
		}
		
		/* 
	     * Constructor Copia
	     */
		
		public Natacao (Natacao m){
			super(m.getTempo(),m.getDistancia(),m.isOlimpica(),m.getAltimetria());
			this.tecnica = m.getTecnica();
			this.equipa = m.isEquipa();
			
		}

		/*
		 * gets sets
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
		
		


		@Override
		public void caloriasGastas(Utilizador t) {
			double res;
			double peso = t.getPeso();
			if(super.getRitmo()<11)res = peso*super.getRitmo()*0.10;
			else res = peso*super.getRitmo()*0.16;
			this.caloriasGastas= res;
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
	    		Natacao m=(Natacao) o;
	    			return(
	    					this.tecnica ==(m.getTecnica())
	    					&& this.equipa == (m.isEquipa()));
	    					
	    	}
	    	
		}
		
		 /*
		 * CLONE(non-Javadoc)
		 * @see java.lang.Object#clone()
		 */
		public Natacao clone(){
			return new Natacao (this);
		}
		
		/*toString
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */

		public String toString(){
			StringBuilder s = new StringBuilder();
			s.append("\n"+"Nome Modalidade: " + Natacao.nomeMod + "\n");
			s.append("Tempo: " + super.getTempo() + "\n");
			s.append("Calorias: " + super.getCaloriasGastas() + "\n");
			s.append("Distancia: " + super.getDistancia() + "\n");
			s.append("E Olimpica: " + super.isOlimpica() + "\n");
			s.append("Tecnica: " + this.tecnica + "\n");
			s.append("E de equipa: " + this.equipa+"\n");
			
			
			return s.toString();
		}

		@Override
		public double calculaMelhorEstatistica() {
			return getDistancia()/getTempo();
		}
		 
		
}

