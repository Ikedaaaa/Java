package com.algaworks.algalog.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;

	public EntregaController(SolicitacaoEntregaService solicitacaoEntregaService) {
		super();
		this.solicitacaoEntregaService = solicitacaoEntregaService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@RequestBody Entrega entrega) {
		if (entrega.getDestinatario().getComplemento() == null) {
			entrega.getDestinatario().setComplemento("Não informado");
		}
		return solicitacaoEntregaService.solicitar(entrega); //complemento na base está como not null
	}
}
