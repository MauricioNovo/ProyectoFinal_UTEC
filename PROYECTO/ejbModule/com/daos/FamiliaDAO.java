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

/**
 * Session Bean implementation class FamiliasBean
 */
@Stateless
@LocalBean
public class FamiliaDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public FamiliaDAO() {

	}

	// buscar si existe familia por nombre y descripción
	public boolean existeRegistroFamilia(String nombre, String descripcion) {
		try {
			Familia fam = (Familia) em
					.createQuery("SELECT f FROM Familia f WHERE TRIM(UPPER(f.famiNombre)) = TRIM(UPPER(:nombre))"
							+ "or TRIM(UPPER(f.famiDescrip)) = TRIM(UPPER(:descripcion))", Familia.class)
					.setParameter("nombre", nombre).setParameter("descripcion", descripcion).getSingleResult();
			return fam != null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// alta de familia
	public long alta(Familia familia) throws ServiceException {
		try {
			em.persist(familia);
			em.flush();
			System.out.println("SE INSERTAN REGISTROS CON EXITO EN LA TABLA FAMILIA: ");
			System.out.println("Descripcion: " + familia.getFamiDescrip());
			System.out.println("Incompatibilidad: " + familia.getFamiIncompat());
			System.out.println("Nombre: " + familia.getFamiNombre());
			System.out.println("Id: " + familia.getFamiCodi());
			System.out.println("Habili: " + familia.getFamihabili());
			return familia.getFamiCodi();
		} catch (PersistenceException e) {
			// throw new ServiceException("No se pudo crear la familia \n"+e.getMessage());
			System.out.println("OCURRIO UN ERROR AL INSERTAR FAMILIA: " + e.getLocalizedMessage());
			return -1;
		}
	}

	// Eliminar una familia existente(baja logica)
	public long bajaLogica(Familia familia) throws ServiceException {
		try {
			familia.setFamihabili(1);
			em.merge(familia);
			em.flush();
			return familia.getFamiCodi();
		} catch (PersistenceException pe) {
			throw new ServiceException("No se pudo modificar la familia \n" + pe.getMessage());
		}

	}

	// Modifico una familia existente
	public long modificacion(Familia familia) throws ServiceException {
		try {
			em.merge(familia);
			em.flush();
			return familia.getFamiCodi();
		} catch (PersistenceException pe) {
			throw new ServiceException("No se pudo modificar la familia \n" + pe.getMessage());
		}

	}

	// obtener familia por nombre
	public Familia obtenerPorNombre(String nom) throws ServiceException {
		TypedQuery<Familia> query = em
				.createQuery("SELECT f FROM Familia f WHERE TRIM(UPPER(f.famiNombre)) = TRIM(UPPER(:nom))",
						Familia.class)
				.setParameter("nom", nom.trim());
		return query.getSingleResult();
	}

	// Obtener Familia por id
	public Familia obtenerPorId(long idFamilia) throws ServiceException {
		return em.find(Familia.class, idFamilia);
	}

	// Obtener Familia por descripcion
	public Familia obtenerPorDesc(String desc) {
		try {
			Familia fam = (Familia) em
					.createQuery("SELECT f FROM Familia f WHERE TRIM(UPPER(f.famiDescrip)) = TRIM(UPPER(:descripcion))",
							Familia.class)
					.setParameter("descripcion", desc).getSingleResult();
			return fam;
		} catch (PersistenceException pe) {
			return null;
		}
	}

	// Obtener familia por rango id
	public List<Familia> obtenerPorRangoId(long desde, long hasta) throws ServiceException {
		TypedQuery<Familia> query = em.createQuery(
				"SELECT f FROM Familia f WHERE f.idFamilia >= :desde AND f.idFamilia <= :hasta ORDER BY f.nombre",
				Familia.class).setParameter("desde", desde);
		query.setParameter("hasta", hasta);
		return query.getResultList();
	}

	// obtener todas las familias
	public List<Familia> obtenerTodos() {
		TypedQuery<Familia> query = em.createQuery("SELECT f FROM Familia f", Familia.class);
		return query.getResultList();
	}

	// Obtener productos asociados a familia
	public List<Producto> obtenerPorFamilia(Familia familia) {
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.familia = :fam", Producto.class)
				.setParameter("fam", familia);
		return query.getResultList();
	}
	
	//Obtener familia incompatible
	

}
