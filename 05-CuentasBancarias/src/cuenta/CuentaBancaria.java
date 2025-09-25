package cuenta;

import java.math.BigDecimal;

import cuenta.excepciones.CuentaAhorroException;
import cuenta.excepciones.CuentaCorrienteException;

public class CuentaBancaria {
	private BigDecimal saldoTotal;
	private CuentaCorriente cuentaCorriente;
	private CuentaDeAhorros cuentaDeAhorros;

	public CuentaBancaria() {
		this.saldoTotal = BigDecimal.ZERO;
		this.cuentaCorriente = new CuentaCorriente(BigDecimal.ZERO, Cuenta.LIMITE_DESCUBIERTO_POR_DEFECTO);
		this.cuentaDeAhorros = null;
	}

	public BigDecimal consultarSaldoTotal() {
		return this.saldoTotal;
	}

	private void actualizarSaldoTotal() {
		BigDecimal saldoCuentaCorriente = cuentaCorriente != null ? cuentaCorriente.getSaldo() : BigDecimal.ZERO;
		BigDecimal saldoCuentaAhorros = cuentaDeAhorros != null ? cuentaDeAhorros.getSaldo() : BigDecimal.ZERO;
		this.saldoTotal = saldoCuentaCorriente.add(saldoCuentaAhorros);
	}

	// -------------Metodos para CuentaCorriente-----------------------

	public void depositar(BigDecimal monto) {
		this.cuentaCorriente.depositar(monto);
		actualizarSaldoTotal();
	}

	public void debitar(BigDecimal monto) {
		this.cuentaCorriente.debitar(monto);
		actualizarSaldoTotal();
	}

	public BigDecimal consultarSaldoCuentaCorriente() {
		return this.cuentaCorriente.getSaldo();
	}

	public void transferir(BigDecimal monto, CuentaBancaria cuentaDestino) {
		this.debitar(monto);
		cuentaDestino.depositar(monto);
	}

	private boolean esCuentaCorrienteActiva() {
		return this.cuentaCorriente != null;
	}

	private void validarCuentaCorrienteActiva() {
		if (!this.esCuentaCorrienteActiva()) {
			throw new CuentaCorrienteException("No existe una cuenta corriente asociada a esta cuenta");
		}
	}

	// -------------Metodos para cuenta de ahorros-----------------------
	
	public void abrirCuentaDeAhorros() {
		this.abrirCuentaDeAhorros(BigDecimal.ZERO);
	}

	public void abrirCuentaDeAhorros(BigDecimal montoInicial) {
		validarCuentaDeAhorrosCerrada();
		this.cuentaDeAhorros = new CuentaDeAhorros();
		reservarSaldoACuentaDeAhorros(montoInicial);
	}

	private void validarCuentaDeAhorrosCerrada() {
		if (this.esCuentaDeAhorrosActiva()) {
			throw new CuentaAhorroException("Ya existe una cuenta de ahorros asociada a esta cuenta");
		}
	}

	private boolean esCuentaDeAhorrosActiva() {
		return this.cuentaDeAhorros != null;
	}

	private void validarCuentaDeAhorrosActiva() {
		if (!this.esCuentaDeAhorrosActiva()) {
			throw new CuentaAhorroException("No existe una cuenta de ahorros asociada a esta cuenta");
		}
	}

	public BigDecimal consultarSaldoCuentaDeAhorros() {
		this.validarCuentaDeAhorrosActiva(); // lanza CuentaAhorroException si no hay cuenta de ahorros
		return this.cuentaDeAhorros.getSaldo();
	}

	public void reservarSaldoACuentaDeAhorros(BigDecimal monto) {
		this.validarCuentaDeAhorrosActiva();
		this.validarCuentaCorrienteActiva();
		this.cuentaCorriente.debitar(monto);
		this.cuentaDeAhorros.depositar(monto);
	}

	public void reintegrarSaldoDesdeCuentaDeAhorros(BigDecimal monto) {
		this.validarCuentaDeAhorrosActiva();
		this.validarCuentaCorrienteActiva();
		this.cuentaDeAhorros.debitar(monto);
		this.cuentaCorriente.depositar(monto);
	}
}
