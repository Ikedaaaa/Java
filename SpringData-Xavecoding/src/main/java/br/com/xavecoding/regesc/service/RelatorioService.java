package br.com.xavecoding.regesc.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.AlunoRepository;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class RelatorioService {

	private AlunoRepository alunoRepository;
	private ProfessorRepository professorRepository;

	public RelatorioService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
		this.alunoRepository = alunoRepository;
		this.professorRepository = professorRepository;
	}
	
	public void menu(Scanner scanner) {
		boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("\nQual relatório você deseja?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Alunos por um dado Nome");
			System.out.println("2 - Alunos por Nome e Idade MENOR ou igual");
			System.out.println("3 - Alunos por Nome e Idade MAIOR ou igual");
			System.out.println("4 - Alunos Matriculados com Nome e Idade MAIOR ou igual");
			System.out.println("5 - Professores Atribuídos");
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.alunosPorNome(scanner);
				break;
			case 2:
				this.alunosPorNomeIdadeMenorOuIgual(scanner);
				break;
			case 3:
				this.alunosPorNomeIdadeMaiorOuIgual(scanner);
				break;
			case 4:
				this.alunosMatriculadosComNomeIdadeMaiorOuIgual(scanner);
				break;
			case 5:
				this.professoresAtribuidos(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}
	
	private void alunosPorNome(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		List<Aluno> alunos = this.alunoRepository.findByNomeContaining(nome);
		alunos.forEach(System.out::println);
	}
	
	private void alunosPorNomeIdadeMenorOuIgual(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("Idade: ");
		Integer idade = scanner.nextInt();
		
		List<Aluno> alunos = this.alunoRepository.findByNomeContainingAndIdadeLessThanEqual(nome, idade);
		alunos.forEach(System.out::println);
	}
	
	private void alunosPorNomeIdadeMaiorOuIgual(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("Idade: ");
		Integer idade = scanner.nextInt();
		
		List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaior(nome, idade);
		alunos.forEach(System.out::println);
	}
	
	private void alunosMatriculadosComNomeIdadeMaiorOuIgual(Scanner scanner) {
		System.out.println("Nome do Aluno: ");
		String nome = scanner.next();
		
		System.out.println("Idade do Aluno: ");
		Integer idade = scanner.nextInt();
		
		System.out.println("Nome da Disciplina: ");
		String nomeDisciplina = scanner.next();
		
		List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaiorMatriculado(nome, idade, nomeDisciplina);
		alunos.forEach(System.out::println);
	}
	
	private void professoresAtribuidos(Scanner scanner) {
		System.out.println("Nome do Professor: ");
		String nomeProfessor = scanner.next();
		
		System.out.println("Nome da Disciplina: ");
		String nomeDisciplina = scanner.next();
		
		List<Professor> professores = this.professorRepository.findProfessorAtribuido(nomeProfessor, nomeDisciplina);
		professores.forEach(System.out::println);
	}
}
