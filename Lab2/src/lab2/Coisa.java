package lab2;

import java.util.Scanner;
/** 
 * @author Mirlla Marques Santana Alves - 119210401
 */
public class Coisa {
	
	public static void cadastroLaboratorio(Aluno aluno, String[] comandos) {
		if (comandos.length == 2) {
			aluno.cadastraLaboratorio(comandos[1]);
		} else {
			int cota = Integer.parseInt(comandos[2]);
			aluno.cadastraLaboratorio(comandos[1], cota);
		}
	}
	public static void cadastroDisciplina(Aluno aluno, String[] comandos) {
		if (comandos.length == 2) {
			aluno.cadastraDisciplina(comandos[1]);
		} else {
			int notas = Integer.parseInt(comandos[2]);
			aluno.cadastraDisciplina(comandos[1], notas);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Aluno aluno = new Aluno();
		
		while(true) {
			String comando = sc.nextLine();
			if (comando.contentEquals("SAIR")) {
				break;
			} else {
				String[] comandos = comando.split(" ");
				/**
				 * Operações do objeto aluno utilizando a classe ContaLaboratorio
				 */
				if (comandos[0].equals("LABORATORIO")) {
					cadastroLaboratorio(aluno, comandos);
				} else if (comandos[0].equals("CONSOME")) {
					int cota = Integer.parseInt(comandos[2]);
					aluno.consomeEspaco(comandos[1], cota);
				} else if (comandos[0].equals("LIBERA")) {
					aluno.liberaEspaco(comandos[1], Integer.parseInt(comandos[2]));
				} else if(comandos[0].equals("ATINGIU")) {
					System.out.println(aluno.atingiuCota(comandos[1]));
				} else if (comandos[0].equals("LABORATORIOTOSTRING")) {
					System.out.println(aluno.laboratorioToString(comandos[1]));
				} 
				/**
				 * Operações do objeto aluno utilizando a classe Disciplina
				 */
				else if (comandos[0].equals("DISCIPLINA")) {
					cadastroDisciplina(aluno, comandos);
				} else if (comandos[0].equals("HORAS")) {
					int horas = Integer.parseInt(comandos[2]);
					aluno.cadastraHoras(comandos[1], horas);
				} else if (comandos[0].equals("NOTA")) {
					int nota = Integer.parseInt(comandos[2]);
					int valorNota = Integer.parseInt(comandos[3]);
					aluno.cadastraNota(comandos[1], nota, valorNota);
				} else if (comandos[0].equals("APROVADO")) {
					System.out.println(aluno.aprovado(comandos[1]));
				} else if (comandos[0].equals("DISCIPLINATOSTRING")) {
					System.out.println(aluno.disciplinaToString(comandos[1]));
				}
				/**
				 * Operações do objeto aluno utilizando a classe ContaCantina
				 */
				else if (comandos[0].equals("CONTACANTINA")) {
					aluno.cadastraCantina(comandos[1]);
				} else if (comandos[0].equals("LANCHE")) {
					int qtdItens = Integer.parseInt(comandos[2]);
					int valorCentavos = Integer.parseInt(comandos[3]);
					aluno.cadastraLanche(comandos[1], qtdItens, valorCentavos);
				} else if (comandos[0].equals("PAGAR")) {
					int valorCentavos = Integer.parseInt(comandos[2]);
					aluno.pagarConta(comandos[1], valorCentavos);
				} else if (comandos[0].equals("LISTA")) {
					aluno.listarDetalhes(comandos[1]);
				} else if (comandos[0].equals("FALTAPAGAR")) {
					System.out.println(aluno.getFaltaPagar(comandos[1]));
				} else if (comandos[0].equals("CANTINATOSTRING")) {
					System.out.println(aluno.cantinaToString(comandos[1]));
				}
				/**
				 * Operações do objeto aluno utilizando a classe Saude
				 */
				else if (comandos[0].equals("SAUDEMENTAL")) {
					aluno.defineSaudeMental(comandos[1]);
				} else if (comandos[0].equals("SAUDEFISICA")) {
					aluno.defineSaudeFisica(comandos[1]);
				} else if (comandos[0].equals("EMOJI")) {
					aluno.emoji(comandos[1]);
				} else if (comandos[0].equals("SAUDEGERAL")) {
					System.out.println(aluno.getStatusGeral());
				}
			} 			
		}
	}
}
