package cuenta;

import java.math.BigDecimal;
import cuenta.excepciones.MontoNegativoException;
import cuenta.excepciones.FondoInsuficienteException;

public class Cuenta {
	private BigDecimal saldo;    // mejor usar BigDecimal para dinero
	
	public Cuenta() {
		saldo = BigDecimal.ZERO;
	}
	
	private boolean esMontoPositivo(BigDecimal monto) {
		return monto.compareTo(BigDecimal.ZERO) > 0;
	}
	
	public void depositar(BigDecimal monto) {
		if (esMontoPositivo(monto)) {
			saldo = saldo.add(monto);
		} else {
			throw new MontoNegativoException("El monto a depositar debe ser positivo");
		}
	}
	
	public void debitar(BigDecimal monto) {
		if (!esMontoPositivo(monto)) {
			throw new MontoNegativoException("El monto a extraer debe ser positivo");
		}
		if (monto.compareTo(saldo) > 0) {
			throw new FondoInsuficienteException("Fondos insuficientes", saldo, monto);
		}
		this.saldo = this.saldo.subtract(monto);
	}
	
	public BigDecimal consultarSaldo() {
		return this.saldo;
	}
}