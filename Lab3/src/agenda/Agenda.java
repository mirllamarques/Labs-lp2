package agenda;

import java.util.ArrayList;
/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author Mirlla Marques Santana Alves
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	
	private Contato[] repoContatos;
	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.repoContatos = new Contato[TAMANHO_AGENDA];
	}	
	/**
	 * Acessa o array list de contatos mantidos.
	 * @return O array list de contatos.
	 */
	public ArrayList<String> getContatos() {
		ArrayList<String> retorno = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < this.repoContatos.length; i++) {
			if (this.repoContatos[i] != null) {
				retorno.add(count, repoContatos[i].exibirContato());
				count ++;
			}
		}
		return retorno;
	}
	/**
	 * Acessa o array list de contatos prioritários.
	 * @return O array list de contatos prioritários.
	 */
	public ArrayList<String> getPrioritarios() {
		ArrayList<String> retorno = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < this.repoContatos.length; i++) {
			if (this.repoContatos[i] != null) {
				retorno.add(count, repoContatos[i].getPrioritario());
				count ++;
			}
		}
		return retorno;
	}
	/**
	 * Acessa o array list de contatos com zap.
	 * @return O array list de contatos com zap.
	 */
	public ArrayList<String> getZaps() {
		ArrayList<String> retorno = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < this.repoContatos.length; i++) {
			if (this.repoContatos[i] != null) {
				retorno.add(count, repoContatos[i].getZap());
				count ++;
			}
		}
		return retorno;
	}

	/**
	 * Acessa os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public String getContato(int posicao) {
		return this.repoContatos[posicao].toString();
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 */
	public void cadastraContato(int posicao, String nome, String sobrenome, String fone1, String fone2, String fone3, String fonePrio, String foneZap) {
		Contato contatinho = new Contato(posicao, nome, sobrenome, fone1, fone2, fone3, fonePrio, foneZap);
		this.repoContatos[posicao-1] = contatinho;
	}

}