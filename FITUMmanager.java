import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FITUMmanager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Utilizador> listaDeUtilizadores;
	private TreeSet<Evento> listaDeEventos;

	/**
	 * Constructor Vazio
	 */

	public FITUMmanager() {
		this(new HashMap<String, Utilizador>(), new TreeSet<Evento>(new EventoComparator()));
	}

	/**
	 * Constructor partes
	 */

	public FITUMmanager(HashMap<String, Utilizador> listaDeUtilizadores, TreeSet<Evento> listaDeEventos) {
		this.listaDeUtilizadores = listaDeUtilizadores;
		this.listaDeEventos = listaDeEventos;
		addAdminToList();
	}

	/**
	 * Constructor Copia
	 */

	public FITUMmanager(FITUMmanager x) {
		this.listaDeUtilizadores = x.getListaDeUtilizadores();
		addAdminToList();
	}

	/**
	 * 
	 */
	private void addAdminToList() {
		if (!this.listaDeUtilizadores.containsKey("admin@fitum.com")) {
			this.listaDeUtilizadores.put("admin", new Utilizador("admin@fitum.com", "admin", "admin", "Undefined", 3, 50, new GregorianCalendar(2014, 4, 17), "Jolas"));
		}
	}

	public boolean verificaUserAdmin(String uti) throws UtilizadorException {
		if (uti.equals("admin@fitum.com")) {
			return true;
		} else {
			throw new UtilizadorException(uti);
		}
	}

	public Set<Evento> getListaEvento() {
		return listaDeEventos;
	}

	/**
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean loginUser(String user, String password) throws UtilizadorException {
		return containsUser(user, password);

	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UtilizadorException
	 */
	private boolean containsUser(String username, String password) throws UtilizadorException {
		if (containsUser(username)) {
			Utilizador user = this.listaDeUtilizadores.get(username);
			if (password != null && password.equals(user.getPsw())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param username
	 * @return
	 * @throws UtilizadorException
	 */
	private boolean containsUser(String username) throws UtilizadorException {
		if (!this.listaDeUtilizadores.containsKey(username)) {
			throw new UtilizadorException();
		}

		return this.listaDeUtilizadores.containsKey(username);
	}

	/**
	 * 
	 * @param user
	 */
	public void addUtilizador(Utilizador user) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(user.getEmail())) {
			throw new UtilizadorException(user.getEmail());
		}
		this.listaDeUtilizadores.put(user.getEmail(), user.clone());
		// return user;
	}

	public void modificaUtilizador(Utilizador uti, String pass, String nome, String genero, double altura, double peso, GregorianCalendar dataNas, String desportoFav) throws UtilizadorException {
		if (!this.listaDeUtilizadores.containsKey(uti.getEmail())) {
			throw new UtilizadorException();
		}
		Utilizador utilizador = this.listaDeUtilizadores.get(uti.getEmail());
		utilizador.modificaUtilizador(pass, nome, genero, altura, peso, dataNas, desportoFav);
		// actualiza amigos
		for (Utilizador amigo : utilizador.getListaAmigos()) {
			// Utilizador utiTmp =
			// this.listaDeUtilizadores.get(amigo.getEmail());
			amigo.actualizaListaDeAmigos(utilizador);
			this.listaDeUtilizadores.put(amigo.getEmail(), amigo);
		}
		// actualiza com as novas infos
		this.listaDeUtilizadores.put(utilizador.getEmail(), utilizador);
	}

	/**
	 * Remove Utilizador da lista
	 * 
	 * @param mail
	 * @throws UtilizadorException
	 */
	public void removeUtilizador(String mail) throws UtilizadorException {
		if (!this.listaDeUtilizadores.containsKey(mail)) {
			throw new UtilizadorException(mail);
		} else {
			Utilizador aux = this.listaDeUtilizadores.get(mail);
			for (Utilizador uti : aux.getListaAmigos()) {
				uti.getListaAmigos().remove(aux);
			}
			this.listaDeUtilizadores.remove(mail);
		}
	}

	/**
	 * 
	 * @param user
	 * @param modalidade
	 * @return
	 */
	public Estatistica getModalidade(String user, String modalidade) {
		Utilizador uti = this.listaDeUtilizadores.get(user);

		return uti.getStatModalidade(modalidade);

	}

	/**
	 * 
	 * @param user
	 * @param amigo
	 * @throws UtilizadorException
	 */
	public void addFriend(String user, String amigo) throws UtilizadorException {
		Utilizador aux1 = new Utilizador();
		Utilizador aux2 = new Utilizador();

		if (this.listaDeUtilizadores.containsKey(user)) {
			aux1 = this.listaDeUtilizadores.get(user);
		}
		if (this.listaDeUtilizadores.containsKey(amigo)) {
			aux2 = this.listaDeUtilizadores.get(amigo);
		}

		aux2.addFriendListaPendente(aux1.clone());
	}

	/**
	 * 
	 * @param user
	 * @param amigo
	 * @throws UtilizadorException
	 */
	public void confirmaFriend(String user, String amigo) throws UtilizadorException {
		Utilizador aux1 = this.listaDeUtilizadores.get(user);
		Utilizador aux2 = this.listaDeUtilizadores.get(amigo);

		aux1.confirmaFriend(amigo);
		aux2.getListaAmigos().add(aux1.clone());

	}

	/**
	 * 
	 * @param user
	 * @param amigo
	 * @throws UtilizadorException
	 */
	public void removeFriend(String user, String amigo) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(user) && this.listaDeUtilizadores.containsKey(amigo)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			Utilizador aux1 = this.listaDeUtilizadores.get(amigo);

			for (Utilizador uti : aux.getListaAmigos()) {
				if (uti.getEmail().equals(amigo)) {
					aux.getListaAmigos().remove(uti);
				}
			}
			for (Utilizador uti : aux1.getListaAmigos()) {
				if (uti.getEmail().equals(user)) {
					aux1.getListaAmigos().remove(uti);
				}
			}

		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public Set<Utilizador> getListaDeAmigos(String user) {
		if (this.listaDeUtilizadores.containsKey(user)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			return aux.getListaAmigos();
		}
		return null;

	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public Set<Utilizador> getListaAmigosPendentes(String user) {
		if (this.listaDeUtilizadores.containsKey(user)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			return aux.getListaAmigosPendentes();
		}
		return null;
	}

	/**
	 * getListaBestTempos
	 * 
	 * @param uti
	 * @return
	 * @throws UtilizadorException
	 */
	public Map<String, Map<String, Estatistica>> getListaBestTempos(Utilizador uti) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(uti)) {
			Utilizador aux = this.listaDeUtilizadores.get(uti);
			return aux.getListaBestTempos();
		}
		return null;
	}

	public Set<Estatistica> getListaActividadesDeUmAmigo(String userloged, String amigologin, int n) throws UtilizadorException {
		Set<Estatistica> resultado = new TreeSet<Estatistica>(new DataComparator2());
		if (this.listaDeUtilizadores.containsKey(userloged) && this.listaDeUtilizadores.containsKey(amigologin)) {
			Utilizador aux1 = this.listaDeUtilizadores.get(userloged);
			Utilizador aux2 = this.listaDeUtilizadores.get(amigologin);
			if (aux1.getListaAmigos().contains(aux2)) {
				resultado = aux2.getLastNEvents(n);
			}
			return resultado;
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param user
	 * @param m
	 * @param patamar
	 * @param data
	 * @throws UtilizadorException
	 */

	public void addActividadeAUser(String user, Actividade m, String patamar, GregorianCalendar data) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(user)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			// calcula calorias
			m.caloriasGastas(aux);
			aux.addEstatistica(new Estatistica(m, data, patamar));
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * // remove activade a user dado como parametro e estatistica tb dada
	 * 
	 * @param user
	 * @param est
	 * @throws UtilizadorException
	 */
	public void removeActividadeDeUser(String user, Estatistica est) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(user)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			aux.removeActividadePelaEstatistica(est);
		} else {
			throw new UtilizadorException();
		}
	}

	public void removeActividadeDeUserPelaData(String userlogin, GregorianCalendar data) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			aux.removeActividadePelaData(data);
		} else {
			throw new UtilizadorException();
		}
	}

	public Set<Estatistica> getListaDasUltimasXActividades(String userlogin, int n) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getLastNEvents(n);
		} else {
			throw new UtilizadorException();
		}
	}

	public Set<Estatistica> getListaDasUltimasXActividadesAmigo(String userlogin, String amigo, int n) throws UtilizadorException {
		Utilizador aux = new Utilizador();
		Set<Estatistica> aux1 = new TreeSet<Estatistica>(new DataComparator());
		if (this.listaDeUtilizadores.containsKey(amigo)) {
			aux = this.listaDeUtilizadores.get(userlogin);
			for (Utilizador uti : aux.getListaAmigos()) {
				if (uti.getEmail().equals(amigo)) {
					Utilizador aux3 = this.listaDeUtilizadores.get(amigo);
					aux1 = aux3.getLastNEvents(n);
				}
			}// else {throw new UtilizadorException();}}
			return aux1;
		} else {
			throw new UtilizadorException(amigo);
		}

	}

	/**
	 * Get lista de actividades mensais
	 * 
	 * @param user
	 * @return
	 * @throws UtilizadorException
	 */
	public Set<Estatistica> getListaDeActividadesMensais(String user) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(user)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			return aux.getEstatisticasMensais();
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param userlogin
	 * @param ano
	 * @param mes
	 * @return distancia mensal percorrida pelo utilizador
	 * @throws UtilizadorException
	 */
	public double getEstatisticaMensalDistanciaUser(String userlogin, int ano, int mes) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getEstatisticaMensalDistancia(ano, mes);
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param userlogin
	 * @param ano
	 * @return distancia anual percorrida pelo utilizador
	 * @throws UtilizadorException
	 */

	public double getEstatisticaAnualDistanciaUser(String userlogin, int ano) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getEstatisticaAnualDistancia(ano);
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param userlogin
	 * @param ano
	 * @param mes
	 * @return estatistica tempo mensal gasto pelo utilizador
	 * @throws UtilizadorException
	 */
	public double getEstatisticaMensalTempoUser(String userlogin, int ano, int mes) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getEstatisticaMensalTempo(ano, mes);
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param userlogin
	 * @param ano
	 * @return estatistica do tempo anual gasto pelo utilizador
	 * @throws UtilizadorException
	 */

	public double getEstatisticaAnualTempoUser(String userlogin, int ano) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getEstatisticaAnualTempo(ano);
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param userlogin
	 * @param ano
	 * @param mes
	 * @return estatistica mensal de calorias gastas
	 * @throws UtilizadorException
	 */

	public double getEstatisticaMensalCaloriasUser(String userlogin, int ano, int mes) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getEstatisticaMensalCalorias(ano, mes);
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param userlogin
	 * @param ano
	 * @return estatistica anual de calorias gasta pelo utilizador
	 * @throws UtilizadorException
	 */
	public double getEstatisticaAnualCaloriasUser(String userlogin, int ano) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(userlogin)) {
			Utilizador aux = this.listaDeUtilizadores.get(userlogin);
			return aux.getEstatisticaAnualCalorias(ano);
		} else {
			throw new UtilizadorException();
		}
	}

	// get Map bestTempos
	/**
	 * 
	 * @param user
	 * @return
	 * @throws UtilizadorException
	 */
	public Map<String, Map<String, Estatistica>> getListaDeBestTimes(String user) throws UtilizadorException {
		if (this.listaDeUtilizadores.containsKey(user)) {
			Utilizador aux = this.listaDeUtilizadores.get(user);
			return aux.getListaBestTempos();
		} else {
			throw new UtilizadorException();
		}
	}

	/**
	 * 
	 * @param uti
	 * @param ev
	 */
	public void addEvento(Utilizador uti, Evento ev) throws EventoException {
		if (uti.getEmail().equals("admin@fitum.com")) {
			this.listaDeEventos.add(ev.clone());
		} else {
			throw new EventoException(ev.getNome());
		}
	}

	public void addModalidadePatamar(Utilizador user, String evento, String modalidade, String patamar) throws EventoException, UtilizadorException {
		Evento aux = null;
		if (user.getEmail().equals("admin@fitum.com")) {
			for (Evento ev : this.listaDeEventos) {
				if (ev.getNome().equals(evento)) {
					aux = ev;
				} else {
					throw new EventoException(evento);
				}
			}
			aux.insereModalidade(modalidade, patamar);
		} else {
			throw new UtilizadorException(user.getEmail());
		}
	}

	/*
	 * 
	 * SETS AND GETS
	 */
	/**
	 * 
	 * @param login
	 * @return
	 */
	public Utilizador getUtilizador(String login) {
		Utilizador agora = new Utilizador();
		for (String s : this.listaDeUtilizadores.keySet()) {
			if (s.equals(login)) {
				agora = listaDeUtilizadores.get(s);
			}

		}
		return agora;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Utilizador> getListaDeUtilizadores() {
		return listaDeUtilizadores;
	}

	public void registaUtiListaInscritos(Utilizador user, String evento, String actividade, String patamar) {
		for (Evento ev : this.listaDeEventos) {
			if (ev.getNome().equals(evento)) {
				Evento aux = ev;
				if (aux.getModalidadePatamar().containsKey(actividade) && user.verificaActividadePatamarListaBestTempos(actividade, patamar)) {
					aux.addListaDeInscritos(actividade, user, patamar);
				}

			}
		}
	}

	/**
	 * 
	 * @param listaDeUtilizadores
	 */
	public void setListaDeUtilizadores(HashMap<String, Utilizador> listaDeUtilizadores) {
		this.listaDeUtilizadores = listaDeUtilizadores;
	}

	/**
	 * Clone
	 */

	@Override
	public FITUMmanager clone() {
		return new FITUMmanager(this);

	}

	/**
	 * Equals
	 */
	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		} else {
			FITUMmanager m = (FITUMmanager) o;
			return (this.listaDeUtilizadores.equals(m.getListaDeUtilizadores()));
		}
	}

	/**
	 * ToString
	 */

	public String UtilizadorestoString() {
		StringBuilder s = new StringBuilder();
		for (Utilizador uti : this.listaDeUtilizadores.values()) {
			s.append("Lista De Utilizadores: " + uti.toString() + "\n");
		}

		return s.toString();
	}

	public String EventostoString() {
		StringBuilder s = new StringBuilder();
		for (Evento ev : this.listaDeEventos) {
			s.append("Lista De Eventos: " + ev.toString() + "\n");
		}

		return s.toString();
	}

	/**
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void writeBinary(ObjectOutputStream out) throws IOException {
		try {
			out.writeObject(this);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw e;
		}

		out.flush();
	}

	/**
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void writeBinary(String path) throws IOException {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(this);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Grava em txt os dados
	 * 
	 * @param fich
	 * @throws IOException
	 */

	public void gravaTxt(String fich) throws IOException {
		PrintWriter pw = new PrintWriter(fich);
		pw.print(this);
		pw.flush();
		pw.close();
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public FITUMmanager readGestorInBinary(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		FITUMmanager fitum;

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
		fitum = (FITUMmanager) in.readObject();
		this.listaDeUtilizadores = fitum.listaDeUtilizadores;
		this.listaDeEventos = fitum.listaDeEventos;
		in.close();

		return fitum;
	}
}
