package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TIPO_LOCALES database table.
 * 
 */
@Entity
@Table(name = "TIPO_LOCALES")
@NamedQuery(name = "TipoLocale.findAll", query = "SELECT t FROM TipoLocale t")
public class TipoLocale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOCTIPO_ID", nullable = false, length = 10)
	private long loctipoId;

	@Column(name = "LOCTIPO_NOMBRE", nullable = false, length = 50)
	private String loctipoNombre;

	@Column(name = "LOCTIPO_HABILI", nullable = false, length = 1)
	private int loctipoHabili;

	// bi-directional many-to-one association to Locale
	@OneToMany(mappedBy = "tipoLocale")
	private List<Locale> locales;

	public TipoLocale() {
	}

	public long getLoctipoId() {
		return this.loctipoId;
	}

	public void setLoctipoId(long loctipoId) {
		this.loctipoId = loctipoId;
	}

	public String getLoctipoNombre() {
		return this.loctipoNombre;
	}

	public void setLoctipoNombre(String loctipoNombre) {
		this.loctipoNombre = loctipoNombre;
	}

	public int getLoctipoHabili() {
		return loctipoHabili;
	}

	public void setLoctipoHabili(int loctipoHabili) {
		this.loctipoHabili = loctipoHabili;
	}

	public List<Locale> getLocales() {
		return this.locales;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	public Locale addLocale(Locale locale) {
		getLocales().add(locale);
		locale.setTipoLocale(this);

		return locale;
	}

	public Locale removeLocale(Locale locale) {
		getLocales().remove(locale);
		locale.setTipoLocale(null);

		return locale;
	}

}