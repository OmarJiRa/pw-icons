package pe.edu.upc.icons.models.repositories;

import java.util.List;

import pe.edu.upc.icons.models.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByNombre(String nombre) throws Exception;
}
