package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RENGLONESPEDIDOS database table.
 * 
 */
@Entity
@Table(name = "RENGLONESPEDIDOS")
@NamedQuery(name = "Renglonespedido.findAll", query = "SELECT r FROM Renglonespedido r")
public class Renglonespedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "RENPED_ID", length = 10, nullable = false)
	private int renpedId;

	// bi-directional one-to-one association to Pedido
	@OneToOne
	@JoinColumn(name = "PED_ID", nullable = false)
	private Pedido pedido;

	@Column(name = "RENPED_CANT", length = 10, nullable = false, columnDefinition = "NUMBER(9,2)")
	private Double renpedCant;

	@Column(name = "RENPED_NRO", length = 10, nullable = false, columnDefinition = "NUMBER(9,2)")
	private Double renpedNro;

	@Column(name = "RENPED_HABILI", nullable = false, length = 1)
	private int renpedHabili;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "PROD_ID")
	private Producto producto;

	public Renglonespedido() {
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Double getRenpedCant() {
		return this.renpedCant;
	}

	public void setRenpedCant(Double renpedCant) {
		this.renpedCant = renpedCant;
	}

	public int getRenpedId() {
		return this.renpedId;
	}

	public void setRenpedId(int renpedId) {
		this.renpedId = renpedId;
	}

	public Double getRenpedNro() {
		return this.renpedNro;
	}

	public void setRenpedNro(Double renpedNro) {
		this.renpedNro = renpedNro;
	}

	public int getRenpedHabili() {
		return renpedHabili;
	}

	public void setRenpedHabili(int renpedHabili) {
		this.renpedHabili = renpedHabili;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}