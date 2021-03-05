package fornecedor;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import produto.Combo;
import produto.Produto;
import utilidades.ComparatorCombo;
import utilidades.ComparatorProduto;
import utilidades.Util;

/**
 * Classe que representa um fornecedor do sistema
 * 
 * @author Aluno de período anterior
 *
 */
public class Fornecedor {
	/**
	 * Nome do fornecedor
	 */
	private String nome;

	/**
	 * Email do fornecedor
	 */
	private String email;

	/**
	 * Telefone do fornecedor
	 */
	private String telefone;

	/**
	 * Hash map dos produtos que o fornecedor oferece sendo as chaves a concatenação
	 * das String que representam o nome e descrição do produto respectivamente, e
	 * os valores são os objetos que representam os produtos
	 */
	private HashMap<String, Produto> produtos;
	
	/**
	 * Hash map dos combos que o fornecedor oferece sendo as chaves a concatenação
	 * das String que representam o nome e descrição do combo respectivamente, e
	 * os valores são os objetos que representam os combos
	 */
	private HashMap<String, Combo> combos;

	/**
	 * ArrayList de Strings que são a concatenação do nome e descrição dos produtos
	 */
	private ArrayList<String> produtosCadastrados;
	
	/**
	 * ArrayList de Strings que são a concatenação do nome e descrição dos produtos
	 */
	private ArrayList<String> combosCadastrados;
	
	/**
	 * Comparator que compara dois produtos
	 */
	private Comparator<Produto> comparadorProduto;
	
	/**
	 * Comparator que compara dois combos
	 */
	private Comparator<Combo> comparadorCombo;


	/**
	 * Constrói um fornecedor dado seu nome, email e telefone
	 * 
	 * @param nome     Nome do fornecedor
	 * @param email    Email do fornecedor
	 * @param telefone Telefone do fornecedor
	 */
	public Fornecedor(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new HashMap<String, Produto>();
		this.combos = new HashMap<String, Combo>();
		this.produtosCadastrados = new ArrayList<String>();
		this.combosCadastrados = new ArrayList<String>();
		this.comparadorProduto = new ComparatorProduto();
		this.comparadorCombo = new ComparatorCombo();
	}

	/**
	 * Retorna a representação textual do fornecedor no formato "Nome" - "Email" -
	 * "Telefone"
	 */
	@Override
	public String toString() {
		return this.getNome() + " - " + this.getEmail() + " - " + this.getTelefone();
	}

