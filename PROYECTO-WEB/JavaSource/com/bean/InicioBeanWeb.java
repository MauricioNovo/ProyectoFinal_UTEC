package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import com.entities.Usuario;
import com.messages.MessageView;

@ManagedBean
@RequestScoped
public class InicioBeanWeb {

	@EJB
	private MessageView messages;
	
	FacesContext facesContext;
	FacesMessage facesMessage;
	
	Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

	@PostConstruct
	public void init() {

	}
	
	public void verificarSesion() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		
		if(us == null) {
			context.getExternalContext().redirect("login.xhtml");
		}else {
			String perfil = us.getPerfile().getPerfNombre();
			if(perfil.equals("OPERARIO")) {
				context.getExternalContext().redirect("inicio.xhtml");
				
			}
		}
	}

	public String aProd() {
		String red = null;
		String perfil = us.getPerfile().getPerfNombre();
		System.out.println("El perfil" + perfil);
		facesContext = FacesContext.getCurrentInstance();
		try {
			if (perfil.equals("ADMINISTRADOR")  || perfil.equals("SUPERVISOR")) {
				System.out.println("Entra al if");
				red = "altaProd?faces-redirect=true";
			} else {
				
				System.out.println("Entra al else");
				messages.setMessage("No tiene permisos suficientes para realizar esta acción");
				messages.errorMessage();
			}
		} catch (Exception e) {
			System.out.println("Entra al catch");
			messages.setMessage("Error");
			messages.errorMessage();
			
		}
		System.out.println("sale al red");
		return red;

	}

	public String bProd() {
		String red = null;
		String perfil = us.getPerfile().getPerfNombre();
		System.out.println("El perfil" + perfil);
		facesContext = FacesContext.getCurrentInstance();
		try {
			if (perfil.equals("ADMINISTRADOR")  || perfil.equals("SUPERVISOR")) {
				System.out.println("Entra al if");
				red = "productoBaja?faces-redirect=true";
			} else {
				
				System.out.println("Entra al else");
				messages.setMessage("No tiene permisos suficientes para realizar esta acción");
				messages.errorMessage();
			}
		} catch (Exception e) {
			System.out.println("Entra al catch");
			messages.setMessage("Error");
			messages.errorMessage();
			
		}
		System.out.println("sale al red");
		return red;

	}
	
	public String mProd() {
		String red = null;
		String perfil = us.getPerfile().getPerfNombre();
		System.out.println("El perfil" + perfil);
		facesContext = FacesContext.getCurrentInstance();
		try {
			if (perfil.equals("ADMINISTRADOR")  || perfil.equals("SUPERVISOR")) {
				System.out.println("Entra al if");
				red = "modProd?faces-redirect=true";
			} else {
				
				System.out.println("Entra al else");
				messages.setMessage("No tiene permisos suficientes para realizar esta acción");
				messages.errorMessage();
			}
		} catch (Exception e) {
			System.out.println("Entra al catch");
			messages.setMessage("Error");
			messages.errorMessage();
			
		}
		System.out.println("sale al red");
		return red;

	}
	
	public String aFam() {
		String red = null;
		String perfil = us.getPerfile().getPerfNombre();
		System.out.println("El perfil" + perfil);
		facesContext = FacesContext.getCurrentInstance();
		try {
			if (perfil.equals("ADMINISTRADOR")  || perfil.equals("SUPERVISOR")) {
				System.out.println("Entra al if");
				red = "familiaAlta?faces-redirect=true";
			} else {
				
				System.out.println("Entra al else");
				messages.setMessage("No tiene permisos suficientes para realizar esta acción");
				messages.errorMessage();
			}
		} catch (Exception e) {
			System.out.println("Entra al catch");
			messages.setMessage("Error");
			messages.errorMessage();
			
		}
		System.out.println("sale al red");
		return red;

	}
	
	public String bFam() {
		String red = null;
		String perfil = us.getPerfile().getPerfNombre();
		System.out.println("El perfil" + perfil);
		facesContext = FacesContext.getCurrentInstance();
		try {
			if (perfil.equals("ADMINISTRADOR")  || perfil.equals("SUPERVISOR")) {
				System.out.println("Entra al if");
				red = "familiaBaja?faces-redirect=true";
			} else {
				
				System.out.println("Entra al else");
				messages.setMessage("No tiene permisos suficientes para realizar esta acción");
				messages.errorMessage();
			}
		} catch (Exception e) {
			System.out.println("Entra al catch");
			messages.setMessage("Error");
			messages.errorMessage();
			
		}
		System.out.println("sale al red");
		return red;

	}
	
	public String mFam() {
		String red = null;
		String perfil = us.getPerfile().getPerfNombre();
		System.out.println("El perfil" + perfil);
		facesContext = FacesContext.getCurrentInstance();
		try {
			if (perfil.equals("ADMINISTRADOR")  || perfil.equals("SUPERVISOR")) {
				System.out.println("Entra al if");
				red = "familiaModif?faces-redirect=true";
			} else {
				
				System.out.println("Entra al else");
				messages.setMessage("No tiene permisos suficientes para realizar esta acción");
				messages.errorMessage();
			}
		} catch (Exception e) {
			System.out.println("Entra al catch");
			messages.setMessage("Error");
			messages.errorMessage();
			
		}
		System.out.println("sale al red");
		return red;

	}
}
