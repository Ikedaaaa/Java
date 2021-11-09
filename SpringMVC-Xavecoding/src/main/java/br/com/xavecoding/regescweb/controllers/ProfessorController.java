package br.com.xavecoding.regescweb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repository.ProfessorRepository;

@Controller
public class ProfessorController {

	private ProfessorRepository professorRepository;
	
	public ProfessorController(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	@GetMapping("/professores")
	public ModelAndView index() {
		
		List<Professor> professores = this.professorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("professores", professores);
		return mv;
	}
	
	@GetMapping("/professor/new")
	public ModelAndView newProfessor() {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("statusProfessor", StatusProfessor.values());
		return mv;
	}
	
}
