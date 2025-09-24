package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cuenta.Cuenta;

class CuentaTest {

	@Test
	void MiCuentaTest() {
		Cuenta miCuenta = new Cuenta();
		
		double expectedSaldo = 0;  //no es conveniente acceder directamente a los atributos
		
		assertEquals(expectedSaldo, miCuenta.saldo);		
	}
	
	void depositoTest() {
		Cuenta miCuenta = new Cuenta();
		
		miCuenta.saldo += 150;
		double expectedSaldo = 150;
		assertEquals(expectedSaldo, miCuenta.saldo);
		
		miCuenta.saldo += 50;
		expectedSaldo = 200;
		assertEquals(expectedSaldo, miCuenta.saldo);
		
		miCuenta.saldo += -300;
		expectedSaldo = -100;
		assertEquals(expectedSaldo, miCuenta.saldo);
		// No deberia permitir depositar valores negativos
	}
	
	void retiroTest() {
		Cuenta miCuenta = new Cuenta();
		
		miCuenta.saldo += 500; // deposito inicial
		miCuenta.saldo -= 150; // retiro
		double expectedSaldo = 350;
		assertEquals(expectedSaldo, miCuenta.saldo);
		
		miCuenta.saldo -= 50;
		expectedSaldo = 300;
		assertEquals(expectedSaldo, miCuenta.saldo);
		
		miCuenta.saldo -= 400; // retiro
		expectedSaldo = -100;
		assertEquals(expectedSaldo, miCuenta.saldo);
		// No deberia permitir retirar mas de lo que hay en la cuenta
	}

}
