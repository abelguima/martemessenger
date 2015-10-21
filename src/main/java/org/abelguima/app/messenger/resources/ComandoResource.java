package org.abelguima.app.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.abelguima.app.messenger.model.Comando;
import org.abelguima.app.messenger.model.Robo;
import org.abelguima.app.messenger.resources.beans.FiltroComandoBean;
import org.abelguima.app.messenger.service.ComandoService;
import org.abelguima.app.messenger.service.RoboService;

@Path("/comandos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComandoResource {
	
	ComandoService comandoService = new ComandoService();
	RoboService roboService = new RoboService();
	
	@GET
	public List<Comando> getMessages(){
		return comandoService.getAllComandos();
	}
	
	public List<Comando> getJsonMessages(@BeanParam FiltroComandoBean filterBean){		
		System.out.println("chamada do metodo Json");
		if (filterBean.getAno() > 0){
			return comandoService.getAllComandosPorAno(filterBean.getAno());
		}
		if (filterBean.getInicio() >= 0 && filterBean.getTamanho() > 0){
			return comandoService.getAllComandosPaginados(filterBean.getInicio(), filterBean.getTamanho());
		}
		return comandoService.getAllComandos();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Comando> getXmlMessages(@BeanParam FiltroComandoBean filterBean){		
		System.out.println("XML method called");
		if (filterBean.getAno() > 0){
			return comandoService.getAllComandosPorAno(filterBean.getAno());
		}
		if (filterBean.getInicio() >= 0 && filterBean.getTamanho() > 0){
			return comandoService.getAllComandosPaginados(filterBean.getInicio(), filterBean.getTamanho());
		}
		return comandoService.getAllComandos();
	}
	
	@GET
	@Path("/{comandoId}")
	public Comando getComando(@PathParam("comandoId") long comandoId, @Context UriInfo uriInfo){
		
		Comando comando = comandoService.getComando(comandoId);
		comando.addLink(getUriParaOMesmo(uriInfo, comando), "omesmo");
		comando.addLink(getUriParaRobo(uriInfo, comando), "robo");
		comando.addLink(getUriParaComandos(uriInfo, comando), "comandos");
		return comando;
	}

	private String getUriParaComandos(UriInfo uriInfo, Comando comando) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ComandoResource.class)
				.resolveTemplate("comandoId", comando.getId())
				.build();
		return uri.toString();
	}

	private String getUriParaRobo(UriInfo uriInfo, Comando comando) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(RoboResource.class)
				.path(comando.getRobo().toString())
				.build();
		return uri.toString();
	}

	private String getUriParaOMesmo(UriInfo uriInfo, Comando comando) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ComandoResource.class)
				.path(Long.toString(comando.getId()))
				.build();
		return uri.toString();
	}
	
	@POST
	public Response addComando(Comando comando, @Context UriInfo urlInfo) throws URISyntaxException{
		System.out.println(urlInfo.getAbsolutePath());
		Comando novoComando = comandoService.addComando(comando);
		Robo robo = updateRobo(comando);
		String novoId = String.valueOf(novoComando.getId());
		URI uri = urlInfo.getAbsolutePathBuilder().path(novoId).build();
		return Response.created(uri)
				.entity(novoComando)
				.entity(robo)
				.build();
	}
	
	private Robo updateRobo(Comando comando) {
		Robo robo = roboService.getRobo(comando.getRobo());
		String menssagem = comando.getMessage();
		String direcao = robo.getDirecao();
		int coordenadaX = robo.getCoordenadaX();
		int coordenadaY = robo.getCoordenadaY();
		for(int i=0;i<menssagem.length();i++){
			if("M".equals(menssagem.charAt(i)+"")){
				if(direcao.equals("N")){
					if(coordenadaY<5){
						coordenadaY=coordenadaY+1;
					}
				}
				else if(direcao.equals("W")){
					if(coordenadaX<5){
						coordenadaX=coordenadaX+1;
					}
				}
				else if(direcao.equals("S")){
					if(coordenadaY>0){
						coordenadaY=coordenadaY-1;
					}
				}
				else{
					if(coordenadaX>0){
						coordenadaX=coordenadaX-1;
					}
				}
			}
			else{
				if("R".equals(menssagem.charAt(i)+"") && direcao.equals("N")){
					direcao="E";
				}
				else if("R".equals(menssagem.charAt(i)+"") && direcao.equals("S")){
					direcao="W";
				}
				else if("R".equals(menssagem.charAt(i)+"") && direcao.equals("E")){
					direcao="S";
				}
				else if("R".equals(menssagem.charAt(i)+"") && direcao.equals("W")){
					direcao="N";
				}
				else if("L".equals(menssagem.charAt(i)+"") && direcao.equals("N")){
					direcao="W";
				}
				else if("L".equals(menssagem.charAt(i)+"") && direcao.equals("S")){
					direcao="E";
				}
				else if("L".equals(menssagem.charAt(i)+"") && direcao.equals("E")){
					direcao="N";
				}
				else if("L".equals(menssagem.charAt(i)+"") && direcao.equals("W")){
					direcao="S";
				}
			}
		}
		robo.setCoordenadaX(coordenadaX);
		robo.setCoordenadaY(coordenadaY);
		robo.setDirecao(direcao);
		comandoService.updateComandoRobo(comando,robo);
		return roboService.getRobo(robo.getRoboNome());
	}

	@PUT
	@Path("/{comandoId}")
	public Comando updateComando(@PathParam("comandoId") long comandoId, Comando comando){
		comando.setId(comandoId);
		return comandoService.updateComando(comando);
	}
	
	@DELETE
	@Path("/{comandoId}")
	public void deleteComando(@PathParam("comandoId") long comandoId){		
		comandoService.removeComando(comandoId);
	}

}
