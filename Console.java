import java.io.IOException;
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * 
 * @author Arcannis
 * 
 */

public class Console implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	/**
	 * 
	 * @param prompt
	 */
	public static void printPrompt(String prompt) {
		System.out.print(prompt + " ");
		System.out.flush();
	}

	/**
	 * 
	 * @return
	 */

	public static String readString() {
		int ch;
		String r = "";
		boolean done = false;
		while (!done) {
			try {
				ch = System.in.read();
				if (ch < 0 || (char) ch == '\n')
					done = true;
				else
					r = r + (char) ch;
			} catch (java.io.IOException e) {
				done = true;
			}
		}
		return r.trim(); // Para revolver problema em Windows.
	}

	/**
	 * 
	 * @param prompt
	 * @return
	 */

	public static String readString(String prompt) {
		printPrompt(prompt);
		return readString();
	}

	/**
	 * 
	 * @return
	 */

	public static String readWord() {
		int ch;
		String r = "";
		boolean done = false;
		while (!done) {
			try {
				ch = System.in.read();
				if (ch < 0 || java.lang.Character.isWhitespace((char) ch))
					done = true;
				else
					r = r + (char) ch;
			} catch (java.io.IOException e) {
				done = true;
			}
		}
		return r;
	}

	/**
	 * 
	 * @param prompt
	 * @return
	 */

	public static int readInt(String prompt) {
		while (true) {
			printPrompt(prompt);
			try {
				return Integer.valueOf(readString().trim()).intValue();
			} catch (NumberFormatException e) {
				System.out.println("Nao e um inteiro. Tente outra vez!");
			}
		}
	}

	/**
	 * 
	 * @param prompt
	 * @return
	 */

	public static double readDouble(String prompt) {
		while (true) {
			printPrompt(prompt);
			try {
				return Double.valueOf(readString().trim()).doubleValue();
			} catch (NumberFormatException e) {
				System.out.println("Double nao Valido! Tente outra vez!");
			}
		}
	}

	/**
	 * 
	 * @param option
	 * @return
	 */
	public static int menu(String[] option) {
		int opcao;
		do {
			// mostrar menu
			System.out
					.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t##### "
							+ option[0] + " #####\n");
			for (int i = 1; i < option.length; i++) {
				System.out.print("\t\t" + (i));
				System.out.println(" > " + option[i]);
			}
			System.out.println("\t\t0 > Exit");

			// leitura
			opcao = Console.readInt("\n\t\t Option: ");
			if (opcao < 0 || opcao > option.length) {
				System.out.println("\tOpcao Invalida !!!");
				Console.readString("\n\tPRIMA ENTER PARA CONTINUAR\n");
			}
		} while (opcao < 0 || opcao > option.length);
		return opcao;
	}

	/**
	 * Read gregorian -le uma string e devolve um gregoria calendar
	 * 
	 * @param prompt
	 * @return
	 */
	public static GregorianCalendar readGreg(String prompt) {

		try {
			String data = Console.readString();
			String[] parts = data.split(",");
			String part1 = parts[0];
			int ano = Integer.valueOf(part1);
			String part2 = parts[1];
			int mes = Integer.valueOf(part2);
			String part3 = parts[2];
			int dia = Integer.valueOf(part3);
			if ((mes >= 1 && mes <= 12) && (dia >= 1 && dia <= 31)) {
				mes = mes - 1;

				return new GregorianCalendar(ano, mes, dia);
			}
		} catch (NumberFormatException e) {
			System.out.println("Data nao valida!!Tente outra vez\n");
		} catch (ArrayIndexOutOfBoundsException s) {
			System.out.println("Data invalida! Digite outra vez(aaaa,mm,dd)");
		}
		return readGreg(prompt);

	}

	/**
	 * 
	 * @param prompt
	 * @return
	 */
	public static GregorianCalendar readGregHM(String prompt) {
		try {
			String data = Console.readString();
			String[] parts = data.split(",");
			String part1 = parts[0];
			int ano = Integer.valueOf(part1);
			String part2 = parts[1];
			int mes = Integer.valueOf(part2);
			String part3 = parts[2];
			int dia = Integer.valueOf(part3);
			String part4 = parts[3];
			int hora = Integer.valueOf(part4);
			String part5 = parts[4];
			int minuto = Integer.valueOf(part5);
			if (((mes >= 1 && mes <= 12) && (dia >= 1 && dia < 31))
					&& ((hora >= 0 && hora <= 23))
					&& ((minuto >= 0 && minuto <= 59))) {
				mes = mes - 1;

				return new GregorianCalendar(ano, mes, dia, hora, minuto);
			}

		} catch (NumberFormatException e) {
			System.out.println("Data ou hora invalida! Tente outtra vez");
		} catch (ArrayIndexOutOfBoundsException s) {
			System.out
					.println("Data invalida! Digite outra vez(aaaa,mm,dd,hh,mim)");
		}
		return readGregHM(prompt);
	}

	/**
	 * Read sim ou nao do prompt
	 * 
	 * @param prompt
	 * @return
	 * @throws IOException
	 */

	public static boolean readBool(String prompt) throws IOException {
		@SuppressWarnings("unused")
		boolean flag = false;
		String s = Console.readString();
		if (s.equals("sim"))
			return flag = true;
		if (s.equals("nao"))
			return flag = false;

		else {
			throw new IOException();
		}
	}

}