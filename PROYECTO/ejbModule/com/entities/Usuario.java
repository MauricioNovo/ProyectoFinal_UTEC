package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name = "USUARIOS")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USU_CODIGO", length = 2, nullable = false)
	private long usuCodigo;

	@Column(name = "USU_APELLIDO", length = 50, nullable = false)
	private String usuApellido;

	@Column(name = "USU_CONTRASEÑA", length = 16, nullable = false)
	private String usuContrasenia;

	@Column(name = "USU_CORREO", length = 40, nullable = false)
	private String usuCorreo;

	@Column(name = "USU_NOMACCESO", length = 30, nullable = false)
	private String usuNomacceso;

	@Column(name = "USU_NOMBRE", length = 50, nullable = false)
	private String usuNombre;

	@Column(name = "USU_HABILI", nullable = false, length = 1)
	private int usuHabili;

	// bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "usuario")
	private List<Producto> productos;

	// bi-directional many-to-one association to Perfile
	@ManyToOne
	@JoinColumn(name = "PERF_CODIGO", nullable = false)
	private Perfile perfile;

	public Usuario() {
	}

	public Usuario(long usuCodigo, String usuApellido, String usuContrasenia, String usuCorreo, String usuNomacceso,
			String usuNombre) {
		super();
		this.usuApellido = usuApellido;
		this.usuCodigo = usuCodigo;
		this.usuContrasenia = usuContrasenia;
		this.usuCorreo = usuCorreo;
		this.usuNombre = usuNombre;
		this.usuNomacceso = usuNomacceso;
	}

	public long getUsuCodigo() {
		return this.usuCodigo;
	}

	public void setUsuCodigo(long usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuApellido() {
		return this.usuApellido;
	}

	public void setUsuApellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	public String getUsuContrasenia() {
		return this.usuContrasenia;
	}

	public void setUsuContrasenia(String usuContraseña) {
		this.usuContrasenia = usuContraseña;
	}

	public String getUsuCorreo() {
		return this.usuCorreo;
	}

	public void setUsuCorreo(String usuCorreo) {
		this.usuCorreo = usuCorreo;
	}

	public String getUsuNomacceso() {
		return this.usuNomacceso;
	}

	public void setUsuNomacceso(String usuNomacceso) {
		this.usuNomacceso = usuNomacceso;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public int getUsuHabili() {
		return usuHabili;
	}

	public void setUsuHabili(int usuHabili) {
		this.usuHabili = usuHabili;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setUsuario(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUsuario(null);

		return producto;
	}

	public Perfile getPerfile() {
		return this.perfile;
	}

	public void setPerfile(Perfile perfile) {
		this.perfile = perfile;
	}

}