package com.APIPitang.resources;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.APIPitang.domain.Telefone;
import com.APIPitang.domain.Usuario;
import com.APIPitang.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class Resources {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity <List<Usuario>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Validated @RequestBody Usuario usuario) {
		usuario = usuarioService.salvar(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> Buscar(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar (@PathVariable("id")Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Validated @RequestBody Usuario usuario ,
			@PathVariable("id") Long id) {
		usuario.setId(id);
		usuarioService.atualizar(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/Telefone" , method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarTelefone(@Validated @PathVariable("id") Long usuarioId,
			@RequestBody Telefone telefone) {
		
		usuarioService.salvarTelefone(usuarioId, telefone);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value = "/{id}/Telefone" , method = RequestMethod.GET)
	public ResponseEntity<List<Telefone>> listarTelefone(@PathVariable("id") Long usuarioIdLong){
		List<Telefone> telefone = usuarioService.listarTelefone(usuarioIdLong);
		
		return ResponseEntity.status(HttpStatus.OK).body(telefone);
	}

}
