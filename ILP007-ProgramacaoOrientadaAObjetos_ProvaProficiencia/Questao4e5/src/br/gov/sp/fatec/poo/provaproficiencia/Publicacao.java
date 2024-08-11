package br.gov.sp.fatec.poo.provaproficiencia;

public class Publicacao {
	protected String titulo;
	protected String editora;
	protected float preco;
	
	public Publicacao(String titulo, String editora, float preco) {
		this.titulo = titulo;
		this.editora = editora;
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void reajustaPreco(float perc) {
		preco += (preco * perc);
	}
	
	public void exibeDados() {
		System.out.println("Titulo: " + getTitulo());
		System.out.println("Editora: " + getEditora());
		System.out.println("Preco: R$" + preco);
	}
}
