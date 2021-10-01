package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	
	public EntregaController(EntregaRepository entregaRepository, SolicitacaoEntregaService solicitacaoEntregaService,
			FinalizacaoEntregaService finalizacaoEntregaService, EntregaAssembler entregaAssembler) {
		super();
		this.entregaRepository = entregaRepository;
		this.solicitacaoEntregaService = solicitacaoEntregaService;
		this.finalizacaoEntregaService = finalizacaoEntregaService;
		this.entregaAssembler = entregaAssembler;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		
		if ((entregaSolicitada.getDestinatario().getComplemento() == null) || (entregaSolicitada.getDestinatario().getComplemento() == "")) {
			entregaSolicitada.getDestinatario().setComplemento("Não informado");
		}
		return entregaAssembler.toModel(solicitacaoEntregaService.solicitar(entregaSolicitada)); //complemento na base está como not null
	}
	
	@PutMapping("/{identrega}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long identrega) {
		finalizacaoEntregaService.finalizar(identrega);
	}
	
	@PutMapping("/{identrega}/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long identrega) {
		finalizacaoEntregaService.cancelar(identrega);
	}
	
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toColletionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{identrega}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long identrega) {
		return entregaRepository.findById(identrega)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}
