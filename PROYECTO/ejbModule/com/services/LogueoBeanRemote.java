package com.services;

import javax.ejb.Remote;
import com.entities.Usuario;

@Remote
public interface LogueoBeanRemote {
	
	public Usuario iniciarSesion(Usuario usu);
	public Usuario obtenerUsuarioPorNombre(String usuNomacceso);
    public String obtenerPerfil(Usuario usuario);
    public Usuario obtenerUsuarioPorNombreContra(String usuNomacceso, String usuContrasenia);

	
}