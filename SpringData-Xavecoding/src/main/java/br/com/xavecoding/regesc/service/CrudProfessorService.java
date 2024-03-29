package br.com.xavecoding.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class CrudProfessorService {

	private ProfessorRepository professorRepository; //dependência da classe CrudProfessorService
	
	// o Spring automaticamente cria um objeto com a interface 'ProfessorRepository'
	// e o injeta para nós no construtor da classe atual => Injeção de Dependências
	public CrudProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}

	@Transactional
	public void menu(Scanner scanner) {
		boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("\nQual ação você quer executar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo Professor");
			System.out.println("2 - Atualizar um Professor");
			System.out.println("3 - Visualizar todos os Professores");
			System.out.println("4 - Deletar um Professor");
			System.out.println("5 - Visualizar um Professor");
			System.out.println("9 - Atualizar um Professor sem FindById (Tenta atualizar pelo Id passado, "+
			"se não encontrar, cria um novo registro)");
			
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
				this.visualizarProfessor(scanner);
				break;
			case 9:
				this.atualizarSemFindById(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}

	private void cadastrar(Scanner scanner) {
		System.out.println("Digite o nome do professor: ");
		String nome = scanner.next();
		
		System.out.println("Digite o prontuário do professor: ");
		String prontuario = scanner.next();
		
		Professor professor = new Professor(nome, prontuario);
		this.professorRepository.save(professor);
		System.out.println("Professor salvo no banco!!\n");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("\nDigite o Id do Professor a ser atualizado: ");
		Long id = scanner.nextLong();
		
		System.out.println();
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		// se o hibernate conseguiu achar uma tupla/registro na tabela de professores com id igual ao passado pelo usuário
		if(optional.isPresent()) {
			System.out.println(atualizaCampos(scanner, optional));
		} else {
			System.out.println("O Id do professor informado [ " + id +" ] é inválido");
		}
	}
	
	private void visualizar() {
		Iterable<Professor> professores = this.professorRepository.findAll();
		
		System.out.println("\nLista com foreach");
		
		for(Professor professor: professores) {
			System.out.println(professor);
		}
		
		System.out.println("\nLista com lambda function forEach()");
		professores.forEach(professor -> {
			System.out.println(professor);
		});
		
		System.out.println("\nLista com System.out::println");
		professores.forEach(System.out::println);
		System.out.println();
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("\nDigite o Id do Professor a ser deletado: ");
		Long id = scanner.nextLong();
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			this.professorRepository.deleteById(id);
			System.out.println("Professor deletado!\n");
		} else {
			System.out.println("O Id do professor informado [ " + id +" ] é inválido");
		}
	}
	
	//Mesmo se o tipo de fetching for LAZY, o hibernate retorna os
	//objetos associados se for usada a anotação @Transactional:
	//1. No método que chama o getDisciplinas;
	//2. No método que chama o método que chama o getDisciplinas.
	//Nesse caso: menu() e visualizarProfessor()
	//
	//Com @Transational e o fetch LAZY, são feitas duas consultas ao banco de dados:
	//no findById() e no getDisciplinas();
	//Com fetch EAGER em List<Disciplina>, é feita apenas
	//uma consulta, no findById()
	//
	/* Como alternativa, a anotação pode ser colocada na classe
	 * Indicando que todos os métodos da classe estão aptos a
	 * interagir com o banco de dados*/
	@Transactional
	private void visualizarProfessor(Scanner scanner) {
		System.out.println("\nDigite o Id do Professor a ser visualizado: ");
		Long id = scanner.nextLong();
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			
			System.out.println("\nProfessor: {");
			System.out.println("ID: " + professor.getId());
			System.out.println("Nome: " + professor.getNome());
			System.out.println("Prontuario: " + professor.getProntuario());
			System.out.println("Disciplina: [");
			
			if (professor.getDisciplinas() != null) {
				for (Disciplina disciplina : professor.getDisciplinas()) {
					System.out.println("\n\tID: " + disciplina.getId());
					System.out.println("\tNome: " + disciplina.getNome());				
					System.out.println("\tSemestre: " + disciplina.getSemestre());
				}
			} else {
				System.out.println("\tNenhuma disciplina associada ao Professor");
			}
			System.out.println("	]\n}");
		} else {
			System.out.println("O Id do professor informado [ " + id +" ] é inválido");
		}
	}
	
	private String atualizaCampos(Scanner scanner, Optional<Professor> optional) {
		
		Professor professor = optional.get();
		
		System.out.println("\nQual campo quer atualizar?");
		System.out.println("1 - Nome");
		System.out.println("2 - Prontuário");
		System.out.println("3 - Ambos");
		
		int opcao = scanner.nextInt();
		
		if (opcao == 1 || opcao == 3) {
			System.out.println("Digite o nome do professor: ");
			String nome = scanner.next();
			professor.setNome(nome);
		}
		if (opcao == 2 || opcao == 3) {
			System.out.println("Digite o prontuário do professor: ");
			String prontuario = scanner.next();
			professor.setProntuario(prontuario);
		}
		if(opcao < 1 || opcao > 3) {
			return "\nOpção inválida :(";
		}
		
		professorRepository.save(professor);
		return "\nProfessor atualizado com sucesso no banco!!\n";
	}
	
	private void atualizarSemFindById(Scanner scanner) {
		System.out.println("Digite o Id do Professor a ser atualizado: ");
		Long id = scanner.nextLong();
		
		System.out.println("Digite o nome do professor: ");
		String nome = scanner.next();
		
		System.out.println("Digite o prontuário do professor: ");
		String prontuario = scanner.next();
		
		@SuppressWarnings("deprecation")
		Professor professor = new Professor();
		professor.setId(id);
		professor.setNome(nome);
		professor.setProntuario(prontuario);
		
		professorRepository.save(professor);
		System.out.println("Professor atualizado com sucesso no banco!!\n");
	}
	
}
