package cuenta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cuenta.excepciones.MontoNegativoException;
import cuenta.excepciones.FondoInsuficienteException;


public abstract class Cuenta {
	protected BigDecimal saldo;
	private final List<Transaccion> transacciones = new ArrayList<>();

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

	// Nuevo método para depositar con motivo
	public void depositar(BigDecimal monto, String motivo) {
		validarMontoPositivo(monto);
		this.saldo = this.saldo.add(monto);
		transacciones.add(new Transaccion(Transaccion.Tipo.ACREDITACION, monto, motivo));
	}

	// Sobrecarga para compatibilidad
	public void depositar(BigDecimal monto) {
		depositar(monto, "Operación sin motivo");
	}

	// Nuevo método para debitar con motivo
	public void debitar(BigDecimal monto, String motivo) {
		validarMontoPositivo(monto);
		validarFondoSuficiente(monto);
		this.saldo = this.saldo.subtract(monto);
		transacciones.add(new Transaccion(Transaccion.Tipo.DEBITO, monto, motivo));
	}

	// Sobrecarga para compatibilidad
	public void debitar(BigDecimal monto) {
		debitar(monto, "Operación sin motivo");
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}
	
	public List<Transaccion> getTransacciones() {
        return Collections.unmodifiableList(transacciones);
    }
	

}