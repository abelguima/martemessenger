package org.abelguima.app.messenger.model;

import java.util.Date;

public class Robo {
	
	private long id;
	private String roboNome;
	private int coordenadaX;
	private int coordenadaY;
	private String direcao;
	private Date created;
	
	public Robo(){
		
	}
	
	public Robo(long id, String roboNome, int coordenadaX, int coordenadaY, String direcao) {
		super();
		this.id = id;
		this.roboNome = roboNome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.direcao = direcao;
		this.created = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoboNome() {
		return roboNome;
	}
	public void setRoboNome(String roboNome) {
		this.roboNome = roboNome;
	}
	public int getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(int coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public int getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(int coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	
	

}
