package deustorepara;

import java.io.*;

public class Software implements Reparable, Serializable{
	
	protected String nombre;
	protected double version;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}
	
	public Software() {
		this.nombre = "";
		this.version = 0.0;
	}
	
	
	public Software(String nombre, double version) {
		super();
		this.nombre = nombre;
		this.version = version;
	}

	@Override
	public String toString() {
		return "Software [nombre=" + nombre + ", version=" + version + "]";
	}

	@Override
	public int getDificultad() {
		int dificultad = (int) this.version/4;
		if (dificultad > 5) {
			return 5;
		}
		else {
			return dificultad;
		}
		
	}
}
