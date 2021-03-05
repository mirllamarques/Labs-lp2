package produto;

import java.util.HashMap;
import java.util.Set;

import fornecedor.Fornecedor;

/**
 *  Classe que representa um combo de produtos do SAGA - Sistema para Auto-Gestão de comércio de Alimentos
 * @author Mirlla Marques Santana Alves
 */
public class Combo {
	/**
	 * Fornecedor responsável por este combo
	 */
	private Fornecedor fornecedor;
	/**
	 * Nome do combo
	 */
	private String nome;
	/**
	 * Descrição do combo
	 */
	private String descricao; 
	/**
	 * Fator de desconto do combo
	 */
	private double fator;
	/**
	 * Mapa com os produtos do combo
	 */
	private HashMap<String, Produto> produtos;
	/**
	 * Preço do combo
	 */
	private double preco;

	/**
	 * Constrói um combo de produtos de um fornecedor
	 * @param fornecedor
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 */
	public Combo(Fornecedor fornecedor, String nome, String descricao, double fator, String produtos) {
		this.fornecedor = fornecedor;
		this.nome = nome;
		this.descricao = descricao;
		this.fator = fator;
		this.produtos = new HashMap<>();
		String[] keys = produtos.split(",");
		for(String key : keys) {
			this.produtos.put(key.trim(), this.fornecedor.getProduto(key.trim()));
		}
		this.preco = calculaPreco();
	}
	
	/**
	 * @return O valor do preco do combo
	 */
	private double calculaPreco() {
		double preco = 0.00;
		Set<String> produtos = this.produtos.keySet();
		for(String produto : produtos) {
			preco += this.produtos.get(produto).getPreco();
		}
		preco = preco - (preco * this.fator);
		return preco;
	}

	/**
	 * Altera o fator de desconto e refaz o cálculo do preço
	 * @param fator
	 */
	public void setFator(double fator) {
		this.fator = fator;
		this.preco = calculaPreco();
	}
	/**
	 * Representação textual do combo
	 */
	@Override
	public String toString() {
		String preco = String.format("%.2f", this.preco);
		return this.nome + " - " + this.descricao + " - R$" + preco.replace(".", ",");
	}

	/**
	 * @return Retorna o nome do combo para o comparator
	 */
	public String getNome() {
		return this.nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fator);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combo other = (Combo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (Double.doubleToLongBits(fator) != Double.doubleToLongBits(other.fator))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		return true;
	}
	
}
