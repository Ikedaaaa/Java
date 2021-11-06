package br.com.xavecoding.regesc.service;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.repository.AlunoRepository;

@Service
public class CrudAlunoService {

	private AlunoRepository alunoRepository;

	public CrudAlunoService(AlunoRepository alunoRepository) {
		super();
		this.alunoRepository = alunoRepository;
	}
	
	@Transactional
	public void menu(Scanner scanner) {
		boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("\nQual ação você quer executar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo Aluno");
			System.out.println("2 - Atualizar um Aluno");
			System.out.println("3 - Visualizar todos os Alunos");
			System.out.println("4 - Deletar um Aluno");
			System.out.println("5 - Visualizar um Aluno");
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.atualizar(scanner);
				break;
			case 3:
				this.visualizar();
				break;
			case 4:
				this.deletar(scanner);
				break;
			case 5:
				this.visualizarAluno(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}

	private void cadastrar(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("Idade: ");
		Integer idade = scanner.nextInt();
		
		Aluno aluno = new Aluno(nome, idade);
		this.alunoRepository.save(aluno);
		System.out.println("Aluno salvo no banco!!\n");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("\nDigite o Id do Aluno a ser atualizado: ");
		Long id = scanner.nextLong();
		
		System.out.println();
		
		Optional<Aluno> optional = this.alunoRepository.findById(id);
		
		if(optional.isPresent()) {
			System.out.println(atualizaCampos(scanner, optional));
		} else {
			System.out.println("O Id do aluno informado [ " + id +" ] é inválido");
		}
	}
	
	private void visualizar() {
		Iterable<Aluno> alunos = this.alunoRepository.findAll();
		
		System.out.println();
		
		for(Aluno aluno: alunos) {
			System.out.println(aluno);
		}
		
		System.out.println();
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("\nId do Aluno: ");
		Long id = scanner.nextLong();
		
		Optional<Aluno> optional = this.alunoRepository.findById(id);
		
		if(optional.isPresent()) {
			this.alunoRepository.deleteById(id);
			System.out.println("Aluno deletado!\n");
		} else {
			System.out.println("O Id da Disciplina informado [ " + id +" ] é inválido");
		}
	}
	
	@Transactional
	private void visualizarAluno(Scanner scanner) {
		System.out.println("\nDigite o Id do Aluno a ser visualizado: ");
		Long id = scanner.nextLong();
		
		Optional<Aluno> optional = this.alunoRepository.findById(id);
		
		if(optional.isPresent()) {
			Aluno aluno = optional.get();
			
			System.out.println("\nAluno: {");
			System.out.println("ID: " + aluno.getId());
			System.out.println("Nome: " + aluno.getNome());
			System.out.println("Idade: " + aluno.getIdade());
			System.out.println("Disciplina: [");
			
			Set<Disciplina> disciplinas = aluno.getDisciplinas();
			
			if (disciplinas != null) {
				for (Disciplina disciplina : disciplinas) {
					System.out.println("\n\tID: " + disciplina.getId());
					System.out.println("\tNome: " + disciplina.getNome());				
					System.out.println("\tSemestre: " + disciplina.getSemestre());
				}
			} else {
				System.out.println("\tNenhuma disciplina associada ao Aluno");
			}
			System.out.println("	]\n}");
		} else {
			System.out.println("O Id do aluno informado [ " + id +" ] é inválido");
		}
	}
	
	private String atualizaCampos(Scanner scanner, Optional<Aluno> optional) {
		
		Aluno aluno = optional.get();
		
		System.out.println("\nQual campo quer atualizar?");
		System.out.println("1 - Nome");
		System.out.println("2 - Idade");
		System.out.println("3 - Ambos");
		
		int opcao = scanner.nextInt();
		
		if (opcao == 1 || opcao == 3) {
			System.out.println("Nome: ");
			String nome = scanner.next();
			aluno.setNome(nome);
		}
		if (opcao == 2 || opcao == 3) {
			System.out.println("Idade: ");
			Integer idade = scanner.nextInt();
			aluno.setIdade(idade);
		}
		if(opcao < 1 || opcao > 3) {
			return "\nOpção inválida :(";
		}
		
		alunoRepository.save(aluno);
		return "\nAluno atualizado com sucesso no banco!!\n";
	}
	
}
