package ufcg.ccc.domino;

/**
 * Classe responsável por armazenar as pontuações de um jogador
 * @author Mirlla Marques Santana Alves
 */
public class TiposVitorias {
	
	/**
	 * Pontuação total do jogador
	 */
	private int total;
	/**
	 * Quantas vitórias foram por carroções
	 */
	private int carrocao;
	/**
	 * Quantas vitórias foram por lá e ló
	 */
	private int laelo;
	/**
	 * Quantas vitórias foram lá e ló de carroção
	 */
	private int laelocruzado;
	/**
	 * Vitórias simples
	 */
	private int normal;
		
	/**
	 * Constrói o objeto TipoVitorias
	 */
	public TiposVitorias() {
		this.total = 0;
		this.carrocao = 0;
		this.laelo = 0;
		this.laelocruzado = 0;
		this.normal = 0;
	}
	
	/**
	 * @param pontos Pontos ganhos em uma partida
	 */
	public void add(int pontos) {
		if (pontos == 1) {
			this.normal++;
			total += pontos;
		} else if (pontos ==2) {
			this.carrocao++;
			total += pontos;
		} else if (pontos == 3) {
			this.laelo++;
			total += pontos;
		} else if (pontos == 6) {
			this.laelocruzado ++;
			total += pontos;
		}
	}
		
	/**
	 * @return Retona quantas vezes o jogador ganhou com carroção
	 */
	public int getCarrocao() {
		return this.carrocao;
	}
		
	/**
	 * @return Retona quantas vezes o jogador ganhou por lá e ló
	 */
	public int getLaELo() {
		return this.laelo;
	}
	/**
	 * @return  Retona quantas vezes o jogador ganhou por lá e ló de carroção
	 */
	public int getLaLoCruzado() {
		return this.laelocruzado;
	}
		
	/**
	 * @return Retona quantas vezes o jogador ganhou vitórias simples
	 */
	public int getNormal() {
		return this.normal;
	}
	
	/**
	 * @return Retorna quantos pontos o jogador fez
	 */
	public int getTotal() {
		return this.total;
	}
}
