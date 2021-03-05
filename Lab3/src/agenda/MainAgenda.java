package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author Mirlla Marques Santana Alves
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" + 
						"(T)elefone Preferidos\n" + 
						"(Z)aps\n" + 
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "T":
			exibePreferidos(agenda, scanner);
			break;
		case "Z":
			exibeZaps(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		ArrayList<String> contatos = agenda.getContatos();
		for (int i = 0; i < contatos.size(); i++) {
			System.out.println(contatos.get(i));
		}
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		try {
			int posicao = scanner.nextInt();
			String contato = agenda.getContato(posicao - 1);
			System.out.println("Dados do contato:\n" + contato);
		} catch (NullPointerException e) {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
			scanner.nextLine();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
			scanner.nextLine();
		}
	}
	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda: ");
		try {
			int posicao = scanner.nextInt();
			if(posicao < 1 || posicao > 100) {
				System.out.println("POSIÇÃO INVÁLIDA!");
			} else {
				scanner.nextLine();
				System.out.print("\nNome: ");
				String nome = scanner.nextLine();
				System.out.print("\nSobrenome: ");
				String sobrenome = scanner.nextLine();
				System.out.print("\nTelefone1: ");
				String fone1 = scanner.nextLine();
				System.out.print("\nTelefone2: ");
				String fone2 = scanner.nextLine();
				System.out.print("\nTelefone3: ");
				String fone3 = scanner.nextLine();
				System.out.print("\nTelefone prioritário: ");
				String fonePrio = scanner.nextLine();
				System.out.print("\nContato whatsapp: ");
				String foneZap = scanner.nextLine();
				try {
				agenda.cadastraContato(posicao, nome, sobrenome, fone1, fone2, fone3, fonePrio, foneZap); 
				System.out.println("CADASTRO REALIZADO");
				} catch(NullPointerException e) {
					System.out.println("INFORMAÇÕES INVÁLIDAS!\npress enter");
					scanner.nextLine();
				} catch(IllegalArgumentException e) {
					System.out.println("NOME/NÚMERO ESPERADO\npress enter");
					scanner.nextLine();
				}
			}
		}catch (InputMismatchException e) {
			System.out.println("POSIÇÃO INVÁLIDA!\n");
			scanner.nextLine();
		}
	}
	private static void exibePreferidos(Agenda agenda, Scanner scanner) {
		ArrayList<String> preferidos = agenda.getPrioritarios();
		for (int i = 0; i < preferidos.size(); i++) {
			System.out.println(preferidos.get(i));
		}
	}
	private static void exibeZaps(Agenda agenda, Scanner scanner) {
		ArrayList<String> zaps = agenda.getZaps();
		for (int i = 0; i < zaps.size(); i++) {
			System.out.println(zaps.get(i));
		}
	}
	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}