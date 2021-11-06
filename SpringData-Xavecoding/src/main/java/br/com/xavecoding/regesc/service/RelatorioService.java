package br.com.xavecoding.regesc.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.repository.AlunoRepository;

@Service
public class RelatorioService {

	private AlunoRepository alunoRepository;

	public RelatorioService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public void menu(Scanner scanner) {
		boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("\nQual relatório você deseja?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Alunos por um dado Nome");
			System.out.println("2 - Alunos por Nome e Idade menor ou igual");
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.alunosPorNome(scanner);
				break;
			case 2:
				this.alunosPorNomeIdadeMenorOuIgual(scanner);
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
}
