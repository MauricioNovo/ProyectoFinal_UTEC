package com.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.services.FamiliaBeanRemote;
import com.entities.Familia;
import com.exceptions.ServiceException;
import com.google.gson.Gson;

@Stateless
@Path("familia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamiliaRest {
	@EJB
	FamiliaBeanRemote famBean;
	
	// Todas las Familias
	@GET
	@Path("todos")
	public List<Familia> obtenerTodos() throws ServiceException {
		List<Familia> listaFam=new ArrayList<Familia>();
		try {
			listaFam=famBean.obtenerFamilias();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listaFam;
	}
	
	// Agregar Familia (No pasar ID es autogenerado)
	@POST
	@Path("agregar")

	public Response agregar(String jsonFamilia) throws ServiceException {
		
		Familia fam=new Familia();
		Gson gFam=new Gson();
		fam=(Familia) gFam.fromJson(jsonFamilia, Familia.class);
		famBean.agregarFamilia(fam);

		return Response.status(200).build();
	}
	
	
	// Eliminar Familia
	@DELETE
	@Path("eliminar/{id}")
	public boolean eliminar(@PathParam("id") long id) throws ServiceException {
		
		boolean result=false;
		
		try {
			Familia fam=famBean.familiaPorId(id);
			if (fam.getFamiCodi()==id) {
				famBean.eliminarFamilia(fam);
				result=true;
			}
			else {
				System.out.println("No existe la familia");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		//famBean.
	}
	
	// Buscar Familia por Id
	@GET
	@Path("buscarid/{id}")
	public Response buscarId(@PathParam("id") long id) throws ServiceException {
		
		Familia fam=new Familia();
		
		try {
			fam=famBean.familiaPorId(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(fam).build();
	}
	
	// Buscar Familia por nombre
	@GET
	@Path("buscarxnombre/{nombre}")
	public Response buscarNombre(@PathParam("nombre") String nombre) throws ServiceException {
		
		Familia fam=new Familia();
		
		try {
			fam=famBean.familiaPorNombre(nombre);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(fam).build();
	}
}
