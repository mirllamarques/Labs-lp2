package utilidades;

import java.util.Comparator;

import produto.Combo;

public class ComparatorCombo implements Comparator<Combo>{

	@Override
	public int compare(Combo c1, Combo c2) {
		String nome1 = c1.getNome();
		String nome2 = c2.getNome();
		
		return nome1.compareTo(nome2);
	}
}
