package com.algaworks.osworks.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@RestController
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/pesquisanome")
	public List<Cliente> listarPorNome(@RequestParam String nome) {
		return clienteRepository.findByNome(nome);
	}
	
	@GetMapping("/clientes/pesquisacontaining")
	public List<Cliente> listarPorNomeContaining(@RequestParam String nomelike) {
		return clienteRepository.findByNomeContaining(nomelike);
	}
}
