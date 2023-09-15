package deustorepara;

import java.io.Serializable;

// TAREA 1c
public class Especialista implements Comparable<Especialista>, Serializable{
	protected String nombre;
	protected int reparaPC;
	protected int reparaMovil;
	protected int reparaTV;
	protected double bonificacion;

	public Especialista(String nombre, int reparaPC, int reparaMovil, int reparaTV, double bonificacion) {
		super();
		this.nombre = nombre;
		this.reparaPC = reparaPC;
		this.reparaMovil = reparaMovil;
		this.reparaTV = reparaTV;
		this.bonificacion = bonificacion;
	}
	
	public Especialista() {
		super();
		this.nombre = "";
		this.reparaPC = 0;
		this.reparaMovil = 0;
		this.reparaTV = 0;
		this.bonificacion = Math.random();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getReparaPC() {
		return reparaPC;
	}

	public void setReparaPC(int reparaPC) {
		this.reparaPC = reparaPC;
	}

	public int getReparaMovil() {
		return reparaMovil;
	}

	public void setReparaMovil(int reparaMovil) {
		this.reparaMovil = reparaMovil;
	}

	public int getReparaTV() {
		return reparaTV;
	}

	public void setReparaTV(int reparaTV) {
		this.reparaTV = reparaTV;
	}

	public double getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(double bonificacion) {
		this.bonificacion = bonificacion;
	}

	@Override
	public String toString() {
		return nombre + ", (PC:" + reparaPC + ", movil:" + reparaMovil
				+ ", TV:" + reparaTV + "), bonificacion:" + bonificacion;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialista other = (Especialista) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (reparaMovil != other.reparaMovil)
			return false;
		if (reparaPC != other.reparaPC)
			return false;
		if (reparaTV != other.reparaTV)
			return false;
		return true;
	}

	@Override
	public int compareTo(Especialista o) {
		// TODO Auto-generated method stub
		return (int) o.bonificacion - (int) this.bonificacion;
	}

	
	
}