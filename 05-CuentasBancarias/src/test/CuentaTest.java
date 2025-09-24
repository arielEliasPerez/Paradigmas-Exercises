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
	
	
	@Test
	void transferirTest() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		cuentaOrigen.depositar(new BigDecimal(1000)); // deposito inicial
		cuentaDestino.depositar(new BigDecimal(500));  // deposito inicial
		
		// Transferencia de 300 desde cuentaOrigen a cuentaDestino
		BigDecimal montoTransferencia = new BigDecimal(300);
		BigDecimal expectedSaldoOrigen = new BigDecimal(700);
		BigDecimal expectedSaldoDestino = new BigDecimal(800);
		
		cuentaOrigen.transferir(montoTransferencia, cuentaDestino);
		
		assertEquals(expectedSaldoOrigen, cuentaOrigen.consultarSaldo());
		assertEquals(expectedSaldoDestino, cuentaDestino.consultarSaldo());
	}
	
	@Test
	void transferirFondosInsuficientesTest() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		cuentaOrigen.depositar(new BigDecimal(200)); // deposito inicial
		cuentaDestino.depositar(new BigDecimal(500));  // deposito inicial
		
		BigDecimal montoTransferencia = new BigDecimal(300);
		
		assertThrows(FondoInsuficienteException.class, () -> {
			cuentaOrigen.transferir(montoTransferencia, cuentaDestino);
		});
		
		// Verificar que los saldos no hayan cambiado
		assertEquals(new BigDecimal(200), cuentaOrigen.consultarSaldo());
		assertEquals(new BigDecimal(500), cuentaDestino.consultarSaldo());
	}
	
	@Test
	void transferirMontoNegativoTest() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		cuentaOrigen.depositar(new BigDecimal(500)); // deposito inicial
		cuentaDestino.depositar(new BigDecimal(500));  // deposito inicial
		
		BigDecimal montoTransferencia = new BigDecimal(-100);
		
		assertThrows(MontoNegativoException.class, () -> {
			cuentaOrigen.transferir(montoTransferencia, cuentaDestino);
		});
		
		// Verificar que los saldos no hayan cambiado
		assertEquals(new BigDecimal(500), cuentaOrigen.consultarSaldo());
		assertEquals(new BigDecimal(500), cuentaDestino.consultarSaldo());
	}
}