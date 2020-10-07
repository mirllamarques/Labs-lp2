package agenda;

/**
 * Representa um contato na agenda
 * @author Mirlla Marques Santana Alves - 119210401
 */
public class Contato {
	
	private int posicao;
	private String nome;
	private String sobrenome;
	private String telefone1;
	private String telefone2;
	private String telefone3;
	private String prioritario;
	private String zap;
	
	/**
	 * Constrói um contato
	 * @param posicao Posição em que ele se encontra na agenda
	 * @param nome Nome do contato
	 * @param sobrenome Sobrenome do contato
	 * @param num1 Telefone 1 do contato
	 * @param num2 Telefone 2 do contato
	 * @param num3 Telefone 3 do contato
	 * @param prioridade Qual telefone é o prioritário, caso não seja informado
	 * o primeiro telefone é o prioritário
	 * @param whatsapp Qual telefone é zap, se houver
	 */
	public Contato(int posicao, String nome, String sobrenome, String num1, String num2, String num3, String prioridade, String whatsapp) {
		this.posicao = posicao -1;
		if (nome != null) {
			if (nome.trim().equals("")) {
				throw new IllegalArgumentException();
			} else {
				this.nome = nome.trim();
			}
		} else {
			throw new NullPointerException();
		} if (sobrenome != null) {
			if (sobrenome.trim().equals("")) {
				throw new IllegalArgumentException();
			} else {
				this.sobrenome = sobrenome.trim();
			}
		} else {
			throw new NullPointerException();
		} if (num1 != null) {
			if (num1.trim().equals("")) {
				throw new IllegalArgumentException();
			} else {
				this.telefone1 = num1.trim();
			}
		} else {
			throw new NullPointerException();
		} if (num2 != null) {
			this.telefone2 = num2.trim();
		} else {
			throw new NullPointerException();
		} if (num3 != null) {
			this.telefone3 = num3.trim();
		} else {
			throw new NullPointerException();
		} if (prioridade != null) {
			this.prioritario = prioridade.trim();
		} else {
			throw new NullPointerException();
		} if (whatsapp != null) {
			this.zap = whatsapp.trim();
		}else {
			throw new NullPointerException();
		}
	}
	/**
	 * Define qual é o número prioritário, caso não seja passado,
	 * o padrão é o primeiro número cadastrado
	 * @return retorna qual é o número prioritário
	 */
	private String definePrioritario() {
		String num = this.telefone1;
		if (this.prioritario.equals("2")) {
			num = this.telefone2;
		} else if (this.prioritario.equals("3")) {
			num = this.telefone3;
		}
		return num;
	}
	/**
	 * Define qual é o zap caso não tenha retorna uma String "Não tem"
	 * @return retorna qual é o número de zap, se houver
	 */
	private String defineZap() {
		String num = "";
		if (this.zap.equals("2")) {
			num = this.telefone2;
		} else if (this.zap .equals("3")) {
			num = this.telefone3;
		} else if (this.zap .equals("1")) {
			num = this.telefone1;
		} else {
			num = "Não tem";
		}
		return num;
	}
	/**
	 * Formata o número pioritário para o formato
	 * Nome Sobrenome - numero prioritário
	 * @return Retorna o número prioritário
	 */
	public String getPrioritario() {
		return this.nome + " " + this.sobrenome + " - " + definePrioritario();
	}
	/**
	 * Formata o número zap para o formato
	 * Nome Sobrenome - numero zap ou 
	 * Nome Sobrenome - Não tem 
	 * @return Retorna o número prioritário
	 */
	public String getZap() {
		return this.nome + " " + this.sobrenome + " - " + defineZap();
	}
	/**
	 * Formata o nome e a posição no formato
	 * posição - Nome Sobrenome
	 * @return a formatação do nome da posição
	 */
	public String exibirContato() {
		int posicao = this.posicao + 1;
		return posicao + " - " + this.nome + " " + this.sobrenome;
	}
	/**
	 * @Override
	 *@return Retorna os dados do contato
	 */
	public String toString() {
		String nomeCompleto = this.nome + " " + this.sobrenome + "\n"; 
		String prio = definePrioritario() + " (prioritário)\n";
		String zap = defineZap() + " (zap)\n";
		return nomeCompleto + prio + zap;
	}
}
