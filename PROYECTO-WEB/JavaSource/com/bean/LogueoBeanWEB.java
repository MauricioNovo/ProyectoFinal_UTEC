package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.entities.Usuario;
import com.services.LogueoBeanRemote;
import com.session.SS;

@ManagedBean
@SessionScoped
public class LogueoBeanWEB {

	@EJB
	private LogueoBeanRemote logueoBean;

	private Usuario user;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	@PostConstruct
	public void LoginSuss() {
		user = new Usuario();

	}

	public String Login() {
		Usuario u;
		String red = null;

		try {
			u = logueoBean.iniciarSesion(user);
			if (u != null) {
				HttpSession hs = SS.getSession();
				hs.setAttribute("usuario", u);
				hs.getAttribute("usuario");
				red = "inicio?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Usuario y/o Contraseña incorrectas"));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "Verificar Error"));
			red = "login?faces-redirect=true";
		}

		return red;
	}

	public String Logout() {
		HttpSession hs = SS.getSession();
		hs.invalidate();
		return "login?faces-redirect=true";

	}

	public String recuperarContrasena() {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención!",
				"Por olvido de la contraseña deberá enviar un mail a: admin@mail.com"));

		return null;
	}

}
