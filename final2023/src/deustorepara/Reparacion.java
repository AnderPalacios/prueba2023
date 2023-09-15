package deustorepara;

import java.io.Serializable;
import java.util.Date;

public class Reparacion implements Serializable{
	protected Averia averia;
	protected Especialista especialista;
	protected Date fecha;
	
	public Reparacion(Averia averia, Especialista especialista, Date fecha) {
		super();
		this.averia = averia;
		this.especialista = especialista;
		this.fecha = fecha;
	}

	public Reparacion() {
		super();
		this.averia = null;
		this.especialista = null;
		this.fecha = new Date();
	}

	public Averia getAveria() {
		return averia;
	}

	public void setAveria(Averia averia) {
		this.averia = averia;
	}

	public Especialista getEspecialista() {
		return especialista;
	}

	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Reparacion de " + averia.getReparable() + " por " + especialista.nombre + ", " + fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averia == null) ? 0 : averia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reparacion other = (Reparacion) obj;
		if (averia == null) {
			if (other.averia != null)
				return false;
		} else if (!averia.equals(other.averia))
			return false;
		return true;
	}

	double getPresupuesto() {
		double presupuesto = 0.0;
		Reparable reparable = this.averia.getReparable();
		int dificultad = reparable.getDificultad();
		
		if (reparable instanceof PC) {
			presupuesto = dificultad * 50.0;
		} else if (reparable instanceof Movil) {
			presupuesto = dificultad * 30.0;
		} else if (reparable instanceof TV) {
			presupuesto = dificultad * 50.0;
		} else {
			presupuesto = dificultad * 15.0;
		}
		
		return presupuesto;
	}
}
