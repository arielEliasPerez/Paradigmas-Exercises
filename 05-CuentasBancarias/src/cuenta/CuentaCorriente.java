package cuenta;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
	private BigDecimal limiteDescubierto;

	public CuentaCorriente(BigDecimal montoInicial, BigDecimal limiteDescubierto) {
		super(montoInicial);
		this.limiteDescubierto = limiteDescubierto;
	}

	@Override
	public void debitar(BigDecimal monto) {
		validarMontoPositivo(monto);

		BigDecimal saldoDisponible = this.saldo.add(this.limiteDescubierto);

		if (monto.compareTo(saldoDisponible) > 0) {
			throw new IllegalArgumentException("Fondos insuficientes");
		}

		this.saldo = this.saldo.subtract(monto);
	}

}