package pe.edu.upc.icons.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioPostId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer usuario;
	private Integer post;
	
	public UsuarioPostId() {
		
	}
	
	public UsuarioPostId(Integer usuario, Integer post) {
		super();
		this.usuario = usuario;
		this.post = post;
	}
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	public Integer getPost() {
		return post;
	}
	public void setPost(Integer post) {
		this.post = post;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(usuario, post);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
        if (obj == null || getClass() != obj.getClass()) 
        	return false;
        UsuarioPostId usuarioPostId = (UsuarioPostId) obj;
        if (this.usuario != usuarioPostId.usuario) 
        	return false;
        return this.post == usuarioPostId.post;
	}

}
