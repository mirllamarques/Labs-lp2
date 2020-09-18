package lab2;
/**
 * Representação de um controle de uma disciplina,
 * que deve ser cadastrada, onde o aluno controla 
 * seu tempo de estudo e cadastrando-o e suas notas, 
 * podendo ser registradas até 4 notas e consultar
 * a situação da disciplia e verificar aprovação
 * @author Mirlla Marques
 *
 */
import java.util.ArrayList;

public class Disciplina {
	/**
	 * Disciplina em que será realizado o controle
	 */
	private String cadeira;
	/**
	 * Armazena as notas do aluno na disciplina
	 */
	private ArrayList<Double> notas;
	/**
	 * Tempo em que o aluno passou estudando para 
	 * aquela disciplina em horas
	 */
	private int tempoEstudando;
	
	/**
	 * Constrói uma disciplina para acompanhamento de dados
	 * @param nomeDisciplina Nome da disciplina a ser cadastrada
	 * Tempo inicial de estudos é 0 e a lista de notas está vazia
	 */
	public Disciplina(String nomeDisciplina) {
		this.cadeira = nomeDisciplina;
		this.tempoEstudando = 0;
		this.notas = new ArrayList<Double>();
		inicializaArrayList();
	}
	/**
	 * Define as quatro notas inicialmente como zero
	 */
	private void inicializaArrayList() {
		for (int i = 0; i < 4; i++) {
			this.notas.add(i,0.0);
		}
	}
	/**
	 * Aumenta a quantidade total de horas estudadas
	 * na disciplina cadastrada
	 * @param horas Quantidade de horas estudadas
	 */
	public void cadastraHoras(int horas) {
		this.tempoEstudando += horas;
	}
	/**
	 * Adiciona uma nota na lista de notas em determinada posição
	 * @param nota Qual é a nota (1,2,3,4) 
	 * @param valorNota Valor obtido pelo aluno na avaliação
	 */
	public void cadastraNota(int nota, double valorNota) {
		this.notas.set(nota - 1, valorNota);
	}
	/**
	 * Faz o cálculo da média do aluno
	 * @return Média do aluno
	 */
	private double media() {
		double somatorio = 0;
		for(int i =0; i < this.notas.size(); i++) {
			somatorio += notas.get(i);
		}
		return somatorio/4;
	}
	/**
	 * Testa se a média do aluno é maior ou 
	 * igual a média de aprovação
	 * @return Retorna true caso o aluno seja aprovado 
	 * e caso contrário retorna false
	 */
	public boolean aprovado() {
		return media() >= 7;
	}
	/**
	 *@return Retorna o nome da disciplina, horas totais estudadas, 
	 *média do aluno e as notas cadastradas
	 */
	public String toString() {
		return this.cadeira + " " + this.tempoEstudando + " "
			+ media() + " " + this.notas;
	}
}