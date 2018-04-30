package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.buisness.service.IAccountService;

@Path("/account")
public class AccountEndPoint {

	@Inject
	private IAccountService service ;
	
	@POST
	@Path("/json/")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public String addAccount(String accountAsJSON) {
		return service.addAccount(accountAsJSON);
	}
	
	@GET
	@Path("/json/{accountNumber}")
	@Produces({"application/json"})
	public String findAccount(@PathParam("accountNumber")Long accountNumber) {
		return service.findAccount(accountNumber);
	}
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String getAllAccounts() {
		return service.getAllAccounts();
	}
	
	@DELETE
	@Path("/json/{accountNumber}")
	@Produces({"application/json"})
	public String deleteAccount(@PathParam("accountNumber")Long accountNumber) {
		return service.deleteAccount(accountNumber);
	}
	
	@PUT
	@Path("/json/{accountNumber}")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public String updateAccount(@PathParam("accountNumber")Long accountNumber,String accountAsJSON) {
		return service.updateAccount(accountNumber, accountAsJSON);
	}
	
	
	
	
	
}
