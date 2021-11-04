package br.com.xavecoding.regesc.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
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
	//
	//Cascade: cascade = CascadeType.ALL
	//uma ação que ocorrer com um Professor, se propaga para as tabelas relacionadas
	//Ex: Se um professor for apagado, as disciplinas relacionadas
	//a esse professor também são apagadas
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
	
	//Um jeito de fazer ON REMOVE SET NULL
	//Outra alternativa seria criar um Professor padrão no banco do Id 1
	//Que nunca seria removido e seria atribuído esse Id quando
	//Um professor fosse excluído
	//Outra alternativa seria reescrever o método deleteById() do repositório
	//Para que ela tivesse um comportamento diferente
	@PreRemove
	public void atualizaDisciplinaOnRemove() {
		System.out.println("******* atualizaDisciplinaOnRemove *******");
		for (Disciplina listDisciplinas : this.getDisciplinas()) {
			listDisciplinas.setProfessor(null);
		}
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
	}
	
}
