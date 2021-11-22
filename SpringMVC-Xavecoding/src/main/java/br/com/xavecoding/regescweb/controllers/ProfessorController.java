package br.com.xavecoding.regescweb.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.xavecoding.regescweb.dto.RequisicaoFormProfessor;
import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repository.ProfessorRepository;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

	private ProfessorRepository professorRepository;
	
	public ProfessorController(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	@GetMapping
	public ModelAndView index() {
		
		List<Professor> professores = this.professorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("professores", professores);
		return mv;
	}
	
	@GetMapping("/new")
	public ModelAndView newProfessor(RequisicaoFormProfessor requisicao) {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("statusProfessor", StatusProfessor.values());
		return mv;
	}
	
	//create(Professor professor) = Web Parameter Tempering
	//https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/ - 2.3.1 Jakarta Bean Validation Constraints
	@PostMapping
	public ModelAndView create(@Valid RequisicaoFormProfessor novoProfessor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/new");
			mv.addObject("statusProfessor", StatusProfessor.values());
			return mv;
		} else {
			Professor professor = novoProfessor.toProfessor();
			this.professorRepository.save(professor);
			return new ModelAndView("redirect:/professores/" + professor.getId());
		}
	}
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			
			ModelAndView mv = new ModelAndView("professores/show");
			mv.addObject("professor", professor);
			
			return mv;
		} else {
			return this.buildErrorModelAndView("SHOW ERROR: ", id);
		}
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			requisicao.fromProfessor(professor);
		
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("idprofessor", professor.getId());
			mv.addObject("statusProfessor", StatusProfessor.values());
			return mv;
		} else {
			return this.buildErrorModelAndView("EDIT ERROR: ", id);
		}
	}
	
	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("idprofessor", id);
			mv.addObject("statusProfessor", StatusProfessor.values());
			return mv;
		} else {
		
			Optional<Professor> optional = this.professorRepository.findById(id);

			if(optional.isPresent()) {
				Professor professor = requisicao.toProfessor(optional.get());
				this.professorRepository.save(professor);

				return new ModelAndView("redirect:/professores/" + professor.getId());
			} else {
				return this.buildErrorModelAndView("UPDATE ERROR: ", id);
			}
		}
	}
	
	//Tentar implementar modal perguntando se o usuário tem certeza se deseja excluir o registro;
	//Tentar implementar paginação na lista de professores
	@GetMapping("/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/professores");
		
		try {
			this.professorRepository.deleteById(id);
			mv.addObject("mensagem", "Professor #" + id + " deletado com sucesso");
			mv.addObject("erro", "false");
		} catch (EmptyResultDataAccessException e) {
			mv = this.buildErrorModelAndView("DELETE ERROR: ", id);
		}
		return mv;
	}
	
	private ModelAndView buildErrorModelAndView(String msg, Long id) {
		ModelAndView mv = new ModelAndView("redirect:/professores");
		mv.addObject("mensagem", msg + "Professor #" + id + " não encontrado");
		mv.addObject("erro", "true");
		return mv;
	}
	
}
