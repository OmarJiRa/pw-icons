package pe.edu.upc.icons.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.icons.models.entities.Comunidad;
import pe.edu.upc.icons.models.repositories.ComunidadRepository;
import pe.edu.upc.icons.services.ComunidadService;

@Named
@ApplicationScoped
public class ComunidadServiceImpl implements ComunidadService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ComunidadRepository comunidadRepository;

	@Transactional
	@Override
	public Comunidad save(Comunidad entity) throws Exception {
		return comunidadRepository.save(entity);
	}

	@Transactional
	@Override
	public Comunidad update(Comunidad entity) throws Exception {
		return comunidadRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		comunidadRepository.deleteById(id);
	}

	@Override
	public Optional<Comunidad> findById(Integer id) throws Exception {
		return comunidadRepository.findById(id);
	}

	@Override
	public List<Comunidad> findAll() throws Exception {
		return comunidadRepository.findAll();
	}

	@Override
	public List<Comunidad> findByNombre(String nombre) throws Exception {
		return comunidadRepository.findByNombre(nombre);
	}

}
