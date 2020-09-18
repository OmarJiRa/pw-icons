package pe.edu.upc.icons.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.icons.models.entities.Usuario;

public interface UsuarioService extends CrudService<Usuario, Integer> {
	List<Usuario> findByNombresApellidos(String nombresApellidos) throws Exception;
	Optional<Usuario> findByEmail(String email) throws Exception;
}
