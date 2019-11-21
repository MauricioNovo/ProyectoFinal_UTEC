package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.daos.LogueoDAO;
import com.daos.UsuarioDAO;
import com.entities.Usuario;
import com.exceptions.AccessControlException;

/**
 * Session Bean implementation class LogueoBean
 */
@Stateless
public class LogueoBean implements LogueoBeanRemote {
	
	@EJB
	private LogueoDAO loginDAO;
	
	@EJB
	private UsuarioDAO usuarioDAO;

    /**
     * Default constructor. 
     */
    public LogueoBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
 	public Usuario obtenerUsuarioPorNombre(String usuNomacceso) {
 		return loginDAO.obtenerUsuarioPorNombre(usuNomacceso);	
 	}
     
     @Override
     public String obtenerPerfil(Usuario usuario) {
     	return usuarioDAO.obtenerPerfil(usuario);
     }

	@Override
	public Usuario obtenerUsuarioPorNombreContra(String usuNomacceso, String usuContrasenia) {
		return  loginDAO.obtenerUsuarioPorNombre(usuNomacceso);	
	}
    
    @Override
    public Usuario iniciarSesion(Usuario usu) throws AccessControlException {
    	return loginDAO.iniciarSesion(usu);
    }

}