package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerated.PedEstado;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the PEDIDOS database table.
 * 
 */
@Entity
@Table(name = "PEDIDOS")
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "PED_ID", nullable = false, length = 10)
	private long pedId;

	@Enumerated(EnumType.STRING)
	@Column(name = "PED_ESTADO", length = 1, nullable = false)
	private PedEstado pedEstado;

	@Temporal(TemporalType.DATE)
	@Column(name = "PED_FEC_ESTIM_ENT", nullable = false)
	private Date pedFecEstimEnt;

	@Temporal(TemporalType.DATE)
	@Column(name = "PED_FECHA", nullable = false)
	private Date pedFecha;

	@Column(name = "PED_REC_CODIGO", length = 50, nullable = false)
	private String pedRecCodigo;

	@Column(name = "PED_REC_COMENTARIO", nullable = false, length = 100)
	private String pedRecComentario;

	@Temporal(TemporalType.DATE)
	@Column(name = "PED_REC_FECHA")
	private Date pedRecFecha;

	@Column(name = "PED_HABILI", nullable = false, length = 1)
	private int pedHabili;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "USU_CODIGO", nullable = false)
	private Usuario usuario;

	// bi-directional one-to-one association to Renglonespedido
	@OneToOne(mappedBy = "pedido")
	private Renglonespedido renglonespedido;

	public Pedido() {
	}

	public Pedido(PedEstado pedEstado, long pedId, Date pedRecFec, String pedRecComent) {
		super();
		this.pedEstado = pedEstado;
		this.pedId = pedId;
		this.pedRecComentario = pedRecComent;
		this.pedRecFecha = pedRecFec;
	}

	public long getPedId() {
		return this.pedId;
	}

	public void setPedId(long pedId) {
		this.pedId = pedId;
	}

	public PedEstado getPedEstado() {
		return this.pedEstado;
	}

	public void setPedEstado(PedEstado pedEstado) {
		this.pedEstado = pedEstado;
	}

	public Date getPedFecEstimEnt() {
		return this.pedFecEstimEnt;
	}

	public void setPedFecEstimEnt(Date pedFecEstimEnt) {
		this.pedFecEstimEnt = pedFecEstimEnt;
	}

	public Date getPedFecha() {
		return this.pedFecha;
	}

	public void setPedFecha(Date pedFecha) {
		this.pedFecha = pedFecha;
	}

	public String getPedRecCodigo() {
		return this.pedRecCodigo;
	}

	public void setPedRecCodigo(String pedRecCodigo) {
		this.pedRecCodigo = pedRecCodigo;
	}

	public String getPedRecComentario() {
		return this.pedRecComentario;
	}

	public void setPedRecComentario(String pedRecComentario) {
		this.pedRecComentario = pedRecComentario;
	}

	public Date getPedRecFecha() {
		return this.pedRecFecha;
	}

	public void setPedRecFecha(Date pedRecFecha) {
		this.pedRecFecha = pedRecFecha;
	}

	public int getPedHabili() {
		return pedHabili;
	}

	public void setPedHabili(int pedHabili) {
		this.pedHabili = pedHabili;
	}

	public Usuario getUsuCodigo() {
		return this.usuario;
	}

	public void setUsuCodigo(Usuario usuario) {
		this.usuario = usuario;
	}

	public Renglonespedido getRenglonespedido() {
		return this.renglonespedido;
	}

	public void setRenglonespedido(Renglonespedido renglonespedido) {
		this.renglonespedido = renglonespedido;
	}

}