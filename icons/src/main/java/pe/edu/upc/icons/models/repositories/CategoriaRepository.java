package pe.edu.upc.icons.models.repositories;

import java.util.List;

import pe.edu.upc.icons.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	List<Categoria> findByNombre(String nombre) throws Exception;
}