package br.com.xavecoding.regesc.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Alunos")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idaluno")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private Integer idade;
	
	@ManyToMany(mappedBy = "alunos")
	List<Disciplina> disciplinas;
	
	public Aluno() {}
	
	public Aluno(String nome, Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public Aluno(Long id, String nome, Integer idade, List<Disciplina> disciplinas) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.disciplinas = disciplinas;
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

	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
	}
	
}
