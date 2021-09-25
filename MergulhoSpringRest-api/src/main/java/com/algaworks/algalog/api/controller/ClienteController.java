package com.algaworks.algalog.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	private ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		//return clienteRepository.findByNome("Matheus Ikeda");
		//return clienteRepository.findByNomeContaining("Matheus");
		return clienteRepository.findAll();
	}
}
