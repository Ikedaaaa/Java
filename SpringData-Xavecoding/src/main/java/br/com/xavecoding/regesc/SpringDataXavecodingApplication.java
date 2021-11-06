package br.com.xavecoding.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.service.CrudAlunoService;
import br.com.xavecoding.regesc.service.CrudDisciplinaService;
import br.com.xavecoding.regesc.service.CrudProfessorService;
import br.com.xavecoding.regesc.service.RelatorioService;

@SpringBootApplication
public class SpringDataXavecodingApplication implements CommandLineRunner {
	
	private CrudProfessorService crudProfessorService;
	private CrudDisciplinaService crudDisciplinaService;
	private CrudAlunoService crudAlunoService;
	private RelatorioService relatorioService;

	//os objetos passados por parâmetro são injetadas automaticamente pelo Spring
	//pq suas classes possuem a anotação @Service
	public SpringDataXavecodingApplication(CrudProfessorService crudProfessorService, CrudDisciplinaService crudDisciplinaService,
			CrudAlunoService crudAlunoService, RelatorioService relatorioService) {
		super();
		this.crudProfessorService = crudProfessorService;
		this.crudDisciplinaService = crudDisciplinaService;
		this.crudAlunoService = crudAlunoService;
		this.relatorioService = relatorioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataXavecodingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);
		
		while(isTrue) {
			System.out.println("\nO que deseja acessar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Disciplina");
			System.out.println("3 - Aluno");
			System.out.println("4 - Relatório");
			
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
			case 4:
				this.relatorioService.menu(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}

}
