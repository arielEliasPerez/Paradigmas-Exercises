package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import exceptions.GestorVotacionException;
import reino.magia.Mago;
import reino.sufragio.GestorVotacion;

public class GestorVotacionTest {
    private GestorVotacion gestor;
    private Mago mago1;
    private Mago mago2;
    private Mago mago3;

    @Before
    public void setUp() {
        gestor = new GestorVotacion();
        mago1 = new Mago("Merlin");
        mago2 = new Mago("Gandalf");
        mago3 = new Mago("Saruman");
    }

    @Test(expected = GestorVotacionException.class)
    public void testAgregarCandidatoNulo() {
        gestor.agregarCandidato(null);
    }

    @Test(expected = GestorVotacionException.class)
    public void testAgregarCandidatoVacio() {
        gestor.agregarCandidato("");
    }

    @Test
    public void testAgregarCandidatoValido() {
        gestor.agregarCandidato("Merlin");
        // No exception expected
    }

    @Test(expected = GestorVotacionException.class)
    public void testAgregarCandidatosSetNulo() {
        gestor.agregarCandidatos(null);
    }

    @Test(expected = GestorVotacionException.class)
    public void testAgregarCandidatosSetVacio() {
        gestor.agregarCandidatos(new HashSet<>());
    }

    @Test
    public void testAgregarCandidatosSetValido() {
        Set<String> candidatos = new HashSet<>();
        candidatos.add("Merlin");
        candidatos.add("Gandalf");
        gestor.agregarCandidatos(candidatos);
        // No exception expected
    }

    @Test(expected = GestorVotacionException.class)
    public void testRegistrarVotanteNulo() {
        gestor.registrarVotante(null);
    }

    @Test
    public void testRegistrarVotanteValido() {
        gestor.registrarVotante(mago1);
        // No exception expected
    }

    @Test(expected = GestorVotacionException.class)
    public void testRegistrarVotantesSetNulo() {
        gestor.registrarVotantes(null);
    }

    @Test(expected = GestorVotacionException.class)
    public void testRegistrarVotantesSetVacio() {
        gestor.registrarVotantes(new HashSet<>());
    }

    @Test
    public void testRegistrarVotantesSetValido() {
        Set<Mago> magos = new HashSet<>();
        magos.add(mago1);
        magos.add(mago2);
        gestor.registrarVotantes(magos);
        // No exception expected
    }

    @Test(expected = GestorVotacionException.class)
    public void testVotarCandidatoNoExistente() {
        gestor.registrarVotante(mago1);
        gestor.votar(mago1, "Merlin");
    }

    @Test
    public void testVotarCandidatoValido() {
        gestor.registrarVotante(mago1);
        gestor.agregarCandidato("Merlin");
        gestor.votar(mago1, "Merlin");
        // No exception expected
    }

    @Test(expected = GestorVotacionException.class)
    public void testDeterminarGanadorSinVotos() {
        gestor.agregarCandidato("Merlin");
        gestor.determinarGanador();
    }

    @Test(expected = GestorVotacionException.class)
    public void testDeterminarGanadorEmpate() {
        gestor.agregarCandidato("Merlin");
        gestor.agregarCandidato("Gandalf");
        gestor.registrarVotante(mago1);
        gestor.registrarVotante(mago2);
        gestor.votar(mago1, "Merlin");
        gestor.votar(mago2, "Gandalf");
        gestor.determinarGanador();
    }

    @Test
    public void testDeterminarGanadorUnico() {
        gestor.agregarCandidato("Merlin");
        gestor.agregarCandidato("Gandalf");
        gestor.registrarVotante(mago1);
        gestor.registrarVotante(mago2);
        gestor.votar(mago1, "Merlin");
        gestor.votar(mago2, "Merlin");
        String ganador = gestor.determinarGanador();
        assertEquals("Merlin", ganador);
    }
}