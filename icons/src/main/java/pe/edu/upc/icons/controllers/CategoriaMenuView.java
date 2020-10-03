package pe.edu.upc.icons.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.icons.models.entities.Categoria;
import pe.edu.upc.icons.services.CategoriaService;

@Named("categoriaMenuView")

@ViewScoped
public class CategoriaMenuView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Categoria> categorias;
	private Categoria categoria; 
	
	@Inject
	private CategoriaService categoriaService;
	
	@PostConstruct
	public void init() {
		loadCategorias();
	}
	
	public void loadCategorias() {
		try {
			this.categorias = categoriaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
		
	public List<Categoria> getCategorias(){
		return categorias; 
	}
	
	public Categoria getCategoria() {
		return categoria; 
	}
}
