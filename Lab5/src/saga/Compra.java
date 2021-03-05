package saga;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que gerencia as informações a respeito de uma compra no SAGA
 * @author Mirlla Marques Santana Alves
 */
public class Compra {
	/**
	 * Nome do cliente que comprou
	 */
	private String cliente;
	/**
	 * Nome do fornecedor que vendeu
	 */
	private String fornecedor;
	/**
	 * Nome do produto comprado
	 */
	private String nome;
	/**
	 * Descrição  do produto comprado
	 */
	private String descricao;
	/**
	 * Representação textual da data
	 */
	private String data;
	/**
	 * Objeto com a data da compra
	 */
	private Date date;
	/**
	 * Contrói uma compra no Saga
	 * @param cliente Nome do cliente
	 * @param fornecedor Nome do fornecedor
	 * @param nome Nome do produto
	 * @param descricao Descrição do produto
	 * @param data Data da compra
	 * @throws Exception Joga erro caso a data passada seja inválida
	 */
	public Compra(String cliente, String fornecedor,String nome, String descricao, String data) throws Exception {
		if (fornecedor == null) {
			throw new NullPointerException();
		} else if(fornecedor.trim().equals("")) {
			throw new IllegalArgumentException();
		} if (cliente == null) {
			throw new NullPointerException();
		} else if(cliente.trim().equals("")) {
			throw new IllegalArgumentException();
		} if (nome == null) {
			throw new NullPointerException();
		} else if(nome.trim().equals("")) {
			throw new IllegalArgumentException();
		} if (descricao == null) {
			throw new NullPointerException();
		} else if(descricao.trim().equals("")) {
			throw new IllegalArgumentException();
		} if (data == null) {
			throw new NullPointerException();
		} else if(data.trim().equals("")) {
			throw new IllegalArgumentException();
		}
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.descricao = descricao;
		this.nome = nome + " - " + data.replace("/", "-");
		this.data = data;
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.date = date.parse(data);
		} catch (ParseException e) {
			throw new Exception("Erro ao cadastrar compra: data invalida.");
		}
		
	}
	/**
	 * @return Retorna o nome do cliente
	 */
	public String getCliente() {
		return this.cliente;
	}
	/**
	 * @return Retorna o nome do fornecedor
	 */
	public String getFornecedor() {
		return this.fornecedor;
	}
	/**
	 * @return Retorna a descrição do produto
	 */
	public String getDescricao() {
		return this.descricao;
	}
	/**
	 * @return Retorn a data da compra
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * @return Retorna uma representação textual da compra no seguinte formato <cliente, fornecedor, descricao, data>
	 */
	public String getPorCliente() {
		return this.cliente+ ", " + this.fornecedor + ", " + this.descricao + ", " + this.data;
	}
	/**
	 * @return Retorna uma representação textual da compra no seguinte formato <fornecedor, cliente, descricao, data>
	 */
	public String getPorFornecedor() {
		return this.fornecedor + ", " + this.cliente + ", " +  this.descricao + ", " + this.data;
	}
	/**
	 * @return Retorna uma representação textual da compra no seguinte formato <data, fornecedor, cliente, descricao>
	 */
	public String getData() {
		return this.data + ", " + this.cliente + ", " + this.fornecedor + ", " + this.descricao;
	}
	/**
	 * Retorna uma representação textual dessa compra com o nome e data da compra
	 */
	@Override
	public String toString() {
		return this.nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Compra other = (Compra) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
