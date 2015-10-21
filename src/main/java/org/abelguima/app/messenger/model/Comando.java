package org.abelguima.app.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


public class Comando {
	
	private long id;
	private String menssagem;
	private Date created;
	private String roboNome;
	protected List<Link> links = new ArrayList<>();
	
	public Comando(){
		
	}
	
	

	public Comando(long id, String menssagem, String roboNome) {
		super();
		this.id = id;
		this.menssagem = menssagem;
		this.created = new Date();
		this.roboNome = roboNome;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return menssagem;
	}

	public void setMessage(String message) {
		this.menssagem = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getRobo() {
		return roboNome;
	}

	public void setRobo(String roboNome) {
		this.roboNome = roboNome;
	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url, String rel){
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	
	

}
