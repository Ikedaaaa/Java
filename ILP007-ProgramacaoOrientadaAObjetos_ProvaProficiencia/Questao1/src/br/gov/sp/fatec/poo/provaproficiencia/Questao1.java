package br.gov.sp.fatec.poo.provaproficiencia;
import java.util.Scanner;

public class Questao1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		float[] salarios = new float[15];
		float media = 0;
		
		int abaixoMedia = 0;
		int naMedia = 0;
		int acimaMedia = 0;
		
		System.out.println("Digite o salário de 15 trabalhadores:");
		for (int i = 0; i < 15; i++) {
			System.out.print((i+1)+"º trabalhador: ");
			salarios[i] = scanner.nextFloat();
			media += salarios[i];
		}
		scanner.close();
		media = media / 15;
		
		for (float salario: salarios) {
			if (salario == media)
				naMedia++;
			else if (salario < media)
				abaixoMedia++;
			else
				acimaMedia++;
		}
		
		System.out.println("\n******** ESTATÍSTICAS ********\n");
		System.out.println("Pessoas abaixo da média: " + abaixoMedia);
		System.out.println("Pessoas acima da média: " + acimaMedia);
		System.out.println("Pessoas na média: " + naMedia);
	}

}
