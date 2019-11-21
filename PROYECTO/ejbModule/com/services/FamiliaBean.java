package com.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.daos.FamiliaDAO;
import com.entities.Familia;
import com.entities.Producto;
import com.exceptions.ServiceException;

@Stateless
public class FamiliaBean implements FamiliaBeanRemote {

	@EJB
	private FamiliaDAO familiaDAO;

	public FamiliaBean() {

	}

	@Override
	public long agregarFamilia(Familia familia) throws ServiceException {
		if (!existeRegistroFamilia(familia.getFamiNombre(), familia.getFamiDescrip())) {
			return familiaDAO.alta(familia);
		} else {
			return -1;
		}
	}

	public long eliminarFamilia(Familia familia) throws ServiceException {
		if (obtenerPorFamilia(familia).isEmpty()) {
				return familiaDAO.bajaLogica(familia);
			} else {
				return 0;
			}

	}


	public long modificarFamilia(Familia familia) throws ServiceException {
		if (existeRegistroFamilia(familia.getFamiNombre(), familia.getFamiDescrip())) {
			return familiaDAO.modificacion(familia);
		} else {
			return -1;
		}
	}

	public List<Familia> obtenerFamilias() {
		return familiaDAO.obtenerTodos();
	}

	public Familia familiaPorId(long idFamilia) throws ServiceException {
		return familiaDAO.obtenerPorId(idFamilia);
	}

	public List<Familia> familiasPorRangoId(long desde, long hasta) throws ServiceException {
		return familiaDAO.obtenerPorRangoId(desde, hasta);
	}

	@Override
	public boolean existeRegistroFamilia(String nombre, String descripcion) throws ServiceException {
		return familiaDAO.existeRegistroFamilia(nombre, descripcion);
	}

	@Override
	public Familia familiaPorDescripcion(String familiaDesc) throws ServiceException {
		return familiaDAO.obtenerPorDesc(familiaDesc);
	}

	public List<Producto> obtenerPorFamilia(Familia familia) throws ServiceException {
		return familiaDAO.obtenerPorFamilia(familia);
	}

	public Familia familiaPorNombre(String nombre) throws ServiceException {
		return familiaDAO.obtenerPorNombre(nombre);
	}

}
