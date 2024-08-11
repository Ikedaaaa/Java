package br.gov.sp.fatec.poo.provaproficiencia;

public class Livro extends Publicacao {
	private String autor;

	public Livro(String titulo, String autor, String editora, float preco) {
		super(titulo, editora, preco);
		this.autor = autor;
	}
	
	public String getAutor() {
		return autor;
	}
	
	@Override
	public void exibeDados() {
		super.exibeDados();
		System.out.println("Autor: " + getAutor());
	}
}
