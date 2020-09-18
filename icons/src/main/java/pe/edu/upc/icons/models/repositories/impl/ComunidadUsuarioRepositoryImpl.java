package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.ComunidadUsuario;
import pe.edu.upc.icons.models.repositories.ComunidadUsuarioRepository;

public class ComunidadUsuarioRepositoryImpl implements ComunidadUsuarioRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public ComunidadUsuario save(ComunidadUsuario entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public ComunidadUsuario update(ComunidadUsuario entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<ComunidadUsuario> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}

	}

	@Override
	public Optional<ComunidadUsuario> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<ComunidadUsuario> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT cu FROM ComunidadUsuario cu WHERE cu.id = ?1";
		// Crear la consulta
		TypedQuery<ComunidadUsuario> query = em.createQuery(qlString, ComunidadUsuario.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		ComunidadUsuario comunidadUsuario = query.getSingleResult();
		// Verificar la existencia del objeto
		if (comunidadUsuario != null) {

			optional = Optional.of(comunidadUsuario);
		}
		return optional;
	}

	@Override
	public List<ComunidadUsuario> findAll() throws Exception {
		// Declarar variable a retornar
		List<ComunidadUsuario> comunidadUsuarios = new ArrayList<ComunidadUsuario>();
		// Elaborar el JPQL
		String qlString = "SELECT cu FROM ComunidadUsuario cu";
		// Crear la consulta
		TypedQuery<ComunidadUsuario> query = em.createQuery(qlString, ComunidadUsuario.class);
		// Obtener resultado de consulta
		comunidadUsuarios = query.getResultList();
		return comunidadUsuarios;
	}

}
