package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the PERFILES database table.
 * 
 */
@Entity
@Table(name = "PERFILES")
@NamedQuery(name = "Perfile.findAll", query = "SELECT p FROM Perfile p")
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "PERF_CODIGO", nullable = false, length = 10)
	private long perfCodigo;

	@Column(name = "PERF_NOMBRE", length = 16, nullable = false)
	private String perfNombre;

	@Column(name = "PERF_HABILI", nullable = false, length = 1)
	private int perfHabili;

	// bi-directional many-to-one association to Usuario
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "perfile")
	private List<Usuario> usuarios;

	public Perfile() {
	}

	public long getPerfCodigo() {
		return this.perfCodigo;
	}

	public void setPerfCodigo(long perfCodigo) {
		this.perfCodigo = perfCodigo;
	}

	public String getPerfNombre() {
		return this.perfNombre;
	}

	public void setPerfNombre(String perfNombre) {
		this.perfNombre = perfNombre;
	}

	public int getPerfHabili() {
		return perfHabili;
	}

	public void setPerfHabili(int perfHabili) {
		this.perfHabili = perfHabili;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPerfile(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPerfile(null);

		return usuario;
	}

}