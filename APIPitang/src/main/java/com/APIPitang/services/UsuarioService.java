package com.APIPitang.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.APIPitang.domain.Telefone;
import com.APIPitang.domain.Usuario;
import com.APIPitang.repository.TelefoneRepository;
import com.APIPitang.repository.UsuarioRepository;
import com.APIPitang.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> buscar(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("O usuário não pode ser encontrado");
		}
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario) {
		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}
	
	public void deletar(Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException("O usuário não pode ser encontrado");
		}
	}
	
	public void atualizar(Usuario usuario) {
		verificarExistencia(usuario);
		usuarioRepository.save(usuario);
	}
	
	private void verificarExistencia(Usuario usuario) {
		buscar(usuario.getId());
	}
	
	public Telefone salvarTelefone(Long UsuarioId, Telefone telefone) {
		Optional<Usuario> usuario = buscar(UsuarioId);
		
		telefone.setUsuario(usuario);
		
		return telefoneRepository.save(telefone);
	}
	public List<Telefone> listarTelefone(Long UsuarioId){
		return telefoneRepository.findAll();
	}

}
