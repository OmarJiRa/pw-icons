package pe.edu.upc.icons.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import pe.edu.upc.icons.models.entities.Categoria;
import pe.edu.upc.icons.models.repositories.CategoriaRepository;
import pe.edu.upc.icons.services.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaRepository categoriaRepository;

	@Transactional
	@Override
	public Categoria save(Categoria entity) throws Exception {
		return categoriaRepository.save(entity);
	}

	@Transactional
	@Override
	public Categoria update(Categoria entity) throws Exception {
		return categoriaRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		categoriaRepository.deleteById(id);
	}

	@Override
	public Optional<Categoria> findById(Integer id) throws Exception {
		return categoriaRepository.findById(id);
	}

	@Override
	public List<Categoria> findAll() throws Exception {
		return categoriaRepository.findAll();
	}

	@Override
	public List<Categoria> findByNombre(String nombre) throws Exception {
		return categoriaRepository.findByNombre(nombre);
	}

}
