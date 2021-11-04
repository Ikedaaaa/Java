package br.com.xavecoding.regesc.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Disciplinas")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="iddisciplina")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private Integer semestre;
	
	//Tipo de fetch padrão: fetch = FetchType.EAGER
	//Pq por padrão em um relacionamento Muitos para Um,
	//é leve retornar um objeto a mais do banco
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor_id", nullable = true)
	private Professor professor;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Disciplinas_Alunos", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	List<Aluno> alunos;

	@Deprecated
	public Disciplina() {
		super();
	}

	public Disciplina(String nome, Integer semestre, Professor professor) {
		super();
		this.nome = nome;
		this.semestre = semestre;
		this.professor = professor;
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

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", semestre=" + semestre + ", professor=" + professor
				+ ", alunos=" + alunos + "]";
	}
	
}
