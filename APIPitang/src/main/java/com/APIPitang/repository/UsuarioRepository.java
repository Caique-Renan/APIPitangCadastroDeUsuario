package com.APIPitang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.APIPitang.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
