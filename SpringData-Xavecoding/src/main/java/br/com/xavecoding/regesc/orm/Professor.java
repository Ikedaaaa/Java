package br.com.xavecoding.regesc.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(nullable = false, unique = true)
	private String prontuario;
	
	//Tipo de fetch padrão: fetch = FetchType.LAZY
	//Pq por padrão em um relacionamento Um para Muitos,
	//o sistema poderia ser sobrecarregdo se esse objeto estivesse associado
	//a vários outros.
	//Como nesse exemplo,
	//se um professor estivesse associado a milhares de disciplinas
	//Poderia ficar muito pesado ter que retornar tantos objetos assim
	//para cada getDisciplinas que fosse feito
	@OneToMany(mappedBy = "professor"/*, fetch = FetchType.EAGER*/)
	private List<Disciplina> disciplinas; 
	
	@Deprecated
	public Professor() {
		super();
	}

	public Professor(String nome, String prontuario) {
		super();
		this.nome = nome;
		this.prontuario = prontuario;
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

	public String getProntuario() {
		return prontuario;
	}
	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
	}
	
}
