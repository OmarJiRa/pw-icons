package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.Creador;
import pe.edu.upc.icons.models.repositories.CreadorRepository;

public class CreadorRepositoryImpl implements CreadorRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public Creador save(Creador entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Creador update(Creador entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<Creador> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Creador> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<Creador> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Creador c WHERE c.id = ?1";
		// Crear la consulta
		TypedQuery<Creador> query = em.createQuery(qlString, Creador.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		Creador creador = query.getSingleResult();
		// Verificar la existencia del objeto
		if (creador != null) {

			optional = Optional.of(creador);
		}
		return optional;
	}

	@Override
	public List<Creador> findAll() throws Exception {
		// Declarar variable a retornar
		List<Creador> creadores = new ArrayList<Creador>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Creador c";
		// Crear la consulta
		TypedQuery<Creador> query = em.createQuery(qlString, Creador.class);
		// Obtener resultado de consulta
		creadores = query.getResultList();
		return creadores;
	}

	@Override
	public List<Creador> findByNombresApellidos(String nombresApellidos) throws Exception {
		// Declarar variable a retornar
		List<Creador> creadores = new ArrayList<Creador>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Creador c WHERE c.nombresApellidos LIKE '%?1%'";
		TypedQuery<Creador> query = em.createQuery(qlString, Creador.class);
		query.setParameter(1, nombresApellidos);
		creadores = query.getResultList();
		return creadores;
	}

	@Override
	public Optional<Creador> findByEmail(String email) throws Exception {
		// Declarar variable a retornar
		Optional<Creador> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Creador c WHERE c.email = ?1";
		TypedQuery<Creador> query = em.createQuery(qlString, Creador.class);
		query.setParameter(1, email);
		Creador creador = query.getSingleResult();
		// Verificar la existencia del objeto
		if (creador != null) {

			optional = Optional.of(creador);
		}
		return optional;
	}

}
