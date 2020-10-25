package ControleAluno;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representação de um grupo de alunos
 * @author Mirlla Marques Santana Alves - 119210401
 */
public class Grupo {	
	/**
	 * Mapa de objetos Aluno identifacdos por uma matricula String 
	 */
	private HashMap<String, Aluno> alunos;
	/**
	 * O nome do grupo
	 */
	private String nomeGrupo;
	/**
	 * ArrayList que guarda todas as matriculas dos alunos cadastrados
	 */
	private ArrayList<String> keys;
	/**
	 * Constrói um grupo
	 * @param nome Nome do grupo a ser construído
	 */
	public Grupo(String nome) {
		this.alunos = new HashMap<>();
		this.nomeGrupo = nome;
		this.keys = new ArrayList<>();
	}
	/**
	 * Adiciona um aluno ao grupo
	 * @param aluno Aluno a ser adicionado
	 * @param matricula Matricula do aluno
	 */
	public void adicionaAluno(Aluno aluno, String matricula) {
		this.alunos.put(matricula, aluno);
		this.keys.add(matricula);
	}
	/** 
	 * Pega um aluno do grupo pela matrícula
	 * @param matricula Matrícula do aluno desejado
	 * @return Retorna um toString do objeto Aluno
	 */
	public String getAluno(String matricula) {
		return this.alunos.get(matricula).toString();
	}
	/**
	 * @return Retorna o nome do grupo tal qual foi cadastrado
	 */
	public String getNome() {
		return this.nomeGrupo;
	}
	/**
	 * @return Retorna a ArrayList de matrículas dos alunos
	 */
	public ArrayList<String> getKeys(){
		return this.keys;
	}
	
}
