package ControleAluno;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface com menu de texto para manipular o Controle de Alunos
 * @author Mirlla Marques Santana Alves - 119210401
 */
public class MenuMain {
	
	public static void main(String[] args) {
		Turma turma = new Turma();
		Scanner scanner = new Scanner(System.in);
		String opcao = "";
		while (true) {
			opcao = menu(scanner);
			comando(opcao, turma, scanner);
		}
}
	/**
	* Exibe o menu e o input do usuário
	* @param scanner Para captura da opção do usuário.
	* @return O comando escolhido.
	*/
	private static String menu(Scanner scanner) {
		System.out.print(
				"\n---\nMENU\n" + 
						"(C)adastrar Aluno\n" + 
						"(E)xibir Aluno\n" + 
						"(N)ovo Grupo\n" + 
						"(A)locar Aluno no Grupo e Imprimir Grupos\n" +
						"(R)egistrar Aluno que Respondeu\n" +
						"(I)mprimir Alunos que Responderam\n" + 
						"(O)ra, vamos fechar o programa!" +
						"\n" + "Opção> ");
		return scanner.next().toUpperCase().trim();

	}
	/**
	 * Interpreta a opção do usuário e invoca o método correspondente
	 * @param opcao Opção digitada pelo usuário
	 * @param turma A turma que está sendo manipulada
	 * @param scanner Objeto scanner para o caso do método precisar de mais input
	 */
	private static void comando(String opcao, Turma turma, Scanner scanner) {
		switch (opcao) {
		case "C":
			adicionaAluno(turma, scanner);
			break;
		case "E":
			exibirAluno(turma, scanner);
			break;
		case "N":
			cadastraGrupo(turma, scanner);
			break;
		case "A":
			refinaEscolha(turma, scanner);
			break;
		case "R":
			registraAluno(turma, scanner);
			break;
		case "I":
			alunosResponderam(turma);
			break;
		case "O":
			System.exit(0);
		default:
			System.out.println("Opção inválida!");
		}
	}
	/**
	 * Identifica entradas inválidas do usuário e lança o erro correspondente
	 * @param input Entrada do usuário
	 */
	private static void confereEntrada(String input) {
		if (input == null) {
			throw new NullPointerException();
		} else if (input.trim().equals("")) {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Cadastra um aluno na turma
	 * @param turma Turma em que o aluno é adicionado
	 * @param scanner Scanner para pedir informações do aluno
	 */
	private static void adicionaAluno(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		confereEntrada(matricula);
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		confereEntrada(nome);
		System.out.print("Curso: ");
		String curso = scanner.nextLine();
		confereEntrada(curso);
		if (turma.alunoMatriculado(matricula)) {
			System.out.println("MATRÍCULA JÁ CADASTRADA!\n");
		} else {
			turma.cadastraAluno(matricula, nome, curso);
			System.out.println("CADASTRO REALIZADO!\n");
		}
	}
	/**
	 * Exibe as informações de um aluno
	 * @param turma Turma em que o aluno é cadastrado
	 * @param scanner Scanner para pedir informações sobre o aluno
	 */
	private static void exibirAluno(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		confereEntrada(matricula);
		if (turma.alunoMatriculado(matricula)) {
			System.out.println("Aluno: " + turma.alunoToString(matricula));
		} else {
			System.out.println("Aluno não cadastrado.");
		}
	}
	/**
	 * Cadastra um grupo na turma
	 * @param turma Turma em que o grupo é adicionado
	 * @param scanner Scanner para pedir informações sobre o grupo
	 */
	private static void cadastraGrupo(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		confereEntrada(nome);
		if (turma.grupoCadastrado(nome)) {
			System.out.println("GRUPO JÁ CADASTRADO!\n");
		} else {
			turma.cadastraGrupo(nome);
			System.out.println("CADASTRO REALIZADO!\n");
		}
	}
	/**
	 * Pede mais um input ao usuário para saber especificamente o que ele deseja fazer
	 * @param turma A turma que está sendo manipulada
	 * @param scanner Scanner para pedir informações sobre a escolha do usuário
	 */
	private static void refinaEscolha(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("(A)locar Aluno ou (I)mprimir Grupo? ");
		String opcao = scanner.next().toUpperCase().trim();
		confereEntrada(opcao);
		if (opcao.equals("A")) {
			alocarAluno(turma, scanner);
		} else {
			imprimeGrupo(turma, scanner);
		}
	}
	/**
	 * Aloca um aluno em um grupo
	 * @param turma Turma em que estão o aluno e o grupo
	 * @param scanner Scanner para pedir informações sobre a alocação
	 */
	private static void alocarAluno(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		confereEntrada(matricula);
		System.out.print("Grupo: ");
		String nomeGrupo = scanner.nextLine().toUpperCase();
		confereEntrada(nomeGrupo);
		//Testa se essa matricula é de um aluno cadastrado
		if (turma.alunoMatriculado(matricula)) {
			//Testa se esse grupo existe
			if (turma.grupoCadastrado(nomeGrupo)) {
				//Testa se o aluno já está nesse grupo
				if (turma.verificaGrupo(matricula, nomeGrupo)) {
					turma.alocaAluno(matricula, nomeGrupo);
				}
				System.out.println("ALUNO ALOCADO!");
			}else {
				System.out.println("Aluno não cadastrado.");
			}
		} else {
			System.out.println("Grupo não cadastrado.");
		}
	}
	/**
	 * Imprime todos os alunos que estão em determinado grupo
	 * @param turma Turma em que o grupo é adicionado
	 * @param scanner Scanner para pedir informações sobre o grupo
	 */
	private static void imprimeGrupo(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Grupo: ");
		String nomeGrupo = scanner.nextLine().toUpperCase();
		confereEntrada(nomeGrupo);
		ArrayList<String> grupo = turma.grupoToString(nomeGrupo);
		for (int i = 0; i < grupo.size(); i++) {
			System.out.println(grupo.get(i));
		}
	}
	/**
	 * Registra alunos que responderam questões no quadro
	 * @param turma Turma em que os alunos estão cadastrados
	 * @param scanner Scanner para pedir informações sobre o aluno
	 */
	private static void registraAluno(Turma turma, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		confereEntrada(matricula);
		if (turma.alunoMatriculado(matricula)) {
			turma.registraAluno(matricula);
			System.out.println("ALUNO CADASTRADO!\n");
		} else {
			System.out.println("Aluno não cadastrado.");
		}
	}
	/**
	 * Imprime todos os alunos que responderam questões no quadro na ordem em que responderam
	 * @param turma Turma em que os alunos estão cadastrados
	 */
	private static void alunosResponderam(Turma turma) {
		ArrayList<String> alunos = turma.responderamQuadro();
		System.out.print("Alunos:");
		for (int i = 0; i < alunos.size(); i++) {
			System.out.println(i + ". " + alunos.get(i));
		}
	}
}
