package saga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Representação de um controle de operações com objetos do tipo Conta
 * e com um repositório de contas num HashMap
 * @author Mirlla Marques Santana Alves
 */
public class ContaController {
	/**
	 * Mapa com os fornecedores identificados pelo nome
	 */
	private HashMap<String, Fornecedor> fornecedores;
	/**
	 * Mapa com os nome dos clientes identificados pelo cpf
	 */
	private HashMap<String, Cliente> clientes;
	/**
	 * Repositório de contas de um cliente identificados pelo nome do cliente e o nome do fornecedor
	 */
	private HashMap<String, Conta> repoContas;
	/**
	 * Ordena compras pelo critério data
	 */
	private OrdenaDatas ordData;
	/**
	 * Constrói o controller de objetos do tipo Conta
	 * @param fornecedores Mapa com os fornecedores
	 * @param clientes Mapa com os nomes dos clientes
	 */
	public ContaController(HashMap<String, Fornecedor> fornecedores, HashMap<String, Cliente> clientes) {
		this.fornecedores = fornecedores;
		this.clientes = clientes;
		this.repoContas = new HashMap<>();
		this.ordData = new OrdenaDatas();
	}
	/**
	 * Testa de a conta já foi criada
	 * @param cpf Identificador do nome do cliente
	 * @param fornecedor Nome do fornecedor
	 * @return Retorna true se a conta já existe
	 */
	private boolean novaConta(String cpf, String fornecedor) {
		String id = this.clientes.get(cpf).getNome() + fornecedor;
		return !this.repoContas.containsKey(id);
	}
	/**
	 * Testa se as entradas são válidas e joga um erro caso contrário
	 * @param entrada Input a ser testado
	 */
	private boolean testInput(String entrada) {
		return entrada == null || entrada.trim().equals("");
	}
	/**
	* @param cpf CPF a ser validado
	* @return Retorna true se o cpf for válido
	*/
	private boolean cpfInvalido(String cpf) {
		return cpf.length() != 11;
	}
	/**
	 * Cria uma nova conta
	 * @param cpf Identificador do nome do cliente
	 * @param fornecedor Nome do fornecedor
	 */
	private void criaConta(String cpf, String nome) {
		Conta conta = new Conta(nome, this.clientes.get(cpf).getNome());
		this.repoContas.put(this.clientes.get(cpf).getNome() + nome, conta);
		Cliente cliente = this.clientes.get(cpf);
		cliente.addConta(conta);
		Fornecedor fornecedor = this.fornecedores.get(nome);
		fornecedor.addConta(conta);
	}
	/**
	 * @param cpf
	 * @return Retorna true se o cliente for novo
	 */
	private boolean novoCliente(String cpf) {
		return !this.clientes.containsKey(cpf);
	}
	/**
	 * @param fornecedor
	 * @return Retorna true se o fornecedor for novo
	 */
	private boolean novoFornecedor(String fornecedor) {
		return !this.fornecedores.containsKey(fornecedor);
	}
	/**
	 * @param fornecedor
	 * @param key
	 * @return Retorna true se o produto for novo
	 */
	private boolean novoProduto(String fornecedor, String key) {
		HashMap<String, Produto> produtos = this.fornecedores.get(fornecedor).getProdutos();
		return !produtos.containsKey(key);
	}
	/**
	 * Realiza uma compra de um produto
	 * @param cpf Identificador do nome do cliente
	 * @param fornecedor Nome do fornecedor
	 * @param data Data em que foi feita a compra
	 * @param nomeProd Nome do produto comprado
	 * @param descProd Descrição do produto comprado
	 * @throws Exception 
	 */
	public void compra(String cpf, String fornecedor, String data, String nomeProd, String descProd) throws Exception {	
		if (testInput(cpf)) {
			throw new Exception("Erro ao cadastrar compra: cpf nao pode ser vazio ou nulo.");
		} else if (cpfInvalido(cpf)) {
			throw new Exception("Erro ao cadastrar compra: cpf invalido.");
		} else if(novoCliente(cpf)) {
			throw new Exception("Erro ao cadastrar compra: cliente nao existe.");
		} else if (testInput(fornecedor)) {
			throw new Exception("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		} else if(novoFornecedor(fornecedor)) {
			throw new Exception("Erro ao cadastrar compra: fornecedor nao existe.");
		} else if(testInput(data)) {
			throw new Exception("Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
		} else if(data.length() != 10) {
			throw new Exception("Erro ao cadastrar compra: data invalida.");
		} else if(testInput(nomeProd)) {
			throw new Exception("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		} else if(testInput(descProd)) {
			throw new Exception("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
		} else if(novoProduto(fornecedor, nomeProd + descProd)) {
			throw new Exception("Erro ao cadastrar compra: produto nao existe.");
		} 
		if (novaConta(cpf, fornecedor)) {
			criaConta(cpf, fornecedor);
		}
		Conta conta = this.repoContas.get(this.clientes.get(cpf).getNome() + fornecedor);
		double preco = this.fornecedores.get(fornecedor).getProduto(nomeProd+descProd).getPreco();
		Compra compra  = conta.compraProduto(data, nomeProd, descProd, preco);
		this.ordData.adicionaCompra(compra);
	}
	/**
	 * @param cpf Identificador do nome do cliente
	 * @param fornecedor Nome do fornecedor
	 * @return Retorna o débito de uma conta
	 * @throws Exception 
	 */
	public String getDebito(String cpf, String fornecedor) throws Exception {
		if (testInput(cpf)) {
			throw new Exception("Erro ao recuperar debito: cpf nao pode ser vazio ou nulo.");
		} else if (cpfInvalido(cpf)) {
			throw new Exception("Erro ao recuperar debito: cpf invalido.");
		} else if(novoCliente(cpf)) {
			throw new Exception("Erro ao recuperar debito: cliente nao existe.");
		} else if (testInput(fornecedor)) {
			throw new Exception("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		} else if(novoFornecedor(fornecedor)) {
			throw new Exception("Erro ao recuperar debito: fornecedor nao existe.");
		} else if(novaConta(cpf, fornecedor)) {
			throw new Exception("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
		Conta conta = this.repoContas.get( this.clientes.get(cpf).getNome() + fornecedor);
		return conta.getDebito();
	}
	/**
     * Método para obter as informações da conta, contendo cada compra com a data em que foram executadas, o nome e descrição de tais compras e o nome do fornecedor.
     * @param cpf CPF para identificar o cliente e obter suas informações.
     * @param fornecedor nome do fornecedor para complementar o identificador de uma conta.
     * @return Retorno em String das informações da conta.
	 * @throws Exception 
     */
    public String contasFornecedor(String cpf, String fornecedor) throws Exception {
    	if (testInput(cpf)) {
			throw new Exception("Erro ao exibir conta do cliente: cpf nao pode ser vazio ou nulo.");
		} else if (cpfInvalido(cpf)) {
			throw new Exception("Erro ao exibir conta do cliente: cpf invalido.");
		} else if(novoCliente(cpf)) {
			throw new Exception("Erro ao exibir conta do cliente: cliente nao existe.");
		} else if (testInput(fornecedor)) {
			throw new Exception("Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");
		} else if(novoFornecedor(fornecedor)) {
			throw new Exception("Erro ao exibir conta do cliente: fornecedor nao existe.");
		}  else if(novaConta(cpf, fornecedor)) {
			throw new Exception("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
		}
        String id = (this.clientes.get(cpf)).getNome() + fornecedor;
        return "Cliente: " + this.clientes.get(cpf).getNome() + " | " + this.repoContas.get(id).toString();
    }
    /**
     * Método para obter as informações da conta, contendo cada compra com a data em que foram executadas, o nome e descrição de tais compras e o nome do fornecedor.
     * @param cpf CPF para identificar o cliente e obter suas informações.
     * @return Retorno em String das informações da conta.
     * @throws Exception 
     */
    public String contasCliente(String cpf) throws Exception {
    	if (testInput(cpf)) {
			throw new Exception("Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.");
		} else if (cpfInvalido(cpf)) {
			throw new Exception("Erro ao exibir contas do cliente: cpf invalido.");
		} else if(novoCliente(cpf)) {
			throw new Exception("Erro ao exibir contas do cliente: cliente nao existe.");
		}  else if(semContas(cpf)) {
			throw new Exception("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.");
		}
        ArrayList<Conta> contas = this.clientes.get(cpf).getContas();
        String retorno = "Cliente: " + this.clientes.get(cpf).getNome() + " | ";
        for (int i = 0; i < contas.size(); i++) {
        	if (i == contas.size() -1) {
        		retorno += contas.get(i).toString();
        	} else {
        		retorno += contas.get(i).toString() + " | ";
        	}
        }
        return retorno;
    }
    /**
     * @param criterio
     * @return Lista todas as compras de acordo com o ctritério
     * @throws Exception
     */
    public String listarCompras(String criterio) throws Exception {
    	String retorno = "";
		if(criterio.equals("")) {
			throw new Exception("Erro na listagem de compras: criterio ainda nao definido pelo sistema.");
		} else if(criterio.equals("Cliente")) {
			retorno = clientesOrdenados();
		} else if(criterio.equals("Fornecedor")){
			retorno = fornecedoresOrdenados();
		} else if(criterio.equals("Data")) {
			retorno = datasOrdenadas();
		}
		criterio = "";
		return retorno;
    }
	/**
	 * @return Retorna uma string com todas as representações textuais de compras pelo critério de ordenação datas
	 */
    private String datasOrdenadas() {
        return this.ordData.getData();
    }
	/**
	 * @return Retorna uma ArrayList com os clientes ordenados
	 */
	private List<Cliente> ordenaClientes() {
		List<Cliente> listaClientesOrdem = new ArrayList<>(this.clientes.values());
	    Collections.sort(listaClientesOrdem);
	    return listaClientesOrdem;
	}
	/**
	 * @return Retorna uma string com todas as representações textuais de compras pelo critério de ordenação cliente
	 */
	private String clientesOrdenados(){
		List<Cliente> clientes = ordenaClientes();
		String retorno = "";
		for (int i =0; i < clientes.size(); i++) {
			Cliente cliente = (Cliente) clientes.get(i);
			List<Conta> contas = new ArrayList<>(cliente.getContas());
			for (int l =0; l < contas.size(); l++) {
				Conta conta = (Conta) contas.get(l);
				if (retorno.equals("")) {
					retorno += conta.ordenaPorCliente();
				} else {
					retorno += " | " + conta.ordenaPorCliente();
				}
			}
		}
		return retorno;
	}
	/**
     * @return Retorna uma ArrayList com os fornecedores ordenados
     */
    private List<Fornecedor> ordenaFornecedores() {
        List<Fornecedor> listaFornecedoresOrdem = new ArrayList<>(this.fornecedores.values());
        Collections.sort(listaFornecedoresOrdem);
        return listaFornecedoresOrdem;
    }
    /**
	 * @return Retorna uma string com todas as representações textuais de compras pelo critério de ordenação fornecedor
	 */
    private String fornecedoresOrdenados(){
		List<Fornecedor> fornecedores = ordenaFornecedores();
		String retorno = "";
		for (int i =0; i < fornecedores.size(); i++) {
			Fornecedor fornecedor = (Fornecedor) fornecedores.get(i);
			List<Conta> contas = new ArrayList<>(fornecedor.getContas());
			for (int l =0; l < contas.size(); l++) {
				Conta conta = (Conta) contas.get(l);
				if (retorno.equals("")) {
					retorno += conta.ordenaPorFornecedor();
				} else {
					retorno += " | " + conta.ordenaPorFornecedor();
				}
			}
		}
		return retorno;
	}
	/**
	 * @param cpf
	 * @return Retorna true se o cliente não tiver contas
	 */
	private boolean semContas(String cpf) {
		return this.clientes.get(cpf).getContas().size() == 0;
	}
	/**
	 * @param criterio
	 * @return Retorna o novo criterio
	 * @throws Exception
	 */
	public String setCriterio(String criterio) throws Exception {
		if (criterio == null || criterio.trim().equals("")) {
			throw new Exception("Erro na listagem de compras: criterio nao pode ser vazio ou nulo.");
		} else if(!criterio.equals("Cliente") && !criterio.equals("Fornecedor") && !criterio.equals("Data")){
			throw new Exception("Erro na listagem de compras: criterio nao oferecido pelo sistema.");
		} else {
			return criterio;
		}
	}
}