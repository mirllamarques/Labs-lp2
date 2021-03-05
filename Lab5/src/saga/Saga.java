package saga;

/**
 * Interface com os métodos que serão implementados nos controllers 
 * de cliente e fornecedor
 * @author Mirlla Marques Santana Alves
 */
public interface Saga {
	/**
	 * Remove um objeto a ser removido de um HashMap
	 * @param identificador Identificador do objeto a ser removido do HashMap
	 * @throws Exception 
	 */
	void remover(String identificador) throws Exception;
	/**
	 * @param identificador Chave que identifica o objeto
	 * @return Retorna a representação textual de um objeto
	 * @throws Exception 
	 */
	String toString(String cpf) throws Exception;
	/**
	 * @return Imprime todas as representações textuais dos objetos armazenados nos controllers
	 */
	String imprimeTodos();
}
