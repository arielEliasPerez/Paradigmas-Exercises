package cuenta.excepciones;

import java.math.BigDecimal;

public class FondoInsuficienteException extends RuntimeException {
    private final BigDecimal saldoActual;
    private final BigDecimal montoSolicitado;

    public FondoInsuficienteException(String mensaje, BigDecimal saldoActual, BigDecimal montoSolicitado) {
        super(mensaje);
        this.saldoActual = saldoActual;
        this.montoSolicitado = montoSolicitado;
    }
    
    public FondoInsuficienteException() {
		this("Fondos insuficientes", null, null);
	}
    
    public FondoInsuficienteException(String mensaje) {
    	this(mensaje, null, null);
    }
    
    public FondoInsuficienteException(BigDecimal saldoActual, BigDecimal montoSolicitado) {
		this("Fondos insuficientes", saldoActual, montoSolicitado);
	}

    @Override
    public String getMessage() {
    	String saldoStr = (this.saldoActual == null) ? "N/A" : this.saldoActual.toString();
    	String montoStr = (this.montoSolicitado == null) ? "N/A" : this.montoSolicitado.toString();
    		
        return super.getMessage() + " | Saldo actual: " + saldoStr + ", Monto solicitado: " + montoStr;
    }
}
