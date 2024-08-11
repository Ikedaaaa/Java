package br.gov.sp.fatec.poo.provaproficiencia;
import java.util.Scanner;

public class Questao4e5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Publicacao[] publicacoes = new Publicacao[5];
		
		int isLivro = 0;
		String titulo = "";
		String editora = "";
		float preco = 0;
		
		String autor = "";
		
		int anoPublicacao = 0;
		int numPublicacao = 0;
		
		System.out.println("Digite as informações de 5 publicações para serem armazenadas:\n");
		for (int i = 0; i < 5; i++) {
			System.out.println("******************* " + (i+1) + "ª Publicação *******************");
			System.out.println("Deseja armazenar um Livro ou Revista? (Digite 1 para Livro, ou qualquer outro número para revista)");
			System.out.print("Opção: ");
			isLivro = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Título: ");
			titulo = scanner.nextLine();
			
			System.out.print("Editora: ");
			editora = scanner.nextLine();
			
			System.out.print("Preço: ");
			preco = Float.parseFloat(scanner.nextLine());
			
			if (isLivro == 1) {
				System.out.print("Autor: ");
				autor = scanner.nextLine();
				
				publicacoes[i] = new Livro(titulo, autor, editora, preco);
			} else {
				System.out.print("Ano da Publicação: ");
				anoPublicacao = Integer.parseInt(scanner.nextLine());
				
				System.out.print("Número da Publicação: ");
				numPublicacao = Integer.parseInt(scanner.nextLine());
				
				publicacoes[i] = new Revista(titulo, numPublicacao, anoPublicacao, editora, preco);
			}
			System.out.println();
		}
		scanner.close();
		
		for (int i = 0; i < 5; i++) {
			if ("Lucrativa".equals(publicacoes[i].getEditora()))
				publicacoes[i].reajustaPreco(0.1f);
		}
		
		System.out.println("\n**************************************************************");
		System.out.println("Dados das 5 publicações informadas:\n");
		for (Publicacao publicacao: publicacoes) {
			publicacao.exibeDados();
			System.out.println();
		}
	}
}
