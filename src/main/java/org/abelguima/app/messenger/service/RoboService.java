package org.abelguima.app.messenger.service;

import java.util.ArrayList;
import java.util.Map;

import org.abelguima.app.messenger.database.DatabaseClass;
import org.abelguima.app.messenger.model.Robo;

public class RoboService {
	
	private Map<String, Robo> robos = DatabaseClass.getRobos();
	
	
	public RoboService(){
		if(robos.isEmpty()){
			robos.put("R1", new Robo(1L, "R1", 0,0,"N"));
			robos.put("R2", new Robo(2L, "R2", 0,0,"N"));
			robos.put("R3", new Robo(3L, "R3", 0,0,"N"));
		}
	}
	public ArrayList<Robo> getAllRobos(){
		return new ArrayList<Robo>(robos.values());
	}
	
	public Robo getRobo(String roboNome){
		return robos.get(roboNome);
	}

	public Robo addRobo(Robo robo){
		robo.setId(robos.size() + 1);
		robos.put(robo.getRoboNome(), robo);
		return robo;
	}
	
	public Robo updateRobo(Robo robo){
		if(robo.getRoboNome().isEmpty()){
			return null;
		}
		robos.put(robo.getRoboNome(), robo);
		return robo;
	}

	public Robo removeRobo(String roboNome){
		return robos.remove(roboNome);
	}

}
