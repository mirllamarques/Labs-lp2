package saga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representação de uma conta de um cliente com um fornecedor do Sistema para Auto-Gestão de comércio de Alimentos
 * @author Mirlla Marques Santana Alves
 */
public class Conta {
	/**
	 * Nome do Cliente
	 */
	private String cliente;
	/**
	 * Nome do fornecedor
	 */
	private String fornecedor;
	/**
	 * Débito das compras
	 */
	private double debito;
	/**
	 * Armazena  os objetos Compra
	 */
	private ArrayList<Compra> compras;

	public Conta(String fornecedor, String cliente) {
		if (fornecedor == null) {
			throw new NullPointerException();
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException();
		} if (cliente == null) {
			throw new NullPointerException();
		} else if(cliente.trim().equals("")) {
			throw new IllegalArgumentException();
		}
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.debito = 0;
		this.compras = new ArrayList<>();
	}
	/**
	 * Registra uma compra do cliente
	 * @param data Data em que foi feita a compra
	 * @param nome Nome do produto comprado
	 * @param descricao Descrição do produto comprado
	 * @param preco Preço do produto comprado
	 * @throws Exception 
	 */
	public Compra compraProduto(String data, String nome, String descricao, double preco) throws Exception {
		if (data == null) {
			throw new NullPointerException();
		} else if(data.trim().equals("")) {
			throw new IllegalArgumentException();
		} if (nome == null) {
			throw new NullPointerException();
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException();
		} else if (descricao == null) {
			throw new NullPointerException();
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException();
		}
		this.debito += preco;
		Compra compra = new Compra(this.cliente, this.fornecedor, nome, descricao, data);
		this.compras.add(compra);
		return compra;
	}
	/**
	 * @return Retorna o débito total da conta
	 */
	public String getDebito() {
		String valor = String.format("%.2f", this.debito);
		return valor.replace(",", ".");
	}
	/**
	 * @return Retorna uma string com as compras na ordem cliente
	 */
	public String ordenaPorCliente() {
		List<String> clientes = new ArrayList<>();
		for (Compra c : compras) {
			clientes.add(c.getPorCliente());
		}
		Collections.sort(clientes);
		String retorno = "";
		for(int i = 0; i< clientes.size(); i++) {
			if (retorno.equals("")) {
				retorno += clientes.get(i);
			} else {
				retorno += " | " + clientes.get(i);
			}	
		}
			
		return retorno;
	}
	/**
	 * @return Retorna uma string com as compras na ordem fornecedor
	 */
	public String ordenaPorFornecedor() {
		List<String> fornecedor = new ArrayList<>();
		for (Compra c : compras) {
			fornecedor.add(c.getPorFornecedor());
		}
		Collections.sort(fornecedor);
		String retorno = "";
		for(int i = 0; i< fornecedor.size(); i++) {
			if (retorno.equals("")) {
				retorno += fornecedor.get(i);
			} else {
				retorno += " | " + fornecedor.get(i);
			}	
		}
			
		return retorno;	
	}
	
	/**
	 * @return Retorna uma representação textual de todas as compras em ordem alfabética
	 */
	private String listarCompras() {
		String retorno = this.fornecedor + " | ";
		for (int i = 0; i < this.compras.size(); i++) {
			if(i != this.compras.size() - 1) {
				retorno += this.compras.get(i) + " | ";
			} else {
				retorno += this.compras.get(i);
			}
		}
		return retorno;
	}
	/**
	 * @return Retorna a todas as compras de um cliente em ordem alfabética
	 */
	@Override
	public String toString() {
		return listarCompras();
	}
	/**
	 * @return Número que identifica essa conta
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		return result;
	}
	/**
	 * @return Testa se um objeto é igual a esta Conta
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		return true;
	}
}
