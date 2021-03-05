package ControleAluno;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representação de uma turma que interage com objetos Aluno e Grupo
 * @author Mirlla Marques Santana Alves
 */
public class Turma {
	/**
	 * Repositório de alunos da turma
	 */
	private HashMap<String, Aluno> repoAlunos;
	/**
	 * Repositório de grupos da turma
	 */
	private HashMap<String, Grupo> repoGrupos; 
	/**
	 * ArrayList que armazena os alunos que responderam questões no quadro
	 */
	private ArrayList<String> respondeuQuestao;
	/**
	 * Constŕoi uma Turma
	 */
	public Turma() {
		this.repoAlunos = new HashMap<>();
		this.repoGrupos = new HashMap<>();
		this.respondeuQuestao = new ArrayList<>();
	}
	/**
	 * Cria um objeto aluno e adiciona ao repositório de alunos
	 * @param matricula Matrícula do aluno
	 * @param nome Nome do aluno
	 * @param curso Curso em que o aluno é matriculado
	 */
	public void cadastraAluno(String matricula, String nome, String curso) {
		Aluno aluno = new Aluno(matricula, nome, curso);
		this.repoAlunos.put(matricula, aluno);	
	}
	/**
	 * Testa se um aluno já foi matriculado antes
	 * @param matricula Matricula do aluno a ser conferida
	 * @return Retorna true caso o aluno já seja matriculado
	 */
	public boolean alunoMatriculado(String matricula) {
		boolean matriculado = false;
		for (int i = 0; i < this.repoAlunos.size(); i++) {
			if(this.repoAlunos.containsKey(matricula)) {
				matriculado = true;
				break;
			}
		}
		return matriculado;
	}
	/**
	 * Pega uma string com infromações de um aluno
	 * @param matricula Matrícula do aluno desejado
	 * @return Retorna um toString do objeto aluno desejado
	 */
	public String alunoToString(String matricula) {
		return this.repoAlunos.get(matricula).toString();
	}
	/**
	 * Cadastra um grupo, para garantir indistinção entre letras maiúsculas e 
	 * minúsculas será padrão que a identificação no HashMap esteja em UpperCase
	 * @param nome Nome do grupo
	 */
	public void cadastraGrupo(String nome) {
		Grupo grupo = new Grupo(nome);
		this.repoGrupos.put(nome.toUpperCase(), grupo);
	}
	/**
	 * Testa se um grupo existe
	 * @param nome Nome do grupo que será feita a verificação
	 * @return Retorna true caso o grupo exista
	 */
	public boolean grupoCadastrado(String nome) {
		boolean matriculado = false;
		for (int i = 0; i < this.repoGrupos.size(); i++) {
			if(this.repoGrupos.containsKey(nome)) {
				matriculado = true;
				break;
			}
		}
		return matriculado;
	}
	/**
	 * Testa se um aluno não está em um grupo
	 * @param matricula Matrícula do aluno
	 * @param nomeGrupo Nome do grupo que será feita a verificação
	 * @return
	 */
	public boolean verificaGrupo(String matricula, String nomeGrupo) {
		ArrayList<String> alunos = this.repoGrupos.get(nomeGrupo).getKeys();
		return !alunos.contains(matricula);
	}
	/**
	 * Aloca um aluno em um grupo
	 * @param matricula Matrícula do aluno a ser alocado
	 * @param nomeGrupo Nome do grupo em que o aluno será alocado
	 */
	public void alocaAluno(String matricula, String nomeGrupo) {
		Grupo grupo = this.repoGrupos.get(nomeGrupo);
		ArrayList<String> alunos = this.repoGrupos.get(nomeGrupo).getKeys();
		grupo.adicionaAluno(this.repoAlunos.get(matricula), matricula);
	}
	/**
	 * Cria um ArrayList com informações sobre o grupo
	 * @param nomeGrupo Nome do grupo em que deseja pegar as informações
	 * @return Retorna um ArrayList com nome dos integrantes do grupo
	 */
	public ArrayList<String> grupoToString(String nomeGrupo) {
		ArrayList<String> retorno = new ArrayList<>();
		ArrayList<String> alunos = this.repoGrupos.get(nomeGrupo).getKeys();
		Grupo grupo = this.repoGrupos.get(nomeGrupo);
		String mensagem = "Alunos do grupo " + grupo.getNome() + ":";
		retorno.add(mensagem);
		
		for (int i =0; i < alunos.size(); i ++) {
			String aluno = "* " + grupo.getAluno(alunos.get(i));
			retorno.add(aluno);
		}
		return retorno;
	}
	/**
	 * Registra um aluno que respondeu uma questão no quadro no ArrayList
	 * @param matricula Matrícula do aluno que respondeu a questão
	 */
	public void registraAluno(String matricula) {
		this.respondeuQuestao.add(this.repoAlunos.get(matricula).toString());
	}
	/**
	 * @return Retorna a ArrayList com os alunos que responderam questões no quadro
	 */
	public ArrayList<String> responderamQuadro() {
		return this.respondeuQuestao;
	}
}
