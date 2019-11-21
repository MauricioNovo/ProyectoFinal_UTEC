package com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.entities.Familia;
import com.exceptions.ServiceException;
import com.messages.MessageView;
import com.services.FamiliaBeanRemote;

@ManagedBean
@SessionScoped
public class familiaBeanWEB {

	@EJB
	private FamiliaBeanRemote familiaEJBBean;

	@EJB
	private MessageView messages;

	FacesContext facesContext;
	FacesMessage facesMessage;

	private long famiCodi;
	private String famiDescrip;
	private String famiIncompat;
	private String famiNombre;
	private int famihabili;

	// para buscar y filtrar familia

	private String filtrar;
	private String buscar;
	private String tmp;
	private String edition;

	// combobox
	private List<Familia> selectedValues;
	private List<String> selectedText;
	private List<Long> incompatibilidad;

	@PostConstruct
	public void onload() {
		selectedValues = familiaEJBBean.obtenerFamilias();
		incompatibilidad = new ArrayList<>();
		incompatibilidad = new ArrayList<>();
		this.edition = "true";
	}

	/* private Boolean famiHabili; */

	public List<Long> getIncompatibilidad() {
		return incompatibilidad;
	}

	public List<Familia> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<Familia> selectedValues) {
		this.selectedValues = selectedValues;
	}

	public List<String> getSelectedText() {
		return selectedText;
	}

	public void setSelectedText(List<String> selectedText) {
		this.selectedText = selectedText;
	}

	public void setIncompatibilidad(List<Long> incompatibilidad) {
		this.incompatibilidad = incompatibilidad;
	}

	public long getFamiCodi() {
		return famiCodi;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setFamiCodi(long famiCodi) {
		this.famiCodi = famiCodi;
	}

	public String getFamiDescrip() {
		return famiDescrip;
	}

	public void setFamiDescrip(String famiDescrip) {
		this.famiDescrip = famiDescrip;
	}

	public String getFamiIncompat() {
		return famiIncompat;
	}

	public void setFamiIncompat(String famiIncompat) {
		this.famiIncompat = famiIncompat;
	}

	public String getFamiNombre() {
		return famiNombre;
	}

	public void setFamiNombre(String famiNombre) {
		this.famiNombre = famiNombre;
	}

	public int getFamihabili() {
		return famihabili;
	}

	public void setFamihabili(int famihabili) {
		this.famihabili = famihabili;
	}

	public String getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(String filtrar) {
		this.filtrar = filtrar;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	// Alta de Familias//
	public void crearFamilia() throws ServiceException {
		String inc = "";
		for (int i = 0; i <= incompatibilidad.size() - 1; i++) {
			if ((i + 1) >= incompatibilidad.size()) {
				inc += String.valueOf(incompatibilidad.get(i));
			} else {
				inc += String.valueOf(incompatibilidad.get(i)) + "-";
			}
		}
		Familia fam = new Familia();
		fam.setFamiDescrip(famiDescrip);
		fam.setFamiIncompat(inc);
		fam.setFamiNombre(famiNombre);
		fam.setFamihabili(famihabili);

		facesContext = FacesContext.getCurrentInstance();

		long id = familiaEJBBean.agregarFamilia(fam);
		if (id > 0) {
			onload();
			borrarCampos();
			String successMessage = "Familia codigo: " + id + " ingresada con exito";
			facesMessage = new FacesMessage(successMessage);
			facesContext.addMessage(null, facesMessage);
		} else {
			String duplicateMessage = "Familia ya existente, por favor revise sus datos";
			facesMessage = new FacesMessage(duplicateMessage);
			facesContext.addMessage(null, facesMessage);
		}

	}

//Modificacion de Familias//

	public void obtenerfamilia() throws ServiceException {
		if (filtrar.equals("ID")) {
			obtenerfamiliaPorId();
		} else if (filtrar.equals("Descripcion")) {
			obtenerFamiliaPorDescripcion();
		}
	}

	public void obtenerfamiliaPorId() {
		try {
			long id = Long.parseLong(buscar);
			if (id != 0) {
				Familia fami = new Familia();
				fami = familiaEJBBean.familiaPorId(id);
				if (fami != null) {
					setFamiCodi(fami.getFamiCodi());
					setFamiDescrip(fami.getFamiDescrip());
					setFamiIncompat(fami.getFamiIncompat());
					setFamiNombre(fami.getFamiNombre());
					setFamihabili(fami.getFamihabili());
					this.tmp = getFamiDescrip();
					setEdition("false");
				} else {
					messages.setMessage("La familia buscada no exite");
					messages.errorMessage();
					borrarCampos();
				}
			}
		} catch (Exception e) {
			messages.setMessage("Ingrese un identificador valido");
			messages.errorMessage();
			borrarCampos();
		}
	}

	public void obtenerFamiliaPorDescripcion() throws ServiceException {
		if (!isNullOrBlank(buscar)) {
			Familia fami = new Familia();
			fami = familiaEJBBean.familiaPorDescripcion(buscar);
			if (fami != null) {
				setFamiCodi(fami.getFamiCodi());
				setFamiDescrip(fami.getFamiDescrip());
				setFamiIncompat(fami.getFamiIncompat());
				setFamiNombre(fami.getFamiNombre());
				setFamihabili(fami.getFamihabili());
				this.tmp = getFamiDescrip();
				setEdition("false");
			} else {
				messages.setMessage("La familia buscada no exite");
				messages.errorMessage();
				borrarCampos();
			}
		} else {
			messages.setMessage("Ingrese un identificador valido");
			messages.errorMessage();
			borrarCampos();
		}

	}

	public static boolean isNullOrBlank(String param) {
		return param == null || param.trim().length() == 0;
	}

	public void modificarFamilia() throws ServiceException {
		Familia fami = new Familia();
		fami.setFamiDescrip(famiDescrip);
		fami.setFamiCodi(famiCodi);
		fami.setFamiIncompat(famiIncompat);
		fami.setFamiNombre(famiNombre);
		fami.setFamihabili(famihabili);

		if (!this.tmp.equals(famiDescrip)) {
			if (edition.equals("false")) {
				if (familiaEJBBean.modificarFamilia(fami) > 0) {
					this.tmp = famiDescrip;
					borrarCampos();
					String successMessage = "Familia modificada con exito!";
					messages.setMessage(successMessage);
					messages.successMessage();
					setEdition("true");
				} else {
					String duplicateMessage = "Familia ya existente, por favor revise sus datos!";
					messages.setMessage(duplicateMessage);
					messages.errorMessage();
					setEdition("true");
				}
			} else {
				String noHabilitado = "Realice una busqueda para poder modificar una familia!";
				messages.setMessage(noHabilitado);
				messages.errorMessage();
				borrarCampos();
			}
		} else {
			String errorMessage = "La descripcion no ha sido modificada";
			messages.setMessage(errorMessage);
			messages.errorMessage();
		}
	}

	// Buscar limpiar campos desde la pagina
	public void borrarCampos() {
		setEdition("true");
		setFamiCodi(0);
		setFamiDescrip(null);
		setFamiIncompat(null);
		setFamiNombre(null);

	}

//Baja de Familia
	public void BajaFamilia() throws ServiceException {

		Familia fami = new Familia();
		fami.setFamiDescrip(famiDescrip);
		fami.setFamiCodi(famiCodi);
		fami.setFamiIncompat(famiIncompat);
		fami.setFamiNombre(famiNombre);
		fami.setFamihabili(famihabili);

		if (familiaEJBBean.eliminarFamilia(fami) > 0) {
			this.tmp = famiDescrip;
			borrarCampos();
			String successMessage = "Familia eliminada con exito!";
			messages.setMessage(successMessage);
			messages.successMessage();
			setEdition("true");
		} else {
			String duplicateMessage = "La familia que intenta eliminar está asociada a productos. Corrobore";
			messages.setMessage(duplicateMessage);
			messages.errorMessage();
			setEdition("true");
		}
	}

}