package lab2;
/**
 * Representação de um controle de uma disciplina,
 * que deve ser cadastrada, onde o aluno controla 
 * seu tempo de estudo e cadastrando-o e suas notas, 
 * podendo ser registradas até 4 notas e consultar
 * a situação da disciplia e verificar aprovação
 * @author Mirlla Marques Santana Alves
 *
 */
public class Disciplina {
	/**
	 * Disciplina em que será realizado o controle
	 */
	private String cadeira;
	/**
	 * Armazena as notas do aluno na disciplina
	 */
	private double[] notas;
	/**
	 * Tempo em que o aluno passou estudando para 
	 * aquela disciplina em horas
	 */
	private int tempoEstudando;
	/**
	 * Pesos de cada nota para o cálculo de uma média ponderada
	 */
	private int[] pesos;
	/**
	 * Constrói uma disciplina para acompanhamento de dados
	 * @param nomeDisciplina Nome da disciplina a ser cadastrada
	 * Tempo inicial de estudos é 0 e a lista de notas com 4 notas 
	 * padrão e peso padrão 1
	 */
	public Disciplina(String nomeDisciplina) {
		this.cadeira = nomeDisciplina;
		this.tempoEstudando = 0;
		this.notas = new double[4];
		this.pesos = new int[1];
	}
	/**
	 * Constrói uma disciplina para acompanhamento de dados
	 * @param nomeDisciplina Nome da disciplina a ser cadastrada
	 * @param numNotas Quantidade de notas da disciplina
	 * Tempo inicial de estudos é 0 e peso padrão 1
	 **/
	public Disciplina(String nomeDisciplina, int numNotas) {
		this(nomeDisciplina);
		this.notas = new double[numNotas];
		this.pesos = new int[1];
	}
	/**
	 * Constrói uma disciplina para acompanhamento de dados
	 * @param nomeDisciplina Nome da disciplina a ser cadastrada
	 * @param numNotas Quantidade de notas da disciplina
	 * @param pesos Peso das notas para o calculo de uma média
	 * ponderada e tempo inicial de estudos é 0
	 **/
	public Disciplina(String nomeDisciplina, int numNotas, int[] pesos) {
		this.cadeira = nomeDisciplina;
		this.notas = new double[numNotas];
		this.pesos = pesos;
		this.tempoEstudando = 0;
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
		this.notas[nota - 1] = valorNota;
	}
	/**
	 * Faz o cálculo da média do aluno
	 * @return Média do aluno
	 */
	private double media() {
		double somatorio = 0;
		for(int i =0; i < this.notas.length; i++) {
			somatorio += notas[i];
		}
		return somatorio/this.notas.length;
	}
	/**
	 * Faz o cálculo da média ponderada 
	 * @return se o array de pesos não foi passado retorna a média
	 * aritmética, caso contrário retorna a média ponderada
	 */
	public double mediaPonderada() {
		if (this.pesos.length == 1) {
			return media();
		} else {
			double somatorio = 0;
			for(int i = 0; i < this.pesos.length; i++) {
				somatorio += this.notas[i] * this.pesos[i];
			}
			return somatorio/10;
		}
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
	 * Faz um string com as notas do aluno no formato
	 * [nota 1, nota 2, nota 3, nota 4]
	 * @return Retorna uma String com as notas do array notas
	 */
	private String boletim() {
		String retorno = "[";
		for(int i =0; i < this.notas.length; i++) {
			if (i == this.notas.length - 1) {
				retorno += this.notas[i] +"]";
			}else {
				retorno += this.notas[i] + ", ";
			}
		}
		return retorno;
	}
	/**
	 *@return Retorna o nome da disciplina, horas totais estudadas, 
	 *média do aluno e as notas cadastradas
	 */
	public String toString() {
		return this.cadeira + " " + this.tempoEstudando + " "
			+ media() + " " + boletim();
	}
}