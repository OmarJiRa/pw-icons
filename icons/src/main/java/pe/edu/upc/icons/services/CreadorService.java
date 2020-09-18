package pe.edu.upc.icons.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.icons.models.entities.Creador;

public interface CreadorService extends CrudService<Creador, Integer> {
	List<Creador> findByNombresApellidos(String nombresApellidos) throws Exception;
	Optional<Creador> findByEmail(String email) throws Exception;
}
