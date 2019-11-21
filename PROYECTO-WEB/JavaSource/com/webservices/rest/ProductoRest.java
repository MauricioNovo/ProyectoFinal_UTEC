package com.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.services.LogueoBeanRemote;
import com.services.ProductoBeanRemote;
import com.entities.Producto;
import com.entities.Usuario;
import com.exceptions.ServiceException;
import com.google.gson.Gson;

@Stateless
@Path("producto")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRest {
	
	@EJB
	ProductoBeanRemote prodBean;
	
	@EJB
	LogueoBeanRemote logueoBean;
	
	
	// Todos los productos
	@GET
	@Path("todos")
	public Response obtenerTodos() throws ServiceException {
		List<Producto> listaProd=new ArrayList<Producto>();
		Gson gProds=new Gson();
		try {
			listaProd=prodBean.consultaProductos();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String resultado=gProds.toJson(listaProd);
		return Response.ok(resultado).build();
	}
	
	// Agregar nuevo producto
	@POST
	@Path("agregar")
	public Response agregarProducto(String jsonProd, @QueryParam("nombreUsuario") String usuNomAcceso) throws ServiceException {
		long res=-1;
		Producto prod=new Producto();
		Gson gProd=new Gson();
		prod=(Producto) gProd.fromJson(jsonProd, Producto.class);
		Usuario usuario=new Usuario();

		try {
			usuario=logueoBean.obtenerUsuarioPorNombre(usuNomAcceso);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
//		// Usuario hardcodeado. cambiar por usuario logueado
//		Usuario usuario=new Usuario();
//		usuario.setUsuNomacceso("fernando");
//		Perfile perfil=new Perfile();
//		perfil.setPerfCodigo(1);
//		perfil.setPerfNombre("ADMINISTRADOR");
//		usuario.setPerfile(perfil);
		
		try {
			res=prodBean.crearProducto(prod, usuario);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok(res).build();
	}
	
}
