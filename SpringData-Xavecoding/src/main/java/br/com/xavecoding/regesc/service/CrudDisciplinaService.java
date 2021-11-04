package br.com.xavecoding.regesc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.AlunoRepository;
import br.com.xavecoding.regesc.repository.DisciplinaRepository;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {

	private ProfessorRepository professorRepository;
	private DisciplinaRepository disciplinaRepository;
	private AlunoRepository alunoRepository;
	
	public CrudDisciplinaService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository,
			AlunoRepository alunoRepository) {
		super();
		this.professorRepository = professorRepository;
		this.disciplinaRepository = disciplinaRepository;
		this.alunoRepository = alunoRepository;
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
			System.out.println("5 - Matricular Alunos");
			
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
				this.matricularAlunos(scanner);
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
			
			List<Aluno> alunos = this.matricular(scanner);
			if (!alunos.isEmpty()) {
				disciplina.setAlunos(alunos);
			}
			
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
	
	private void matricularAlunos(Scanner scanner) {
		System.out.println("Digite o Id da Disciplina para matricular alunos");
		Long id = scanner.nextLong();
		
		Optional<Disciplina> optional = this.disciplinaRepository.findById(id);
		if(optional.isPresent()) {
			Disciplina disciplina = optional.get();
			List<Aluno> novosAlunos = this.matricular(scanner);
			disciplina.getAlunos().addAll(novosAlunos);
			this.disciplinaRepository.save(disciplina);
		} else {
			System.out.println("O Id da Disciplina informado [ " + id +" ] é inválido");
		}
	}
	
	private List<Aluno> matricular(Scanner scanner) {
		Boolean verdadeiro = true;
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		while (verdadeiro) {
			System.out.println("Id do Aluno a ser matriculado: (0 - Sair)");
			Long id = scanner.nextLong();
			
			if (id > 0) {
				Optional<Aluno> optional = this.alunoRepository.findById(id);
				if(optional.isPresent()) {
					alunos.add(optional.get());
				} else {
					System.out.println("O Id do Aluno informado [ " + id +" ] é inválido");
				}
			} else {
				verdadeiro = false;
			}
		}
		return alunos;
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
		
		System.out.println("\nAtualizar Alunos? (0 - não; 1 - sim)");
		opcao = scanner.nextInt();
		
		if (opcao == 1) {
			List<Aluno> alunos = this.matricular(scanner);
			if (!alunos.isEmpty()) {
				disciplina.setAlunos(alunos);
				camposAtualizados += 1;
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
