package pe.edu.upc.icons.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.icons.models.entities.Categoria;
import pe.edu.upc.icons.models.repositories.CategoriaRepository;

public class CategoriaRepositoryImpl implements CategoriaRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "IconsPU")
	private EntityManager em;

	@Override
	public Categoria save(Categoria entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Categoria update(Categoria entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// Declarar y asignar la variable
		Optional<Categoria> optional = findById(id);
		// Verifica si optional contiene algo
		if (optional.isPresent()) {
			// Remuevo el objeto
			em.remove(optional.get());
		}
	}

	@Override
	public Optional<Categoria> findById(Integer id) throws Exception {
		// Declarar la variable a retornar
		Optional<Categoria> optional = Optional.empty();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Categoria c WHERE c.id = ?1";
		// Crear la consulta
		TypedQuery<Categoria> query = em.createQuery(qlString, Categoria.class);
		// Estableciendo los parametros: id
		query.setParameter(1, id);
		// Obtener el resultado del query
		Categoria categoria = query.getSingleResult();
		// Verificar la existencia del objeto
		if (categoria != null) {

			optional = Optional.of(categoria);
		}
		return optional;
	}

	@Override
	public List<Categoria> findAll() throws Exception {
		// Declarar variable a retornar
		List<Categoria> categorias = new ArrayList<Categoria>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Categoria c";
		// Crear la consulta
		TypedQuery<Categoria> query = em.createQuery(qlString, Categoria.class);
		// Obtener resultado de consulta
		categorias = query.getResultList();
		return categorias;
	}

	@Override
	public List<Categoria> findByNombre(String nombre) throws Exception {
		// Declarar variable a retornar
		List<Categoria> categorias = new ArrayList<Categoria>();
		// Elaborar el JPQL
		String qlString = "SELECT c FROM Categoria c WHERE c.nombre LIKE '%?1%'";
		TypedQuery<Categoria> query = em.createQuery(qlString, Categoria.class);
		query.setParameter(1, nombre);
		categorias = query.getResultList();
		return categorias;
	}

}