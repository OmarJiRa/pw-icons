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

import pe.edu.upc.icons.models.entities.Comunidad;
import pe.edu.upc.icons.models.repositories.ComunidadRepository;

@Named
@ApplicationScoped
public class ComunidadRepositoryImpl implements ComunidadRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public Comunidad save(Comunidad entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Comunidad update(Comunidad entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<Comunidad> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}

	}

	@Override
	public Optional<Comunidad> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<Comunidad> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Comunidad c WHERE c.id = ?1";
		// Crear la consulta
		TypedQuery<Comunidad> query = em.createQuery(qlString, Comunidad.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		Comunidad comunidad = query.getSingleResult();
		// Verificar la existencia del objeto
		if (comunidad != null) {

			optional = Optional.of(comunidad);
		}
		return optional;
	}

	@Override
	public List<Comunidad> findAll() throws Exception {
		// Declarar variable a retornar
		List<Comunidad> comunidades = new ArrayList<Comunidad>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Comunidad c";
		// Crear la consulta
		TypedQuery<Comunidad> query = em.createQuery(qlString, Comunidad.class);
		// Obtener resultado de consulta
		comunidades = query.getResultList();
		return comunidades;
	}

	@Override
	public List<Comunidad> findByNombre(String nombre) throws Exception {
		// Declarar variable a retornar
		List<Comunidad> comunidades = new ArrayList<Comunidad>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Comunidad c WHERE c.nombre LIKE '?1'";
		TypedQuery<Comunidad> query = em.createQuery(qlString, Comunidad.class);
		//establecer los parametros
		query.setParameter(1, "%"+ nombre.toUpperCase() + "%");
		//obtener el resultado de la consulta
		comunidades = query.getResultList();
		return comunidades;
	}

}
