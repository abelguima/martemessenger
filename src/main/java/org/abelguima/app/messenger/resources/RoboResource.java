package org.abelguima.app.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.abelguima.app.messenger.model.Robo;
import org.abelguima.app.messenger.service.RoboService;

//@Produces(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/robos")
public class RoboResource {
	
	RoboService roboservice = new RoboService();
	
	
	@GET
	public List<Robo> getRobos(){
		return roboservice.getAllRobos();
	}
	
	@GET
	@Path("/{roboNome}")
	public Robo getRobo(@PathParam("roboNome") String roboNome){
		return roboservice.getRobo(roboNome);
	}
	
	@POST
	public Robo addRobo(Robo robo){
		return roboservice.addRobo(robo);
	}
	
	@PUT
	@Path("/{roboNome}")
	public Robo updateRobo(@PathParam("roboNome") String roboNome, Robo robo){
		robo.setRoboNome(roboNome);
		return roboservice.updateRobo(robo);
	}
	
	@DELETE
	@Path("/{roboNome}")
	public void deleteRobo(@PathParam("roboNome") String roboNome){		
		roboservice.removeRobo(roboNome);
	}
	

}
