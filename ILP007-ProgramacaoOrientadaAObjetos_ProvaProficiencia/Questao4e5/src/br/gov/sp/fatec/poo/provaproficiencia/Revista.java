package br.gov.sp.fatec.poo.provaproficiencia;

public class Revista extends Publicacao {
	private int anoPublicacao;
	private int numPublicacao;

	public Revista(String titulo, int numPublicacao, int anoPublicacao, String editora, float preco) {
		super(titulo, editora, preco);
		this.anoPublicacao = anoPublicacao;
		this.numPublicacao = numPublicacao;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public int getNumPublicacao() {
		return numPublicacao;
	}
	
	@Override
	public void exibeDados() {
		super.exibeDados();
		System.out.println("Ano da Publicação: " + getAnoPublicacao());
		System.out.println("Número da Publicação: " + getNumPublicacao());
	}
}
