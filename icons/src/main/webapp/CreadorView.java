package pe.edu.upc.icons.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.upc.icons.models.entities.Creador;
import pe.edu.upc.icons.services.CreadorService;
import pe.edu.upc.icons.utils.Action;

@Named("creadorView")
@ViewScoped
public class CreadorView implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Creador> creadores;
	private Creador creador;
	private Creador creadorSelected;
	private Creador creadorSearch;

	private Action action;

	private String stylePanelCreador;
	private String styleTableCreador;

	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;

	private String messageConfirmDialog;

	@Inject
	private CreadorService creadorService;

	@PostConstruct
	public void init() {
		this.loadCreadores();
		this.cleanForm();
		this.stateList();
		this.action = Action.NONE;
		this.creadorSearch = new Creador();

	}

	public void cleanForm() {
		this.creador = new Creador();
		this.creadorSelected = null;
	}

	public void newCreador() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Hice click en Nuevo");
	}

	public void loadCreadores() {
		try {
			this.creadores = creadorService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void editCreador() {
		if (this.creadorSelected != null) {
			this.creador = this.creadorSelected;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}
	}

	public void deleteCreador() {
		if (this.creadorSelected != null) {
			try {
				creadorService.deleteById(this.creadorSelected.getId());
				cleanForm();
				loadCreadores();
				this.action = Action.NONE;
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void findByNombresApellidos() {
		if (!this.creadorSearch.getNombresApellidos().isEmpty()) {
			try {
				this.creadores = creadorService.findByNombresApellidos(creadorSearch.getNombresApellidos());
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void findByNumeroDocumento() {
		if (!this.creadorSearch.getNumeroDocumento().isEmpty()) {
			try {
				this.creadores = new ArrayList<>();
				Optional<Creador> optional = creadorService.findByNumeroDocumento(creadorSearch.getNumeroDocumento());
				if (optional.isPresent()) {
					this.creadores.add(optional.get());
				}
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void cleanByNombresApellidos() {
		this.creadorSearch.setNombresApellidos("");
		loadCreadores();
		this.stateList();
	}

	public void cleanByNumeroDocumento() {
		this.creadorSearch.setNumeroDocumento("");
		loadCreadores();
		this.stateList();
	}

	public CreadorService getCreadorService() {
		return creadorService;
	}

	public void saveCreador() {
		boolean uniqueNumeroDocumento = true;
		if (this.action == Action.NEW || this.action == Action.EDIT) {
			try {
				// Para verificar que el número de documento es único
				Optional<Creador> optional = creadorService.findByNumeroDocumento(creador.getNumeroDocumento());
				if (optional.isPresent()) {
					if (!optional.get().getId().equals(creador.getId())) {
						uniqueNumeroDocumento = false;
					}
				}
				if (uniqueNumeroDocumento == true) {
					if (this.action == Action.NEW)
						creadorService.save(this.creador);
					else
						creadorService.update(this.creador);
					cleanForm();
					loadCreadores();
					this.action = Action.NONE;
					this.stateList();
				} else {
					this.addMessage("El número de documento: " + creador.getNumeroDocumento() + " ya existe");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	public void cancelCreador() {
		cleanForm();
		this.stateList();
	}

	public void selectCreador(SelectEvent<Creador> e) {
		this.creadorSelected = e.getObject();
		this.messageConfirmDialog = this.creadorSelected.getNombresApellidos();
		this.stateSelectRow();
	}

	public void unselectCreador(UnselectEvent<Creador> e) {
		this.creadorSelected = null;
		this.stateList();
	}

	public void cancelCliente() {
		cleanForm();
		this.stateList();
	}

	public List<Creador> getCreadores() {
		return creadores;
	}

	public void stateNewEdit() {
		this.stylePanelCreador = "display:block;";
		this.styleTableCreador = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateList() {
		this.stylePanelCreador = "display:none;";
		this.styleTableCreador = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}

	public void stateSelectRow() {
		this.stylePanelCreador = "display:none;";
		this.styleTableCreador = "display:block;";
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Creador getCreador() {
		return creador;
	}

	public Creador getCreadorSelected() {
		return creadorSelected;
	}

	public Creador getCreadorSearch() {
		return creadorSearch;
	}

	public Action getAction() {
		return action;
	}

	public String getStylePanelCreador() {
		return stylePanelCreador;
	}

	public String getStyleTableCreador() {
		return styleTableCreador;
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

	public String getMessageConfirmDialog() {
		return messageConfirmDialog;
	}

}
