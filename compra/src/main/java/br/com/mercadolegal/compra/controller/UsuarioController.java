package br.com.mercadolegal.compra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping
	@Operation(summary = "Recupera usuarios", description = "Recupera todos os Usuarios cadastrados")
	public ResponseEntity<List<UsuarioPojo>> getAll() {
		List<Usuario> listUsuario = usuarioRepository.findAll();
		List<UsuarioPojo> listUsuarioPojo = new ArrayList<>();
		for (Usuario usuario : listUsuario) {
			listUsuarioPojo.add(new UsuarioPojo(usuario));
		}
		return ResponseEntity.ok(listUsuarioPojo);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UsuarioPojo> get(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			return ResponseEntity.ok(new UsuarioPojo(usuarioOptional.get()));
		}
		return  ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UsuarioPojo> create(@RequestBody UsuarioPojo usuarioPojo) {

		return new ResponseEntity<UsuarioPojo> (new UsuarioPojo(usuarioRepository.save(usuarioPojo.toEntity())), HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<UsuarioPojo> update(@RequestBody UsuarioPojo usuarioPojo) {

		return ResponseEntity.ok(new UsuarioPojo(usuarioRepository.save(usuarioPojo.toEntity())));

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		return  ResponseEntity.ok().build();

	}

}
