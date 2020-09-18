package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.CreadorUsuario;
import pe.edu.upc.icons.models.repositories.CreadorUsuarioRepository;

public class CreadorUsuarioRepositoryImpl implements CreadorUsuarioRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public CreadorUsuario save(CreadorUsuario entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public CreadorUsuario update(CreadorUsuario entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<CreadorUsuario> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<CreadorUsuario> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<CreadorUsuario> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM CreadorUsuario c WHERE c.id = ?1";
		// Crear la consulta
		TypedQuery<CreadorUsuario> query = em.createQuery(qlString, CreadorUsuario.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		CreadorUsuario creadorUsuario = query.getSingleResult();
		// Verificar la existencia del objeto
		if (creadorUsuario != null) {

			optional = Optional.of(creadorUsuario);
		}
		return optional;
	}

	@Override
	public List<CreadorUsuario> findAll() throws Exception {
		// Declarar variable a retornar
		List<CreadorUsuario> creadorUsuarios = new ArrayList<CreadorUsuario>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM CreadorUsuario c";
		// Crear la consulta
		TypedQuery<CreadorUsuario> query = em.createQuery(qlString, CreadorUsuario.class);
		// Obtener resultado de consulta
		creadorUsuarios = query.getResultList();
		return creadorUsuarios;
	}

}
