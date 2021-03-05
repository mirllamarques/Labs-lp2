package saga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.lang.Comparable;

/**
 * Representação de um fornecedor do Sistema para Auto-Gestão de comércio de Alimentos  
 * @author Mirlla Marques Santana Alves
 */
public class Fornecedor implements Comparable<Fornecedor> {
	/**
	 * Nome do fornecedor
	 */
	private final String NOME;
	/**
	 *Email do fornecedor 
	 */
	private String email;
	/**
	 * Telefone do fornecedor
	 */
	private String telefone;
	/**
	 * Mapa com os produtos do fornecedor identificados pelo nome
	 */
	private HashMap<String, Produto> produtos;
	/**
	 * ArrayList com as contas desse fornecedor
	 */
	private ArrayList<Conta> repoContas;
	/**
	 * Contrói um fornecedor
	 * @param nome Nome do fornecedor
	 * @param email Email do fornecedor 
	 * @param telefone Telefone do fornecedor
	 */
	public Fornecedor(String nome, String email, String telefone){
		if (nome == null) {
			throw new NullPointerException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		} if (email == null) {
			throw new NullPointerException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		} else if(email.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		} else if (telefone == null) {
			throw new NullPointerException("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		} else if(telefone.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		} 
		this.NOME = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new HashMap<>();
		this.repoContas = new ArrayList<>();
	}
	/**
	 * Adiciona um produto ao mapa de produtos
	 * @param nome Nome do produto a ser adicionado
	 * @param descricao Descrição do produto a ser adicionado
	 * @param preco Preço do produto a ser adicionado
	 */
	public void adicionaProduto(String nome, String descricao, double preco) {
		Produto produto = new Produto(nome, descricao, preco);
		this.produtos.put(nome + descricao, produto);
	}
	/**
	 * @param conta Adiciona uma conta a ArrayList de contas
	 */
	public void addConta(Conta conta) {
		this.repoContas.add(conta);
	}
	/**
	 * Altera o email do fornecedor
	 * @param email Novo email
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		} else if(email.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		}
		this.email = email;
	}
	/**
	 * Altera o telefone do fornecedor
	 * @param telefone Novo telefone
	 */
	public void setTelefone(String telefone) {
		if (telefone == null) {
			throw new NullPointerException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		} else if(telefone.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		} 
		this.telefone = telefone;
	}
	/**
	 * @return Retorna o nome do fornecedor
	 */
	public String getNome() {
		return this.NOME;
	}
	/**
	 * @param nome Nome do produto desejado
	 * @return Retorna o produto desejado 
	 */
	public Produto getProduto(String idProduto) {
		return this.produtos.get(idProduto);
	}
	/**
	 * @return Retorna o mapa com todos os produtos do fornecedor
	 */
	public HashMap<String, Produto> getProdutos() {
		return this.produtos;
	}
	/**
	 * @return Retorna as contas do fornecedor
	 */
	public ArrayList<Conta> getContas() {
		return this.repoContas;
	}
	/**
	 * @return Retorna a representação textual de todos os produtos de um fornecedor
	 */
	public String toStringProdutos() {
		String retorno = "";
		Set<String> produtosKeys = this.produtos.keySet();
		int count = 0;
		for (String produtosKey : produtosKeys) {
			if(count != produtosKeys.size()-1) {
				retorno += this.NOME + " - " + this.produtos.get(produtosKey).toString() + " | ";
			} else {
				retorno += this.NOME + " - " + this.produtos.get(produtosKey).toString();
			}
			count++;
		}
		return retorno;
	}
	/**                                                      
	 * @return Número que identifica esse fornecedor            
	 */                                                      
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NOME == null) ? 0 : NOME.hashCode());
		return result;
	}
	/**                                                     
	 * @return Testa se um objeto é igual a este Fornecedor    
	 */                                                     
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (NOME == null) {
			if (other.NOME != null)
				return false;
		} else if (!NOME.equals(other.NOME))
			return false;
		return true;
	}
	/**
	 * @return Retorna a representação textual desse Fornecedor
	 */
	@Override
	public String toString() {
		return this.NOME + " - " + this.email + " - " + this.telefone;
	}
	/**
     * Comparador baseado no nome do objeto Fornecedor
     * @param fornecedor Fornecedor a ser comparado
     */
	@Override
	public int compareTo(Fornecedor fornecedor) {
		return this.NOME.toLowerCase().compareTo(fornecedor.getNome().toLowerCase());
	}
	/**
	 * Remove um produto do mapa de produtos
	 * @param nomeProduto
	 */
	public void removerProduto(String nomeProduto) {
		this.produtos.remove(nomeProduto);
	}
	
}
