package pe.edu.upc.icons.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "creador_id")
	private Creador creador;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "tipo", length = 20, nullable = false)
	private String tipo;
	
	@Column(name = "descripcion", length = 200, nullable = false)
	private String descripcion;
	
	@Column(name = "fecha_publicacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaPublicacion;
	
	@Column(name = "hora_publicacion", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaPublicacion;
	
	@Column(name = "exclusivo", nullable = false)
	private Boolean exclusivo;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Column(name = "link", length = 200, nullable = false)
	private String link;
	
	@OneToMany(mappedBy = "post")
	private List<UsuarioPost> usuarioPosts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Creador getCreador() {
		return creador;
	}

	public void setCreador(Creador creador) {
		this.creador = creador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Date getHoraPublicacion() {
		return horaPublicacion;
	}

	public void setHoraPublicacion(Date horaPublicacion) {
		this.horaPublicacion = horaPublicacion;
	}

	public Boolean getExclusivo() {
		return exclusivo;
	}

	public void setExclusivo(Boolean exclusivo) {
		this.exclusivo = exclusivo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<UsuarioPost> getUsuarioPosts() {
		return usuarioPosts;
	}

	public void setUsuarioPosts(List<UsuarioPost> usuarioPosts) {
		this.usuarioPosts = usuarioPosts;
	}
	
}
