package cuenta;

import java.math.BigDecimal;
import cuenta.excepciones.MontoNegativoException;
import cuenta.excepciones.FondoInsuficienteException;


public abstract class Cuenta {
	protected BigDecimal saldo;
	
	public static final BigDecimal LIMITE_DESCUBIERTO_POR_DEFECTO = BigDecimal.TEN;
	
	public Cuenta() {
		this.saldo = BigDecimal.ZERO;
	}
	
	public Cuenta(BigDecimal monto) {
		validarMontoPositivo(monto);
		this.saldo = monto;
	}
	
	protected boolean esMontoPositivo(BigDecimal monto) {
		return monto.compareTo(BigDecimal.ZERO) >= 0;
	}

	protected void validarMontoPositivo(BigDecimal monto) {
		if (!esMontoPositivo(monto)) {
			throw new MontoNegativoException("El monto debe ser positivo", monto);
		}
	}

	public void validarFondoSuficiente(BigDecimal monto) {
		if (monto.compareTo(this.saldo) > 0) {
			throw new FondoInsuficienteException("Fondos insuficientes", saldo, monto);
		}
	}

	public void depositar(BigDecimal monto) {
		validarMontoPositivo(monto);
		this.saldo = this.saldo.add(monto);
	}

	public void debitar(BigDecimal monto) {
		validarMontoPositivo(monto);
		validarFondoSuficiente(monto);
		this.saldo = this.saldo.subtract(monto);
	}


	public BigDecimal getSaldo() {
		return this.saldo;
	}
	

}