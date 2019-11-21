package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 * 
	 * @return
	 */
	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	public void alta(Usuario usuario) throws ServiceException {
		try {
			em.persist(usuario);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiceException("No se pudo crear el usuario \n" + e.getMessage());
		}
	}

	public List<Usuario> obtenerTodos() {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		return query.getResultList();
	}

	public Usuario obtenerPorNomAcceso(String loginName) throws ServiceException {
		try {
			TypedQuery<Usuario> query = em
					.createQuery("SELECT u FROM Usuario u WHERE TRIM(UPPER(u.nomAcceso)) = TRIM(UPPER(:nombre))",
							Usuario.class)
					.setParameter("nombre", loginName);
			if (query.getResultList().isEmpty()) {
				throw new ServiceException("No se pudo obtener la información de usuario\n");
			}
			return query.getSingleResult();
		} catch (Exception exc) {
			throw new ServiceException(exc.getMessage());
		}
	}

	public String obtenerPerfil(Usuario usuario) {
		Usuario usr;
		usr = em.find(Usuario.class, usuario.getUsuCodigo());
		return usr.getPerfile().getPerfNombre().toUpperCase();
	}

	public Usuario loginUsuario(String usuario, String contrasenia) {
		TypedQuery<Usuario> consulta = em
				.createQuery("SELECT u FROM Usuario u WHERE u.nomAcceso = :nombre and u.contrasenia = :contrasenia",
						Usuario.class)
				.setParameter("nombre", usuario).setParameter("contrasenia", contrasenia);
		return consulta.getSingleResult();
	}

}
