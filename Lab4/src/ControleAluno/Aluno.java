package ControleAluno;

/**
 * Representação de um aluno que tem informações como
 * nome, matricula e o curso
 * @author mirlla
 *
 */
public class Aluno {
	/**
	 * Matricula do aluno
	 */
	private String matricula;
	/**
	 * Nome do aluno
	 */
	private String nome;
	/**
	 * Curso em que o aluno é matriculado
	 */
	private String curso;
	
	/**
	 * Constrói um aluno
	 * @param matricula Matricula do aluno
	 * @param nome Nome do aluno
	 * @param curso Curso em que o aluno é matriculado
	 */
	public Aluno(String matricula, String nome, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;		
	}
	/**
	 *@return Retorna uma String com a  matricula, o nome e o curso do aluno
	 */
	public String toString() {
		return this.matricula + " - "+ this.nome + " - " + this.curso;
	}
}
