package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Robertinho");
		cliente1.setEmail("ashdh@gmail.com");
		cliente1.setTelefone("11 93847-9476");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Kyiosaki Junior");
		cliente2.setEmail("lajhfs@gmail.com");
		cliente2.setTelefone("11 98615-0145");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
