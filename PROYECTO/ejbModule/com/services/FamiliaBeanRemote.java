package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Familia;
import com.entities.Producto;
import com.exceptions.ServiceException;

@Remote
public interface FamiliaBeanRemote {
	
	long agregarFamilia(Familia familia) throws ServiceException;
	long modificarFamilia(Familia familia) throws ServiceException;
	long eliminarFamilia(Familia familia) throws ServiceException;
	List<Familia> obtenerFamilias();
	Familia familiaPorId(long idFamilia) throws ServiceException;
	Familia familiaPorDescripcion(String familiaDesc) throws ServiceException;
	List<Familia> familiasPorRangoId(long desde, long hasta) throws ServiceException;
	boolean existeRegistroFamilia(String nombre, String descripcion) throws ServiceException;
	List<Producto> obtenerPorFamilia(Familia familia) throws ServiceException;
	Familia familiaPorNombre(String nombre) throws ServiceException;

}
