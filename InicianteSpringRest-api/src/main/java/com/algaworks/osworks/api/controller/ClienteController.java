package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/pesquisanome")
	public List<Cliente> listarPorNome(@RequestParam String nome) {
		return clienteRepository.findByNome(nome);
	}
	
	@GetMapping("/pesquisacontaining")
	public List<Cliente> listarPorNomeContaining(@RequestParam String nomelike) {
		return clienteRepository.findByNomeContaining(nomelike);
	}
	
	@GetMapping("/{idcliente}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long idcliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idcliente);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}
	
	@PutMapping("/{idcliente}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long idcliente, @RequestBody Cliente cliente){
		if (!clienteRepository.existsById(idcliente)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(idcliente);
		cliente = cadastroCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{idcliente}")
	public ResponseEntity<Void> remover(@PathVariable Long idcliente){
		if (!clienteRepository.existsById(idcliente)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroCliente.excluir(idcliente);
		
		return ResponseEntity.noContent().build();
	}
}
