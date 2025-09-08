package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rango.Rango;

class RangoTest {

	@Test
	void testContieneValor() {
		Rango rangoCerrado = Rango.cerrado(-1.0, 1.0);
		assertTrue(rangoCerrado.contiene(0.0));
		assertTrue(rangoCerrado.contiene(-1.0));
		assertTrue(rangoCerrado.contiene(1.0));
		assertFalse(rangoCerrado.contiene(-1.5));
		assertFalse(rangoCerrado.contiene(1.5));

		Rango rangoAbierto = Rango.abierto(-2.0, 3.5);
		assertTrue(rangoAbierto.contiene(0.0));
		assertFalse(rangoAbierto.contiene(-2.0));
		assertFalse(rangoAbierto.contiene(3.5));
		assertFalse(rangoAbierto.contiene(-2.5));
		assertFalse(rangoAbierto.contiene(4.0));
	}
	
	@Test
	void testContieneRango() {
		Rango rangoCerrado = Rango.cerrado(-1.0, 1.0);
		Rango rangoDentro = Rango.abierto(-0.5, 0.5);
		Rango rangoFuera = Rango.abierto(-2.0, 2.0);
		
		assertTrue(rangoCerrado.contiene(rangoDentro));
		assertFalse(rangoCerrado.contiene(rangoFuera));
	}
	
	@Test
	void testHayInterseccion() {
		Rango rango1 = Rango.cerrado(-1.0, 1.0);
		Rango rango2 = Rango.abierto(0.5, 2.0);
		Rango rango3 = Rango.abierto(1.5, 3.0);
		
		assertTrue(rango1.hayInterseccion(rango2));
		assertFalse(rango1.hayInterseccion(rango3));
	}
	
	@Test
	void testToString() {
		Rango rangoCerrado = Rango.cerrado(-1.0, 1.0);
		Rango rangoAbierto = Rango.abierto(-2.0, 3.5);
		Rango rangoAbiertoCerrado = Rango.abiertoCerrado(0.0, 5.0);
		Rango rangoCerradoAbierto = Rango.cerradoAbierto(-3.0, 2.0);
		
		String expectedCerrado = "[-1.0, 1.0]";
		String expectedAbierto = "(-2.0, 3.5)";
		String expectedAbiertoCerrado = "(0.0, 5.0]";
		String expectedCerradoAbierto = "[-3.0, 2.0)";
		
		assertEquals(expectedCerrado, rangoCerrado.toString());
		assertEquals(expectedAbierto, rangoAbierto.toString());
		assertEquals(expectedAbiertoCerrado, rangoAbiertoCerrado.toString());
		assertEquals(expectedCerradoAbierto, rangoCerradoAbierto.toString());
	}
}