	/**
	 * Compara dois fornecedores pelo nome
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Fornecedor) {
			Fornecedor f = (Fornecedor) o;

			if (this.getNome().equals(f.getNome())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gera o hashCode do fornecedor baseado no nome
	 */
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}

	/**
	 * Retorna o nome do fornecedor
	 * 
	 * @return String contendo o nome do fornecedor
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o telefone do fornecedor
	 * 
	 * @return String contendo o telefone do fornecedor
	 */
	private String getTelefone() {
		return this.telefone;
	}

	/**
	 * Retorna o email do fornecedor
	 * 
	 * @return String contendo o email do fornecedor
	 */
	private String getEmail() {
		return this.email;
	}

	/**
	 * Redefine o email do fornecedor dado o novo valor
	 * 
	 * @param email Novo email do fornecedor
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Redefine o telefone do fornecedor dado o novo valor
	 * 
	 * @param telefone Novo numero de telefone do fornecedor
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Adiciona um produto ao forncedor dado o nome, descricao e preco do produto
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 * @param preco     Preco do produto
	 */
	public void adicionaProduto(String nome, String descricao, double preco) {
		String key = nome + " - " + descricao;

		if (this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
		}

		this.produtos.put(key, new Produto(nome, descricao, preco));
		this.produtosCadastrados.add(key);

		for (int i = this.produtosCadastrados.size() - 1; i > 0; i--) {
			String produto1 = this.produtosCadastrados.get(i);
			String produto2 = this.produtosCadastrados.get(i - 1);

			Produto p1 = this.produtos.get(produto1);
			Produto p2 = this.produtos.get(produto2);

			int compare = this.comparadorProduto.compare(p1, p2);

			if (compare < 0) {
				Collections.swap(this.produtosCadastrados, i, i - 1);
			}
		}
	}

	/**
	 * Exibe um produto do fornecedor dado o nome e a descricao do produto
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 * @return Representação String do produto
	 */
	public String exibeProduto(String nome, String descricao) {	
		String key = (nome + " - " + descricao).replace("+", "-");
		String retorno = "";
		if (!this.produtos.containsKey(nome + " - " + descricao) && !this.combos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: produto nao existe.");
		}
		
		if (this.produtos.containsKey(nome + " - " + descricao)) {
			retorno =  this.produtos.get(nome + " - " + descricao).toString();
		} if(this.combos.containsKey(key)) {
			retorno = this.combos.get(key).toString();

		}
		return retorno;
	}

	/**
	 * Lista todos os produtos do forncedor em ordem alfabética do nome do produto
	 * 
	 * @return String contendo a lista de todos os produtos do fornecedor
	 */
	public String listaProdutos() {
		String retorno = this.getNome() + " -";
		ArrayList<String> chaves = new ArrayList<>();
		
		for (int i = 0; i < this.produtosCadastrados.size(); i++) {
			chaves.add(this.produtosCadastrados.get(i));	
		}
		
		for (int j = 0; j < this.combosCadastrados.size(); j++) {
			chaves.add(this.combosCadastrados.get(j));
		}

		Collections.sort(chaves);
		
		for (int l = 0; l < chaves.size(); l++) {
			if (this.produtosCadastrados.contains(chaves.get(l))) {
				Produto produto = this.produtos.get(chaves.get(l));
				if (l == 0) {
					retorno += " " + produto.toString();
				} else {
					retorno += " | " +  this.getNome() + " - " + produto.toString();
				}	
			} else {
				Combo combo = this.combos.get(chaves.get(l));
				if (l == 0) {
					retorno += " " + combo.toString();
				} else {
					retorno += " | " +  this.getNome() + " - " + combo.toString();
				}	
			}
		}

		return retorno;
	}

	/**
	 * Edita o preco de um produto do fornecedor dado o nome e descricao do produto
	 * e o novo preco
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 * @param novoPreco Novo preco do produto
	 */
	public void editaProduto(String nome, String descricao, double novoPreco) {
		String key = nome + " - " + descricao;

		if (!this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na edicao de produto: produto nao existe.");
		}

		this.produtos.get(key).modificaProduto(novoPreco);

	}

	/**
	 * Remove um produto do fornecedor
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 */
	public void removeProduto(String nome, String descricao) {
		String key = (nome  + " - "+ descricao).replace("+", "-");

		if (!this.produtos.containsKey(nome  + " - "+ descricao) && !this.combos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na remocao de produto: produto nao existe.");
		}
		if (this.produtos.containsKey(nome  + " - "+ descricao)){
			this.produtos.remove(nome  + " - "+ descricao);
			this.produtosCadastrados.remove(nome  + " - "+ descricao);
		} 
		if (this.combos.containsKey(key)) {
			this.combos.remove(key);
			this.combosCadastrados.remove(key);
		}


	}

	/**
	 * Retorna o preco de um produto dado o nome e descricao do produto
	 * 
	 * @param nomeProduto      Nome do produto
	 * @param descricaoProduto Descricao do produto
	 * @return Preco do produto
	 */
	public double getPrecoProduto(String nomeProduto, String descricaoProduto) {
		if (!this.produtos.containsKey(nomeProduto + " - " + descricaoProduto)) {
			throw new IllegalArgumentException("Erro ao pegar preco do produto: produto nao existe.");
		}

		return this.produtos.get(nomeProduto + " - " + descricaoProduto).getPreco();
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Produto getProduto(String key) {
		return this.produtos.get(key);
	}

	/**
	 * @param produtos
	 * @return
	 */
	private boolean testProduto(String produtos) {
		String[] keys = produtos.split(",");
		for(String key : keys) {
			if (!this.produtosCadastrados.contains(key.trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param produtos
	 * @return
	 */
	private boolean testCombo(String produtos) {
		String[] keys = produtos.split(",");
		Set<String> combos = this.combos.keySet();
		for(String key : keys) {
			if (combos.contains(key.replace("+", "-").trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 */
	public void adicionaCombo(String nome, String descricao, double fator, String produtos) {
		String add = (nome + " - " + descricao).replace("+", "-");

		if (this.combos.containsKey(add)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo ja existe.");
		}
		
		Util.testaNull(produtos, "Erro no cadastro de combo: combo deve ter produtos.");
		Util.testaVazio(produtos, "Erro no cadastro de combo: combo deve ter produtos.");
		
		if(testCombo(produtos)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
		}
		
		if(testProduto(produtos)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: produto nao existe.");
		}
		
		this.combos.put(add, new Combo(this, nome, descricao, fator, produtos));
		this.combosCadastrados.add(add);

		for (int i = this.combosCadastrados.size() - 1; i > 0; i--) {
			String combo1 = this.combosCadastrados.get(i);
			String combo2 = this.combosCadastrados.get(i - 1);

			Combo c1 = this.combos.get(combo1);
			Combo c2 = this.combos.get(combo2);

			int compare = this.comparadorCombo.compare(c1, c2);

			if (compare < 0) {
				Collections.swap(this.combosCadastrados, i, i - 1);
			}
		}
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param novoFator
	 */
	public void editaCombo(String nome, String descricao, double novoFator) {
		String key = (nome + " - " + descricao).replace("+", "-");
		if (!this.combos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na edicao de combo: produto nao existe.");
		}
		this.combos.get(key).setFator(novoFator);
	}

}
