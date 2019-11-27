import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Utilizador implements Comparable<Utilizador>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Variaveis de Classe
	 */

	private String email;
	private String psw;
	private String nome;
	private String genero;
	private double altura;
	private double peso;
	private GregorianCalendar dataNascimento;
	private String desportoFavorito;

	private Set<Estatistica> listaTemposMensais;

	// A key e String com o nome da modalidade, values (map de Key
	// patamar,values Estatistica do melhor tempo)
	private Map<String, Map<String, Estatistica>> listaBestTempos;

	private Set<Utilizador> listaAmigos;

	private Set<Utilizador> listaAmigosPendentes;

	/*
	 * Hashmap Actividades que realizou treemap melhores tempos Hashmap lista de
	 * amigos
	 */

	/*
	 * Construtor Vazio
	 */

	public Utilizador() {
		this.email = "";
		this.psw = "";
		this.nome = "";
		this.genero = "";
		this.altura = 0.0;
		this.peso = 0.0;
		this.dataNascimento = new GregorianCalendar();
		this.desportoFavorito = "";
		this.listaTemposMensais = new TreeSet<Estatistica>(new DataComparator());
		this.listaAmigos = new TreeSet<Utilizador>();
		this.listaBestTempos = new HashMap<String, Map<String, Estatistica>>();
		this.listaAmigosPendentes = new TreeSet<Utilizador>();
	}

	/*
	 * Construtor a partir das partes
	 */

	public Utilizador(String email, String psw, String nome, String genero,
			double altura, double peso, GregorianCalendar datanascimento,
			String desportoFavorito) {
		this(); // chamar construtor vazio para inicializar todas as variaveis
		this.email = email;
		this.psw = psw;
		this.nome = nome;
		this.genero = genero;
		this.altura = altura;
		this.peso = peso;
		this.dataNascimento = datanascimento;
		this.desportoFavorito = desportoFavorito;

	}

	/*
	 * Constructor Copia
	 */
	public Utilizador(Utilizador m) {
		this.email = m.getEmail();
		this.psw = m.getPsw();
		this.nome = m.getNome();
		this.genero = m.getGenero();
		this.altura = m.getAltura();
		this.peso = m.getPeso();
		this.dataNascimento = m.getDataNascimento();
		this.desportoFavorito = m.getDesportoFavorito();
		this.listaAmigos = m.getListaAmigos();
		this.listaTemposMensais = m.getListaTemposMensais();
		this.listaBestTempos = m.getListaBestTempos();
		this.listaAmigosPendentes = m.getListaAmigosPendentes();
	}

	/**
	 * Adiciona Utilizador
	 * 
	 * @param user
	 */
	public void addAFriend(String userLogin) throws UtilizadorException {
		Utilizador aux = new Utilizador();
		for (Utilizador uti : this.listaAmigosPendentes) {
			if (uti.getEmail().equals(userLogin)) {
				aux = uti.clone();
			}
		}
		if (!this.listaAmigos.contains(aux)) {
			this.listaAmigos.add(aux);
			removeFriendListaPendent(aux);
		}
	}

	/**
	 * 
	 * @param email
	 * @param pass
	 * @param nome
	 * @param genero
	 * @param altura
	 * @param peso
	 * @param dataNas
	 * @param desportoFav
	 */
	public void modificaUtilizador(String pass, String nome, String genero,
			double altura, double peso, GregorianCalendar dataNas,
			String desportoFav) {
		this.psw = pass;
		this.nome = nome;
		this.genero = genero;
		this.altura = altura;
		this.peso = peso;
		this.dataNascimento = dataNas;
		this.desportoFavorito = desportoFav;
	}

	/**
	 * remove Amigo da lista de amigos
	 * 
	 * @param user
	 * @throws UtilizadorException
	 */
	public void removeFriend(Utilizador user) throws UtilizadorException {
		if (this.listaAmigos.contains(user)) {
			this.listaAmigos.remove(user);
		} else {
			throw new UtilizadorException(user.getNome());
		}
	}

	/**
	 * Adiciona utilizador na lista pendente
	 * 
	 * @param user
	 * @throws UtilizadorException
	 */
	public void addFriendListaPendente(Utilizador user)
			throws UtilizadorException {
		if (!this.listaAmigosPendentes.contains(user)) {
			this.listaAmigosPendentes.add(user);
		} else {
			throw new UtilizadorException(user.getNome());
		}
	}

	/**
	 * remove utilizador da lista pendente
	 * 
	 * @param user
	 * @throws UtilizadorException
	 */
	public void removeFriendListaPendent(Utilizador user)
			throws UtilizadorException {
		if (this.listaAmigosPendentes.contains(user)) {
			this.listaAmigosPendentes.remove(user);
		} else {
			throw new UtilizadorException(user.getNome());
		}
	}

	/**
	 * Confirma Utilizador para tornar se amigo
	 * 
	 * @param amigo
	 * @throws UtilizadorException
	 */
	public void confirmaFriend(String amigo) throws UtilizadorException {
		addAFriend(amigo);
	}

	/**
	 * adiciona melhores tempos
	 * 
	 * @param novaEstatistica
	 */
	public void addBestTimeV2(Estatistica novaEstatistica) {

		if (this.listaBestTempos.containsKey(novaEstatistica.getModalidade()
				.getNomeMod())) {
			String modName = novaEstatistica.getModalidade().getNomeMod();
			Map<String, Estatistica> patamares = this.listaBestTempos
					.get(modName);
			if (patamares.containsKey(novaEstatistica.getPatamar())) {
				String patamarName = novaEstatistica.getPatamar();

				Estatistica modPatStat = patamares.get(patamarName);
				if (modPatStat.getModalidade().calculaMelhorEstatistica() > novaEstatistica
						.getModalidade().calculaMelhorEstatistica()) {
					patamares.put(patamarName, novaEstatistica);
				}
			} else {
				patamares.put(novaEstatistica.getPatamar(), novaEstatistica);
			}
		} else {
			Map<String, Estatistica> patamares = new HashMap<String, Estatistica>();
			patamares.put(novaEstatistica.getPatamar(), novaEstatistica);
			this.listaBestTempos.put(novaEstatistica.getModalidade()
					.getNomeMod(), patamares);
		}

	}

	/**
	 * 
	 * @param ano
	 * @param mes
	 * @return distacia mensal percorrida
	 */
	public double getEstatisticaMensalDistancia(int ano, int mes) {
		double resultado = 0;
		for (Estatistica est : this.listaTemposMensais) {
			Calendar aux = est.getData();
			if (aux.get(Calendar.YEAR) == ano && aux.get(Calendar.MONTH) == mes) {
				resultado += est.getModalidade().getDistancia();
			}
		}
		return resultado;
	}

	/**
	 * 
	 * @param ano
	 * @return distacia anual percorrida
	 */
	public double getEstatisticaAnualDistancia(int ano) {
		double resultado = 0;
		for (Estatistica est : this.listaTemposMensais) {
			Calendar aux = est.getData();
			if (aux.get(Calendar.YEAR) == ano) {
				resultado += est.getModalidade().getDistancia();
			}
		}
		return resultado;
	}

	/**
	 * 
	 * @param ano
	 * @param mes
	 * @return tempo mensal gasto
	 */
	public double getEstatisticaMensalTempo(int ano, int mes) {
		double resultado = 0;
		for (Estatistica est : this.listaTemposMensais) {
			Calendar aux = est.getData();
			if (aux.get(Calendar.YEAR) == ano && aux.get(Calendar.MONTH) == mes) {
				resultado += est.getModalidade().getTempo();
			}
		}
		return resultado;
	}

	/**
	 * 
	 * @param ano
	 * @return tempo anual gasto
	 */
	public double getEstatisticaAnualTempo(int ano) {
		double resultado = 0;
		for (Estatistica est : this.listaTemposMensais) {
			Calendar aux = est.getData();
			if (aux.get(Calendar.YEAR) == ano) {
				resultado += est.getModalidade().getTempo();
			}
		}
		return resultado;
	}

	/**
	 * 
	 * @param ano
	 * @param mes
	 * @return calorias mensais gastas
	 */

	public double getEstatisticaMensalCalorias(int ano, int mes) {
		double resultado = 0;
		for (Estatistica est : this.listaTemposMensais) {
			Calendar aux = est.getData();
			if (aux.get(Calendar.YEAR) == ano && aux.get(Calendar.MONTH) == mes) {
				resultado += est.getModalidade().getCaloriasGastas();
			}
		}
		return resultado;
	}

	/**
	 * 
	 * @param ano
	 * @return calorias anuais gastas
	 */
	public double getEstatisticaAnualCalorias(int ano) {
		double resultado = 0;
		for (Estatistica est : this.listaTemposMensais) {
			Calendar aux = est.getData();
			if (aux.get(Calendar.YEAR) == ano) {
				resultado += est.getModalidade().getCaloriasGastas();
			}
		}
		return resultado;
	}

	/**
	 * Ultimos Eventos (lista)
	 * 
	 * @param nevents
	 * @return
	 */
	public Set<Estatistica> getLastNEvents(int nevents) {
		int i = 0;
		Set<Estatistica> aux = new TreeSet<Estatistica>(new DataComparator2());
		for (Estatistica est : this.listaTemposMensais) {
			aux.add(est.clone());
		}
		Set<Estatistica> result = new TreeSet<Estatistica>(new DataComparator());
		Iterator<Estatistica> iter = aux.iterator();
		while (iter.hasNext() && i < nevents) {

			result.add(iter.next());
			i++;
		}
		// TODO: complete with friends stats
		return result;
	}

	// nEventos de amigos
	// falta a expepcao de quando nao encontra o amigo

	public Set<Estatistica> getLastNEventsAmigos(String amigo, int i) {
		Utilizador friend = null;
		for (Utilizador est : this.listaAmigos) {
			if (est.getEmail().equals(amigo)) {
				friend = est;
			}
		}
		return friend.getLastNEvents(i);

	}

	public Estatistica getStatModalidade(String modalidade) {
		Estatistica result = null;
		for (Estatistica est : this.listaTemposMensais) {
			if (est.getModalidade().equals(modalidade)) {
				result = est.clone();
			}
		}
		return result;
	}

	public void addEstatistica(Estatistica est) throws UtilizadorException {
		if (!this.listaTemposMensais.contains(est)) {
			this.listaTemposMensais.add(est.clone());
			addBestTimeV2(est);
		} else {
			throw new UtilizadorException();
		}
	}

	public Estatistica getEstatisticaPelaData(GregorianCalendar data) {
		Estatistica res = null;
		for (Estatistica est : this.listaTemposMensais) {
			if (est.getData().equals(data)) {
				res = est.clone();
			}
		}
		return res;
	}

	public Set<Estatistica> getEstatisticasMensais() {
		return this.listaTemposMensais;
	}

	public void removeActividadePelaData(GregorianCalendar data)
			throws UtilizadorException {
		boolean flag = false;
		for (Estatistica est : this.listaTemposMensais) {
			if (est.getData().equals(data)) {
				this.listaTemposMensais.remove(est);
			}
			flag = true;
		}
		if (flag == false) {
			throw new UtilizadorException();
		}
	}

	public void removeActividadePelaEstatistica(Estatistica est)
			throws UtilizadorException {
		boolean flag = false;
		for (Estatistica e : this.listaTemposMensais) {
			if (e.equals(est)) {
				this.listaTemposMensais.remove(e);
			}
			flag = true;
		}
		if (flag == false) {
			throw new UtilizadorException();
		}
	}

	/*
	 * GETSSETS
	 */

	public String getEmail() {
		return email;
	}

	public Set<Utilizador> getListaAmigosPendentes() {
		return listaAmigosPendentes;
	}

	public void setListaAmigosPendentes(Set<Utilizador> listaAmigosPendentes) {
		this.listaAmigosPendentes = listaAmigosPendentes;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public GregorianCalendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(GregorianCalendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDesportoFavorito() {
		return desportoFavorito;
	}

	public void setDesportoFavorito(String desportoFavorito) {
		this.desportoFavorito = desportoFavorito;
	}

	public Set<Estatistica> getListaTemposMensais() {
		return listaTemposMensais;
	}

	public void setListaTemposMensais(Set<Estatistica> listaTemposMensais) {
		this.listaTemposMensais = listaTemposMensais;
	}

	public Map<String, Map<String, Estatistica>> getListaBestTempos() {
		return listaBestTempos;
	}

	public void setListaBestTempos(
			Map<String, Map<String, Estatistica>> listaBestTempos) {
		this.listaBestTempos = listaBestTempos;
	}

	public Set<Utilizador> getListaAmigos() {
		return this.listaAmigos;
	}

	public void setListaAmigos(Set<Utilizador> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

	public boolean verificaActividadePatamarListaBestTempos(String actividade,
			String patamar) {
		boolean flag = false;
		if (this.listaBestTempos.containsKey(actividade)) {
			Map<String, Estatistica> aux = this.listaBestTempos.get(actividade);
			for (String s : aux.keySet()) {
				if (s.equals(patamar)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/*
	 * CLONE(non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Utilizador clone() {
		return new Utilizador(this);
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
			Utilizador m = (Utilizador) o;
			return (this.email.equals(m.getEmail())
					&& this.psw.equals(m.getPsw())
					&& this.nome.equals(m.getNome())
					&& this.genero.equals(m.getGenero())
					&& this.dataNascimento.equals(m.getDataNascimento())
					&& this.desportoFavorito.equals(m.getDesportoFavorito())
					&& this.altura == (m.getAltura()) && this.peso == (m
						.getPeso()));
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
		s.append("\n" + "Email: " + this.email + "\n");
		// s.append("Password: " + this.psw + "\n");
		s.append("Nome: " + this.nome + "\n");
		s.append("Genero: " + this.genero + "\n");
		s.append("Altura: " + this.altura + "\n");
		s.append("Peso: " + this.peso + "\n");
		s.append("Data de Nascimento: "
				+ this.dataNascimento.getTime().toString() + "\n");
		s.append("Desporto Favorito: " + this.desportoFavorito + "\n");
		return s.toString();
	}

	@Override
	public int compareTo(Utilizador user) {
		// TODO Auto-generated method stub
		return user.getEmail().compareTo(this.getEmail());
	}

	public void actualizaListaDeAmigos(Utilizador utilizador) {
		// TODO Auto-generated method stub
		Utilizador aux = null;
		boolean found = false;
		for (Utilizador uti : this.listaAmigos) {
			if (uti.getEmail().equals(utilizador.getEmail())) {
				aux = uti;
				found = true;
			}
		}

		if (found) {
			this.listaAmigos.remove(aux);
			this.listaAmigos.add(utilizador);

		}
	}

}
