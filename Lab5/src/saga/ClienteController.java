package saga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Representação de um controle de operações com objetos do tipo Cliente
 * e com um repositório de clientes num HashMap
 * @author Mirlla Marques Santana Alves
 */
public class ClienteController implements Saga{
	/**
	 * Repositório de objetos do tipo Cliente identificados por seu CPF 
	 */
	private HashMap<String, Cliente> repoClientes;
	/**
	 * Contrói o controller de Cliente
	 */
	public ClienteController() {
		this.repoClientes = new HashMap<>();
	}
	/**
	 * @return Retorna o repositório de clientes
	 */
	public HashMap<String, Cliente> getRepoClientes() {
		return this.repoClientes;
	}
	/**
	 * Testa se o cadastro é novo
	 * @param key Possível nova chave
	 * @return Retorna true se o cliente ainda não foi cadastrado
	 */
	private boolean novoCadastro(String key) {
		return !this.repoClientes.containsKey(key);
	}
	/**
	* @param cpf CPF a ser validado
	* @return Retorna true se o cpf for válido
	 * @throws Exception 
	*/
	private boolean cpfInvalido(String cpf) throws Exception {
		 return cpf.length() != 11;
	}
	/**
	 * Método que inicializa um cliente e adiciona no hashMap
	 * @param cpf CPF do cliente
	 * @param nome Nome do cliente
	 * @param email Email do cliente
	 * @param localizacao Onde o cliente trabalha
	 * @throws Exception 
	 */
	public void cadastro(String cpf, String nome, String email, String localizacao) throws Exception {
		if (cpf == null) {
			throw new NullPointerException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpf.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpfInvalido(cpf)) {
			 throw new Exception("Erro no cadastro do cliente: cpf invalido.");
		 } else if (!novoCadastro(cpf)) {
			throw new Exception("Erro no cadastro do cliente: cliente ja existe.");
		} 
		Cliente novo = new Cliente(cpf, nome, email, localizacao);
		this.repoClientes.put(cpf, novo);
	}
	/**
	 * Realiza alguma mudança no cliente
	 * @param cpf CPF do cliente em que será feito a mudança
	 * @param atributo Atributo a ser alterado
	 * @param novoValor Novo valor do atributo
	 * @throws Exception
	 */
	public void change(String cpf, String atributo, String novoValor) throws Exception {
		if (cpf == null) {
			throw new NullPointerException("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpf.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpfInvalido(cpf)) {
			 throw new Exception("Erro na edicao do cliente: cpf invalido.");
		 } else if (novoCadastro(cpf)) {
			throw new Exception("Erro na edicao do cliente: cliente nao existe.");
		} if (atributo == null) {
			throw new NullPointerException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		} else if(atributo.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		} else if(atributo.toLowerCase().equals("cpf")) {
			throw new Exception("Erro na edicao do cliente: cpf nao pode ser editado.");
		} else if(atributo.toLowerCase().equals("nome")) {
			updateNome(cpf, novoValor);
		} else if(atributo.toLowerCase().equals("email")) {
			updateEmail(cpf, novoValor);
		} else if(atributo.toLowerCase().equals("localizacao")) {
			updateLocalizacao(cpf, novoValor);
		} else {
			throw new Exception("Erro na edicao do cliente: atributo nao existe.");
		}
	}
	/**
	 * Método que atualiza o nome do cliente
	 * @param cpf CPF do cliente
	 * @param nome Nome do cliente
	 * @throws Exception 
	 */
	private void updateNome(String cpf, String nome) {
		Cliente cliente = this.repoClientes.get(cpf);
		cliente.setNome(nome);
	}
	/**
	 * Método que atualiza o email do cliente
	 * @param cpf CPF do cliente
	 * @param email Email do cliente
	 * @throws Exception 
	 */
	private void updateEmail(String cpf, String email) {
		Cliente cliente = this.repoClientes.get(cpf);
		cliente.setEmail(email);
	}
	/**
	 * Método que atualiza o local de trabalho do cliente
	 * @param cpf CPF do cliente
	 * @param localizacao Onde o cliente trabalha
	 * @throws Exception 
	 */
	private void updateLocalizacao(String cpf, String localizacao) {
		Cliente cliente = this.repoClientes.get(cpf);
		cliente.setLocal(localizacao);
	}
	/**
	 * Método que remove o cliente do hashMap e, consequentemente, seu cadastro
	 * @param cpf CPF do cliente a ser removido
	 * @throws Exception 
	 */
	@Override
	public void remover(String cpf) throws Exception {
		if (cpf == null) {
			throw new NullPointerException("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		} else if(cpf.trim().equals("")) {
			throw new IllegalArgumentException("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		 } else if (novoCadastro(cpf)) {
			throw new Exception("Erro na remocao do cliente: cliente nao existe.");
		} 
		this.repoClientes.remove(cpf);
	}
	/**
	 * @param cpf CPF do cliente
	 * @return Retorna a representação textual de um cliente
	 * @throws Exception 
	 */
	@Override
	public String toString(String cpf) throws Exception {
		if (cpf == null) {
			throw new NullPointerException("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpf.trim().equals("")) {
			throw new IllegalArgumentException("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpfInvalido(cpf)) {
			 throw new Exception("Erro na exibicao do cliente: cpf invalido.");
		 } else if (novoCadastro(cpf)) {
			throw new Exception("Erro na exibicao do cliente: cliente nao existe.");
		} 
		Cliente cliente = this.repoClientes.get(cpf);
		return cliente.toString();
	}
	 /**
     * Metodo responsavel por ordenar todos os objetos Cliente a partir do seu próprio nome.
     * @return lista ordenada com todos os objetos Cliente já ordenados
     */
    private List<Cliente> ordenaClientes() {
        List<Cliente> listaClientesOrdem = new ArrayList<>(this.repoClientes.values());
        Collections.sort(listaClientesOrdem);
        return listaClientesOrdem;
    }
	/**
	 * @return Retorna a representação textual de todos os clientes de forma ordenada
	 */
	@Override
	public String imprimeTodos() {
		List<Cliente> clientes = ordenaClientes();
		String retorno = "";
		for(int i =0; i < clientes.size(); i++) {
			if(i != clientes.size() -1) {
				retorno += clientes.get(i).toString() + " | ";
			} else {
				retorno += clientes.get(i).toString();
			}
		}
		return retorno;
	}

}
