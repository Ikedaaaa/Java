package br.com.xavecoding.regescweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.xavecoding.regescweb.dto.RequisicaoNovoProfessor;
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
	
	@GetMapping("/professores/new")
	public ModelAndView newProfessor(RequisicaoNovoProfessor requisicao) {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("statusProfessor", StatusProfessor.values());
		return mv;
	}
	
	//create(Professor professor) = Web Parameter Tempering
	//https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/ - 2.3.1 Jakarta Bean Validation Constraints
	@PostMapping("professores")
	public ModelAndView create(@Valid RequisicaoNovoProfessor novoProfessor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/new");
			mv.addObject("statusProfessor", StatusProfessor.values());
			return mv;
		} else {
			Professor professor = novoProfessor.toProfessor();
			this.professorRepository.save(professor);
			return new ModelAndView("redirect:/professores");
		}
	}
	
}
