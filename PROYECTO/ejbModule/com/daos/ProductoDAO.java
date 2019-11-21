package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Familia;
import com.entities.Producto;
import com.exceptions.ServiceException;

@LocalBean
@Stateless
public class ProductoDAO {

	@PersistenceContext
	private EntityManager em;

	public ProductoDAO() {

	}

	public void crearProdcuto(Producto producto) throws ServiceException{
		try {
			em.persist(producto);
			em.flush();
		}catch(PersistenceException e) {
			throw new ServiceException("No se puedo crear el producto");
		}
	}

	public void modificarProducto(Producto prodMod) throws ServiceException{
		try{
			em.merge(prodMod);
			em.flush();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("No se pudo actualizar el producto");
		}
	}

	public void borrarProducto(Producto producto) throws ServiceException {
		try{
			producto.setProdHabili(1);
			em.merge(producto);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiceException("No se pudo borrar el producto");
		}

	}

	public List<Producto> consultaProductos() throws ServiceException {
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p",Producto.class);
		return query.getResultList();
	}


	public List<Producto> obtenerTodos(String filtro) throws ServiceException {
		TypedQuery<Producto> query = em.createQuery("SELECT d FROM Productos d WHERE d.nombre LIKE :nombre",Producto.class)
				.setParameter("nombre", filtro); 
		return query.getResultList();
	}

	public Producto obtenerProductoPorCodigo(long prodId) {
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.prodId = :cod AND p.prodHabili = 0", Producto.class).setParameter("cod", prodId );
		//Producto producto = em.find(Producto.class, prodId);
		if (query.getSingleResult() == null) {
			return null;
		}else {
			return query.getSingleResult();    			
		}
	}

	public List<Producto> obtenerPorFamilia(Familia familia) {
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.familia = :fam", Producto.class).setParameter("fam", familia);
		return query.getResultList();
	}

	public Producto obtenerPorNombre(String nombre) {
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE TRIM(UPPER(p.prodNombre)) = TRIM(UPPER(:nom)) AND p.prodHabili = 0", Producto.class).setParameter("nom", nombre );
		if (query.getSingleResult() == null) {
			return null;
		}else {
			return query.getSingleResult();    			
		}
	}
	
	public boolean existeProducto(String nombre) {
		try {
			obtenerPorNombre(nombre);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}