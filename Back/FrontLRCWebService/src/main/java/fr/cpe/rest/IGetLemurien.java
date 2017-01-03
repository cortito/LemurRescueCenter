package fr.cpe.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.cpe.model.lemurien.LemurienModel;

@Path("/getLemurien")
public interface IGetLemurien {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public String getAllLemurien();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public String getLemurienById(LemurienModel lemurienM);
}
