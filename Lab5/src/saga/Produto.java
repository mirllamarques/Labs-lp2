package saga;

/**
 * Representação de um produto de um fornecedor do Sistema para Auto-Gestão de comércio de Alimentos  
 * @author Mirlla Marques Santana Alves
 */
public class Produto implements Comparable<Produto>{
	/**
	 * Nome do produto
	 */
	private final String NOME;
	/**
	 * Descrição do produto
	 */
	private final String DESCRICAO;
	/**
	 * Preco do produto
	 */
	private double preco;
	/**
	 * Constrói um produto
	 * @param nome Nome do produto
	 * @param descricao Descrição do produto
	 * @param preco Preço do produto
	 */
	public Produto(String nome, String descricao, double preco) {
		if (nome == null) {
			throw new NullPointerException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		} if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		} if(preco <= 0.0) {
			throw new IllegalArgumentException("Erro no cadastro de produto: preco invalido.");
		}
		this.NOME = nome;
		this.DESCRICAO = descricao;
		this.preco = preco;
	}
	/**
	 * Altera o preço do produto
	 * @param preco Novo preço
	 */
	public void setPreco(double preco) {
		if(preco <= 0.0) {
			throw new IllegalArgumentException();
		}
		this.preco = preco;
	}
	/**
	 * @return Retorna o preço de um produto
	 */
	public double getPreco() {
		return this.preco;
	}
	/**                                                      
	 * @return Número que identifica esse fornecedor            
	 */    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESCRICAO == null) ? 0 : DESCRICAO.hashCode());
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
		Produto other = (Produto) obj;
		if (DESCRICAO == null) {
			if (other.DESCRICAO != null)
				return false;
		} else if (!DESCRICAO.equals(other.DESCRICAO))
			return false;
		if (NOME == null) {
			if (other.NOME != null)
				return false;
		} else if (!NOME.equals(other.NOME))
			return false;
		return true;
	}
	/**
	 * @return Retorna a representação textual desse Produto
	 */
	@Override
	public String toString() {
		String preco = String.format("%.2f", this.preco);
		return this.NOME + " - " + this.DESCRICAO + " - R$" + preco;
	}
	/**
     * Comparador baseado no nome do objeto Fornecedor
     * @param fornecedor Fornecedor a ser comparado
     */
	@Override
	public int compareTo(Produto produto) {
		String nomeProduto = this.NOME + this.DESCRICAO;
		return nomeProduto.toLowerCase().compareTo(produto.getNome().toLowerCase());
	}
	/**
	 * @return Retorna o nome que identifica o produto
	 */
	private String getNome() {
		return this.NOME + this.DESCRICAO;
	}
}
