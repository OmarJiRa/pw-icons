package pe.edu.upc.icons.models.repositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.icons.models.entities.Creador;

public interface CreadorRepository extends JpaRepository<Creador, Integer> {
	List<Creador> findByNombresApellidos(String nombresApellidos) throws Exception;
	Optional<Creador> findByEmail(String email) throws Exception;
}
