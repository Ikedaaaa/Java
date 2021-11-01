package br.com.xavecoding.regesc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@SpringBootApplication
public class SpringDataXavecodingApplication implements CommandLineRunner {

	private ProfessorRepository repository;
	
	public SpringDataXavecodingApplication(ProfessorRepository repository) {
		super();
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataXavecodingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Professor professor = new Professor("Qualquer coisa", "sp1751837");
		
		System.out.println("Professor ANTES do save (persistência com BD)");
		System.out.println(professor);
		
		this.repository.save(professor);
		
		System.out.println("Professor DEPOIS do save (persistência com BD)");
		System.out.println(professor);
	}

}
