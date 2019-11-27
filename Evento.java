import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Evento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	// actividade -> lista de patamares
	private Map<String, List<String>> modalidadePatamar;
	private int maxInscricoes;
	private List<Utilizador> participantes;
	// acti -> lista user/inscritos
	private Map<String, List<Inscricao>> listaDeInscritos;
	private GregorianCalendar dataInicio;
	private GregorianCalendar dataFimInscricoes;

	/*
	 * Contructor Vazio
	 */
	public Evento() {
		this.nome = "";
		this.modalidadePatamar = new TreeMap<String, List<String>>();
		this.maxInscricoes = 0;
		this.participantes = new ArrayList<Utilizador>();
		this.dataInicio = new GregorianCalendar();
		this.dataFimInscricoes = new GregorianCalendar();
		this.listaDeInscritos = new HashMap<String, List<Inscricao>>();
	}

	/*
	 * Construtor a partir das partes
	 */
	public Evento(String nome, int maxInscricoes, GregorianCalendar dataInicio, GregorianCalendar dataFimInscricoes) {
		this();
		this.nome = nome;
		this.maxInscricoes = maxInscricoes;
		this.dataInicio = dataInicio;
		this.dataFimInscricoes = dataFimInscricoes;

	}

	/*
	 * Constructor Copia
	 */

	public Evento(Evento m) {
		this.nome = m.getNome();
		this.modalidadePatamar = m.getModalidadePatamar();
		this.maxInscricoes = m.getMaxInscricoes();
		this.participantes = m.getParticipantes();
		this.dataInicio = m.getDataInicio();
		this.dataFimInscricoes = m.getDataFimInscricoes();
		this.listaDeInscritos = m.getListaDeInscritos();

	}

	/**
	 * Sets and Gets
	 * 
	 * @return
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * @return the listaDeInscritos
	 */
	public Map<String, List<Inscricao>> getListaDeInscritos() {
		return listaDeInscritos;
	}

	/**
	 * @param listaDeInscritos
	 *            the listaDeInscritos to set
	 */
	public void setListaDeInscritos(Map<String, List<Inscricao>> listaDeInscritos) {
		this.listaDeInscritos = listaDeInscritos;
	}

	public GregorianCalendar getDataFimInscricoes() {
		return dataFimInscricoes;
	}

	public void setDataFimInscricoes(GregorianCalendar dataFimInscricoes) {
		this.dataFimInscricoes = dataFimInscricoes;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, List<String>> getModalidadePatamar() {
		return modalidadePatamar;
	}

	public void setModalidadePatamar(Map<String, List<String>> modalidadePatamar) {
		this.modalidadePatamar = modalidadePatamar;
	}

	public int getMaxInscricoes() {
		return maxInscricoes;
	}

	public void setMaxInscricoes(int maxInscricoes) {
		this.maxInscricoes = maxInscricoes;
	}

	public List<Utilizador> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Utilizador> participantes) {
		this.participantes = participantes;
	}

	public GregorianCalendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(GregorianCalendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * METODOS EVENTO Participantes
	 */

	/**
	 * Numero de participantes
	 * 
	 * @return
	 */
	public int numeroParticipantes() {
		return this.participantes.size();
	}

	/**
	 * Insere utilizador no array participantes
	 * 
	 * @param v
	 */
	public void insereUtilizador(Utilizador v) {
		this.participantes.add(v);
	}

	/**
	 * Verifica se existe um utilizador no array
	 * 
	 * @param v
	 * @return
	 */

	public boolean existeUtilizador(Utilizador v) {
		return this.participantes.contains(v);
	}

	/**
	 * Verifica se existe participante por email
	 * 
	 * @param email
	 * @return
	 */

	public boolean existeParticipante(String email) {
		boolean encontrado = false;
		for (Utilizador v : participantes) {
			if (v.getEmail().equals(email)) {
				encontrado = true;
			}
		}
		return encontrado;

	}

	/**
	 * Metodos Evento Modalidade
	 */

	/**
	 * Numero de modalidades
	 * 
	 * @return
	 */
	public int numeroModalidades() {
		return this.modalidadePatamar.keySet().size();

	}

	/**
	 * Insere Modalidade
	 * 
	 * @param v
	 */
	public void insereModalidade(String actividade, String patamar) throws UtilizadorException {

		if (!this.modalidadePatamar.containsKey(actividade)) {
			List<String> aux1 = new ArrayList<String>();
			aux1.add(patamar);
			this.modalidadePatamar.put(actividade, aux1);
		} else {
			List<String> aux = this.modalidadePatamar.get(actividade);
			if (!aux.contains(patamar)) {
				aux.add(patamar);
				this.modalidadePatamar.put(actividade, aux);
			} else {
				throw new UtilizadorException(patamar);
			}
		}

	}

	/**
	 * Verifica se existe modalidade
	 * 
	 * @param v
	 * @return
	 */

	public boolean existeModaliade(String v) {
		return this.modalidadePatamar.containsKey(v);
	}

	public Set<String> getModalidade() {
		Set<String> aux = new TreeSet<String>();
		for (String s : this.modalidadePatamar.keySet()) {
			aux.add(s);
		}
		return aux;
	}

	public void addListaDeInscritos(String actividade, Utilizador user, String patamar) {
		if (!this.listaDeInscritos.keySet().contains(actividade)) {
			List<Inscricao> aux = new ArrayList<Inscricao>();
			aux.add(new Inscricao(patamar, user));
			this.listaDeInscritos.put(actividade, aux);
		} else {
			List<Inscricao> aux2 = this.listaDeInscritos.get(actividade);
			boolean flag = false;
			for (Inscricao x : aux2) {
				if (x.getUtilizador().equals(user)) {
					flag = true;
				}
			}
			if (flag == false) {
				aux2.add(new Inscricao(patamar, user));
			}
		}

	}

	/*
	 * CLONE(non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */

	@Override
	public Evento clone() {
		return new Evento(this);

	}

	/*
	 * Equals (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		} else {
			Evento m = (Evento) o;
			return (this.nome.equals(m.getNome()) && this.modalidadePatamar.equals(m.getModalidadePatamar()) && this.maxInscricoes == (m.getMaxInscricoes())
					&& this.participantes.equals(m.getParticipantes()) && this.dataInicio.equals(m.getDataInicio()));
		}
	}

	/*
	 * toString (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Nome: " + this.nome + "\n");
		s.append("Modalidade: " + this.modalidadePatamar + "\n");
		s.append("Max Inscricoes: " + this.maxInscricoes + "\n");
		s.append("Participantes: " + this.participantes + "\n");
		s.append("Lista de inscritos: " + this.listaDeInscritos + "\n");
		s.append("Data de inicio: " + this.dataInicio.getTime().toString() + "\n");
		return s.toString();
	}

	public int compareTo(Evento ev) {
		// TODO Auto-generated method stub
		return ev.getDataInicio().compareTo(this.getDataInicio());
	}

}
