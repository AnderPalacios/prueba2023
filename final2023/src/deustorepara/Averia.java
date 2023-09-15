package deustorepara;

import java.io.Serializable;
import java.util.Objects;

public class Averia implements Serializable{
	
	//Static
	protected static int ID = 0;
	
	//Parte no static
	protected Reparable reparable;
	protected int id;

	public Averia(Reparable reparable) {
		super();
		this.reparable = reparable;
		this.id = ID;
		ID ++;
	}
	
	public Averia() {
		super();
		this.reparable = null;
		this.id = ID;
		ID ++;
	}

	public Reparable getReparable() {
		return reparable;
	}

	public void setReparable(Reparable reparable) {
		this.reparable = reparable;
	}
	
	public static int getID() {
		return ID;
	}

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Averia [reparable=" + reparable + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reparable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Averia other = (Averia) obj;
		return id == other.id && Objects.equals(reparable, other.reparable);
	}
	
	
}
