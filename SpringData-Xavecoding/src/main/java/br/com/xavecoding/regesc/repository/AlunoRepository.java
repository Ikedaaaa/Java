package br.com.xavecoding.regesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.xavecoding.regesc.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	//docs.spring.io - 6.3 Query Methods
	List<Aluno> findByNome(String nome);
	List<Aluno> findByNomeStartingWith(String nome);
	List<Aluno> findByNomeContaining(String nome);
	List<Aluno> findByNomeContainingAndIdadeLessThanEqual(String nome, Integer idade);
	
	//JPQL
	@Query("SELECT a FROM Aluno a WHERE a.nome like %:nome% AND a.idade >= :idade")
	List<Aluno> findNomeIdadeIgualOuMaior(String nome, Integer idade);
	
	@Query("SELECT a FROM Aluno a INNER JOIN a.disciplinas d WHERE a.nome like %:nomeAluno% AND a.idade >= :idadeAluno AND d.nome like %:nomeDisciplina%")
	List<Aluno> findNomeIdadeIgualOuMaiorMatriculado(String nomeAluno, Integer idadeAluno, String nomeDisciplina);
}
