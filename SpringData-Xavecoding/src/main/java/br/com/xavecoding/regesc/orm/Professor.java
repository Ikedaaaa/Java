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
	
	@OneToMany(mappedBy = "professor")
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

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
	}
	
}
