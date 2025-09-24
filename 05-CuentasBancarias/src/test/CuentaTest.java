package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import cuenta.Cuenta;
import cuenta.excepciones.MontoNegativoException;
import cuenta.excepciones.FondoInsuficienteException;

class CuentaTest {

	@Test
	void MiCuentaTest() {
		Cuenta miCuenta = new Cuenta();
		BigDecimal expectedSaldo = BigDecimal.ZERO;  
		assertEquals(expectedSaldo, miCuenta.consultarSaldo());		
	}
	
	@Test
	void depositoTest() {
		Cuenta miCuenta = new Cuenta();
		
		miCuenta.depositar(new BigDecimal(500)); // deposito inicial
		BigDecimal expectedSaldo = new BigDecimal(500);
		assertEquals(expectedSaldo, miCuenta.consultarSaldo());
		
		miCuenta.depositar(new BigDecimal(50));
		expectedSaldo = new BigDecimal(550);
		assertEquals(expectedSaldo, miCuenta.consultarSaldo());
		
		assertThrows(MontoNegativoException.class, () -> {
			miCuenta.depositar(new BigDecimal(-100));   // deposito negativo:
		});
	}
	
	@Test
	void debitarTest() {
		Cuenta miCuenta = new Cuenta();
		
		miCuenta.depositar(new BigDecimal(500)); // deposito inicial
		miCuenta.debitar(new BigDecimal(150));
		BigDecimal expectedSaldo = new BigDecimal(350);
		assertEquals(expectedSaldo, miCuenta.consultarSaldo());
		
		assertThrows(FondoInsuficienteException.class, () -> {
			miCuenta.debitar(new BigDecimal(400));   // retiro mayor al saldo:
		});
		
		assertThrows(MontoNegativoException.class, () -> {
			miCuenta.debitar(new BigDecimal(-100));   // retiro negativo:
		});
	}

}