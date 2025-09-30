package reino.sufragio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import exceptions.GestorVotacionException;
import reino.magia.Mago;

public class GestorVotacion {
	HashSet<Mago> votantes;
	HashMap<String, Integer> votos;
	String ganador;
	
	public GestorVotacion() {
		votantes = new HashSet<>();
		votos = new HashMap<>();
		ganador = null;
	}
	
	public void agregarCandidato(String candidato) {
		if(candidato == null || candidato.trim().isEmpty()) {
			throw new GestorVotacionException("El nombre del candidato no puede ser nulo o vacío.");
		}
		votos.putIfAbsent(candidato, 0);
	}
	
	public void agregarCandidatos(Set<String> candidatos) {
		validarSetCandidatos(candidatos);
		for(String candidato : candidatos) {
			agregarCandidato(candidato);
		}
	}

	public void registrarVotante(Mago mago) {
		if(mago == null) {
			throw new GestorVotacionException("El mago proporcionado es nulo.");
		}
		votantes.add(mago);
	}
	
	public void registrarVotantes(Set<Mago> magos) {
		validarSetVotantes(magos);
		votantes.addAll(magos);
	}
	
	public void votar(Mago mago, String candidato) {
		validarCandidato(candidato);
		votos.put(candidato, votos.get(candidato) + 1);
	}
	
	private void validarVotante(Mago mago) throws GestorVotacionException {
		if(mago == null) {
			throw new GestorVotacionException("El mago proporcionado es nulo.");
		}
		if(!votantes.contains(mago)) {
			throw new GestorVotacionException("El mago no está registrado como votante.");
		}
	}

	private void validarSetVotantes(Set<Mago> magos) throws GestorVotacionException {
		if(magos == null || magos.isEmpty()) {
			throw new GestorVotacionException("La lista de magos no puede ser nula o vacía.");
		}
	}
	
	private void validarCandidato(String candidato) throws GestorVotacionException {
		if(candidato == null || candidato.trim().isEmpty()) {
			throw new GestorVotacionException("El nombre del candidato no puede ser nulo o vacío.");
		}
		if(!votos.containsKey(candidato)) {
			throw new GestorVotacionException("El candidato no está en la lista de candidatos.");
		}
	}
	
	private void validarSetCandidatos(Set<String> candidatos) throws GestorVotacionException {
		if(candidatos == null || candidatos.isEmpty()) {
			throw new GestorVotacionException("La lista de candidatos no puede ser nula o vacía.");
		}
	}
	
	public String determinarGanador() {
		int maxVotos = -1;
		String posibleGanador = null;
		boolean empate = false;
		
		for (String candidato : votos.keySet()) {
			int cantVotos = votos.get(candidato);
			if (cantVotos > maxVotos) {
				maxVotos = cantVotos;
				posibleGanador = candidato;
				empate = false;
			} else if (cantVotos == maxVotos) {
				empate = true;
			}
		}
		
		if (empate || maxVotos == 0) {
			throw new GestorVotacionException("No hay un ganador claro.");			
		}
			
		ganador = posibleGanador;
		
		return ganador;
	}
	
}