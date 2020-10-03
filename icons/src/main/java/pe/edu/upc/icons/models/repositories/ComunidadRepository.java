package pe.edu.upc.icons.models.repositories;

import java.util.List;

import pe.edu.upc.icons.models.entities.Comunidad;

public interface ComunidadRepository extends JpaRepository<Comunidad, Integer> {
	List<Comunidad> findByNombre(String nombre) throws Exception;
}