package lab2;
/**
 * Representação de um controle de gastos nas cantinas
 * em que um aluno deve criar uma conta identificando
 * a cantina e assim registrar lanches comprados, valor
 * gasto e saldo devedor
 * @author 	Mirlla Marques
 *
 */
public class ContaCantina {
	/**
	 * Nome da cantina em que está sendo criada a conta
	 */
	private String cantina;
	/**
	 * Saldo devedor, em centavos, na cantina
	 */
	private int devedor;
	/**
	 * Valor, em centavos, que já foi gasto em lanches
	 */
	private int totalGasto;
	/**
	 * Total de itens já comprados
	 */
	private int itens;
	/**Constrói uma conta numa cantina da UFCG
	 * @param nomeDaCantina A cantina em que a conta foi criada
	 */
	public ContaCantina(String nomeDaCantina) {
		this.cantina = nomeDaCantina;
		this.devedor = 0;
		this.totalGasto = 0;
		this.itens = 0;
	}
	/**
	 * Adiciona uma nova compra
	 * @param qtdItens Quantidade de itens comprados
	 * @param valorCentavos Valor, em centavos, gasto na compra
	 */
	public void cadastraLanche(int qtdItens, int valorCentavos) {
		this.itens += qtdItens;
		this.devedor += valorCentavos;
		this.totalGasto += valorCentavos;
	}
	/**
	 * Quita determinada quantia da dívida
	 * @param valorCentavos Valor, em centavos, a ser pago
	 */
	public void pagaConta(int valorCentavos) {
		if (valorCentavos <= this.devedor) {
			this.devedor -= valorCentavos;
		}
	}
	/**
	 * Calcula o valor que ainda falta ser pago da dívida
	 * @return Quantidade, em centavos, que falta ser paga
	 */
	public int getFaltaPagar() {
		return this.devedor;
	}
	/**
	 *@return Retorna o nome da cantina, quantidade de itens já comprados 
	 *e o gasto total até o momento (saldo quitado e devedor)
	 */
	public String toString() {
		return this.cantina + " " + this.itens + " " + this.totalGasto;
	}
}