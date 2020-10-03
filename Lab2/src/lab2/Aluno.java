package lab2;

import java.util.HashMap;

/**
 * Representação de um aluno que realiza diversas operações 
 * baseadas em métodos de outras classes
 * @author Mirlla Marques Santana Alves - 119210401
 */
public class Aluno {
    /**
     * Identifica um objeto ContaLaboratório com uma String
     */
    private HashMap<String, ContaLaboratorio> lcc;
    /**
     * Identifica um objeto Disciplina com uma String
     */
	private HashMap<String, Disciplina> cadeiras;
	/**
     * Identifica um objeto ContaCantina com uma String
     */
    private HashMap<String, ContaCantina> cantinas;
    /**
     * Representa a saúde do aluno
     */
    private Saude saude;
	/**
	 * Constrói um aluno com etiqutas de objetos desse aluno
	 */
	public Aluno() {
		this.lcc = new HashMap<String, ContaLaboratorio>();
		this.cadeiras = new HashMap<String, Disciplina>();
		this.cantinas = new HashMap<String, ContaCantina>();
		this.saude = new Saude();
	}
	/**
	 * Cria um cadastro em um dos laboratórios
	 * @param nomeLaboratorio Nome do laboratório em que será
	 * criada a conta
	 */
	public void cadastraLaboratorio(String nomeLaboratorio) {
		ContaLaboratorio laboratorio = new ContaLaboratorio(nomeLaboratorio);
        this.lcc.put(nomeLaboratorio, laboratorio);
	}
	/**
	 * Cria um cadastro em um dos laboratórios
	 * @param nomeLaboratorio Nome do laboratório em que será
	 * criada a conta
	 * @param cota Cota de espaço de armazenamento do laboratório
	 */
	public void cadastraLaboratorio(String nomeLaboratorio, int cota) {
		ContaLaboratorio laboratorio = new ContaLaboratorio(nomeLaboratorio, cota);
        this.lcc.put(nomeLaboratorio, laboratorio);
	}
	/**
	 * Espaço de armazenamento a ser utilizado
	 * @param nomeLaboratorio Nome do laboratório em que será utilizado o espaço
	 * @param mbytes Quantidade de espaço a ser utilizada em megaBytes
	 */
	public void consomeEspaco(String nomeLaboratorio, int mbytes) {
		ContaLaboratorio lab = this.lcc.get(nomeLaboratorio);
		lab.consomeEspaco(mbytes);
	}
	/**
	 * Espaço de armazenamento a ser liberada
	 * @param nomeLaboratorio Nome do laboratório em que será liberado o espaço
	 * @param mbytes Quantidade de espaço a ser liberada em megaBytes
	 */
	public void liberaEspaco(String nomeLaboratorio, int mbytes) {
		ContaLaboratorio lab = this.lcc.get(nomeLaboratorio);
		lab.liberaEspaco(mbytes);
	}
	/**
	 * Confere se a cota de espaço foi atingida
	 * @param nomeLaboratorio Nome do laboratório em que será feita a checagem
	 * @return False se ainda não tiver atingido a cota e true se a cota 
	 * foi atingida ou ultrapassada
	 */
	public boolean atingiuCota(String nomeLaboratorio) {
		ContaLaboratorio lab = this.lcc.get(nomeLaboratorio);
		return lab.atingiuCota();
	}
	/**
	 *@return Retorna o estado da conta cadastrada
	 *e a situação do espaço de armazenamento
	 */
	public String laboratorioToString(String nomeLaboratorio) {
		ContaLaboratorio lab = this.lcc.get(nomeLaboratorio);
		return lab.toString();
	}
	/**
	 * Cria uma disciplina para acompanhamento
	 * @param nomeDisciplina Disciplina a ser acompanhada
	 */
	public void cadastraDisciplina(String nomeDisciplina) {
		Disciplina cadeira = new Disciplina(nomeDisciplina);
		this.cadeiras.put(nomeDisciplina, cadeira);
	}
	/**
	 * Cria uma disciplina para acompanhamento
	 * @param nomeDisciplina Disciplina a ser acompanhada
	 * @param notas Quantidade de notas que a disciplina tem
	 */
	public void cadastraDisciplina(String nomeDisciplina, int notas) {
		Disciplina cadeira = new Disciplina(nomeDisciplina, notas);
		this.cadeiras.put(nomeDisciplina, cadeira);
	}
	/**
	 * Cria uma disciplina para acompanhamento
	 * @param nomeDisciplina Disciplina a ser acompanhada
	 * @param notas Quantidade de notas que a disciplina tem
	 * @param pesos Peso das notas para o calculo de uma média ponderada
	 */
	public void cadastraDisciplina(String nomeDisciplina, int notas, int[] pesos) {
		Disciplina cadeira = new Disciplina(nomeDisciplina, notas, pesos);
		this.cadeiras.put(nomeDisciplina, cadeira);
	}
	/**
	 * Adiciona as horas estudadas na disciplina
	 * @param nomeDisciplina Disciplina estudada
	 * @param horas Quantidade de horas estudadas
	 */
	public void cadastraHoras(String nomeDisciplina, int horas) {
		Disciplina materia = this.cadeiras.get(nomeDisciplina);
		materia.cadastraHoras(horas);
	}
	/**
	 * Adiciona as notas obtidas na disciplina
	 * @param nomeDisciplina Disciplina estudada
	 * @param horas Qual a nota a ser adicionada
	 * @param Qual foi a nota obtida
	 */
	public void cadastraNota(String nomeDisciplina, int nota, double valorNota) {
		Disciplina materia = this.cadeiras.get(nomeDisciplina);
		materia.cadastraNota(nota, valorNota);
	}
	/**
	 * Diz a situação de aprovação do aluno
	 * @param nomeDisciplina Disciplina a ser consultada a aprovação
	 * @return true caso o aluno esteja acima da média e false caso vá para final
	 */
	public boolean aprovado(String nomeDisciplina) {
		Disciplina materia = this.cadeiras.get(nomeDisciplina);
		return materia.aprovado();
	}
	/**
	 * @param nomeDisciplina A disciplina a ser exibida
	 * @return  Retorna o nome da disciplina, horas totais estudadas, 
	 *média do aluno e as notas cadastradas
	 */
	public String disciplinaToString(String nomeDisciplina) {
		Disciplina materia = this.cadeiras.get(nomeDisciplina);
		return materia.toString();
	}
	/**
	 * Cantina em que o aluno cria uma conta na UFCG
	 * @param nomeCantina Cantina em que vai ser criada a conta
	 */
	public void cadastraCantina(String nomeCantina) {
		ContaCantina cantina = new ContaCantina(nomeCantina);
		this.cantinas.put(nomeCantina, cantina);
	}
	/**
	 * Cadastra um lanche recém comprado
	 * @param nomeCantina Cantina em que vai ser criada a conta
	 * @param qtdItens Quantidade de itens comprados
	 * @param valorCentavos Valor da compra em centavos
	 */
	public void cadastraLanche(String nomeCantina, int qtdItens, int valorCentavos) {
		ContaCantina cantina = this.cantinas.get(nomeCantina);
		cantina.cadastraLanche(qtdItens, valorCentavos);
	}
	/**
	 * Cadastra um lanche recém comprado
	 * @param nomeCantina Cantina em que vai ser criada a conta
	 * @param qtdItens Quantidade de itens comprados
	 * @param valorCentavos Valor da compra em centavos
	 * @param detalhes Detalhes da compra
	 */
	public void cadastraLanche(String nomeCantina, int qtdItens, int valorCentavos, String detalhes) {
		ContaCantina cantina = this.cantinas.get(nomeCantina);
		cantina.cadastraLanche(qtdItens, valorCentavos, detalhes);
	}
	/**
	 * Lista os detalhes das últimas 5 compras
	 * @param nomeCantina Nome da cantina em que é desejado saber os detalhes
	 * @return Retorna os detalhes das últimas 5 compras
	 */
	public String listarDetalhes(String nomeCantina) {
		ContaCantina cantina = this.cantinas.get(nomeCantina);
		return cantina.listarDetalhes();
	}
	/**
	 * Paga uma parte ou o total da compra
	 * @param nomeCantina Nome da cantina em que será paga a conta
	 * @param valorCentavos Valor a ser pago
	 */
	public void pagarConta(String nomeCantina, int valorCentavos) {
		ContaCantina cantina = this.cantinas.get(nomeCantina);
		cantina.pagaConta(valorCentavos);
	}
	/**
	 * Valor em débito da conta
	 * @param nomeCantina Cantina em que é feita a checagem do saldo devedor
	 * @return Retorna o saldo devedor em determinada cantina
	 */
	public int getFaltaPagar(String nomeCantina) {
		ContaCantina cantina = this.cantinas.get(nomeCantina);
		return cantina.getFaltaPagar();
	}
	/**
	 * Retorna os detalhes da compras em determinada cantina
	 * @param nomeCantina Nome da cantina em que serão exibidas as informações
	 * @return Retorna o nome da cantina, quantidade de itens já comprados 
	 *e o gasto total até o momento (saldo quitado e devedor)
	 */
	public String cantinaToString(String nomeCantina) {
		ContaCantina cantina = this.cantinas.get(nomeCantina);
		return cantina.toString();
	}
	/**
	 * Adiciona o estado de saúde mental do aluno
	 * @param valor Qual o estado de saúde mental
	 */
	public void defineSaudeMental(String valor) {
		this.saude.defineSaudeMental(valor);
	}
	/**
	 * Adiciona o estado de saúde física do aluno
	 * @param valor Qual o estado de saúde física
	 */
	public void defineSaudeFisica(String valor) {
		this.saude.defineSaudeFisica(valor);
	}
	/**
	 * Faz uma avaliação do status geral de saúde do aluno
	 * baseado na saúde física e mental
	 * @return Retorna o balanço geral de saúde do aluno
	 */
	public String getStatusGeral() {
		return this.saude.getStatusGeral();
	}
	/**
	 * Define um emoji que representa o estado do aluno
	 * @param valor O emoji passado pelo usuário
	 */
	public void emoji(String valor) {
		saude.definirEmoji(valor);
	}
}
