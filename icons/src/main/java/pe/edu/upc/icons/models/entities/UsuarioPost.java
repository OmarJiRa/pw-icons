package pe.edu.upc.icons.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario_post")
@IdClass(UsuarioPostId.class)
public class UsuarioPost {
	@Id
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@Column(name = "me_gusta", nullable = false)
	private Boolean meGusta;
	
	@Column(name = "comentario", length = 150, nullable = false)
	private String comentario;
	
	@Column(name = "fecha_comentario", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaComentario;
	
	@Column(name = "hora_publicacion", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaComentario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Boolean getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(Boolean meGusta) {
		this.meGusta = meGusta;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public Date getHoraComentario() {
		return horaComentario;
	}

	public void setHoraComentario(Date horaComentario) {
		this.horaComentario = horaComentario;
	}
	
}
