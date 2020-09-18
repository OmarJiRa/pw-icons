package pe.edu.upc.icons.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import pe.edu.upc.icons.models.entities.Creador;
import pe.edu.upc.icons.models.repositories.CreadorRepository;
import pe.edu.upc.icons.services.CreadorService;

public class CreadorServiceImpl implements CreadorService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private CreadorRepository creadorRepository;

	@Transactional
	@Override
	public Creador save(Creador entity) throws Exception {
		return creadorRepository.save(entity);
	}

	@Transactional
	@Override
	public Creador update(Creador entity) throws Exception {
		return creadorRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		creadorRepository.deleteById(id);
	}

	@Override
	public Optional<Creador> findById(Integer id) throws Exception {
		return creadorRepository.findById(id);
	}

	@Override
	public List<Creador> findAll() throws Exception {
		return creadorRepository.findAll();
	}

	@Override
	public List<Creador> findByNombresApellidos(String nombresApellidos) throws Exception {
		return creadorRepository.findByNombresApellidos(nombresApellidos);
	}

	@Override
	public Optional<Creador> findByEmail(String email) throws Exception {
		return creadorRepository.findByEmail(email);
	}

}
