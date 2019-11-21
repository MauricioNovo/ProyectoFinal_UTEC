package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the LOCALES database table.
 * 
 */
@Entity
@Table(name = "LOCALES")
@NamedQuery(name = "Locale.findAll", query = "SELECT l FROM Locale l")
public class Locale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOC_ID", nullable = false, length = 2)
	private long locId;

	@Column(name = "LOC_DESCRIP", nullable = false, length = 50)
	private String locDescrip;

	@Column(name = "LOC_DIRECCION", nullable = false, length = 50)
	private String locDireccion;

	@Column(name = "LOC_HABILI", nullable = false, length = 1)
	private int locHabili;

	// bi-directional many-to-one association to Almacenamiento
	@OneToMany(mappedBy = "locale")
	private List<Almacenamiento> almacenamientos;

	// bi-directional many-to-one association to Ciudade
	@ManyToOne
	@JoinColumn(name = "LOC_CIU_ID", nullable = false)
	private Ciudade ciudade;

	// bi-directional many-to-one association to TipoLocale
	@ManyToOne
	@JoinColumn(name = "LOC_TIPO_ID", nullable = false)
	private TipoLocale tipoLocale;

	public Locale() {
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public String getLocDescrip() {
		return this.locDescrip;
	}

	public void setLocDescrip(String locDescrip) {
		this.locDescrip = locDescrip;
	}

	public String getLocDireccion() {
		return this.locDireccion;
	}

	public void setLocDireccion(String locDireccion) {
		this.locDireccion = locDireccion;
	}

	public int getLocHabili() {
		return locHabili;
	}

	public void setLocHabili(int locHabili) {
		this.locHabili = locHabili;
	}

	public List<Almacenamiento> getAlmacenamientos() {
		return this.almacenamientos;
	}

	public void setAlmacenamientos(List<Almacenamiento> almacenamientos) {
		this.almacenamientos = almacenamientos;
	}

	public Almacenamiento addAlmacenamiento(Almacenamiento almacenamiento) {
		getAlmacenamientos().add(almacenamiento);
		almacenamiento.setLocale(this);

		return almacenamiento;
	}

	public Almacenamiento removeAlmacenamiento(Almacenamiento almacenamiento) {
		getAlmacenamientos().remove(almacenamiento);
		almacenamiento.setLocale(null);

		return almacenamiento;
	}

	public Ciudade getCiudade() {
		return this.ciudade;
	}

	public void setCiudade(Ciudade ciudade) {
		this.ciudade = ciudade;
	}

	public TipoLocale getTipoLocale() {
		return this.tipoLocale;
	}

	public void setTipoLocale(TipoLocale tipoLocale) {
		this.tipoLocale = tipoLocale;
	}

}