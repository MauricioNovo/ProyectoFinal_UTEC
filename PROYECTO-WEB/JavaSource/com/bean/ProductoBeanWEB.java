package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.entities.Familia;
import com.entities.Producto;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.enumerated.TipoEstiba;
import com.exceptions.ServiceException;
import com.messages.MessageView;
import com.services.FamiliaBeanRemote;
import com.services.LogueoBeanRemote;
import com.services.ProductoBeanRemote;
import com.session.SS;



@ManagedBean
@SessionScoped
public class ProductoBeanWEB {

	@EJB
	private ProductoBeanRemote productoEJBBean;

	@EJB
	private FamiliaBeanRemote familiaEJBBean;

	@EJB
	private LogueoBeanRemote logueoEJBBean;
	
	@EJB
	private MessageView messages;

	//buscar prod
	private String filtar;
	private String buscar;
	//mensaje
	//setear inputs
	private String nombreFam;
	private Producto producto;
	private Familia familia;
	private TipoEstiba estiba;
	private Segmentacion seg;
	private List<Familia> listFamilias;
	//habilitar modificacion
	private boolean habMod; //boolean primitivo (false)
	
	//Usuario ing prod
	private String usuAcceso;
	private Usuario usuario;
	// obtener usuario actual
	private Usuario currentUser;
	//busqueda
	
	public String getFiltar() {
		return filtar;
	}

	public void setFiltar(String filtar) {
		this.filtar = filtar;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	//campos
	public Segmentacion getSeg() {
		return seg;
	}

	public void setSeg(Segmentacion seg) {
		this.seg = seg;
	}

	public Producto getProducto() {
		if(producto == null) {
			producto = new Producto();
			return producto;
		}else {
			return producto;	
		}

	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public TipoEstiba getEstiba() {
		return estiba;
	}

	public void setEstiba(TipoEstiba estiba) {
		this.estiba = estiba;
	}

	public String getNombreFam() {
		onload();
		return nombreFam;
	}

	public void setNombreFam(String nombreFam) {
		this.nombreFam = nombreFam;
	}

	public Usuario getCurrentUser() {
		HttpSession hs = SS.getSession();
		Usuario user = (Usuario) hs.getAttribute("usuario");
		return user;
	}

	public void setCurrentUser(Usuario currentUser) {
		this.currentUser = currentUser;
	}
	
	public List<Familia> getListFamilias() {
		return listFamilias;
	}

	public void setListFamilias(List<Familia> selectedValues) {
		this.listFamilias = selectedValues;
	}

	//obtener valores de enums
	public TipoEstiba[] getTipoEstiba() {
		return TipoEstiba.values();
	}

	public Segmentacion[] getSegmentacion() {
		return Segmentacion.values();
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public String getUsuAcceso() {
		return usuAcceso;
	}

	public void setUsuAcceso(String usuAcceso) {
		this.usuAcceso = usuAcceso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isHabMod() {
		return habMod;
	}

	public void setHabMod(boolean habMod) {
		this.habMod = habMod;
	}

	//metodos 

	@PostConstruct
	public void onload() {
		
	listFamilias = familiaEJBBean.obtenerFamilias();
	
	}
	public void obtenerProducto() {
		if(filtar.equals("ID")) {
			obtenerProductoPorId();
		}else if (filtar.equals("Nombre")) {
			obtenerProductoPorNombre();
		}
	}

	public void obtenerProductoPorId() {
		try {
			long id = Long.parseLong(buscar);
			if (id > 0) {
				Producto prod = new Producto();
				prod = productoEJBBean.obtenerProductoPorCodigo(id);
				if(prod != null) {
					setProducto(prod);
					setEstiba(prod.getProdEstiba());
					setSeg(prod.getProdSegmetac());
					setNombreFam(prod.getFamilia().getFamiNombre());
					setUsuAcceso(prod.getUsuario().getUsuNomacceso());
					habMod = true;
				}
				
			}
		}catch (NumberFormatException n) {
			messages.setMessage("Tipo de dato invalido");
			messages.errorMessage();
			LimpiarCampos();
			n.printStackTrace();
		} catch (Exception e) {
			messages.setMessage(e.getMessage());
			messages.errorMessage();
			LimpiarCampos();
			e.printStackTrace();
		}
	}

	public void obtenerProductoPorNombre() {	
		try {
			Producto prod = new Producto();
			prod = productoEJBBean.productoPorNombre(buscar);
			if(prod != null) {
				setProducto(prod);
				setEstiba(prod.getProdEstiba());
				setSeg(prod.getProdSegmetac());
				setNombreFam(prod.getFamilia().getFamiNombre());
				setUsuAcceso(prod.getUsuario().getUsuNomacceso());
				habMod = true;
			}
			
		} catch (ServiceException e) {
			messages.setMessage(e.getMessage());
			messages.errorMessage();
			LimpiarCampos();
			e.printStackTrace();
		}
	}

	public void modificarProducto() {
		try {
			if (habMod) {
				setFamilia(familiaEJBBean.familiaPorNombre(nombreFam)); 
			setUsuario(logueoEJBBean.obtenerUsuarioPorNombre(usuAcceso));
			producto.setFamilia(familia);
			producto.setUsuario(usuario);
			producto.setProdTipoestiba(estiba);
			producto.setProdSegmetac(seg);
			
			productoEJBBean.modificarProducto(producto, getCurrentUser());
			
			messages.setMessage("Producto modificado con exito!");
			messages.successMessage();
			// limpiar los campos
			LimpiarCampos();
			}else {
				messages.setMessage("Realice una busqueda para poder modificar un producto!");
				messages.errorMessage();
				LimpiarCampos();
			}
			
		}catch (ServiceException e) {
			messages.setMessage(e.getMessage().toString());
			messages.errorMessage();
			e.printStackTrace();
		}

	}

	public void altaProducto() {
		try {
			setFamilia(familiaEJBBean.familiaPorNombre(nombreFam));
			producto.setFamilia(familia);
			producto.setUsuario(getCurrentUser());
			producto.setProdTipoestiba(estiba);
			producto.setProdSegmetac(seg);

			Long codProd = productoEJBBean.crearProducto(producto, producto.getUsuario());
			messages.setMessage("Producto ingresado con exito");
			messages.successMessage();
			messages.setMessage("Codigo producto: " + codProd);
			messages.successMessage();
			LimpiarCampos();

		} catch (ServiceException e) {
			messages.setMessage(e.getMessage().toString());
			messages.errorMessage();
			e.printStackTrace();
		} 
		
	}
	
	public void bajaProducto() {
		try {
			productoEJBBean.borrarProducto(producto, getCurrentUser());
			messages.setMessage("Producto eliminado con exito!");
			messages.successMessage();
			LimpiarCampos();
		} catch (ServiceException e) {
			messages.setMessage(e.getMessage().toString());
			messages.errorMessage();
			e.printStackTrace();
		}
	}
	
	public void LimpiarCampos() {
		producto = null;
		usuario = null;
		familia = null;
		nombreFam = null;
		buscar = null;
		usuAcceso = null;
		habMod = false;
	}
}


