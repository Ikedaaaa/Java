package br.com.xavecoding.regescweb.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;

@Controller
public class ProfessorController {

	@GetMapping("/professores")
	public ModelAndView index() {
		
		Professor depressao = new Professor("Jão Depressão", new BigDecimal(5000.0), StatusProfessor.ATIVO);
		depressao.setId(1L);
		
		Professor sequela = new Professor("Jão Sequela", new BigDecimal(10000.0), StatusProfessor.APOSENTADO);
		sequela.setId(2L);
		
		Professor marquinhos = new Professor("Marquinhos", new BigDecimal(15000.0), StatusProfessor.INATIVO);
		marquinhos.setId(3L);
		
		List<Professor> professores = Arrays.asList(depressao, sequela, marquinhos);
		
		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("professores", professores);
		return mv;
	}
	
}
