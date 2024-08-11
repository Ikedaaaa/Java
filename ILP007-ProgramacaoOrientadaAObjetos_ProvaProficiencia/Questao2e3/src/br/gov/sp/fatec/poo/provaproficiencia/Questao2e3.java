package br.gov.sp.fatec.poo.provaproficiencia;

public class Questao2e3 {

	public static void main(String[] args) {
		Bau b1 = new Bau(10.5, 10.5, 10.5);
		
		System.out.println("Primeiro baú criado, suas informações são:");
		printBauInformations(b1);
		
		Bau b2 = new Bau(5.89, 10.64, 15.45, "branco");
		System.out.println("Segundo baú criado, suas informações são:");
		printBauInformations(b2);
		
		b1.setAltura(-0.01);
		b1.setLargura(0);
		b1.setProfundidade(5.4);
		b1.pintar("");
		b1.pintar("laranja");
		b1.abrir();
		System.out.println("\nAtributos do primeiro baú foram alterados. Suas novas informações são:");
		printBauInformations(b1);
		
		b2.abrir();
		b2.setAltura(50.45);
		b2.setLargura(100.65);
		b2.setProfundidade(50.46);
		b2.pintar("azul");
		b2.fechar();
		System.out.println("\nAtributos do segundo baú foram alterados. Suas novas informações são:");
		printBauInformations(b2);
	}
	
	private static void printBauInformations(Bau bau) {
		System.out.println("Largura: " + bau.getLargura() + " cm");
		System.out.println("Altura: " + bau.getAltura() + " cm");
		System.out.println("Profundidade: " + bau.getProfundidade() + " cm");
		System.out.println("Cor: " + bau.getCor());
		System.out.println("Está aberto? " + (bau.isAberto() ? "Sim\n" : "Não\n"));
	}

}
