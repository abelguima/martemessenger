package org.abelguima.app.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class FiltroComandoBean {

	private @QueryParam("ano") int ano;
	private @QueryParam("incio") int inicio;
	private @QueryParam("tamanho") int tamanho;
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	
	
}
