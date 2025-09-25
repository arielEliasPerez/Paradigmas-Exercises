package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import cuenta.CuentaCorriente;
import cuenta.excepciones.MontoNegativoException;

class CuentaCorrienteTest {

    @Test
    void depositarMontoPositivoTest() {
        CuentaCorriente cuenta = new CuentaCorriente(BigDecimal.ZERO, BigDecimal.TEN);
        cuenta.depositar(new BigDecimal(1000));
        assertEquals(new BigDecimal(1000), cuenta.getSaldo());
    }

    @Test
    void depositarMontoNegativoTest() {
        CuentaCorriente cuenta = new CuentaCorriente(BigDecimal.ZERO, BigDecimal.TEN);
        assertThrows(MontoNegativoException.class, () -> {
            cuenta.depositar(new BigDecimal(-500));
        });
    }

    @Test
    void debitarConSaldoSuficienteTest() {
        CuentaCorriente cuenta = new CuentaCorriente(new BigDecimal(1000), BigDecimal.TEN);
        cuenta.debitar(new BigDecimal(500));
        assertEquals(new BigDecimal(500), cuenta.getSaldo());
    }

    @Test
    void debitarConDescubiertoPermitidoTest() {
        CuentaCorriente cuenta = new CuentaCorriente(new BigDecimal(1000), new BigDecimal(500));
        cuenta.debitar(new BigDecimal(1400));
        assertEquals(new BigDecimal(-400), cuenta.getSaldo());
    }

    @Test
    void debitarExcedeDescubiertoTest() {
        CuentaCorriente cuenta = new CuentaCorriente(new BigDecimal(1000), new BigDecimal(500));
        assertThrows(IllegalArgumentException.class, () -> {
            cuenta.debitar(new BigDecimal(1600));
        });
    }

    @Test
    void debitarMontoNegativoTest() {
        CuentaCorriente cuenta = new CuentaCorriente(new BigDecimal(1000), BigDecimal.TEN);
        assertThrows(MontoNegativoException.class, () -> {
            cuenta.debitar(new BigDecimal(-100));
        });
    }

    @Test
    void consultarSaldoTest() {
        CuentaCorriente cuenta = new CuentaCorriente(new BigDecimal(2000), BigDecimal.TEN);
        assertEquals(new BigDecimal(2000), cuenta.getSaldo());
    }
}
