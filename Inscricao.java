import java.io.Serializable;

public class Inscricao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// patamar
	private String patamar;
	// user
	private Utilizador utilizador;

	public Inscricao(String patamar, Utilizador utilizador) {

		this.patamar = patamar;
		this.utilizador = utilizador;
	}

	public Inscricao() {
		this.patamar = "";
		this.utilizador = null;
	}

	public String getPatamar() {
		return patamar;
	}

	public void setPatamar(String patamar) {
		this.patamar = patamar;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Inscricao other = (Inscricao) obj;
		if (patamar == null) {
			if (other.patamar != null) {
				return false;
			}
		} else if (!patamar.equals(other.patamar)) {
			return false;
		}
		if (utilizador == null) {
			if (other.utilizador != null) {
				return false;
			}
		} else if (!utilizador.equals(other.utilizador)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Inscricao [patamar=" + patamar + ", utilizador=" + utilizador
				+ "]";
	}

}
