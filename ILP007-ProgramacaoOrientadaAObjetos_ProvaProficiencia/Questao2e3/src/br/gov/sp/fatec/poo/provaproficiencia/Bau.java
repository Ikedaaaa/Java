package br.gov.sp.fatec.poo.provaproficiencia;

public class Bau {
	private double largura;
	private double altura;
	private double profundidade;
	private String cor;
	private boolean aberto;
	
	public Bau(double largura, double altura, double profundidade) {
		this(largura, altura, profundidade, "marrom");
	}
	
	public Bau(double largura, double altura, double profundidade, String cor) {
		this.largura = largura;
		this.altura = altura;
		this.profundidade = profundidade;
		this.cor = cor;
		this.aberto = false;
	}

	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		if (largura > 0)
			this.largura = largura;
		else
			System.out.println("Largura não pode ser menor ou igual a 0");
	}

	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		if (altura > 0)
			this.altura = altura;
		else
			System.out.println("Altura não pode ser menor ou igual a 0");
	}

	public double getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(double profundidade) {
		if (profundidade > 0)
			this.profundidade = profundidade;
		else
			System.out.println("Profundidade não pode ser menor ou igual a 0");
	}

	public boolean isAberto() {
		return aberto;
	}	
	public void abrir() {
		this.aberto = true;
	}
	public void fechar() {
		this.aberto = false;
	}
	
	public String getCor() {
		return cor;
	}
	public void pintar(String cor) {
		if ("".equals(cor))
			System.out.println("É necessário informar uma cor");
		else
			this.cor = cor;
	}
}
/*
UML diagram for this class:

https://yuml.me/diagram/scruffy/class/draw
[
Bau
|
-largura: double;-altura: double;-profundidade: double;-cor: String;-aberto: boolean
|
+Bau(largura:double,altura:double,profundidade:double);
+Bau(largura:double,altura:double,profundidade:double,cor:String);
+getLargura(): double;
+setLargura(largura:double): void;
+getAltura(): double;
+setAltura(altura:double): void;
+getProfundidade(): double;
+setProfundidade(profundidade:double): void;
+isAberto(): boolean;
+abrir(): void;
+fechar(): void;
+getCor(): String;
+pintar(cor:String): void
]
*/