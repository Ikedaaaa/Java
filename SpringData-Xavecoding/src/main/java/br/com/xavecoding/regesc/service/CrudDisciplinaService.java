package br.com.xavecoding.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.DisciplinaRepository;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {

	private ProfessorRepository professorRepository;
	private DisciplinaRepository disciplinaRepository;
	
	public CrudDisciplinaService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository) {
		super();
		this.professorRepository = professorRepository;
		this.disciplinaRepository = disciplinaRepository;
	}
	
	public void menu(Scanner scanner) {
		boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("\nQual ação você quer executar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar nova Disciplina");
			System.out.println("2 - Atualizar uma Disciplina");
			System.out.println("3 - Visualizar todas as Disciplinas");
			System.out.println("4 - Deletar uma Disciplina");
			
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
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}
	
	private void cadastrar(Scanner scanner) {
		System.out.println("Nome da Disciplina: ");
		String nome = scanner.next();
		
		System.out.println("Semestre: ");
		Integer semestre = scanner.nextInt();
		
		System.out.println("Id do Professor: ");
		Long idprofessor = scanner.nextLong();
		
		Optional<Professor> optional = this.professorRepository.findById(idprofessor);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			Disciplina disciplina = new Disciplina(nome, semestre, professor);
			disciplinaRepository.save(disciplina);
			System.out.println("Disciplina cadastrada!\n");
		} else {
			System.out.println("O Id do professor informado [ " + idprofessor +" ] é inválido");
		}
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("\nDigite o Id da Disciplina a ser atualizada: ");
		Long id = scanner.nextLong();
		
		System.out.println();
		
		Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
		
		if(optionalDisciplina.isPresent()) {
			System.out.println(atualizaCampos(scanner, optionalDisciplina));
		} else {
			System.out.println("O Id da disciplina informado [ " + id +" ] é inválido");
		}
	}
	
	private void visualizar() {
		Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
		
		System.out.println("\nLista com foreach");
		
		for(Disciplina disciplina: disciplinas) {
			System.out.println(disciplina);
		}
		
		System.out.println();
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("\nId da Disciplina: ");
		Long id = scanner.nextLong();
		
		Optional<Disciplina> optional = this.disciplinaRepository.findById(id);
		
		if(optional.isPresent()) {
			this.disciplinaRepository.deleteById(id);
			System.out.println("Disciplina deletada!\n");
		} else {
			System.out.println("O Id da Disciplina informado [ " + id +" ] é inválido");
		}
	}
	
	private String atualizaCampos(Scanner scanner, Optional<Disciplina> optional) {
		
		Disciplina disciplina = optional.get();
		
		System.out.println("\nAtualizar Nome? (0 - não; 1 - sim)");
		int opcao = scanner.nextInt();
		int camposAtualizados = 0;
		
		if (opcao == 1) {
			System.out.println("Nome: ");
			String nome = scanner.next();
			disciplina.setNome(nome);
			camposAtualizados += 1;
		}
		
		System.out.println("\nAtualizar Semestre? (0 - não; 1 - sim)");
		opcao = scanner.nextInt();
		
		if (opcao == 1) {
			System.out.println("Semestre: ");
			Integer semestre = scanner.nextInt();
			disciplina.setSemestre(semestre);
			camposAtualizados += 1;
		}
		
		System.out.println("\nAtualizar Id do Professor? (0 - não; 1 - sim)");
		opcao = scanner.nextInt();
		
		if (opcao == 1) {
			System.out.println("Id do Professor: ");
			Long idprofessor = scanner.nextLong();
			
			Optional<Professor> optionalProfessor = this.professorRepository.findById(idprofessor);
			if(optionalProfessor.isPresent()) {
				Professor professor = optionalProfessor.get();
				disciplina.setProfessor(professor);
				camposAtualizados += 1;
			} else {
				return "O Id do professor informado [ " + idprofessor +" ] é inválido";
			}
		}
		
		if (camposAtualizados > 0) {
			disciplinaRepository.save(disciplina);
			return "\nDisciplina atualizada com sucesso no banco!!\n";
		} else {
			return "\nNada a ser atualizado\n";
		}
	}
}
