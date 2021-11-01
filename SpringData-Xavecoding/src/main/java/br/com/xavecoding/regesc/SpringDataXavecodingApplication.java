package br.com.xavecoding.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.service.CrudProfessorService;

@SpringBootApplication
public class SpringDataXavecodingApplication implements CommandLineRunner {
	
	private CrudProfessorService crudProfessorService;

	//os objetos passados por parâmetro são injetadas automaticamente pelo Spring
	//pq suas classes possuem a anotação @Service
	public SpringDataXavecodingApplication(CrudProfessorService crudProfessorService) {
		super();
		this.crudProfessorService = crudProfessorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataXavecodingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);
		
		while(isTrue) {
			System.out.println("Qual entidade você deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.crudProfessorService.menu(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}

}
