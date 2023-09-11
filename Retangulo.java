
public class Retangulo {
	private double base;
	private double altura;
	
	public Retangulo() {
		this.base = 0.0;
		this.altura = 0.0;
	}
	
	public Retangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	public double getArea() {
		return base * altura;
	}
	
	public double getPerimetro() {
		return (base * altura) * 2;
	}
	
	public double getDiag() {
		return Math.sqrt(Math.pow(base, 2) + Math.pow(altura, 2));
	}
	
}