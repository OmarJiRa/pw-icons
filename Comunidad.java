package pe.edu.upc.icons.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comunidades")
public class Comunidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 200, nullable = false)
	private String descripcion;
	
	@Column(name = "tema", length = 50, nullable = false)
	private String tema;
	
	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	
	@OneToMany(mappedBy = "comunidad")
	private List<ComunidadUsuario> comunidadUsuarios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<ComunidadUsuario> getComunidadUsuarios() {
		return comunidadUsuarios;
	}

	public void setComunidadUsuarios(List<ComunidadUsuario> comunidadUsuarios) {
		this.comunidadUsuarios = comunidadUsuarios;
	}
	
}
