package cuenta.excepciones;

import java.math.BigDecimal;

public class MontoNegativoException extends RuntimeException {
    private BigDecimal montoNegativo;
	
    public MontoNegativoException(String mensaje, BigDecimal montoNegativo) {
		super(mensaje);
		this.montoNegativo = montoNegativo;
	}
    
    public MontoNegativoException() {
		this("El monto no puede ser negativo", null);
	}
	
	public MontoNegativoException(BigDecimal montoNegativo) {
		this("El monto no puede ser negativo", montoNegativo);
	}
    
	public MontoNegativoException(String mensaje) {
        this(mensaje, null);
    }
	
	@Override
	public String getMessage() {
		String montoStr = (this.montoNegativo == null) ? "N/A" : this.montoNegativo.toString();
		return super.getMessage() + " | Monto: " + montoStr;
	}
}
