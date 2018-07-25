package rech.bolao.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class Ping {
	@GET
	@Produces(value = "application/json")
	public String ping() {
		return "{'ping': 'pong'}";
	}
}