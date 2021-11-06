package br.com.xavecoding.regesc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.xavecoding.regesc.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	//docs.spring.io - 6.3 Query Methods
	List<Aluno> findByNome(String nome);
	List<Aluno> findByNomeStartingWith(String nome);
	List<Aluno> findByNomeContaining(String nome);
	List<Aluno> findByNomeContainingAndIdadeLessThanEqual(String nome, Integer idade);
}
