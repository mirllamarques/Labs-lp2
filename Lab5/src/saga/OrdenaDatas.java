package saga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Essa classe tem por objetivo manter um repositório com todas as compras realizadas pelos clientes e ordená-las 
 * pelas datas, da mais antiga até a mais recente
 * @author Mirlla Marques Santana Alves
 */
public class OrdenaDatas {
	/**
	 * Repositório com todas as compras do SAGA
	 */
	private ArrayList<Compra> compras;
	/**
	 * Contrói o ordenador de datas
	 */
	public OrdenaDatas() {
		this.compras = new ArrayList<>();
	}
	/**
	 * Adiciona uma compra ao repositório de compras
	 * @param compra
	 */
	public void adicionaCompra(Compra compra) {
		this.compras.add(compra);
	}
	/**
	 * @return Retorna uma string com as compras iniciando pela data
	 */
	public String getData() {
		Comparator<Compra> ordenaData= Comparator.comparing(Compra::getDate).thenComparing(Compra::getCliente).thenComparing(Compra::getFornecedor).thenComparing(Compra::getDescricao);
		 Collections.sort(this.compras, ordenaData);
	     String retorno = "";
	     for(int i = 0 ; i< this.compras.size(); i++) {
	    	 if(retorno.equals("")) {
	             retorno += this.compras.get(i).getData();
	         }else {
	           retorno += " | " + this.compras.get(i).getData();
	        }
	     }
	     return retorno;
	}
}
