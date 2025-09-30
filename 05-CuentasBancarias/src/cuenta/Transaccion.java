package cuenta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {
    public enum Tipo { DEBITO, ACREDITACION }

    private final Tipo tipo;
    private final BigDecimal monto;
    private final String motivo;
    private final LocalDateTime fecha;

    public Transaccion(Tipo tipo, BigDecimal monto, String motivo) {
        this.tipo = tipo;
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = LocalDateTime.now();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
