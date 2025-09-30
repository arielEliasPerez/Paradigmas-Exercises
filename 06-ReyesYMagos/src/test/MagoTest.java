package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import exceptions.MagoException;
import reino.magia.Hechizo;
import reino.magia.Mago;

class MagoTest {

	@Test
	void hechizosEnComunTest() {
		Mago mago1 = new Mago();
		Mago mago2 = new Mago();

		Hechizo hechizo1 = new Hechizo("Lumos");
		Hechizo hechizo2 = new Hechizo("Expelliarmus");
		Hechizo hechizo3 = new Hechizo("Alohomora");
		Hechizo hechizo4 = new Hechizo("Wingardium Leviosa");
		Hechizo hechizo5 = new Hechizo("Expecto Patronum");
		Hechizo hechizo6 = new Hechizo("Stupefy");
		
		mago1.agregarHechizo(hechizo1);
		mago1.agregarHechizo(hechizo2);
		mago1.agregarHechizo(hechizo4);
		mago1.agregarHechizo(hechizo5);
		
		mago2.agregarHechizo(hechizo2); // Hechizo en común
		mago2.agregarHechizo(hechizo3);
		mago2.agregarHechizo(hechizo4); // Hechizo en común
		mago2.agregarHechizo(hechizo5); // Hechizo en común
		mago2.agregarHechizo(hechizo6);
		
		Set<Hechizo> expectedComunes = new HashSet<>(Set.of(hechizo2, hechizo4, hechizo5));
		
		assertEquals(expectedComunes, mago1.hechizosEnComun(mago2));
	}

	@Test
	void sinHechizosEnComunTest() {
		Mago mago1 = new Mago();
		Mago mago2 = new Mago();

		reino.magia.Hechizo hechizo1 = new reino.magia.Hechizo("Lumos");
		reino.magia.Hechizo hechizo2 = new reino.magia.Hechizo("Expelliarmus");
		reino.magia.Hechizo hechizo3 = new reino.magia.Hechizo("Alohomora");
		reino.magia.Hechizo hechizo4 = new reino.magia.Hechizo("Wingardium Leviosa");

		mago1.agregarHechizo(hechizo1);
		mago1.agregarHechizo(hechizo2);

		mago2.agregarHechizo(hechizo3);
		mago2.agregarHechizo(hechizo4);

		assertTrue(mago1.hechizosEnComun(mago2).isEmpty());
	}

	@Test
	void hechizosEnComunConMagoNuloTest() {
		Mago mago = new Mago();

		assertThrows(MagoException.class, () -> {
			mago.hechizosEnComun(null);
		});
	}

	@Test
	void agregarHechizoNuloTest() {
		Mago mago = new Mago();

		Exception exception = assertThrows(MagoException.class, () -> {
			mago.agregarHechizo(null);
		});

		String expectedMessage = "No se puede agregar un hechizo nulo.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void agregarHechizoDuplicadoTest() {
		Mago mago = new Mago();

		reino.magia.Hechizo hechizo = new reino.magia.Hechizo("Lumos");

		mago.agregarHechizo(hechizo);
		mago.agregarHechizo(hechizo); // Intento de agregar el mismo hechizo nuevamente

		assertEquals(1, mago.hechizosEnComun(mago).size()); // Debe seguir teniendo solo un hechizo
	}
}
