package br.com.xavecoding.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.service.CrudDisciplinaService;
import br.com.xavecoding.regesc.service.CrudProfessorService;

@SpringBootApplication
public class SpringDataXavecodingApplication implements CommandLineRunner {
	
	private CrudProfessorService crudProfessorService;
	private CrudDisciplinaService crudDisciplinaService;

	//os objetos passados por parâmetro são injetadas automaticamente pelo Spring
	//pq suas classes possuem a anotação @Service
	public SpringDataXavecodingApplication(CrudProfessorService crudProfessorService, CrudDisciplinaService crudDisciplinaService) {
		super();
		this.crudProfessorService = crudProfessorService;
		this.crudDisciplinaService = crudDisciplinaService;
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
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.crudProfessorService.menu(scanner);
				break;
			case 2:
				this.crudDisciplinaService.menu(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}

}
