package pe.edu.upc.icons.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.icons.models.entities.Usuario;
import pe.edu.upc.icons.services.UsuarioService;

@Named("dtUsuarioView")
@ViewScoped
public class UsuarioView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private List<Usuario> usuarios;
	
	@PostConstruct
	public void init() {
		this.loadUsuarios();
	}
	
	public void loadUsuarios() {
		try {
			this.usuarios = usuarioService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
}
