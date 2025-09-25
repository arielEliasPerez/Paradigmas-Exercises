package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import cuenta.CuentaDeAhorros;
import cuenta.excepciones.MontoNegativoException;
import cuenta.excepciones.FondoInsuficienteException;

class CuentaDeAhorrosTest {

	@Test
	void depositarEnCuentaDeAhorrosTest() {
		CuentaDeAhorros cuentaAhorros = new CuentaDeAhorros();
		cuentaAhorros.depositar(new BigDecimal(1500));
		assertEquals(new BigDecimal(1500), cuentaAhorros.getSaldo());
	}

	@Test
	void depositarMontoNegativoEnCuentaDeAhorrosTest() {
		CuentaDeAhorros cuentaAhorros = new CuentaDeAhorros();
		assertThrows(MontoNegativoException.class, () -> {
			cuentaAhorros.depositar(new BigDecimal(-100));
		});
	}

	@Test
	void debitarDeCuentaDeAhorrosTest() {
		CuentaDeAhorros cuentaAhorros = new CuentaDeAhorros(new BigDecimal(1000));
		cuentaAhorros.debitar(new BigDecimal(500));
		assertEquals(new BigDecimal(500), cuentaAhorros.getSaldo());
	}

	@Test
	void debitarMontoMayorAlSaldoEnCuentaDeAhorrosTest() {
		CuentaDeAhorros cuentaAhorros = new CuentaDeAhorros(new BigDecimal(1000));
		assertThrows(FondoInsuficienteException.class, () -> {
			cuentaAhorros.debitar(new BigDecimal(1500));
		});
	}

	@Test
	void consultarSaldoDeCuentaDeAhorrosTest() {
		CuentaDeAhorros cuentaAhorros = new CuentaDeAhorros(new BigDecimal(2000));
		assertEquals(new BigDecimal(2000), cuentaAhorros.getSaldo());
	}
}