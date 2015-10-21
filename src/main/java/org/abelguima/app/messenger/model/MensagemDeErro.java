package org.abelguima.app.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MensagemDeErro {
	
	private String menssagemDeErro;
	private int codigoDoErro;
	private String documento;
	
	public MensagemDeErro(){
		
	}

	public MensagemDeErro(String menssagemDeErro, int codigoDoErro, String documento) {
		super();
		this.menssagemDeErro = menssagemDeErro;
		this.codigoDoErro = codigoDoErro;
		this.documento = documento;
	}

	public String getMensagemDeErro() {
		return menssagemDeErro;
	}

	public void setMensagemDeErro(String menssagemDeErro) {
		this.menssagemDeErro = menssagemDeErro;
	}

	public int getCodigoDoErro() {
		return codigoDoErro;
	}

	public void setCodigoDoErro(int codigoDoErro) {
		this.codigoDoErro = codigoDoErro;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	

}
