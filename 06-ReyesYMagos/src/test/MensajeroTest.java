package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import reino.comunicacion.Mensaje;
import reino.comunicacion.Mensajero;
import reino.comunicacion.PrioridadMensaje;

class MensajeroTest {

	@Test
	void enviarYRecibirUnMensajeTest() {
		Mensajero mensajero = new Mensajero();
		
		Mensaje mensajeAEnviar = new Mensaje("Este es un mensaje de prueba.");
		mensajero.enviarMensaje(mensajeAEnviar);
		Mensaje mensajeRecibido = mensajero.recibirMensaje();
		
		assertNotNull(mensajeRecibido);
		assertEquals(PrioridadMensaje.NORMAL, mensajeRecibido.getPrioridad());
	}
	
	@Test
	void sinMensajesTest() {
		Mensajero mensajero = new Mensajero();
		
		Mensaje mensajeRecibido = mensajero.recibirMensaje();
		
		assertNull(mensajeRecibido);
	}
	
	@Test
	void enviarMensajeNuloTest() {
		Mensajero mensajero = new Mensajero();
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			mensajero.enviarMensaje(null);
		});
		
		String expectedMessage = "No se puede enviar un mensaje nulo.";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void enviarMensajesConPrioridadTest() {
		Mensajero mensajero = new Mensajero();
		
		Mensaje mensajeNormal1 = new Mensaje("Mensaje normal 1");
		Mensaje mensajeAlta1 = new Mensaje("Mensaje alta 1", PrioridadMensaje.ALTA);
		Mensaje mensajeNormal2 = new Mensaje("Mensaje normal 2");
		Mensaje mensajeAlta2 = new Mensaje("Mensaje alta 2", PrioridadMensaje.ALTA);
		
		mensajero.enviarMensaje(mensajeNormal1);
		mensajero.enviarMensaje(mensajeAlta1);
		mensajero.enviarMensaje(mensajeNormal2);
		mensajero.enviarMensaje(mensajeAlta2);
		
		Mensaje primerMensajeRecibido = mensajero.recibirMensaje();
		Mensaje segundoMensajeRecibido = mensajero.recibirMensaje();
		Mensaje tercerMensajeRecibido = mensajero.recibirMensaje();
		Mensaje cuartoMensajeRecibido = mensajero.recibirMensaje();
		
		assertEquals(mensajeAlta2, primerMensajeRecibido);
		assertEquals(mensajeAlta1, segundoMensajeRecibido);
		assertEquals(mensajeNormal1, tercerMensajeRecibido);
		assertEquals(mensajeNormal2, cuartoMensajeRecibido);
		assertNull(mensajero.recibirMensaje());
	}
}
