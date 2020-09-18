package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.Post;
import pe.edu.upc.icons.models.repositories.PostRepository;

public class PostRepositoryImpl implements PostRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public Post save(Post entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Post update(Post entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Post> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Post> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<Post> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT p FROM Post p WHERE p.id = ?1";
		// Crear la consulta
		TypedQuery<Post> query = em.createQuery(qlString, Post.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		Post post = query.getSingleResult();
		// Verificar la existencia del objeto
		if (post != null) {

			optional = Optional.of(post);
		}
		return optional;
	}

	@Override
	public List<Post> findAll() throws Exception {
		// Declarar variable a retornar
				List<Post> posts = new ArrayList<Post>();
				// Elaborar el JPQL
				String qlString = "SELECT p FROM Post p";
				// Crear la consulta
				TypedQuery<Post> query = em.createQuery(qlString, Post.class);
				// Obtener resultado de consulta
				posts = query.getResultList();
				return posts;
	}

	@Override
	public List<Post> findByNombre(String nombre) throws Exception {
		// Declarar variable a retornar
				List<Post> posts = new ArrayList<Post>();
				// Elaborar el JPQL
				String qlString = "SELECT p FROM Post p WHERE p.nombre LIKE '%?1%'";
				TypedQuery<Post> query = em.createQuery(qlString, Post.class);
				query.setParameter(1, nombre);
				posts = query.getResultList();
				return posts;
	}

}
