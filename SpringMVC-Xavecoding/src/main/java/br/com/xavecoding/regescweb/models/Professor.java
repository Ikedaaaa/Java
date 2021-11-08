package br.com.xavecoding.regescweb.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Professores")
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idprofessor")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private StatusProfessor status;
	
	public Professor() {
		super();
	}

	public Professor(String nome, BigDecimal salario, StatusProfessor status) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
