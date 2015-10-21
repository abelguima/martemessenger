package org.abelguima.app.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.abelguima.app.messenger.database.DatabaseClass;
import org.abelguima.app.messenger.exception.DataNotFoundException;
import org.abelguima.app.messenger.model.Comando;
import org.abelguima.app.messenger.model.Robo;

public class ComandoService {
	
	private Map<Long, Comando> comandos = DatabaseClass.getComandos();
	private Map<String, Robo> robos = DatabaseClass.getRobos();
	
	public ComandoService(){
		if(comandos.isEmpty()){
			comandos.put(1L, new Comando(1, "", "R1"));
		}
	}

	public List<Comando> getAllComandos(){
		return new ArrayList<Comando>(comandos.values());
	}
	
	public List<Comando> getAllComandosPorAno(int ano){
		List<Comando> comandosForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Comando message : comandos.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == ano){
				comandosForYear.add(message);
			}
		}
		return comandosForYear;
	}
	
	public List<Comando> getAllComandosPaginados(int inicio, int tamanho){
		ArrayList<Comando> list = new ArrayList<Comando>(comandos.values());
		return list.subList(inicio, inicio + tamanho);
	}
	
	
	public Comando getComando(Long id){
		Comando comando = comandos.get(id);
		if(comando==null){
			throw new DataNotFoundException("Comando com id " + id + " não foi Encontrado");
		}
		return comando;
	}

	public Comando addComando(Comando comando){
		comando.setId(comandos.size() + 1);
		comandos.put(comando.getId(), comando);
		return comando;
	}
	
	public Comando updateComando(Comando comando){
		if(comando.getId() <= 0){
			return null;
		}
		comandos.put(comando.getId(), comando);
		return comando;
	}

	public Comando removeComando(long id){
		return comandos.remove(id);
	}

	public Comando updateComandoRobo(Comando comando, Robo robo) {
		if(comando.getId() <= 0){
			return null;
		}
		robos.put(robo.getRoboNome(), robo);
		comandos.put(comando.getId(), comando);
		return comando;
		
	}

}
