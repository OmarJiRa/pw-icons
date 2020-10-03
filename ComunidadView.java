package pe.edu.upc.icons.controllers;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;


//import javax.accessibility.AccessibleAction;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.upc.icons.models.entities.Comunidad;
import pe.edu.upc.icons.services.ComunidadService;
import pe.edu.upc.icons.utils.Action;


@Named//("ComunidadView")
@ViewScoped
public class ComunidadView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ComunidadService comunidadService;

	private List<Comunidad> comunidades;
	private Comunidad comunidad;

	// PARA SELECCIONAR
	private Comunidad comunidadSelected;
	// PARA BUSCAR
	private Comunidad comunidadSearch;

	private Action action;

	// Estilo del panelGrid
	private String stylePanelComunidad;
	private String styleTableComunidad;

	//Disables for commandButton
	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;
	
	//Texto de confirmacion de dialogo
	private String messageConfirmDialog;

	@PostConstruct
	public void init() {
		this.comunidadSearch = new Comunidad();
		this.cleanForm();
		this.loadComunidades();
		this.action = Action.NONE;
		this.stateList();
	}
	
	public void loadComunidades() {
		try {
			this.setComunidades(comunidadService.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	
	//Para limpiar formulario 
	public void cleanForm() {
		this.comunidad = new Comunidad();
		this.comunidadSelected = null;
	}
	
	//Metodo cuando se hace en el boton nuevo
	public void newComunidad() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Hice click en Nuevo");
	}
	
	
	//Metodo cuando se hace click en el boton guardar
	public void saveComunidad() {
		try {
			comunidadService.save(this.comunidad);
			cleanForm();
			loadComunidades();
			this.action = Action.NONE;
			this.stateList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}	
	}

	// Método cuando se hace click en el boton Cancelar
	public void cancelComunidad() {
		cleanForm();
		this.stateList();
	}
	
	// Metodo que se ejecuta cuando el evento rowSelect ocurra
	public void selectComunidad(SelectEvent<Comunidad> e) {
		this.comunidadSelected = e.getObject();
		this.messageConfirmDialog = this.comunidadSelected.getNombre();
		this.stateSelectRow();
	}
	
	// Metodo que se ejecuta cuando el evento rowUnselect ocurra
		public void unselectComunidad(UnselectEvent<Comunidad> e) { 
			this.comunidadSelected = null;
			this.stateList();
		}
	
	// Método que se ejecuta cuando hago click en el boton Editar
		public void editCliente() {
			if( this.comunidadSelected != null ) {
				this.comunidad = this.comunidadSelected;
				this.action = Action.EDIT;
				this.stateNewEdit();
			}		
		}
	
	
		// Método que se ejecuta cuando hago click en el boton Eliminar
		public void deleteCliente() {
			if( this.comunidadSelected != null ) {
				try {
					comunidadService.deleteById( this.comunidadSelected.getId());
					cleanForm();
					loadComunidades();
					this.action = Action.NONE;
					this.stateList();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
		
		public void findByNombre() {
			if(!this.comunidadSearch.getNombre().isEmpty()) {
				try {
					this.comunidad = (Comunidad) comunidadService.findByNombre( comunidadSearch.getNombre() );
					this.stateList();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
		
		public void cleanByNombre() {
			this.comunidadSearch.setNombre("");
			loadComunidades();
			this.stateList();
		}
		
		
		//cuando se muestra la lista
		public void stateList() {
			this.stylePanelComunidad = "display:none;";
			this.styleTableComunidad = "display:block;";
			this.disabledButtonNuevo = false;
			this.disabledButtonGrabar = true;
			this.disabledButtonCancelar = true;
			this.disabledButtonEditar = true;
			this.disabledButtonEliminar = true;
		}

		//Cuando es nuevo y editar
		public void stateNewEdit() {
			
			this.stylePanelComunidad = "display:block;";
			this.styleTableComunidad = "display:none;";
			//Se oculta
			this.disabledButtonNuevo = true;
			//--
			this.disabledButtonGrabar = false;
			this.disabledButtonCancelar = false;
			this.disabledButtonEditar = true;
			this.disabledButtonEliminar = true;
		}
		
		//cuando seleciona una fila
		public void stateSelectRow() {
			this.stylePanelComunidad = "display:none;";
			this.styleTableComunidad= "display:block;";
			this.disabledButtonNuevo = false;
			this.disabledButtonGrabar = true;
			this.disabledButtonCancelar = true;
			//Se muestran(false)
			this.disabledButtonEditar = false;
			this.disabledButtonEliminar = false;
		}
		
		// Método que muestra los mensajes
		public void addMessage(String summary) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		
	public ComunidadService getComunidadService() {
		return comunidadService;
	}

	public List<Comunidad> getComunidades() {
		return comunidades;
	}

	public void setComunidades(List<Comunidad> comunidades) {
		this.comunidades = comunidades;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public boolean isDisabledButtonNuevo() {
		return disabledButtonNuevo;
	}

	public void setDisabledButtonNuevo(boolean disabledButtonNuevo) {
		this.disabledButtonNuevo = disabledButtonNuevo;
	}

	public boolean isDisabledButtonGrabar() {
		return disabledButtonGrabar;
	}

	public void setDisabledButtonGrabar(boolean disabledButtonGrabar) {
		this.disabledButtonGrabar = disabledButtonGrabar;
	}

	public boolean isDisabledButtonCancelar() {
		return disabledButtonCancelar;
	}

	public void setDisabledButtonCancelar(boolean disabledButtonCancelar) {
		this.disabledButtonCancelar = disabledButtonCancelar;
	}

	public boolean isDisabledButtonEditar() {
		return disabledButtonEditar;
	}

	public void setDisabledButtonEditar(boolean disabledButtonEditar) {
		this.disabledButtonEditar = disabledButtonEditar;
	}

	public boolean isDisabledButtonEliminar() {
		return disabledButtonEliminar;
	}

	public void setDisabledButtonEliminar(boolean disabledButtonEliminar) {
		this.disabledButtonEliminar = disabledButtonEliminar;
	}

	public String getMessageConfirmDialog() {
		return messageConfirmDialog;
	}

	public void setMessageConfirmDialog(String messageConfirmDialog) {
		this.messageConfirmDialog = messageConfirmDialog;
	}

	public Comunidad getComunidadSelected() {
		return comunidadSelected;
	}

	public void setComunidadSelected(Comunidad comunidadSelected) {
		this.comunidadSelected = comunidadSelected;
	}

	public Comunidad getComunidadSearch() {
		return comunidadSearch;
	}

	public void setComunidadSearch(Comunidad comunidadSearch) {
		this.comunidadSearch = comunidadSearch;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getStylePanelComunidad() {
		return stylePanelComunidad;
	}

	public void setStylePanelComunidad(String stylePanelComunidad) {
		this.stylePanelComunidad = stylePanelComunidad;
	}

	public String getStyleTableComunidad() {
		return styleTableComunidad;
	}

	public void setStyleTableComunidad(String styleTableComunidad) {
		this.styleTableComunidad = styleTableComunidad;
	}

	public void setComunidadService(ComunidadService comunidadService) {
		this.comunidadService = comunidadService;
	}

	
}


