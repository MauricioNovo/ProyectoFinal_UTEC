package com.webservices.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entities.Usuario;
import com.exceptions.AccessControlException;
import com.exceptions.ServiceException;
import com.google.gson.Gson;
import com.services.LogueoBeanRemote;

@Stateless
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {
	@EJB
	LogueoBeanRemote logueoBean;
	
	@POST
	@Path("chequear")
	public Response chequearLogin(String jsonUsu) throws ServiceException {

		Gson gUsu=new Gson();
		Usuario usr=new Usuario();
		Usuario usrDev=new Usuario();
		usr=(Usuario) gUsu.fromJson(jsonUsu, Usuario.class);
		System.out.println("Convertido desde JSON: "+usr.toString());
		try {
			usrDev=logueoBean.iniciarSesion(usr);
			System.out.println("Encontrado en BD: "+usrDev.toString());
		}
		catch (AccessControlException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(usrDev).build();
	}
}
