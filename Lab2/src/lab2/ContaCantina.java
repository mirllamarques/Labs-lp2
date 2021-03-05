package lab2;
/**
 * Representação de um controle de gastos nas cantinas
 * em que um aluno deve criar uma conta identificando
 * a cantina e assim registrar lanches comprados, valor
 * gasto e saldo devedor
 * @author Mirlla Marques Santana Alves
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
	/**
	 * Array que armazena os detalhes das 5 últimas compras
	 */
	private String[] detalhes;
	/**
	 * Conta quantas compras foram feitas com mensagens
	 */
	private int compras;
	/**Constrói uma conta numa cantina da UFCG
	 * @param nomeDaCantina A cantina em que a conta foi criada
	 */
	public ContaCantina(String nomeDaCantina) {
		this.cantina = nomeDaCantina;
		this.devedor = 0;
		this.totalGasto = 0;
		this.itens = 0;
		this.detalhes = new String[5];
		this.compras = -1;
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
	 *  Adiciona uma nova compra
	 * @param qtdItens Quantidade de itens comprados
	 * @param valorCentavos Valor, em centavos, gasto na compra
	 * @param detalhes Mensagem sobre a compra
	 */
	public void cadastraLanche(int qtdItens, int valorCentavos, String detalhes) {
		cadastraLanche(qtdItens, valorCentavos);
		this.compras += 1;
		setDetalhes(detalhes);
	}
	/**
	 *Set do array detalhes, adiciona os primeiros detalhes
	 *e atualiza para manter os 5 detalhes mais recentes
	 * @param detalhe O detalhe a ser adicionado
	 */
	private void setDetalhes(String detalhe) {
		if(this.detalhes[this.detalhes.length - 1] == null) {
			this.detalhes[this.compras] = detalhe;
		} else {
			for (int i = 0; i < this.detalhes.length; i++) {
				if (i == this.detalhes.length - 1) {
					this.detalhes[i] = detalhe;
				} else{
					this.detalhes[i] = this.detalhes[i + 1];
				}
			}
		}
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
	 * @return Retorna os últimos 5 detalhes de compras 
	 * Um em cada linha
	 */
	public String listarDetalhes() {
		String retorno = "";
		for(int i = 0; i < this.detalhes.length; i++) {
			if(i == this.detalhes.length -1) {
				retorno += this.detalhes[i];
			} else {
				retorno += this.detalhes[i] + "\n";
			}
		}
		return retorno;
	}
	/**
	 *@return Retorna o nome da cantina, quantidade de itens já comprados 
	 *e o gasto total até o momento (saldo quitado e devedor)
	 */
	public String toString() {
		return this.cantina + " " + this.itens + " " + this.totalGasto;
	}
}