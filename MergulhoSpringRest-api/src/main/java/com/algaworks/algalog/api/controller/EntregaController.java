package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;

	public EntregaController(EntregaRepository entregaRepository, SolicitacaoEntregaService solicitacaoEntregaService) {
		super();
		this.solicitacaoEntregaService = solicitacaoEntregaService;
		this.entregaRepository = entregaRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		if ((entrega.getDestinatario().getComplemento() == null) || (entrega.getDestinatario().getComplemento() == "")) {
			entrega.getDestinatario().setComplemento("Não informado");
		}
		return solicitacaoEntregaService.solicitar(entrega); //complemento na base está como not null
	}
	
	@GetMapping
	public List<Entrega> listar() {
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{identrega}")
	public ResponseEntity<Entrega> buscar(@PathVariable Long identrega) {
		return entregaRepository.findById(identrega)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}