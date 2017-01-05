package fr.lrc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.lrc.model.poids.PoidsModel;

@Path("/addPoids")
public interface IAddPoids {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public String addPoids(PoidsModel poidsM);
}
