package br.com.xavecoding.regesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xavecoding.regesc.orm.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long>{

	//SQL nativo
	@Query(nativeQuery = true,
			value = "SELECT * FROM Professores p INNER JOIN Disciplinas d ON p.idprofessor = d.professor_id WHERE p.nome like :nomeProfessor% AND d.nome like :nomeDisciplina%")
	List<Professor> findProfessorAtribuido(String nomeProfessor, String nomeDisciplina);
	
}
