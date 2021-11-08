package com.APIPitang.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Usuario {
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@NonNull
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	@NonNull
	@JsonInclude(Include.NON_NULL)
	private String senha;
	
	@NonNull
	@JsonInclude(Include.NON_NULL)
	@OneToMany(mappedBy = "usuario")
	private List<Telefone> Telefones;
	
	public Usuario() {}
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Telefone> getTelefones() {
		return Telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		Telefones = telefones;
	}
	

}
