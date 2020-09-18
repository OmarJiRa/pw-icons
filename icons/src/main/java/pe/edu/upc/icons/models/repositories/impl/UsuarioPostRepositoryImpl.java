package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.UsuarioPost;
import pe.edu.upc.icons.models.repositories.UsuarioPostRepository;

public class UsuarioPostRepositoryImpl implements UsuarioPostRepository, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public UsuarioPost save(UsuarioPost entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public UsuarioPost update(UsuarioPost entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<UsuarioPost> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<UsuarioPost> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<UsuarioPost> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT up FROM UsuarioPost up WHERE up.id = ?1";
		// Crear la consulta
		TypedQuery<UsuarioPost> query = em.createQuery(qlString, UsuarioPost.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		UsuarioPost usuarioPost = query.getSingleResult();
		// Verificar la existencia del objeto
		if (usuarioPost != null) {

			optional = Optional.of(usuarioPost);
		}
		return optional;
	}

	@Override
	public List<UsuarioPost> findAll() throws Exception {
		// Declarar variable a retornar
		List<UsuarioPost> usuarioPosts = new ArrayList<UsuarioPost>();
		// Elaborar el JPQL
		String qlString = "SELECT up FROM UsuarioPost up";
		// Crear la consulta
		TypedQuery<UsuarioPost> query = em.createQuery(qlString, UsuarioPost.class);
		// Obtener resultado de consulta
		usuarioPosts = query.getResultList();
		return usuarioPosts;
	}

}
