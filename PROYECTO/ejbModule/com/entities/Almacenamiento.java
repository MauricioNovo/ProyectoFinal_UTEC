package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the ALMACENAMIENTOS database table.
 * 
 */
@Entity
@Table(name = "ALMACENAMIENTOS")
@NamedQuery(name = "Almacenamiento.findAll", query = "SELECT a FROM Almacenamiento a")
public class Almacenamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ALMA_ID", nullable = false, length = 10)
	private long almaId;

	@Column(name = "ALMA_CAPESTIBA", nullable = false, length = 50)
	private String almaCapestiba;

	@Column(name = "ALMA_CAPPESO", nullable = false, columnDefinition = "NUMBER(9,2)")
	private Double almaCappeso;

	@Column(name = "ALMA_COSTOOP", nullable = false, columnDefinition = "NUMBER(9,2)")
	private Double almaCostoop;

	@Column(name = "ALMA_DESCRIP", nullable = false, length = 50)
	private String almaDescrip;

	@Column(name = "ALMA_VOLUMEN", nullable = false, columnDefinition = "NUMBER(9,2)")
	private BigDecimal almaVolumen;

	@Column(name = "ALMA_HABILI", nullable = false, length = 1)
	private int almaHabili;

	// bi-directional many-to-one association to Locale
	@ManyToOne
	@JoinColumn(name = "LOC_ID", nullable = false)
	private Locale locale;

	// bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy = "almacenamiento")
	private List<Movimiento> movimientos;

	public Almacenamiento() {
	}

	public long getAlmaId() {
		return this.almaId;
	}

	public void setAlmaId(long almaId) {
		this.almaId = almaId;
	}

	public String getAlmaCapestiba() {
		return this.almaCapestiba;
	}

	public void setAlmaCapestiba(String almaCapestiba) {
		this.almaCapestiba = almaCapestiba;
	}

	public Double getAlmaCappeso() {
		return this.almaCappeso;
	}

	public void setAlmaCappeso(Double almaCappeso) {
		this.almaCappeso = almaCappeso;
	}

	public Double getAlmaCostoop() {
		return this.almaCostoop;
	}

	public void setAlmaCostoop(Double almaCostoop) {
		this.almaCostoop = almaCostoop;
	}

	public String getAlmaDescrip() {
		return this.almaDescrip;
	}

	public void setAlmaDescrip(String almaDescrip) {
		this.almaDescrip = almaDescrip;
	}

	public BigDecimal getAlmaVolumen() {
		return this.almaVolumen;
	}

	public void setAlmaVolumen(BigDecimal almaVolumen) {
		this.almaVolumen = almaVolumen;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public int getAlmaHabili() {
		return almaHabili;
	}

	public void setAlmaHabili(int almaHabili) {
		this.almaHabili = almaHabili;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setAlmacenamiento(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setAlmacenamiento(null);

		return movimiento;
	}

}