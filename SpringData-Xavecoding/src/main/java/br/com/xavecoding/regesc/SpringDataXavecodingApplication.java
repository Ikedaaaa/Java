package br.com.xavecoding.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.service.CrudAlunoService;
import br.com.xavecoding.regesc.service.CrudDisciplinaService;
import br.com.xavecoding.regesc.service.CrudProfessorService;

@SpringBootApplication
public class SpringDataXavecodingApplication implements CommandLineRunner {
	
	private CrudProfessorService crudProfessorService;
	private CrudDisciplinaService crudDisciplinaService;
	private CrudAlunoService crudAlunoService;

	//os objetos passados por parâmetro são injetadas automaticamente pelo Spring
	//pq suas classes possuem a anotação @Service
	public SpringDataXavecodingApplication(CrudProfessorService crudProfessorService, CrudDisciplinaService crudDisciplinaService,
			CrudAlunoService crudAlunoService) {
		super();
		this.crudProfessorService = crudProfessorService;
		this.crudDisciplinaService = crudDisciplinaService;
		this.crudAlunoService = crudAlunoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataXavecodingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);
		
		while(isTrue) {
			System.out.println("\nQual entidade você deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Disciplina");
			System.out.println("3 - Aluno");
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.crudProfessorService.menu(scanner);
				break;
			case 2:
				this.crudDisciplinaService.menu(scanner);
				break;
			case 3:
				this.crudAlunoService.menu(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}

}
