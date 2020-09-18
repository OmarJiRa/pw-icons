package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.Usuario;
import pe.edu.upc.icons.models.repositories.UsuarioRepository;

@Named
@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public Usuario save(Usuario entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Usuario update(Usuario entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<Usuario> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<Usuario> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT u FROM Usuario u WHERE u.id = ?1";
		// Crear la consulta
		TypedQuery<Usuario> query = em.createQuery(qlString, Usuario.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		Usuario usuario = query.getSingleResult();
		// Verificar la existencia del objeto
		if (usuario != null) {

			optional = Optional.of(usuario);
		}
		return optional;
	}

	@Override
	public List<Usuario> findAll() throws Exception {
		// Declarar variable a retornar
		List<Usuario> usuarioes = new ArrayList<Usuario>();
		// Elaborar el JPQL
		String qlString = "SELECT u FROM Usuario u";
		// Crear la consulta
		TypedQuery<Usuario> query = em.createQuery(qlString, Usuario.class);
		// Obtener resultado de consulta
		usuarioes = query.getResultList();
		return usuarioes;
	}

	@Override
	public List<Usuario> findByNombresApellidos(String nombresApellidos) throws Exception {
		// Declarar variable a retornar
		List<Usuario> usuarioes = new ArrayList<Usuario>();
		// Elaborar el JPQL
		String qlString = "SELECT u FROM Usuario u WHERE u.nombresApellidos LIKE '%?1%'";
		TypedQuery<Usuario> query = em.createQuery(qlString, Usuario.class);
		query.setParameter(1, nombresApellidos);
		usuarioes = query.getResultList();
		return usuarioes;
	}

	@Override
	public Optional<Usuario> findByEmail(String email) throws Exception {
		// Declarar variable a retornar
		Optional<Usuario> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT u FROM Usuario u WHERE u.email = ?1";
		TypedQuery<Usuario> query = em.createQuery(qlString, Usuario.class);
		query.setParameter(1, email);
		Usuario usuario = query.getSingleResult();
		// Verificar la existencia del objeto
		if (usuario != null) {

			optional = Optional.of(usuario);
		}
		return optional;
	}

}
