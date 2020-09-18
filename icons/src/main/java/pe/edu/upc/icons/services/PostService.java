package pe.edu.upc.icons.services;

import java.util.List;

import pe.edu.upc.icons.models.entities.Post;

public interface PostService extends CrudService<Post, Integer> {
	List<Post> findByNombre(String nombre) throws Exception;
}
