package saga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Representação de um controle de operações com objetos dos tipos Fornecedor 
 * e Produto e com um repositório de fornecedores num HashMap
 * @author Mirlla Marques Santana Alves
 */
public class FornecedorController implements Saga{
	/**
	 * Repositório de objetos do tipo Fornecedores identificados por seu nome 
	 */
	private HashMap<String, Fornecedor> repoFornecedores;
	/**
	 * Contrói o controller de fornecedores
	 */
	public FornecedorController() {
		this.repoFornecedores = new HashMap<>();
	}
	/**
	 * @return Retorna o repositório de fornecedores
	 */
	public HashMap<String, Fornecedor> getRepoFornecedores(){
		return this.repoFornecedores;
	}
	/**
	 * Testa se o cadastro é novo
	 * @param key Possível nova chave
	 * @return Retorna true se o fornecedor ainda não foi cadastrado
	 */
	private boolean novoCadastro(String key) {
		return !this.repoFornecedores.containsKey(key);
	}
	/**
	 * Cadastra um fornecedor
	 * @param nome Nome do fornecedor a ser cadastrado
	 * @param email Email do fornecedor a ser cadastrado 
	 * @param telefone Telefone do fornecedor a ser cadastrado
	 * @return Retrona true caso o cadastro seja bem sucedido
	 * @throws Exception 
	 */
	public void cadastro(String nome, String email, String telefone) throws Exception {
		if (!novoCadastro(nome)) {
			throw new Exception("Erro no cadastro de fornecedor: fornecedor ja existe.");
		}
		Fornecedor fornecedor = new Fornecedor(nome, email, telefone);
		this.repoFornecedores.put(nome, fornecedor);
	}
	/**
	 * Testa se o produto é novo
	 * @param key Possível nova chave
	 * @return Retorna true se o produto ainda não foi cadastrado
	 */
	public boolean novoProduto(String fornecedor, String key) {
		HashMap<String, Produto> produtos = this.repoFornecedores.get(fornecedor).getProdutos();
		return !produtos.containsKey(key);
	}
	/**
	 * Adiciona um produto de um fornecedor
	 * @param nomeFornecedor Nome do fornecedor que adicionará o produto
	 * @param nomeProduto Nome do produto a ser adicionado
	 * @param descricao Descrição do produto
	 * @param preco Preço do produto
	 * @throws Exception 
	 */
	public void adicionarProduto(String fornecedor, String nome, String descricao, double preco) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		} if (fornecedor == null) {
			throw new NullPointerException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		else if (novoCadastro(fornecedor)){
			throw new Exception("Erro no cadastro de produto: fornecedor nao existe.");
		} else if (!novoProduto(fornecedor, nome + descricao)){
			throw new Exception("Erro no cadastro de produto: produto ja existe.");
		}
		Fornecedor vendedor = this.repoFornecedores.get(fornecedor);
		vendedor.adicionaProduto(nome, descricao, preco);
	}
	/**
	 * Realiza alguma mudança no fornecedor
	 * @param nome Nome do fornecedor em que será feito a mudança
	 * @param atributo Atributo a ser alterado
	 * @param novoValor Novo valor do atributo
	 * @throws Exception
	 */
	public void change(String nome, String atributo, String novoValor) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if (novoCadastro(nome)) {
			throw new Exception("Erro na edicao do fornecedor: fornecedor nao existe.");
		} if (atributo == null) {
			throw new NullPointerException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		} else if(atributo.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		} else if(atributo.contentEquals("nome")) {
			throw new Exception("Erro na edicao do fornecedor: nome nao pode ser editado.");
		} else if(atributo.equals("telefone")) {
			updateTelefone(nome, novoValor);
		} else if (atributo.equals("email")) {
			updateEmail(nome, novoValor);
		} else {
			throw new Exception("Erro na edicao do fornecedor: atributo nao existe.");
		}
	}
	/**
	 * Método que atualiza o email do fornecedor
	 * @param nome Nome do fornecedor
	 * @param email Novo email do fornecedor
	 * @throws Exception 
	 */
	private void updateEmail(String nome, String email) throws Exception {
		Fornecedor fornecedor = (Fornecedor) this.repoFornecedores.get(nome);
		fornecedor.setEmail(email);
	}
	/**
	 * Método que atualiza o telefone do fornecedor
	 * @param nome Nome do fornecedor
	 * @param telefone Novo telefone do fornecedor
	 * @throws Exception 
	 */
	private void updateTelefone(String nome, String telefone) throws Exception {
		Fornecedor fornecedor = (Fornecedor) this.repoFornecedores.get(nome);
		fornecedor.setTelefone(telefone);
	}
	/**
	 *  Método que atualiza o preço de um produto de um fornecedor
	 * @param nome Nome do produto
	 * @param descricao Descrição do produto
	 * @param preco Novo preço do produto
	 * @throws Exception 
	 */
	public void updatePreco(String fornecedor, String nome, String descricao, double novoPreco) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null) {
			throw new NullPointerException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		} if (fornecedor == null) {
			throw new NullPointerException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (novoCadastro(fornecedor)){
			throw new Exception("Erro na edicao de produto: fornecedor nao existe.");
		} else if(novoPreco <= 0.0) {
			throw new Exception("Erro na edicao de produto: preco invalido.");
		} else if (novoProduto(fornecedor, nome + descricao)){
			throw new Exception("Erro na edicao de produto: produto nao existe.");
		} 
		Fornecedor vendedor = (Fornecedor) this.repoFornecedores.get(fornecedor);
		vendedor.getProduto(nome + descricao).setPreco(novoPreco);
	}
	/**
	 * @param nome Nome do produto desejado
	 * @param descricao Descrição do produto desejado
	 * @return Retorna a representação textual de um produto específico
	 * @throws Exception 
	 */
	public String getProduto(String nome, String descricao, String fornecedor) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null) {
			throw new NullPointerException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		} else if (fornecedor == null) {
			throw new NullPointerException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (novoCadastro(fornecedor)){
			throw new Exception("Erro na exibicao de produto: fornecedor nao existe.");
		} else if (novoProduto(fornecedor, nome + descricao)){
			throw new Exception("Erro na exibicao de produto: produto nao existe.");
		}
		Fornecedor vendedor = (Fornecedor) this.repoFornecedores.get(fornecedor);
		return vendedor.getProduto(nome + descricao).toString();
	}
	/**
	 * Remove um produto de um fornecedor
	 * @param nomeFornecedor Nome do Fornecedor
	 * @param nomeProduto Nome do produto a ser removido
	 * @param descricao Descrição do produto
	 * @throws Exception 
	 */
	public void removerProduto(String fornecedor, String nome, String descricao) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null) {
			throw new NullPointerException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		} else if (fornecedor == null) {
			throw new NullPointerException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (novoCadastro(fornecedor)){
			throw new Exception("Erro na remocao de produto: fornecedor nao existe.");
		} else if (novoProduto(fornecedor, nome + descricao)){
			throw new Exception("Erro na remocao de produto: produto nao existe.");
		}
		Fornecedor vendedor = (Fornecedor) this.repoFornecedores.get(fornecedor);
		vendedor.removerProduto(nome + descricao);
	}
	/**
	 * Método que remove o fornecedor do hashMap e, consequentemente, seu cadastro
	 * @param nome Nome do fornecedor a ser removido
	 * @throws Exception 
	 */
	public void remover(String nome) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");
		} else if(novoCadastro(nome)) {
			throw new Exception("Erro na remocao do fornecedor: fornecedor nao existe.");
		} 
		this.repoFornecedores.remove(nome);	
	}
	/**
	 * @param nome Nome do fornecedor
	 * @return Retorna a representação textual de um cliente
	 * @throws Exception 
	 */
	public String toString(String nome) throws Exception {
		if (nome == null) {
			throw new NullPointerException("Erro na exibicao do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na exibicao do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if(novoCadastro(nome)) {
			throw new Exception("Erro na exibicao do fornecedor: fornecedor nao existe.");
		} 
		Fornecedor fornecedor = (Fornecedor) this.repoFornecedores.get(nome);
		return fornecedor.toString();
	}
	/**
	 * @return Retorna a representação textual de todos os fornecedores de forma ordenada
	 */
	public String imprimeTodos() {
		List<Fornecedor> fornecedores = ordenaFornecedores();
		final int QTDFORNECEDORES = fornecedores.size();
		String retorno = "";
		for(int i = 0; i < QTDFORNECEDORES; i++) {
			if(i != QTDFORNECEDORES - 1) {
				retorno += fornecedores.get(i).toString() + " | ";
			} else {
				retorno += fornecedores.get(i).toString();
			}
		}
		return retorno;
	}
	 /**
     * Metodo responsavel por ordenar todos os objetos Produto a partir do seu próprio nome.
     * @return lista ordenada com todos os objetos Produto já ordenados
     */
    private List<Produto> ordenaProdutos(String nome) {
    	Fornecedor fornecedor = this.repoFornecedores.get(nome);
        List<Produto> listaProdutosOrdem = new ArrayList<>(fornecedor.getProdutos().values());
        Collections.sort(listaProdutosOrdem);
        return listaProdutosOrdem;
    }
	/**
	 * @return Retorna a representação textual de todos os produtos de um fornecedor de forma ordenada
	 * @throws Exception 
	 */
	public String imprimeTodosProdutos(String fornecedor) throws Exception {
		 if (fornecedor == null) {
			throw new NullPointerException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if(novoCadastro(fornecedor)) {
			throw new Exception("Erro na exibicao de produto: fornecedor nao existe.");
		}
		List<Produto> fornecedores = ordenaProdutos(fornecedor);
		String retorno = "";
		for(int i = 0; i < fornecedores.size(); i++) {
			if(i != fornecedores.size() - 1) {
				retorno += fornecedor + " - " + fornecedores.get(i).toString() + " | ";
			} else {
				retorno += fornecedor + " - " + fornecedores.get(i).toString();
			}
		}
		return retorno;
	}
	 /**
     * Metodo responsavel por ordenar todos os objetos Fornecedor a partir do seu próprio nome.
     * @return lista ordenada com todos os objetos Fornecedor já ordenados
     */
    private List<Fornecedor> ordenaFornecedores() {
        List<Fornecedor> listaFornecedoresOrdem = new ArrayList<>(this.repoFornecedores.values());
        Collections.sort(listaFornecedoresOrdem);
        return listaFornecedoresOrdem;
    }
	/**
	 * @return Retorna uma string com todos os produtos de todos os forncedores sendo estes ordenados
	 */
	public String produtosOrdenados() {
		List<Fornecedor> fornecedores = ordenaFornecedores();
		String retorno = "";
		for(int i = 0; i < fornecedores.size(); i++ ) {
			String temp = fornecedores.get(i).getNome();
			List<Produto> produtosKeys = new ArrayList<>(ordenaProdutos(temp));
			if (produtosKeys.size() == 0) {
				if (retorno.equals("")) {
					retorno += temp + " - ";
				} else {
					retorno += "| " +  temp + " - ";
				}
			} else {
				int count = 0;
				for(Produto produto : produtosKeys) {
					if (count != produtosKeys.size() && !retorno.equals("")) {
					retorno += "| " + temp + " - " + produto.toString() + " ";
					} else {
						retorno += temp + " - " + produto.toString() + " ";
					}
					count ++;
				}
			}
		}
		return retorno.trim();
	}
}
