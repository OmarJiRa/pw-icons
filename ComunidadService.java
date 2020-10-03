package pe.edu.upc.icons.services;

import java.util.List;

import pe.edu.upc.icons.models.entities.Comunidad;

public interface ComunidadService extends CrudService<Comunidad, Integer> {
	List<Comunidad> findByNombre(String nombre) throws Exception;
}
