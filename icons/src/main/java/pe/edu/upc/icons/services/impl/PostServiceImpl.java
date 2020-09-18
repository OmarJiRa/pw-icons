package pe.edu.upc.icons.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import pe.edu.upc.icons.models.entities.Post;
import pe.edu.upc.icons.models.repositories.PostRepository;
import pe.edu.upc.icons.services.PostService;

public class PostServiceImpl implements PostService, Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private PostRepository postRepository;

	@Override
	public Post save(Post entity) throws Exception {
		return postRepository.save(entity);
	}

	@Override
	public Post update(Post entity) throws Exception {
		return postRepository.update(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		postRepository.deleteById(id);

	}

	@Override
	public Optional<Post> findById(Integer id) throws Exception {
		return postRepository.findById(id);
	}

	@Override
	public List<Post> findAll() throws Exception {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findByNombre(String nombre) throws Exception {
		return postRepository.findByNombre(nombre);
	}

}
