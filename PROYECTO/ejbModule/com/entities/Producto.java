package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerated.Segmentacion;
import com.enumerated.TipoEstiba;

import java.util.List;
import java.util.Date;

/**
 * The persistent class for the PRODUCTOS database table.
 * 
 */
@Entity
@Table(name = "PRODUCTOS")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROD_ID", nullable = false, length = 10)
	private long prodId;

	@Temporal(TemporalType.DATE)
	@Column(name = "PROD_FELAB", nullable = true)
	private Date prodFelab;

	@Temporal(TemporalType.DATE)
	@Column(name = "PROD_FVEN", nullable = true)
	private Date prodFven;

	@Column(name = "PROD_LOTE", nullable = true, length = 20)
	private String prodLote;

	@Column(name = "PROD_NOMBRE", nullable = false, length = 50)
	private String prodNombre;

	@Column(name = "PROD_PESO", nullable = true, columnDefinition = "NUMBER(9,2)")
	private Double prodPeso;

	@Column(name = "PROD_PRECIO", nullable = true, columnDefinition = "NUMBER(9,2)")
	private Double prodPrecio;

	@Enumerated(EnumType.STRING)
	@Column(name = "PROD_SEGMETAC", nullable = true, length = 2)
	private Segmentacion prodSegmentac;

	@Column(name = "PROD_STKMIN", nullable = true, columnDefinition = "NUMBER(9,2)")
	private Integer prodStkMin;

	@Column(name = "PROD_STKTOTAL", nullable = true, columnDefinition = "NUMBER(9,2)")
	private Integer prodStkTotal;

	@Enumerated(EnumType.STRING)
	@Column(name = "PROD_TIPOESTIBA", nullable = true, length = 2)
	private TipoEstiba prodEstiba;

	@Column(name = "PROD_VOL", nullable = true, columnDefinition = "NUMBER(9,2)")
	private Double prodVol;

	@Column(name = "PROD_HABILI", nullable = false, length = 1)
	private int prodHabili;

	// bi-directional many-to-one association to Movimiento
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "producto")
	private List<Movimiento> movimientos;

	// bi-directional many-to-one association to Familia
	@ManyToOne
	@JoinColumn(name = "FAMI_CODI", nullable = false)
	private Familia familia;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "USU_CODIGO", nullable = false)
	private Usuario usuario;

	// bi-directional many-to-one association to Renglonespedido
	@OneToMany(mappedBy = "producto")
	private List<Renglonespedido> renglonespedidos;

	public Producto() {
	}

	public long getProdId() {
		return this.prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public Date getProdFelab() {
		return this.prodFelab;
	}

	public void setProdFelab(Date prodFelab) {
		this.prodFelab = prodFelab;
	}

	public Date getProdFven() {
		return this.prodFven;
	}

	public void setProdFven(Date prodFven) {
		this.prodFven = prodFven;
	}

	public String getProdLote() {
		return this.prodLote;
	}

	public void setProdLote(String prodLote) {
		this.prodLote = prodLote;
	}

	public String getProdNombre() {
		return this.prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public Double getProdPeso() {
		return this.prodPeso;
	}

	public void setProdPeso(Double prodPeso) {
		this.prodPeso = prodPeso;
	}

	public Double getProdPrecio() {
		return this.prodPrecio;
	}

	public void setProdPrecio(Double prodPrecio) {
		this.prodPrecio = prodPrecio;
	}

	public Segmentacion getProdSegmetac() {
		return this.prodSegmentac;
	}

	public void setProdSegmetac(Segmentacion prodSegmetac) {
		this.prodSegmentac = prodSegmetac;
	}

	public Integer getProdStkmin() {
		return this.prodStkMin;
	}

	public void setProdStkmin(Integer prodStkmin) {
		this.prodStkMin = prodStkmin;
	}

	public Integer getProdStktotal() {
		return this.prodStkTotal;
	}

	public void setProdStktotal(Integer prodStktotal) {
		this.prodStkTotal = prodStktotal;
	}

	public TipoEstiba getProdEstiba() {
		return this.prodEstiba;
	}

	public void setProdTipoestiba(TipoEstiba prodTipoestiba) {
		this.prodEstiba = prodTipoestiba;
	}

	public Double getProdVol() {
		return this.prodVol;
	}

	public void setProdVol(Double prodVol) {
		this.prodVol = prodVol;
	}

	public Segmentacion getProdSegmentac() {
		return prodSegmentac;
	}

	public void setProdSegmentac(Segmentacion prodSegmentac) {
		this.prodSegmentac = prodSegmentac;
	}

	public Integer getProdStkMin() {
		return prodStkMin;
	}

	public void setProdStkMin(Integer prodStkMin) {
		this.prodStkMin = prodStkMin;
	}

	public Integer getProdStkTotal() {
		return prodStkTotal;
	}

	public void setProdStkTotal(Integer prodStkTotal) {
		this.prodStkTotal = prodStkTotal;
	}

	public int getProdHabili() {
		return prodHabili;
	}

	public void setProdHabili(int prodHabili) {
		this.prodHabili = prodHabili;
	}

	public void setProdEstiba(TipoEstiba prodEstiba) {
		this.prodEstiba = prodEstiba;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setProducto(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setProducto(null);

		return movimiento;
	}

	public Familia getFamilia() {
		return this.familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Renglonespedido> getRenglonespedidos() {
		return this.renglonespedidos;
	}

	public void setRenglonespedidos(List<Renglonespedido> renglonespedidos) {
		this.renglonespedidos = renglonespedidos;
	}

	public Renglonespedido addRenglonespedido(Renglonespedido renglonespedido) {
		getRenglonespedidos().add(renglonespedido);
		renglonespedido.setProducto(this);

		return renglonespedido;
	}

	public Renglonespedido removeRenglonespedido(Renglonespedido renglonespedido) {
		getRenglonespedidos().remove(renglonespedido);
		renglonespedido.setProducto(null);

		return renglonespedido;
	}
}