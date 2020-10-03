package pe.edu.upc.icons.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.icons.models.entities.Categoria;
import pe.edu.upc.icons.services.CategoriaService;

@Named("dtCategoriaView")
@ViewScoped
public class CategoriaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		this.loadCategorias();
	}
	
	public void loadCategorias() {
		try {
			this.categorias = categoriaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
}

