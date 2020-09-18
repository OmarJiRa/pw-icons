package pe.edu.upc.icons.services;

import java.util.List;

import pe.edu.upc.icons.models.entities.Categoria;

public interface CategoriaService extends CrudService<Categoria, Integer> {
	List<Categoria> findByNombre(String nombre) throws Exception;
}
