package pe.edu.upc.icons.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombres_apellidos", length = 60, nullable = false)
	private String nombresApellidos; // lower cammel case
	
	@Column(name = "direccion", length = 50, nullable = false)
    private String direccion;
	
	@Column(name = "telefono", length = 9, nullable = false)
    private String telefono;

    @Column(name = "email", length = 30, nullable = false)
    private String email;
    
    @OneToMany(mappedBy = "usuario")
	private List<ComunidadUsuario> comunidadUsuarios;
    
    @OneToMany(mappedBy = "usuario")
    private List<CreadorUsuario> creadorUsuarios;
    
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPost> usuarioPosts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ComunidadUsuario> getComunidadUsuarios() {
		return comunidadUsuarios;
	}

	public void setComunidadUsuarios(List<ComunidadUsuario> comunidadUsuarios) {
		this.comunidadUsuarios = comunidadUsuarios;
	}

	public List<CreadorUsuario> getCreadorUsuarios() {
		return creadorUsuarios;
	}

	public void setCreadorUsuarios(List<CreadorUsuario> creadorUsuarios) {
		this.creadorUsuarios = creadorUsuarios;
	}

	public List<UsuarioPost> getUsuarioPosts() {
		return usuarioPosts;
	}

	public void setUsuarioPosts(List<UsuarioPost> usuarioPosts) {
		this.usuarioPosts = usuarioPosts;
	}
    
}
