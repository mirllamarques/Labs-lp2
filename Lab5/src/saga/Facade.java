package saga;

import easyaccept.EasyAccept;
/**
 * Facade do Sistema para Auto-Gestão de comércio de Alimentos
 * @author Mirlla Marques Santana Alves
 */
public class Facade {
	
	ClienteController clientes;
	FornecedorController fornecedores;
	ContaController contas;
	String criterio;
	/**
	 * Contrói a Facade
	 */
	public Facade() {
		this.clientes = new ClienteController();
		this.fornecedores = new FornecedorController();
		this.contas = new ContaController(this.fornecedores.getRepoFornecedores(), this.clientes.getRepoClientes());
		this.criterio = "";
	}
	/**
	 * Adiciona cliente ao SAGA
	 * @param cpf CPF do cliente
	 * @param nome Nome do cliente
	 * @param email Email do cliente
	 * @param localizacao Local em que o cliente trabalha
	 * @throws Exception
	 */
	public void adicionaCliente(String cpf, String nome, String email, String localizacao) throws Exception {
		this.clientes.cadastro(cpf, nome, email, localizacao);
	}
	/**
	 * @param cpf CPF do cliente
	 * @return Exibe um cliente da SAGA
	 * @throws Exception
	 */
	public String exibeCliente(String cpf) throws Exception {
		return this.clientes.toString(cpf);
	}
	/**
	 * Edita alguma informação do cliente
	 * @param cpf CPF do cliente
	 * @param atributo O que deseja ser ditado
	 * @param novoValor O novo valor do campo alterado
	 * @throws Exception
	 */
	public void editaCliente(String cpf, String atributo, String novoValor) throws Exception {
		this.clientes.change(cpf, atributo, novoValor);
	}
	/**
	 * Remove um cliente da SAGA
	 * @param cpf CPF do cliente
	 * @throws Exception
	 */
	public void removeCliente(String cpf) throws Exception {
		this.clientes.remover(cpf);
	}
	/**
	 * Adiciona um fornecedor a SAGA
	 * @param nome Nome do fornecedor
	 * @param email Email do fornecedor
	 * @param telefone Telefone do fornecedor
	 * @throws Exception
	 */
	public void adicionaFornecedor(String nome, String email, String telefone) throws Exception {
		this.fornecedores.cadastro(nome, email, telefone);
	}
	/**
	 * @param nome Nome de um fonecedor da SAGA
	 * @return Exibe um fornecedor da SAGA
	 * @throws Exception
	 */
	public String exibeFornecedor(String nome) throws Exception {
		return this.fornecedores.toString(nome);
	}
	/**
	 * Edita alguma informação do fornecedor
	 * @param nome Nome do fornecedor
	 * @param atributo Campo que deseja alterar
	 * @param novoValor Novo valor do campo a ser alterado
	 * @throws Exception
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) throws Exception {
		this.fornecedores.change(nome, atributo, novoValor);
	}
	/**
	 * Remove um fornecedor da SAGA
	 * @param nome Nome do fornecedor a ser removido
	 * @throws Exception
	 */
	public void removeFornecedor(String nome) throws Exception {
		this.fornecedores.remover(nome);
	}
	/**
	 * Adiciona um produto a um fornecedor do SAGA
	 * @param fornecedor Fornecedor do produto
	 * @param nome Nome do produto
	 * @param descricao Descrição do produto
	 * @param preco Preco do produto
	 * @throws Exception
	 */
	public void adicionaProduto(String fornecedor, String nome, String descricao, double preco) throws Exception {
		this.fornecedores.adicionarProduto(fornecedor, nome, descricao, preco);
	}
	/**
	 * @param nome Nome do produto
	 * @param descricao Descricao do produto
	 * @param fornecedor Fornecedor do produto
	 * @return Exibe um produto de um fornecedor do SAGA
	 * @throws Exception
	 */
	public String exibeProduto(String nome, String descricao, String fornecedor) throws Exception {
		return this.fornecedores.getProduto(nome, descricao, fornecedor);
	}
	/**
	 * Edita o preco de um produto de um fornecedor do SAGA
	 * @param nome Nome do produto
	 * @param descricao Descrição do produto
	 * @param fornecedor Fornecedor do produto
	 * @param novoPreco Novo preço do produto
	 * @throws Exception
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) throws Exception {
		this.fornecedores.updatePreco(fornecedor, nome, descricao, novoPreco);
	}
	/**
	 * Remove um produto de um fornecedor do SAGA
	 * @param nome Nome do produto
	 * @param descricao Descrição do produto
	 * @param fornecedor Fornecedor do produto
	 * @throws Exception
	 */
	public void removeProduto(String nome, String descricao, String fornecedor) throws Exception {
		this.fornecedores.removerProduto(fornecedor, nome, descricao);
	}
	/**
	 * @return Exibe todos os clientes do SAGA
	 */
	public String exibeClientes() {
		return this.clientes.imprimeTodos();
	}
	/**
	 * @return Exibe todos os fornecedores do SAGA
	 */
	public String exibeFornecedores() {
		return this.fornecedores.imprimeTodos();
	}
	/**
	 * @param fornecedor Fornecedor dos produtos
	 * @return Exibe todos os produtos de um fornecedor
	 * @throws Exception
	 */
	public String exibeProdutosFornecedor(String fornecedor) throws Exception {
		return this.fornecedores.imprimeTodosProdutos(fornecedor);
	}
	/**
	 * @return Exibe todos os produtos do SAGA
	 */
	public String exibeProdutos() {
		return this.fornecedores.produtosOrdenados();
	}
	/**
	 * Compra um produto de um fornecedor do SAGA
	 * @param cpf CPF do cliente
	 * @param fornecedor Fornecedor
	 * @param data Data da compra
	 * @param nome_prod Nome do produto
	 * @param desc_prod Decrição do produto
	 * @throws Exception
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nome_prod, String desc_prod) throws Exception {
		this.contas.compra(cpf, fornecedor, data, nome_prod, desc_prod);
	}
	/**
	 * @param cpf CPF do cliente
	 * @param fornecedor Fornecedor do SAGA
	 * @return Retorn ao débito de um cliente
	 * @throws Exception
	 */
	public String getDebito(String cpf, String fornecedor) throws Exception {
		return this.contas.getDebito(cpf, fornecedor);
	}
	/**
	 * @param cpf CPF do cliente
	 * @param fornecedor Fornecedor do SAGA
	 * @return Exibe todas as contas de um fornecedor
	 * @throws Exception
	 */
	public String exibeContas(String cpf, String fornecedor) throws Exception {
		return this.contas.contasFornecedor(cpf, fornecedor);
	}
	/**
	 * @param cpf CPF do cliente
	 * @return Exibe todas as contas de um cliente
	 * @throws Exception
	 */
	public String exibeContasClientes(String cpf) throws Exception {
		return this.contas.contasCliente(cpf);
	}
	/**
	 * @return Lista as compras ordenadas com base no critério
	 * @throws Exception
	 */
	public String listarCompras() throws Exception {
		return this.contas.listarCompras(this.criterio);
	}
	/**
	 * @param criterio Critério de ordenação da listagem
	 * @throws Exception
	 */
	public void ordenaPor(String criterio) throws Exception {
		this.criterio = this.contas.setCriterio(criterio);
	}
	public static void main(String[] args) {
        args = new String[] { "saga.Facade", "acceptance_test/use_case_1.txt", "acceptance_test/use_case_2.txt", "acceptance_test/use_case_3.txt", 
        						"acceptance_test/use_case_4.txt", "acceptance_test/use_case_5.txt", "acceptance_test/use_case_6.txt"};
        EasyAccept.main(args);
    }
}

