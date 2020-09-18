package lab2;

/**
 * Representação de um controle da saúde de um aluno
 * em que o aluno atualiza sua situação de saúde
 * física e mental e pode ver um balanço geral de 
 * sua saúde
 * @author Mirlla Marques
 *
 */
public class Saude {
	/**
	 * Status de saúde mental podendo ter duas categorias:
	 * fraca ou boa
	 */
	private String saudeMental;
	/**
	 * Status de saúde física podendo ter duas categorias:
	 * fraca ou boa
	 */
	private String saudeFisica;
	/**
	 * Constói um status de saúde do aluno com valores
	 * de saúde a serem definidos
	 */
	public Saude() {
		this.saudeMental = "";
		this.saudeFisica = "";
	}
	/**
	 * Atualiza a situação de saúde mental do aluno
	 * @param valor Situação atual da saúde mental do aluno
	 */
	public void defineSaudeMental(String valor) {
		this.saudeMental = valor;
	}
	/**
	 * Atualiza a situação de saúde física do aluno
	 * @param valor Situação atual da saúde física do aluno
	 */
	public void defineSaudeFisica(String valor) {
		this.saudeFisica = valor;
	}
	/**
	 * Faz uma avaliação do status geral de saúde do aluno
	 * baseado na saúde física e mental
	 * @return Retorna o status de saúde geral do aluno
	 */
	public String getStatusGeral() {
		if (this.saudeMental.equals("fraca") && this.saudeFisica.equals("fraca")) {
			return "fraca";
		} else if (this.saudeMental.equals("fraca") && this.saudeFisica.equals("boa")) {
			return "ok";
		} else if (this.saudeMental.equals("boa") && this.saudeFisica.equals("fraca")) {
			return "ok";
		} else {
			return "boa";
		}
	}
}
