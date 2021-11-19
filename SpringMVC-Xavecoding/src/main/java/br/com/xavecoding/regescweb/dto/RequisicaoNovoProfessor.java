package br.com.xavecoding.regescweb.dto;

import java.math.BigDecimal;

import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;

// É uma classe DTO
public class RequisicaoNovoProfessor {
	
	private String nome;
	private BigDecimal salario;
	private StatusProfessor status;

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
	
}
