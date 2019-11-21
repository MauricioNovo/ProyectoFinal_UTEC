package com.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Usuario;

/**
 * Session Bean implementation class LoginDAO
 */
@Stateless
public class LogueoDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public LogueoDAO() {
	}

	public Usuario obtenerUsuarioPorNombre(String usuNomacceso) {
		try {
			TypedQuery<Usuario> query = em
					.createQuery("SELECT s FROM Usuario s WHERE TRIM(UPPER(s.usuNomacceso)) LIKE TRIM(UPPER(:Nombre))",
							Usuario.class)
					.setParameter("Nombre", usuNomacceso);
			return query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario iniciarSesion(Usuario us) {

		Usuario usr = null;

		try {
			TypedQuery<Usuario> qry = em.createQuery(
					"SELECT s FROM Usuario s WHERE UPPER(s.usuNomacceso) = ?1 AND s.usuContrasenia = ?2",
					Usuario.class);
			qry.setParameter(1, us.getUsuNomacceso().toUpperCase());
			qry.setParameter(2, us.getUsuContrasenia());
			List<Usuario> res = qry.getResultList();
			// Prueba para ver que datos traen los getters
			System.out.println("Getters del parametro: " + us.getUsuNomacceso());
			System.out.println(us.getUsuContrasenia());

			System.out.println("Getters del resultado: " + us.getUsuNomacceso());
			System.out.println(us.getUsuContrasenia());

			if (!res.isEmpty()) {
				usr = res.get(0);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return usr;
	}

}