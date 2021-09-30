package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

@Service
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService buscaEntregaService;
	
	public RegistroOcorrenciaService(BuscaEntregaService buscaEntregaService) {
		super();
		this.buscaEntregaService = buscaEntregaService;
	}

	@Transactional
	public Ocorrencia registrar(Long identrega, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(identrega);
		
		return entrega.adicionarOcorrencia(descricao);
	}

}
