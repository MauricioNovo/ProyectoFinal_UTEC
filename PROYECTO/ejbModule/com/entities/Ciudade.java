package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the CIUDADES database table.
 * 
 */
@Entity
@Table(name = "CIUDADES")
@NamedQuery(name = "Ciudade.findAll", query = "SELECT c FROM Ciudade c")
public class Ciudade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CIU_ID", nullable = false, length = 2)
	private long ciuId;

	@Column(name = "CIU_NOMBRE", nullable = false, length = 50)
	private String ciuNombre;

	@Column(name = "CUI_HABILI", nullable = false, length = 1)
	private int ciuHabili;

	// bi-directional many-to-one association to Locale
	@OneToMany(mappedBy = "ciudade")
	private List<Locale> locales;

	public Ciudade() {
	}

	public long getCiuId() {
		return this.ciuId;
	}

	public void setCiuId(long ciuId) {
		this.ciuId = ciuId;
	}

	public String getCiuNombre() {
		return this.ciuNombre;
	}

	public void setCiuNombre(String ciuNombre) {
		this.ciuNombre = ciuNombre;
	}

	public int getCiuHabili() {
		return ciuHabili;
	}

	public void setCiuHabili(int ciuHabili) {
		this.ciuHabili = ciuHabili;
	}

	public List<Locale> getLocales() {
		return this.locales;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	public Locale addLocale(Locale locale) {
		getLocales().add(locale);
		locale.setCiudade(this);

		return locale;
	}

	public Locale removeLocale(Locale locale) {
		getLocales().remove(locale);
		locale.setCiudade(null);

		return locale;
	}

}