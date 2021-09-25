package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	@GetMapping
	public List<Cliente> listar() {
		//return clienteRepository.findByNome("Matheus Ikeda");
		//return clienteRepository.findByNomeContaining("Matheus");
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{idcliente}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long idcliente) {
		return clienteRepository.findById(idcliente)
				//.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		/*Optional<Cliente> cliente = clienteRepository.findById(idcliente);//precisa do import
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get()); //precisa alterar retorno da função para Cliente
		}
		
		return ResponseEntity.notFound().build();*/
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{idcliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long idcliente, @Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(idcliente)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(idcliente);
		return ResponseEntity.ok(clienteRepository.save(cliente));
	}
	
	@DeleteMapping("/{idcliente}")
	public ResponseEntity<Void> remover(@PathVariable Long idcliente){
		if(!clienteRepository.existsById(idcliente)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(idcliente);
		return ResponseEntity.noContent().build();
	}
}
