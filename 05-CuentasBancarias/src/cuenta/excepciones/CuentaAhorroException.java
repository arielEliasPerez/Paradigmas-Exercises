package cuenta.excepciones;

import cuenta.CuentaDeAhorros;

public class CuentaAhorroException extends RuntimeException {
	private CuentaDeAhorros cuentaDeAhorros;

	public CuentaAhorroException(String mensaje) {
		super(mensaje);
		this.cuentaDeAhorros = null;
	}

	public CuentaAhorroException() {
		this("Error en la cuenta de ahorros");
	}
	
	public CuentaAhorroException(String mensaje, CuentaDeAhorros cuentaDeAhorros) {
		super(mensaje);
		this.cuentaDeAhorros = cuentaDeAhorros;
	}
	
	public CuentaAhorroException(CuentaDeAhorros cuentaDeAhorros) {
		this("Error en la cuenta de ahorros", cuentaDeAhorros);
	}
	
	@Override
	public String getMessage() {
		String cuentaStr = (this.cuentaDeAhorros == null) ? "N/A" : 
			this.cuentaDeAhorros.getSaldo().toString();
		
		return super.getMessage() + " | Cuenta de Ahorros: " + cuentaStr;
	}
}
