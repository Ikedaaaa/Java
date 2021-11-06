package br.com.xavecoding.regescweb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String hello(String arquivo) {
		arquivo = "hello";
		return arquivo; //Spring vai renderizar o arquivo templates/hello.html
	}
	
	@GetMapping("/hello-servlet")
	public String hello(HttpServletRequest request) {
		request.setAttribute("nome", "Berlin");
		return "hello";
	}
	
	@GetMapping("/hello-model")
	public String hello(Model model) {
		model.addAttribute("nome", "Rio");
		return "hello";
	}
	
	@GetMapping("/hello-mv")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello"); // nome do arquivo html a ser renderizado/exibido
		mv.addObject("nome", "Moscow");
		return mv;
	}
	
}
