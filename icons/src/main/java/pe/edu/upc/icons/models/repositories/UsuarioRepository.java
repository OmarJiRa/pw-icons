package pe.edu.upc.icons.models.repositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.icons.models.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByNombresApellidos(String nombresApellidos) throws Exception;
	Optional<Usuario> findByEmail(String email) throws Exception;
}
