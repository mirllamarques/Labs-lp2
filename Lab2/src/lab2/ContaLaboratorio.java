package lab2;
/**
 * Representação de uma conta  em um dos 
 * Laboratórios de Ciêcia da Computação (LCC)
 * em que todo aluno deve cadastrar um LCC
 * e uma cota(opcional) para controle do uso de
 * armazenamento dos computadores
 * 
 *@author Mirlla Marques Santana Alves - 119210401
 *
 */
public class ContaLaboratorio{
	/**
	 * Laboratório cadastrado no formato LCCx onde x
	 * corresponde ao número do laboratório
	 */
	private String lcc;
	/**
	 * A cota é o limite de espaço disponível no 
	 * sistema de armazenamento em megabytes(mb)
	 */
	private int cota;
	/**
	 * Total de espaço de armazenamento utilizado no lcc
	 */
	private int armazenamentoTotal;
	/**
	 * Constrói conta em um LCC
	 * @param nomeLaboratorio indica de qual LCC é a conta criada
	 * Cota padrão de 2000mb
	 */
	public ContaLaboratorio (String nomeLaboratorio){
		this.lcc = nomeLaboratorio;
		this.cota = 2000;
		this.armazenamentoTotal = 0;
	}
	/**
	 * Cria conta em um LCC
	 * @param nomeLaboratorio indica de qual LCC é a conta criada
	 * @param cota quantidade limite, em mb, de armazenamento
	 */
	public ContaLaboratorio (String nomeLaboratorio, int cota){
		this(nomeLaboratorio);
		this.cota = cota;
	}
	/**
	 * Consome espaço de armazenamento disponível
	 * @param mbytes quantidade utilizada do armazenamento total
	 */
	public void consomeEspaco(int mbytes){
		this.armazenamentoTotal += mbytes;
	}
	/**
	 * Libera espaço de armazenamento utilizado
	 * @param mbytes quantidade a ser liberada do armazenamento
	 */
	public void liberaEspaco(int mbytes){
		this.armazenamentoTotal -= mbytes;
	}
	/** Consulta se a cota de armazenamento foi atingida
	 * @return true se a cota for atingida, caso contrário retorna false
	 */
	public boolean atingiuCota() {
		return this.armazenamentoTotal >= this.cota;
	}
	/**
	 *@return Retorna o LCC onde a conta foi cadastrada
	 *e a situação do espaço de armazenamento
	 */
	public String toString(){
		String situacaoArmazenamento = this.armazenamentoTotal + "/" +this.cota;
		return this.lcc + " " + situacaoArmazenamento;
	}
}