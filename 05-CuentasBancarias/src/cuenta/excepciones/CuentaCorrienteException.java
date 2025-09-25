package cuenta.excepciones;

import cuenta.CuentaCorriente;

public class CuentaCorrienteException extends RuntimeException {
	private CuentaCorriente cuentaCorriente;
	
	public CuentaCorrienteException(String mensaje) {
		super(mensaje);
		this.cuentaCorriente = null;
	}
	
	public CuentaCorrienteException() {
		this("Error en la cuenta corriente");
	}
	
	public CuentaCorrienteException(String mensaje, CuentaCorriente cuentaCorriente) {
		super(mensaje);
		this.cuentaCorriente = cuentaCorriente;
	}
	
	public CuentaCorrienteException(CuentaCorriente cuentaCorriente) {
		this("Error en la cuenta corriente", cuentaCorriente);
	}
	
	@Override
	public String getMessage() {
		String cuentaStr = (this.cuentaCorriente == null) ? "N/A" : 
			this.cuentaCorriente.getSaldo().toString();
		
		return super.getMessage() + " | Cuenta Corriente: " + cuentaStr;
	}
}
