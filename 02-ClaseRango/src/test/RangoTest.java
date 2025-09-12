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
		Rango rangoBase = Rango.cerrado(-1.0, 1.0);
		
		Rango rangoInterno = Rango.abierto(-0.5, 0.5);
		Rango rangoParcial = Rango.abierto(-2.0, 0.0);
		Rango rangoExterno = Rango.cerrado(-2.0, 2.0);
		Rango rangoLimiteFinal = Rango.abiertoCerrado(0.0, 1.0);
		Rango rangoLimiteInicio = Rango.cerradoAbierto(-1.0, 0.0);
		
		assertTrue(rangoBase.contiene(rangoInterno));
		assertFalse(rangoBase.contiene(rangoParcial));
		assertFalse(rangoBase.contiene(rangoExterno));
		assertTrue(rangoBase.contiene(rangoLimiteFinal));
		assertTrue(rangoBase.contiene(rangoLimiteInicio));
	}
	
	@Test
	void testContieneRangosIguales() {
		Rango rangoAbierto = Rango.abierto(-1.0, 1.0);
		Rango rangoCerrado = Rango.cerrado(-1.0, 1.0);
		
		assertFalse(rangoAbierto.contiene(rangoCerrado));
		assertTrue(rangoCerrado.contiene(rangoAbierto));
	}
	
	@Test
	void testHayInterseccion() {
		Rango rangoBase = Rango.cerrado(-1.0, 1.0);
		
		Rango rangoParcial = Rango.abierto(0.5, 2.0);
		Rango rangoExterno = Rango.cerrado(2.0, 3.0);
		Rango rangoInternoAbierto = Rango.abierto(-0.5, 0.5);
		Rango rangoInternoCerrado = Rango.cerrado(-0.5, 0.5);
		Rango rangoLimiteInicioAbierto = Rango.abierto(1.0, 10.0);
		Rango rangoLimiteInicioCerrado = Rango.cerrado(1.0, 10.0);
		Rango rangoLimiteFinCerrado = Rango.cerrado(-10.0, -1.0);
		Rango rangoLimiteFinAbierto = Rango.abierto(-10.0, -1.5);
		Rango rangoIgualInicioAbierto = Rango.abierto(-1.0, 0.0);
		Rango rangoIgualInicioCerrado = Rango.cerrado(-1.0, 0.0);
		Rango rangoIgualFinAbierto = Rango.abierto(0.0, 1.0);
		Rango rangoIgualFinCerrado = Rango.cerrado(0.0, 1.0);
		Rango rangoSinInterseccion1 = Rango.abierto(2.0, 3.0);
		
		assertTrue(rangoBase.hayInterseccion(rangoParcial));
		assertFalse(rangoBase.hayInterseccion(rangoExterno));
		assertTrue(rangoBase.hayInterseccion(rangoInternoAbierto));
		assertTrue(rangoBase.hayInterseccion(rangoInternoCerrado));
		assertFalse(rangoBase.hayInterseccion(rangoLimiteInicioAbierto));
		assertTrue(rangoBase.hayInterseccion(rangoLimiteInicioCerrado));
		assertFalse(rangoBase.hayInterseccion(rangoLimiteFinAbierto));
		assertTrue(rangoBase.hayInterseccion(rangoLimiteFinCerrado));
		assertTrue(rangoBase.hayInterseccion(rangoIgualInicioAbierto));
		assertTrue(rangoBase.hayInterseccion(rangoIgualInicioCerrado));
		assertTrue(rangoBase.hayInterseccion(rangoIgualFinAbierto));
		assertTrue(rangoBase.hayInterseccion(rangoIgualFinCerrado));
		assertFalse(rangoBase.hayInterseccion(rangoSinInterseccion1));
	}
	
	@Test
	void testCompareTo() {
		Rango rango1 = Rango.cerrado(-1.0, 1.0);
		Rango rango2 = Rango.abierto(-2.0, 0.0);
		Rango rango3 = Rango.abierto(-1.0, 2.0);
		Rango rango4 = Rango.abierto(-1.0, 1.0);
		Rango rango5 = Rango.cerrado(-1.0, 1.0);
		Rango rango6 = Rango.cerrado(3.0, 5.0);
		
		assertTrue(rango1.compareTo(rango2) > 0);  // rango1 inicia después que rango2
		assertTrue(rango1.compareTo(rango3) < 0);  // mismo inicio, pero rango1 termina antes que rango3
		assertTrue(rango1.compareTo(rango4) < 0);  // mismo inicio y fin, pero rango1 incluye el fin
		assertEquals(0, rango1.compareTo(rango5)); // mismos atributos
		assertTrue(rango1.compareTo(rango6) < 0);  // rango1 termina antes que rango6
	}
	
	@Test
	void testOrdenar() {
		Rango rango1 = Rango.cerrado(1.0, 3.0);
		Rango rango2 = Rango.abierto(-2.0, 0.0);
		Rango rango3 = Rango.abierto(0.5, 2.0);
		Rango rango4 = Rango.cerrado(-1.0, 1.0);
		Rango[] rangos = {rango1, rango2, rango3, rango4};
		
		Rango[] rangosOrdenados = Rango.ordenar(rangos);
		
		Rango[] expected = {rango2, rango4, rango3, rango1};
		
		assertArrayEquals(expected, rangosOrdenados);
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
	
	@Test
	void testSumar() {
		Rango rango1 = Rango.cerrado(1.0, 3.0);
		Rango rango2 = Rango.abierto(2.0, 4.0);
		Rango rango3 = Rango.abiertoCerrado(5.0, 6.0);
		Rango rango4 = Rango.cerradoAbierto(0.0, 1.0);
		
		Rango suma1 = rango1.sumar(rango1);
		Rango suma2 = rango1.sumar(rango2); 
		Rango suma3 = rango1.sumar(rango3);
		Rango suma4 = rango1.sumar(rango4);
		
		Rango expectedSuma1 = Rango.cerrado(1.0, 3.0);
		Rango expectedSuma2 = Rango.cerradoAbierto(1.0, 4.0);
		Rango expectedSuma3 = Rango.cerrado(1.0, 6.0);
		Rango expectedSuma4 = Rango.cerradoAbierto(1.0, 1.0);
		
		assertEquals(expectedSuma1, suma1);
		assertEquals(expectedSuma2, suma2);
		assertEquals(expectedSuma3, suma3);
		assertEquals(expectedSuma4, suma4);
	}
	
	@Test
	void testInterseccion() {
		Rango rango1 = Rango.cerrado(1.0, 3.3);
		Rango rango2 = Rango.abierto(2.0, 4.0);
		Rango rango3 = Rango.abierto(3.3, 6.0);
		Rango rango4 = Rango.cerradoAbierto(0.0, 1.0);
		Rango rango5 = Rango.cerradoAbierto(1.0, 3.3);
		Rango rango6 = Rango.abierto(1.0, 4.0);
		
		Rango interseccion1 = rango1.interseccion(rango1);
		Rango interseccion2 = rango1.interseccion(rango2);
		Rango interseccion3 = rango1.interseccion(rango3);
		Rango interseccion4 = rango1.interseccion(rango4);
		Rango interseccion5 = rango1.interseccion(rango5);
		Rango interseccion6 = rango1.interseccion(rango6);
		
		Rango expectedInterseccion1 = Rango.cerrado(1.0, 3.3);
		Rango expectedInterseccion2 = Rango.abiertoCerrado(2.0, 3.3);
		Rango expectedInterseccion3 = Rango.abierto(0, 0);
		Rango expectedInterseccion4 = Rango.abierto(0, 0);
		Rango expectedInterseccion5 = Rango.cerradoAbierto(1.0, 3.3);
		Rango expectedInterseccion6 = Rango.abiertoCerrado(1.0, 3.3);
		
		
		assertEquals(expectedInterseccion1, interseccion1);
		assertEquals(expectedInterseccion2, interseccion2);
		assertEquals(expectedInterseccion3, interseccion3);
		assertEquals(expectedInterseccion4, interseccion4);
		assertEquals(expectedInterseccion5, interseccion5);
	}
	
	@Test
	void testDesplazar() {
		Rango rango = Rango.cerrado(1.0, 3.0);
		Rango desplazadoPositivo = rango.desplazar(2.0);
		Rango desplazadoNegativo = rango.desplazar(-1.0);
		
		Rango expectedPositivo = Rango.cerrado(3.0, 5.0);
		Rango expectedNegativo = Rango.cerrado(0.0, 2.0);
		
		assertEquals(expectedPositivo, desplazadoPositivo);
		assertEquals(expectedNegativo, desplazadoNegativo);
	}
		
}
