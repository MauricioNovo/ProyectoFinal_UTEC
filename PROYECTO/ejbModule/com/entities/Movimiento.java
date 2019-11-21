package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the MOVIMIENTOS database table.
 * 
 */
@Entity
@Table(name = "MOVIMIENTOS")
@NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MOV_CODIGO", nullable = false, length = 10)
	private long movCodigo;

	@Column(name = "MOV_CANTIDAD", nullable = false, columnDefinition = "NUMBER(9,2)")
	private Double movCantidad;

	@Column(name = "MOV_COSTO", nullable = false, columnDefinition = "NUMBER(9,2)")
	private Double movCosto;

	@Column(name = "MOV_DESCRIPCION", nullable = false, length = 100)
	private String movDescripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "MOV_FECHA")
	private Date movFecha;

	@Column(name = "MOV_TIPO", nullable = false, length = 1)
	private String movTipo;

	@Column(name = "MOV_HABILI", nullable = false, length = 1)
	private int movHabili;

	// bi-directional many-to-one association to Almacenamiento
	@ManyToOne
	@JoinColumn(name = "ALMA_ID", nullable = false)
	private Almacenamiento almacenamiento;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "PROD_ID", nullable = false)
	private Producto producto;

	public Movimiento() {
	}

	public long getMovCodigo() {
		return this.movCodigo;
	}

	public void setMovCodigo(long movCodigo) {
		this.movCodigo = movCodigo;
	}

	public Double getMovCantidad() {
		return this.movCantidad;
	}

	public void setMovCantidad(Double movCantidad) {
		this.movCantidad = movCantidad;
	}

	public Double getMovCosto() {
		return this.movCosto;
	}

	public void setMovCosto(Double movCosto) {
		this.movCosto = movCosto;
	}

	public String getMovDescripcion() {
		return this.movDescripcion;
	}

	public void setMovDescripcion(String movDescripcion) {
		this.movDescripcion = movDescripcion;
	}

	public Date getMovFecha() {
		return this.movFecha;
	}

	public void setMovFecha(Date movFecha) {
		this.movFecha = movFecha;
	}

	public String getMovTipo() {
		return this.movTipo;
	}

	public void setMovTipo(String movTipo) {
		this.movTipo = movTipo;
	}

	public Almacenamiento getAlmacenamiento() {
		return this.almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public int getMovHabili() {
		return movHabili;
	}

	public void setMovHabili(int movHabili) {
		this.movHabili = movHabili;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}