package deustorepara;

public class Movil extends Producto {
	protected String procesador;
	protected double pulgadas;
	
	public Movil(String modelo, String marca, String procesador, double pulgadas) {
		super(modelo, marca);
		this.procesador = procesador;
		this.pulgadas = pulgadas;
	}
	
	public Movil() {
		super();
		this.procesador = "N/A";
		this.pulgadas = 0.0;
	}

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public double getPulgadas() {
		return pulgadas;
	}

	public void setPulgadas(double pulgadas) {
		this.pulgadas = pulgadas;
	}

	@Override
	public String toString() {
		return  "Móvil " + modelo + " - " + marca + " (procesador:" + procesador + ", " + pulgadas + "\")";
	}

	@Override
	public int getDificultad() {
		int dif = (int) this.pulgadas;
		if (dif > 5) {
			dif = 5;
		}
		return dif;
	}
	
}
