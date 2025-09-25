package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import cuenta.CuentaBancaria;
import cuenta.excepciones.CuentaAhorroException;
import cuenta.excepciones.CuentaCorrienteException;
import cuenta.excepciones.FondoInsuficienteException;
import cuenta.excepciones.MontoNegativoException;

class CuentaBancariaTest {

    @Test
    void estadoInicialTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        assertEquals(BigDecimal.ZERO, cuenta.consultarSaldoTotal());
        assertEquals(BigDecimal.ZERO, cuenta.consultarSaldoCuentaCorriente());
        assertThrows(CuentaAhorroException.class, cuenta::consultarSaldoCuentaDeAhorros);
    }

    @Test
    void depositarEnCuentaCorrienteTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        assertEquals(new BigDecimal(1000), cuenta.consultarSaldoCuentaCorriente());
        assertEquals(new BigDecimal(1000), cuenta.consultarSaldoTotal());
    }

    @Test
    void debitarEnCuentaCorrienteTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        cuenta.debitar(new BigDecimal(500));
        assertEquals(new BigDecimal(500), cuenta.consultarSaldoCuentaCorriente());
        assertEquals(new BigDecimal(500), cuenta.consultarSaldoTotal());
    }

    @Test
    void debitarMontoNegativoEnCuentaCorrienteTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        assertThrows(MontoNegativoException.class, () -> cuenta.debitar(new BigDecimal(-100)));
    }

    @Test
    void debitarFondosInsuficientesEnCuentaCorrienteTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        assertThrows(IllegalArgumentException.class, () -> cuenta.debitar(new BigDecimal(100)));
    }

    @Test
    void transferirEntreCuentasTest() {
        CuentaBancaria cuenta1 = new CuentaBancaria();
        CuentaBancaria cuenta2 = new CuentaBancaria();
        cuenta1.depositar(new BigDecimal(1000));
        cuenta1.transferir(new BigDecimal(400), cuenta2);
        assertEquals(new BigDecimal(600), cuenta1.consultarSaldoCuentaCorriente());
        assertEquals(new BigDecimal(400), cuenta2.consultarSaldoCuentaCorriente());
    }

    @Test
    void abrirCuentaDeAhorrosTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.abrirCuentaDeAhorros();
        assertEquals(BigDecimal.ZERO, cuenta.consultarSaldoCuentaDeAhorros());
    }

    @Test
    void abrirCuentaDeAhorrosConMontoInicialTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        cuenta.abrirCuentaDeAhorros(new BigDecimal(500));
        assertEquals(new BigDecimal(500), cuenta.consultarSaldoCuentaDeAhorros());
        assertEquals(new BigDecimal(500), cuenta.consultarSaldoCuentaCorriente());
        assertEquals(new BigDecimal(1000), cuenta.consultarSaldoTotal());
    }

    @Test
    void abrirCuentaDeAhorrosDosVecesTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.abrirCuentaDeAhorros();
        assertThrows(CuentaAhorroException.class, cuenta::abrirCuentaDeAhorros);
    }

    @Test
    void reservarSaldoACuentaDeAhorrosTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        cuenta.abrirCuentaDeAhorros();
        cuenta.reservarSaldoACuentaDeAhorros(new BigDecimal(400));
        assertEquals(new BigDecimal(600), cuenta.consultarSaldoCuentaCorriente());
        assertEquals(new BigDecimal(400), cuenta.consultarSaldoCuentaDeAhorros());
        assertEquals(new BigDecimal(1000), cuenta.consultarSaldoTotal());
    }

    @Test
    void reintegrarSaldoDesdeCuentaDeAhorrosTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        cuenta.abrirCuentaDeAhorros(new BigDecimal(500));
        cuenta.reintegrarSaldoDesdeCuentaDeAhorros(new BigDecimal(200));
        assertEquals(new BigDecimal(700), cuenta.consultarSaldoCuentaCorriente());
        assertEquals(new BigDecimal(300), cuenta.consultarSaldoCuentaDeAhorros());
        assertEquals(new BigDecimal(1000), cuenta.consultarSaldoTotal());
    }

    @Test
    void reservarSaldoSinCuentaDeAhorrosTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        assertThrows(CuentaAhorroException.class, () -> cuenta.reservarSaldoACuentaDeAhorros(new BigDecimal(100)));
    }

    @Test
    void reintegrarSaldoSinCuentaDeAhorrosTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(new BigDecimal(1000));
        assertThrows(CuentaAhorroException.class, () -> cuenta.reintegrarSaldoDesdeCuentaDeAhorros(new BigDecimal(100)));
    }

    @Test
    void consultarSaldoCuentaDeAhorrosSinCuentaTest() {
        CuentaBancaria cuenta = new CuentaBancaria();
        assertThrows(CuentaAhorroException.class, cuenta::consultarSaldoCuentaDeAhorros);
    }
}
