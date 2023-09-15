package deustorepara;

public class TV extends Producto implements Reparable {
	protected double pulgadas;

	public TV(String modelo, String marca, double pulgadas) {
		super(modelo, marca);
		this.pulgadas = pulgadas;
	}
	
	public TV() {
		super();
	}

	public double getPulgadas() {
		return pulgadas;
	}

	public void setPulgadas(double pulgadas) {
		this.pulgadas = pulgadas;
	}

	@Override
	public String toString() {
		return "TV " + modelo + " - " + marca + "(" + pulgadas + "\")";
	}

	@Override
	public int getDificultad() {
		int dif = (int) (this.pulgadas / 20);
		if (dif > 5) {
			dif = 5;
		}
		return dif;
	}
	
}
