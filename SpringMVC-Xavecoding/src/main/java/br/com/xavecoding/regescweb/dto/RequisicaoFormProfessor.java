package br.com.xavecoding.regescweb.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;

// É uma classe DTO
public class RequisicaoFormProfessor {
	
	@NotNull
	@NotBlank
	private String nome; // Em caso de erro, NotBlank.requisicaoNovoProfessor.nome
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal salario;
	private StatusProfessor status; //pesquisar sobre validation de enum

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public StatusProfessor getStatus() {
		return status;
	}
	public void setStatus(StatusProfessor status) {
		this.status = status;
	}
	
	public Professor toProfessor() {
		Professor professor = new Professor();
		professor.setNome(this.nome);
		professor.setSalario(this.salario);
		professor.setStatus(this.status);
		
		return professor;
	}
	
	public void fromProfessor(Professor professor) {
		this.nome = professor.getNome();
		this.salario = professor.getSalario();
		this.status = professor.getStatus();
	}
	
}
