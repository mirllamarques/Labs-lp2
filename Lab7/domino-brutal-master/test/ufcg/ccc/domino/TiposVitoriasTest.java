package ufcg.ccc.domino;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TiposVitoriasTest {
	
	TiposVitorias J1;
	TiposVitorias J2;

	@BeforeEach
	void inicia() {
		this.J1 = new TiposVitorias();
		this.J2 = new TiposVitorias();
	}

	@Test
	void testAddGetCarrocao() {
		this.J1.add(2);
		assertEquals(1, J1.getCarrocao());
		this.J2.add(2);
		this.J2.add(2);
		assertEquals(2, this.J2.getCarrocao());
	}

	@Test
	void testAddGetLaELo() {
		this.J1.add(3);
		assertEquals(1, this.J1.getLaELo());
		this.J2.add(3);
		this.J2.add(3);
		assertEquals(2, this.J2.getLaELo());
	}
	
	@Test
	void testAddGetLaELoCruzado() {
		this.J1.add(6);
		assertEquals(1, this.J1.getLaLoCruzado());
		this.J2.add(6);
		this.J2.add(6);
		assertEquals(2, this.J2.getLaLoCruzado());
	}

	@Test
	void testAddGetNormal() {
		this.J1.add(1);
		assertEquals(1, this.J1.getNormal());
		this.J2.add(1);
		this.J2.add(1);
		assertEquals(2, this.J2.getNormal());
	}

	@Test
	void testAddGetTotal() {
		this.J1.add(6);
		this.J1.add(1);
		this.J1.add(3);
		this.J1.add(10);
		assertEquals(10, this.J1.getTotal());
		this.J2.add(3);
		this.J2.add(2);
		assertEquals(5, this.J2.getTotal());
	}

}
