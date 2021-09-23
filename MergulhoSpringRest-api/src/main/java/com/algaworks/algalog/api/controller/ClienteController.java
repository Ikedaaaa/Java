package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Matheus Ikeda");
		cliente1.setTelefone("11 4536-3847");
		cliente1.setEmail("abc@gmail.com");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Matheus Marques");
		cliente2.setTelefone("95 4256-5467");
		cliente2.setEmail("123@gmail.com");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
