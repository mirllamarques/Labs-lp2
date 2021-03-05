package saga;

import java.util.ArrayList;
import java.lang.Comparable;

/**
 * Representação de um cliente do Sistema para Auto-Gestão de comércio de Alimentos
 * @author Mirlla Marques Santana Alves
 */
public class Cliente implements Comparable<Cliente>{
	/**
	 * CPF do cliente que o identifica e não poe ser alterado
	 */
	private final String CPF;
	/**
	 * Nome do cliente
	 */
	private String nome;
	/**
	 * Email do cliente
	 */
	private String email;
	/**
	 * Local de trabalho do cliente
	 */
	private String local;
	/**
	 * ArrayList com as contas desse cliente
	 */
	private ArrayList<Conta> repoContas;
	/**
	 * Constrói um cliente
	 * @param cpf CPF do cliente
	 * @param nome Nome do cliente
	 * @param email Email do cliente
	 * @param local Local de trabalho do cliente
	 * @throws Exception 
	 */
	public Cliente(String cpf, String nome, String email, String local) throws Exception {
		if (cpf == null) {
			throw new NullPointerException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		} else if(cpf.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		} else if (nome == null) {
			throw new NullPointerException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		} else if (email == null) {
			throw new NullPointerException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		} else if(email.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		} else if (local == null) {
			throw new NullPointerException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		} else if(local.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		}
		this.CPF = cpf;
		this.nome = nome;
		this.email = email;
		this.local = local;
		this.repoContas = new ArrayList<>();
	}
	/**
	 * @return Retorna as contas do cliente
	 */
	public ArrayList<Conta> getContas() {
		return this.repoContas;
	}
	/**
	 * @return Retorna o nome desse cliente
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * Muda o nome do cliente
	 * @param nome Novo nome do cliente
	 */
	public void setNome(String nome) {
		 if (nome == null) {
			throw new NullPointerException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		}
		this.nome = nome;
	}
	/**
	 * Muda o email do cliente
	 * @param email Novo email do cliente
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		} else if(email.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		}
		this.email = email;
	}
	/**
	 * Muda o local de trabalho do cliente
	 * @param local Novo local de trabalho do cliente
	 */
	public void setLocal(String local) {
		if (local == null) {
			throw new NullPointerException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		} else if(local.trim().equals("")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		} 
		this.local = local;
	}
	/**
	 * @param conta Adiciona uma conta a ArrayList de contas
	 */
	public void addConta(Conta conta) {
		this.repoContas.add(conta);
	}
	/**
	 * @return Número que identifica esse cliente
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		return result;
	}
	/**
	 * @return Testa se um objeto é igual a este Cliente
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		return true;
	}
	/**
	 * @return Retorna a representação textual desse cliente
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.local +  " - "  + this.email;
	}
	/**
     * Comparador baseado no nome do objeto Cliente
     * @param cliente Cliente a ser comparado
     */
	@Override
	public int compareTo(Cliente cliente) {
		return this.nome.toUpperCase().compareTo(cliente.getNome().toUpperCase());
	}
}
