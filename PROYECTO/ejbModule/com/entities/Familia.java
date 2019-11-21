package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the FAMILIAS database table.
 * 
 */
@Entity
@Table(name = "FAMILIAS")
@NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f")
public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FAMI_CODI", nullable = false, length = 2)
	private long famiCodi;

	@Column(name = "FAMI_DESCRIP", nullable = false, length = 30)
	private String famiDescrip;

	@Column(name = "FAMI_INCOMPAT", nullable = false, length = 25)
	private String famiIncompat;

	@Column(name = "FAMI_NOMBRE", nullable = false, length = 25)
	private String famiNombre;

	@Column(name = "FAMI_HABILI", nullable = true, length = 1)
	private int famihabili;

//bi-directional many-to-one association to Producto
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "familia")
	private List<Producto> productos;

	public Familia() {
	}

	public long getFamiCodi() {
		return this.famiCodi;
	}

	public String getFamiDescrip() {
		return this.famiDescrip;
	}

	public void setFamiDescrip(String famiDescrip) {
		this.famiDescrip = famiDescrip;
	}

	public String getFamiIncompat() {
		return this.famiIncompat;
	}

	public void setFamiIncompat(String famiIncompat) {
		this.famiIncompat = famiIncompat;
	}

	public String getFamiNombre() {
		return this.famiNombre;
	}

	public void setFamiNombre(String famiNombre) {
		this.famiNombre = famiNombre;
	}

	public void setFamiCodi(long famiCodi) {
		this.famiCodi = famiCodi;
	}

	public int getFamihabili() {
		return famihabili;
	}

	public void setFamihabili(int famihabili) {
		this.famihabili = famihabili;
	}

}