package pe.edu.upc.icons.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.upc.icons.models.entities.Categoria;
import pe.edu.upc.icons.models.entities.Post;
import pe.edu.upc.icons.services.CategoriaService;
import pe.edu.upc.icons.services.PostService;
import pe.edu.upc.icons.utils.Action;

@Named("postView")
@ViewScoped
public class PostView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Post> posts;

	// Objeto asociado al formulario
	private Post post;
	private Post postSelected;
	private Post postSearch;

	private List<Categoria> categorias;
	private Categoria categoriaSelected;

	private Action action;
	private String stylePanelPost;
	private String styleTablePost;

	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;

	private String messageConfirmDialog;

	@Inject
	private PostService postService;

	@Inject
	private CategoriaService categoriaService;

	@PostConstruct
	public void init() {
		this.postSearch = new Post();
		this.cleanForm();
		this.action = Action.NONE;
		this.stateList();
		loadPosts();
	}

	//
	public void loadPosts() {
		try {
			this.posts = postService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}

	//
	public void loadCategoria(Integer id) {
		try {
			this.categoriaSelected = (categoriaService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}

	public void loadCategorias() {
		try {
			this.categorias = categoriaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}

	//
	public void cleanForm() {
		this.post = new Post();
		this.postSelected = null;
	}

	//
	public void newPost() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Agregar un post!");
	}

	// Funciona cuando se cambia el distrito
	public void changeCategoria() {
		if (this.post.getCategoria() != null) {
			if (!this.post.getCategoria().getId().equals(this.post.getCategoriaId())) {
				loadCategoria(this.post.getCategoriaId());
				this.post.setCategoria(this.categoriaSelected);
			}
		} else {
			loadCategoria(this.post.getCategoriaId());
			this.post.setCategoria(this.categoriaSelected);
		}
	}

	// Metodo al grabar
	public void savePost() {
		if (this.action == Action.NEW || this.action == Action.EDIT) {

			try {

				changeCategoria();

				if (this.action == Action.NEW)
					postService.save(this.post);
				else
					postService.update(this.post);

				cleanForm();
				loadPosts();
				this.action = Action.NONE;
				this.stateList();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				// TODO: handle exception
			}
		}
	}

	// Método cuando se hace click en el boton Cancelar
	public void cancelPost() {
		cleanForm();
		this.stateList();
	}

	// Metodo que se ejecuta cuando el evento rowSelect ocurra
	public void selectPost(SelectEvent<Post> e) {
		this.postSelected = e.getObject();
		this.messageConfirmDialog = this.postSelected.getNombre();
		this.stateSelectRow();
	}

	// Metodo que se ejecuta cuando el evento rowUnselect ocurra
	public void unselectPost(UnselectEvent<Post> e) {
		this.postSelected = null;
		this.stateList();
	}

	// Método que se ejecuta cuando hago click en el boton Editar
	public void editPost() {
		if (this.postSelected != null) {
			this.post = this.postSelected;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}
	
	// Método que se ejecuta cuando hago click en el boton Eliminar
	public void deletePost() {
		
		if (this.postSelected != null) {
			try {
				postService.deleteById(this.postSelected.getId());
				cleanForm();
				loadPosts();
				this.action = Action.NONE;
				this.stateList();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void findByNombre() {
		if (!this.postSearch.getNombre().isEmpty()) {
			try {
				this.posts = postService.findByNombre(postSearch.getNombre());
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				// TODO: handle exception
			}
		}
	}
	
	public void cleanByNombres() {
		this.postSearch.setNombre("");
		loadPosts();
		this.stateList();
	}
	
	// State on Application
	public void stateList() {
		this.stylePanelPost = "display:none;";
		this.styleTablePost = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateNewEdit() {
		this.stylePanelPost = "display:block;";
		this.styleTablePost = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelPost = "display:none;";
		this.styleTablePost = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = false;
		this.disabledButtonEliminar = false;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getStylePanelPost() {
		return stylePanelPost;
	}

	public void setStylePanelPost(String stylePanelPost) {
		this.stylePanelPost = stylePanelPost;
	}

	public String getStyleTablePost() {
		return styleTablePost;
	}

	public void setStyleTablePost(String styleTablePost) {
		this.styleTablePost = styleTablePost;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public String getMessageConfirmDialog() {
		return messageConfirmDialog;
	}

	public void setMessageConfirmDialog(String messageConfirmDialog) {
		this.messageConfirmDialog = messageConfirmDialog;
	}

	public boolean isDisabledButtonNuevo() {
		return disabledButtonNuevo;
	}

	public boolean isDisabledButtonGrabar() {
		return disabledButtonGrabar;
	}

	public boolean isDisabledButtonCancelar() {
		return disabledButtonCancelar;
	}

	public boolean isDisabledButtonEditar() {
		return disabledButtonEditar;
	}

	public boolean isDisabledButtonEliminar() {
		return disabledButtonEliminar;
	}
	
	public Post getPostSearch() {
		return postSearch;
	}
	
}
