package br.com.mercadolegal.compra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolegal.compra.entidade.Usuario;
import br.com.mercadolegal.compra.pojo.UsuarioPojo;
import br.com.mercadolegal.compra.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping
	public List<UsuarioPojo> getAll() {
		List<Usuario> listUsuario = usuarioRepository.findAll();
		List<UsuarioPojo> listUsuarioPojo = new ArrayList<>();
		for (Usuario usuario : listUsuario) {
			listUsuarioPojo.add(new UsuarioPojo(usuario));
		}
		return listUsuarioPojo;
	}

	@GetMapping(path = "/{id}")
	public UsuarioPojo get(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			return new UsuarioPojo(usuarioOptional.get());
		}
		return new UsuarioPojo();
	}

	@PostMapping
	public UsuarioPojo create(@RequestBody UsuarioPojo usuarioPojo) {

		return new UsuarioPojo(usuarioRepository.save(usuarioPojo.toEntity()));

	}

	@PutMapping
	public UsuarioPojo update(@RequestBody UsuarioPojo usuarioPojo) {

		return new UsuarioPojo(usuarioRepository.save(usuarioPojo.toEntity()));

	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		usuarioRepository.deleteById(id);

	}

}
