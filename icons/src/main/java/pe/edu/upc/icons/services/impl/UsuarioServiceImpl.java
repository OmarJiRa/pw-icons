package pe.edu.upc.icons.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.icons.models.entities.Usuario;
import pe.edu.upc.icons.models.repositories.UsuarioRepository;
import pe.edu.upc.icons.services.UsuarioService;

@Named
@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario entity) throws Exception {
		return usuarioRepository.save(entity);
	}

	@Override
	public Usuario update(Usuario entity) throws Exception {
		return usuarioRepository.update(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		usuarioRepository.deleteById(id);

	}

	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		return usuarioRepository.findById(id);
	}
	
	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	@Override
	public List<Usuario> findByNombresApellidos(String nombresApellidos) throws Exception {
		return usuarioRepository.findByNombresApellidos(nombresApellidos);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) throws Exception {
		return usuarioRepository.findByEmail(email);
	}
}
