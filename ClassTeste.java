import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.GregorianCalendar;

//import java.util.GregorianCalendar;
//import java.util.TreeSet;

public class ClassTeste implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) throws UtilizadorException,
			IOException, ClassNotFoundException, EventoException {

		FITUMmanager fit = new FITUMmanager();
		Utilizador userLog = new Utilizador();
		/**
		 * String user = Console.readString("User:"); String pass =
		 * Console.readString("Pass:"); if(!fit.loginUser(user, pass)){
		 * System.exit(0); }
		 */

		Utilizador mp0;
		Utilizador mp1;
		Utilizador mp2;
		Utilizador mp3;
		Natacao nat;
		Atletismo atl;
		Atletismo atl1;
		Atletismo atl2;
		Atletismo atl3;
		ArtesMarciais art;
		Futebol fut;

		mp0 = new Utilizador("1", "1", "ricardo", "male", 1.80, 70.9,
				new GregorianCalendar(1981, 03, 02), "natacao");
		mp1 = new Utilizador("ricardo.rouco@gmail.com", "12345", "ricardo",
				"male", 1.80, 70.9, new GregorianCalendar(1981, 03, 02),
				"natacao");
		mp2 = new Utilizador("luiscamelo@gmail.com", "qwerty", "Camelo",
				"male", 1.73, 68.2, new GregorianCalendar(1984, 04, 24),
				"futebol");
		mp3 = new Utilizador("pires@gmail.com", "12345", "pires", "male", 1.92,
				170.9, new GregorianCalendar(1982, 03, 02), "atletismo");
		nat = new Natacao(1, 1000, true, "crowl", false);
		atl = new Atletismo(5, 100, true, "livre", false, 250);
		atl1 = new Atletismo(2, 100, true, "100m", false, 250);
		atl2 = new Atletismo(3, 100, true, "100m", false, 250);
		atl3 = new Atletismo(49, 100, true, "100m", false, 250);
		art = new ArtesMarciais(3, true, "katate", "negro", false);
		fut = new Futebol(4, 1000, false, true, "Porto", false);
		fit.addUtilizador(mp1);
		fit.addUtilizador(mp2);
		fit.addUtilizador(mp0);
		fit.addUtilizador(mp3);

		fit.addFriend("luiscamelo@gmail.com", "pires@gmail.com");
		fit.confirmaFriend("pires@gmail.com", "luiscamelo@gmail.com");
		fit.addActividadeAUser("1", nat, "livre", new GregorianCalendar(2013,
				03, 01));
		fit.addActividadeAUser("1", nat, "livre", new GregorianCalendar(2012,
				02, 31));
		fit.addActividadeAUser("1", atl, "livre", new GregorianCalendar(2012,
				03, 01));
		fit.addActividadeAUser("1", atl1, "100m", new GregorianCalendar(2012,
				03, 04));
		fit.addActividadeAUser("1", atl2, "100m", new GregorianCalendar(2012,
				04, 04));
		fit.addActividadeAUser("1", atl3, "1000m", new GregorianCalendar(2012,
				05, 04));
		fit.addActividadeAUser("1", art, "karate", new GregorianCalendar(2011,
				03, 02));

		fit.addFriend("luiscamelo@gmail.com", "ricardo.rouco@gmail.com");
		System.out.println("lista de amigos pendentes"
				+ fit.getListaAmigosPendentes("luiscamelo@gmail.com") + "\n");
		System.out
				.println("lista de amigos pendentes"
						+ fit.getListaAmigosPendentes("ricardo.rouco@gmail.com")
						+ "\n");
		System.out.println("lista de melhores tempos"
				+ fit.getListaDeBestTimes("1") + "\n");

		System.out.println("lista de actividades: \n"
				+ fit.getListaDeActividadesMensais("luiscamelo@gmail.com")
				+ "\n");
		System.out.println("lista de amigos: "
				+ fit.getListaDeAmigos("luiscamelo@gmail.com") + "\n");
		// fit.removeFriend("luiscamelo@gmail.com", amigo);
		System.out.println("lista de amigos: "
				+ fit.getListaDeAmigos("luiscamelo@gmail.com") + "\n");
		System.out.println("utilizadores na lista de utilizadores: \n"
				+ fit.toString() + "\n"); // System.out.println("Resultado;\n"
											// +
		// mp.toString()); System.out.println("Natacao:\n"+ nat.toString());
		System.out.println("Atletismo:\n" + atl.toString());
		System.out.println("ArtesMarciais:\n" + art.toString());
		System.out.println("Futebol:\n" + fut.toString());

		// -----------------------------------------------MENUS-------------------------------------------------------------------

		int option, option1, option2, option3, option4, option5, option6, option7, option8, option9;
		int op, op1, op2, op3, op4, op5, op6, op7, op8, op9, op10, op11, op13;
		String[] menuLog = { "Login", "LoginUser", "Registar",
				"Carregar Ficheiro" };
		String[] mainmenu = { "FITUM", "Utilizadores", "Modalidades",
				"Eventos", "Estatisticas", "Controlo Ficheiro",/*
																 * "Consultas" ,
																 */
				"Autores POO" };
		String[] menuUtil = { "Gestor Utilizadores", "Modificar", "Listar",
				"Listar amigos", "Remover", "Add Amigo", "Confirma amigo",
				"Lista amigos pendentes", "Remover Amigos" };

		String[] menuMod = { "Gestor de Actividades", "Adicionar Actividades",
				"Listar Actividades", "Remover Actividades",
				"Lista das ultimas n actividades",
				"Lista das ultimas n actividade de um amigo" };
		String[] menuAct = { "Actividades", "Artes Marciais", "Atletismo",
				"Futebol", "Natacao" };
		String[] menuEvent = { "Eventos", "Adicionar Eventos",
				"Registar Utilizador no Evento", "Listar Registo",
				"Modificar Eventos", "Listar Eventos", "Remover Eventos",
				"Adivionar patamar a evento", };

		String[] menuEstat = { "Estatisticas",
				"Ver Estatisticas Melhores tempos",
				"Estatistica mensal tempo gasto",
				"Estatistica mensal distancia gasta",
				"Estatistica mensal calorias gastas",
				"Estatistica anual do tempo gasto",
				"Estatistica anual distancia gasta",
				"Estatistica anual calorias gastas" };

		String[] menuFicheiro = { "Controlo de ficheiro", "Grava Ficheiro" };

		String[] menuautorIV = { "Autores POO", "Ricardo D.S.Rouco 39502",
				"Luis M. C. Martins 40616 " };

		do {
			option9 = Console.menu(menuLog);
			switch (option9) {
			case 1: {
				System.out.println("Introduza email: \n");
				String log = Console.readString();
				System.out.println("Introduza password: \n");
				String password = Console.readString();
				try {
					if (fit.loginUser(log, password)) {
						userLog = fit.getUtilizador(log);

						// }
						do {
							option = Console.menu(mainmenu); // menu principal
							switch (option) {
							case 1: // link para Utilizadores
							{
								do {
									option1 = Console.menu(menuUtil); // menu
																		// Utilizadores
									switch (option1) {

									case 1: //
									{
										// modificar Utilizador

										try {

											System.out
													.println("Introduza Password: ");
											String pws = Console.readString();
											System.out
													.println("Introduza nome de Utilizador: ");
											String nomeUtil = Console
													.readString();
											System.out
													.println("Introduza Genero (Masculuno ou Femenino): ");
											String genero = Console
													.readString();
											System.out
													.println("Introduza Altura: ");
											double altura = Console
													.readDouble("");
											System.out
													.println("Introdura peso: ");
											double peso = Console
													.readDouble("");
											System.out
													.println("Introduza data de nascimento(yyyy,mm,dd)");
											GregorianCalendar data = Console
													.readGreg("");
											System.out
													.println("Introduza desporto favorito: ");
											String despfav = Console
													.readString();
											fit.modificaUtilizador(userLog,
													pws, nomeUtil, genero,
													altura, peso, data, despfav);

											System.out
													.println("O Utilizador foi modificado com sucesso!\n");
											System.out
													.println("Digite qualquer tecla para continuar: ");
											@SuppressWarnings("unused")
											String s = Console.readString();
										} catch (UtilizadorException s) {
											System.out.println("O Utilizador: "
													+ s.getMessage()
													+ " nao existe!\n");
										}

									}
										break;
									case 2: {

										System.out
												.println("utilizadores na lista de utilizadores: \n"
														+ fit.UtilizadorestoString()
														+ "\n");

										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();

									}
										break;
									case 3: {
										System.out.println(userLog
												.getListaAmigos());
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;// fim do case 3

									case 4: {
										// Remover Utilizador
										try {
											fit.verificaUserAdmin(userLog
													.getEmail());
											System.out
													.println("Insira o utilizador que deseja Remover");
											String verifica = Console
													.readString();

											try {
												fit.getListaDeUtilizadores()
														.containsKey(verifica);
												fit.removeUtilizador(verifica);
												System.out
														.println("Utilizador: "
																+ verifica
																+ " removido com sucesso!");
											} catch (UtilizadorException s) {
												System.out
														.println("O utilizador: "
																+ s.getMessage()
																+ " nao existe");
											}
										} catch (UtilizadorException s) {
											System.out
													.println("O utilizador: "
															+ s.getMessage()
															+ " nao tem premissoes para remover utilizadores.");

										}
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;// FIM DO CASE 4

									case 5: {
										System.out
												.println("Digite o email do  seu amigo");
										String amigo = Console.readString();
										fit.addFriend(userLog.getEmail(), amigo);
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;// fim do case 5

									case 6: {
										// confirma amigo
										System.out
												.println("Digite o email do amigo que deseja confirmar");
										String amigo = Console.readString();
										fit.confirmaFriend(userLog.getEmail(),
												amigo);
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;// fim do case 6

									case 7: {
										// lista amigos pendentes
										System.out
												.println(fit
														.getListaAmigosPendentes(userLog
																.getEmail()));
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;// fim do case 7
									case 8: {
										try {
											System.out
													.println("Digite o email do amigo que quer remover: ");
											String amigo = Console.readString();
											fit.removeFriend(
													userLog.getEmail(), amigo);
											fit.removeFriend(amigo,
													userLog.getEmail());
										} catch (UtilizadorException s) {
											System.out
													.println("O Utilizador: "
															+ s.getMessage()
															+ " nao existe na lista de amigos!\n");
										}
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;

									}
									break;// fim do switch

								} while (option1 != 0);

							}
								break;// Final DO CASE Global1

							case 2: {
								// SUBMENU Actividades:

								do {
									option2 = Console.menu(menuMod);
									switch (option2) {

									case 1: {
										do {
											op = Console.menu(menuAct);
											switch (op) {
											case 1: {
												boolean olim = false;
												boolean equip = false;

												System.out
														.println("Digite o tempo que realizou: ");
												double tempo = Console
														.readDouble("");
												try {
													System.out
															.println("A modalidade e Olimpica?");
													olim = Console.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												System.out
														.println("Digite a tecnica utilizada: \n");
												String nomM = Console
														.readString();
												try {
													System.out
															.println("A modalidade e de equipa?");
													equip = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												System.out
														.println("Tipo de cinturao: \n");
												String cint = Console
														.readString();
												System.out
														.println("Introduza data de realizacao(yyyy,mm,dd,hora,minuto)");
												GregorianCalendar data = Console
														.readGregHM("");
												System.out
														.println("Digite o patamar: \n");

												String patamar = Console
														.readString();

												Actividade m = new ArtesMarciais(
														tempo, olim, nomM,
														cint, equip);
												fit.addActividadeAUser(
														userLog.getEmail(), m,
														patamar, data);
												System.out
														.println(fit
																.getListaDeActividadesMensais(userLog
																		.getEmail()));

											}
												break;// fim do case de Artes
														// marciais
											case 2: {

												boolean olim = false;
												boolean equip = false;

												System.out
														.println("Digite o tempo que realizou: ");
												double tempo = Console
														.readDouble("");
												System.out
														.println("Digite a distancia que percorreu: ");
												double dist = Console
														.readDouble("");
												System.out
														.println("Tecnica que realizou: ");
												String tec = Console
														.readString();
												try {
													System.out
															.println("A modalidade e Olimpica?");
													olim = Console.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}

												try {
													System.out
															.println("A modalidade e de equipa?");
													equip = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												System.out
														.println("Ganho de Altimetria: \n");
												double alty = Console
														.readDouble("");
												System.out
														.println("Introduza data de realizacao(yyyy,mm,dd,hora,minuto)");
												GregorianCalendar data = Console
														.readGregHM("");
												System.out
														.println("Digite o patamar: \n");

												String patamar = Console
														.readString();

												Actividade m = new Atletismo(
														tempo, dist, olim, tec,
														equip, alty);
												fit.addActividadeAUser(
														userLog.getEmail(), m,
														patamar, data);
												System.out
														.println(fit
																.getListaDeActividadesMensais(userLog
																		.getEmail()));

											}
												break;// fim do case de
														// Atletismo
											case 3: {
												boolean olimpica = false;
												boolean equipa = false;
												boolean profissional = false;

												System.out
														.println("Digite o tempo que realizou: ");
												double tempo = Console
														.readDouble("");
												System.out
														.println("Digite a distancia que percorreu: ");
												double distancia = Console
														.readDouble("");
												System.out.println("Clube: ");
												String clube = Console
														.readString();
												try {
													System.out
															.println("A modalidade e Olimpica?");
													olimpica = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												try {
													System.out
															.println("A modalidade e de equipa?");
													equipa = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												try {
													System.out
															.println("e profissional?");
													profissional = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}

												System.out
														.println("Introduza data de realizacao(yyyy,mm,dd,hora,minuto)");
												GregorianCalendar data = Console
														.readGregHM("");
												System.out
														.println("Digite o patamar: \n");

												String patamar = Console
														.readString();

												Actividade m = new Futebol(
														tempo, distancia,
														olimpica, equipa,
														clube, profissional);
												fit.addActividadeAUser(
														userLog.getEmail(), m,
														patamar, data);
												System.out
														.println(fit
																.getListaDeActividadesMensais(userLog
																		.getEmail()));

											}
												break;// fim do case Futebol
											case 4: {
												boolean olimpica = false;
												boolean equipa = false;

												System.out
														.println("Digite o tempo que realizou: ");
												double tempo = Console
														.readDouble("");
												System.out
														.println("Digite a distancia que percorreu: ");
												double distancia = Console
														.readDouble("");

												try {
													System.out
															.println("A modalidade e Olimpica?");
													olimpica = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												try {
													System.out
															.println("A modalidade e de equipa?");
													equipa = Console
															.readBool("");
												} catch (IOException s) {
													System.out
															.println("Digite sim ou nao\n");
													@SuppressWarnings("unused")
													String ab = Console
															.readString();
													System.out
															.println("Digite qualquer tecla para continuar: ");
													@SuppressWarnings("unused")
													String a = Console
															.readString();
												}
												System.out
														.println("Tecnica que realizou: ");
												String tecnica = Console
														.readString();

												System.out
														.println("Introduza data de realizacao(yyyy,mm,dd,hora,minuto)");
												GregorianCalendar data = Console
														.readGregHM("");
												System.out
														.println("Digite o patamar: \n");

												String patamar = Console
														.readString();

												Actividade m = new Natacao(
														tempo, distancia,
														olimpica, tecnica,
														equipa);
												fit.addActividadeAUser(
														userLog.getEmail(), m,
														patamar, data);
												System.out
														.println(fit
																.getListaDeActividadesMensais(userLog
																		.getEmail()));

											}
												break;// fim do case Natacao
											}
										} while (op != 0);

									}
										break;// FIM DO CASE 1

									case 3: {
										System.out
												.println("Introduza data da actividade que deseja remover (yyyy,mm,dd)");
										GregorianCalendar data = Console
												.readGreg("");
										fit.removeActividadeDeUserPelaData(
												userLog.getEmail(), data);

									}
										break;

									case 2: {
										// listar actividades

										System.out
												.println(fit
														.getListaDeActividadesMensais(userLog
																.getEmail()));
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();

									}
										break;
									case 4: {
										System.out
												.println("Digite o numero de actividades recentes que deseja ver: \n");
										int valor = Console.readInt("");
										System.out
												.println(fit
														.getListaDasUltimasXActividades(
																userLog.getEmail(),
																valor));
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 5: {
										try {
											System.out
													.println("Digite o login do amigo que deseja consultar: \n");
											String amigo = Console.readString();
											System.out
													.println("Digite o numero de actividades que deseja consultar: \n");
											int valor = Console.readInt("");
											System.out
													.println(fit
															.getListaDasUltimasXActividadesAmigo(
																	userLog.getEmail(),
																	amigo,
																	valor));
										} catch (UtilizadorException s) {
											System.out.println("O amigo "
													+ s.getMessage()
													+ " nao existe\n");
										}
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
									}
									break;

								} while (option2 != 0);

							}
								break;// FIM DO CASE GLOBAL 2
							case 3: {// teste para feicho
								do {
									option3 = Console.menu(menuEvent);
									switch (option3) {

									case 1: {

										try {
											fit.verificaUserAdmin(userLog
													.getEmail());

											System.out
													.println("Digite o nome do evento: \n");

											String nome = Console.readString();

											System.out
													.println("Digite o numero maximo de incricoes: \n");

											int maxInscricoes = Console
													.readInt("");

											System.out
													.println("Digite a data de inicio do evento(aaaa,mm,dd)\n");

											GregorianCalendar dataInicio = Console
													.readGreg("");

											System.out
													.println("Digite a data final para incricoes  (aaaa,mm,dd)\n");

											GregorianCalendar dataFimInscricoes = Console
													.readGreg("");
											Evento aux = new Evento(nome,
													maxInscricoes, dataInicio,
													dataFimInscricoes);
											fit.addEvento(userLog, aux);

										} catch (UtilizadorException s) {
											System.out
													.println("O evento ja existe: \n");
										} catch (EventoException s) {
											System.out.println("O evento"
													+ s.getMessage()
													+ " ja existe");
										}

									}

										break;
									case 2: {
										// Registar Utilizador no evento (String
										// evento, String actividade, String
										// patamar)

										System.out
												.println("Digite o Nome do evento: \n");
										String evento = Console.readString();
										System.out
												.println("Digite o Nome da Actividade: \n");
										String actividade = Console
												.readString();
										System.out
												.println("Digite o patamar: \n");
										String patamar = Console.readString();
										fit.registaUtiListaInscritos(userLog,
												evento, actividade, patamar);
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();

									}
										break;
									case 3: {
										// Listar Registo
										System.out
												.println("Digite o nome do evento ");
										String evento = Console.readString();
										System.out
												.println("Digite o nome da Modalidade ");
										String modalidade = Console
												.readString();
										System.out
												.println("Digite o nome do Patamar ");
										String patamar = Console.readString();
										System.out.println(/*
															 * funcao lista
															 * incritos por
															 * evento
															 * ,modalidade e
															 * patamar
															 */);
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();

										//
									}
										break;

									case 4: {

										// Modifica Evento

									}

										break;

									case 5: {

										System.out
												.println("Listar todos os eventos: \n"
														+ fit.EventostoString()
														+ "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();

									}

										break;

									case 6: {

										// remover Evento

										System.out
												.println("Digite o nome do evento que deseja remover: \n");

										String nome = Console.readString();

									}

										break;
									case 7: {
										try {
											// adicionar patamares a um evento
											System.out
													.println("Insira o nome do evento: ");
											String nomeEvento = Console
													.readString();
											System.out
													.println("Insira o nome da modalidade: ");
											String nomeModalidade = Console
													.readString();
											System.out
													.println("Insira o nome do patamar: ");
											String nomePatamar = Console
													.readString();
											fit.addModalidadePatamar(userLog,
													nomeEvento, nomeModalidade,
													nomePatamar);
										} catch (UtilizadorException s) {
											System.out
													.println("O evento nao existe:\n");
										} catch (EventoException s) {
											System.out.println("O evento "
													+ s.getMessage()
													+ " nao existe\n");
										}
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;

									}
									break;
								} while (option3 != 0);

							}
								break; // FIM DO CASE GLOBAL 4

							case 4: {
								do {
									option4 = Console.menu(menuEstat);
									switch (option4) {

									case 1: {
										// Ver Estatisticas Melhores tempos
										System.out.println(fit
												.getListaDeBestTimes(userLog
														.getEmail()));
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 2: {
										// ver estatistica mensal tempo gasto
										// (recebe mes e ano)
										System.out.println("Digite o ano: ");
										int ano = Console.readInt("");
										System.out.println("Digite o mes: ");
										int mes = Console.readInt("");
										System.out
												.println("Tempo total gasto: "
														+ fit.getEstatisticaMensalTempoUser(
																userLog.getEmail(),
																ano, mes)
														+ "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 3: {
										// verver estatistica mensal ditancia
										// (recebe mes e ano)
										System.out.println("Digite o ano: ");
										int ano = Console.readInt("");
										System.out.println("Digite o mes: ");
										int mes = Console.readInt("");
										System.out
												.println("Distancia percorrida: "
														+ fit.getEstatisticaMensalDistanciaUser(
																userLog.getEmail(),
																ano, mes)
														+ "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 4: {
										// verver estatistica mensal calorias
										// (recebe mes e ano)
										System.out.println("Digite o ano: ");
										int ano = Console.readInt("");
										System.out.println("Digite o mes: ");
										int mes = Console.readInt("");
										System.out
												.println("Calorias gastas: "
														+ fit.getEstatisticaMensalCaloriasUser(
																userLog.getEmail(),
																ano, mes)
														+ "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 5: {
										// verver estatistica anual tempo (ano)
										System.out.println("Digite o ano: ");
										int ano = Console.readInt("");

										System.out
												.println("Tempo anual gasto: "
														+ fit.getEstatisticaAnualTempoUser(
																userLog.getEmail(),
																ano) + "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 6: {
										// verver estatistica anual distancia
										// (ano)
										System.out.println("Digite o ano: ");
										int ano = Console.readInt("");

										System.out
												.println("Distancia anual percorrida: "
														+ fit.getEstatisticaAnualDistanciaUser(
																userLog.getEmail(),
																ano) + "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 7: {
										// verver estatistica anual calorias
										// (ano)
										System.out.println("Digite o ano: ");
										int ano = Console.readInt("");

										System.out
												.println("Calorias anuais gastas: "
														+ fit.getEstatisticaAnualCaloriasUser(
																userLog.getEmail(),
																ano) + "\n");
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();
									}
										break;
									case 8: {
										// COnsultar as ultimas n actividades
										// dos amigos.
										System.out
												.println("Digite o email do amigo: ");
										String var = Console.readString();
										System.out
												.println("Digite o numero de actividades que deseja ver: ");
										int var2 = Console.readInt("");
										System.out.println(fit
												.getListaActividadesDeUmAmigo(
														userLog.getEmail(),
														var, var2));
										System.out
												.println("Digite qualquer tecla para continuar: ");
										@SuppressWarnings("unused")
										String s = Console.readString();

									}
										break;

									}
									break;
								} while (option4 != 0);

							}
								break; // FIM DO CASE GLOBAL 4

							case 5: {
								do {
									option5 = Console.menu(menuFicheiro);
									switch (option5) {

									case 1: {

										System.out
												.println("digite o ficheiro que deseja gravar: ");
										String fich = Console.readString();
										fit.writeBinary(fich);

									}
										break;
									}
									break;
								} while (option5 != 0);

							}
								break; // FIM DO CASE GLOBAL 5

							case 6: //
							{
								do {
									option6 = Console.menu(menuautorIV);
								} while (option6 != 0);

							}
								break;

							}

						} while (option != 0);

					}
				} catch (UtilizadorException s) {
					System.out.println("O Utilizador nao existe: \n");
					System.out
							.println("Digite qualquer tecla para continuar: ");
					@SuppressWarnings("unused")
					String a = Console.readString();
				}
			}
				break;
			case 2:
			// Pronta
			{ // adicionar Utilizador

				try {

					System.out
							.println("Introduza o nome E-mail do Utilizador: ");
					String mail = Console.readString();
					System.out.println("Introduza Password: ");
					String pws = Console.readString();
					System.out.println("Introduza nome de Utilizador: ");
					String nomeUtil = Console.readString();
					System.out
							.println("Introduza Genero (Masculuno ou Femenino): ");
					String genero = Console.readString();
					System.out.println("Introduza Altura: ");
					double altura = Console.readDouble("");
					System.out.println("Introdura peso: ");
					double peso = Console.readDouble("");
					System.out
							.println("Introduza data de nascimento(yyyy,mm,dd)");
					GregorianCalendar data = Console.readGreg("");
					System.out.println("Introduza desporto favorito: ");
					String despfav = Console.readString();

					Utilizador utiTMP = new Utilizador(mail, pws, nomeUtil,
							genero, altura, peso, data, despfav);
					fit.addUtilizador(utiTMP);
					System.out
							.println("O Utilizador foi adicionado com sucesso!\n");
					System.out
							.println("Digite qualquer tecla para continuar: ");
					@SuppressWarnings("unused")
					String s = Console.readString();

				} catch (UtilizadorException s) {
					System.out.println("O Utilizador: " + s.getMessage()
							+ " ja existe!\n");
					System.out
							.println("Digite qualquer tecla para continuar: ");
					@SuppressWarnings("unused")
					String a = Console.readString();
				}
			}
				break; // fecha o case de adicionar Utilizador
			case 3: {
				try {
					System.out.println("Introduza o nome do ficheiro a ler: ");
					String fich = Console.readString();
					fit = fit.readGestorInBinary(fich);
				} catch (FileNotFoundException s) {
					System.out.println("O Nome do ficheiro nao existe: "
							+ s.getMessage() + "\n");
				} catch (IOException s) {
					System.out.println("O Nome do ficheiro nao existe: "
							+ s.getMessage() + "\n");
				} catch (ClassNotFoundException s) {
					System.out.println("O Nome do ficheiro nao existe: "
							+ s.getMessage() + "\n");
				}

			}
				break;

			}

			/*------------------------------Final do menu-------------------------------------------------------*/
		} while (option9 != 0);
	}
}