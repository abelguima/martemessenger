package org.abelguima.app.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.abelguima.app.messenger.model.Comando;
import org.abelguima.app.messenger.model.Robo;

public class DatabaseClass {
	
	
	private static Map<Long, Comando> comandos = new HashMap<>();
	private static Map<String, Robo> robos = new HashMap<>();
	
	public static Map<Long, Comando> getComandos(){
		return comandos;
	}
	
	public static Map<String, Robo> getRobos(){
		return robos;
	}
	

}
