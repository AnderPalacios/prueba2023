package deustorepara;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;

public class DeustoRepara {
	protected ArrayList<Especialista> especialistas;
	protected ArrayList<Averia> averias;
	protected ArrayList<Reparacion> reparaciones;
	// TAREA 2a
	protected HashMap<Especialista, ArrayList<Reparacion>> reparacionesPorEspecialista;
	
	public DeustoRepara(ArrayList<Especialista> especialistas, ArrayList<Averia> averias, ArrayList<Reparacion> reparaciones,
			HashMap<Especialista, ArrayList<Reparacion>> reparacionesPorEspecialista) {
		super();
		this.especialistas = new ArrayList<Especialista>();
		for (Especialista especialista : especialistas) {
			this.especialistas.add(especialista);
		}
		this.averias = new ArrayList<Averia>();
		for (Averia averia : averias) {
			this.averias.add(averia);
		}
		this.reparaciones = new ArrayList<Reparacion>();
		for (Reparacion reparacion : reparaciones) {
			this.reparaciones.add(reparacion);
		}
		// TAREA 2b
		this.reparacionesPorEspecialista = new HashMap<Especialista, ArrayList<Reparacion>>();
		for (Especialista e: reparacionesPorEspecialista.keySet()) {
			/*
			ArrayList<Reparacion> reparaciones1 = new ArrayList<Reparacion>();
			for (Reparacion rep: reparacionesPorEspecialista.get(e)) {
				reparaciones1.add(rep);
			}
			this.reparacionesPorEspecialista.put(e, reparaciones1);
			*/
			
			//Así meto el ArrayList directamente
			reparacionesPorEspecialista.put(e, reparacionesPorEspecialista.get(e));
		}
		recargarMapa();
	}
	
	public DeustoRepara() {
		super();
		this.especialistas = new ArrayList<Especialista>();
		this.averias = new ArrayList<Averia>();
		this.reparaciones = new ArrayList<Reparacion>();
	}

	public ArrayList<Especialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(ArrayList<Especialista> especialistas) {
		this.especialistas = especialistas;
	}

	public ArrayList<Averia> getAverias() {
		return averias;
	}

	public void setAverias(ArrayList<Averia> averias) {
		this.averias = averias;
	}
	
	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(ArrayList<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}

	@Override
	public String toString() {
		return "DeustoRepara [especialistas=" + especialistas + ", averias=" + averias + ", reparaciones="
				+ reparaciones + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averias == null) ? 0 : averias.hashCode());
		result = prime * result + ((especialistas == null) ? 0 : especialistas.hashCode());
		result = prime * result + ((reparaciones == null) ? 0 : reparaciones.hashCode());
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
		DeustoRepara other = (DeustoRepara) obj;
		if (averias == null) {
			if (other.averias != null)
				return false;
		} else if (!averias.equals(other.averias))
			return false;
		if (especialistas == null) {
			if (other.especialistas != null)
				return false;
		} else if (!especialistas.equals(other.especialistas))
			return false;
		if (reparaciones == null) {
			if (other.reparaciones != null)
				return false;
		} else if (!reparaciones.equals(other.reparaciones))
			return false;
		return true;
	}

	// TAREA 1b
	public void cargaAverias(String fichero) {
		/*
		 * Formato del fichero:
		 * reparable;propiedades
		 * PC;Aspire;Acer;Intel i5;16;512;
		 * Movil;iPhone 14;Apple;A16;6.7;
		 * TV;Smart TV P1;Xiaomi;55;
		 * Software;Windows;11;
		 */
		try {
			Scanner sc = new Scanner(new File(fichero));
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				Averia averia = new Averia();
				Reparable reparable;
				if (campos[0].equals("PC")) {
					reparable = new PC();
					((Producto) reparable).setModelo(campos[1]);
					((Producto) reparable).setMarca(campos[2]);
					((PC) reparable).setProcesador(campos[3]);
					((PC) reparable).setRam(Integer.parseInt(campos[4]));
					((PC) reparable).setDisco(Integer.parseInt(campos[5]));
				} else if (campos[0].equals("Movil")) {
					reparable = new Movil();
					((Producto) reparable).setModelo(campos[1]);
					((Producto) reparable).setMarca(campos[2]);
					((Movil) reparable).setProcesador(campos[3]);
					((Movil) reparable).setPulgadas(Double.parseDouble(campos[4]));
				} else if (campos[0].equals("TV")){
					reparable = new TV();
					((Producto) reparable).setModelo(campos[1]);
					((Producto) reparable).setMarca(campos[2]);
					((TV) reparable).setPulgadas(Double.parseDouble(campos[3]));
				} 
				else {
					reparable = new Software();
					((Software) reparable).setNombre(campos[1]);
					((Software) reparable).setVersion(Double.parseDouble(campos[2]));
				}
				averia.setReparable(reparable);
				this.averias.add(averia);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error al cargar averías");
		}
	}

	// TAREA 2b
	public void recargarMapa() {
		// TODO Codificar tarea 2b
		this.reparacionesPorEspecialista.clear();
		for (Reparacion rep: reparaciones) {
			Especialista esp = rep.getEspecialista();
			if (!reparacionesPorEspecialista.containsKey(esp)) {
				reparacionesPorEspecialista.put(esp, new ArrayList<Reparacion>());
			}
			reparacionesPorEspecialista.get(esp).add(rep); 
		} 
	}
	
	// TAREA 2c
	public Especialista especialistaDelMes() {
		// TODO Codificar tarea 2c
		double mayor = 0;
		Especialista aReturn = null;
		for (Especialista especialista: reparacionesPorEspecialista.keySet()) {
			double totalPorEspecialista = 0;
			for (Reparacion reparacion: reparacionesPorEspecialista.get(especialista)) {
				totalPorEspecialista += reparacion.getPresupuesto();
			}
			if (totalPorEspecialista > mayor) {
				mayor = totalPorEspecialista;
				aReturn = especialista;
			}
		}
		
		return aReturn;
	}
	
	// TAREA 2d
	public void actualizarBonificaciones() {
		// TODO Codificar tarea 2d
		for (Especialista esp: reparacionesPorEspecialista.keySet()) {
			double presupuesto = 0.0;
			for (Reparacion rep: reparacionesPorEspecialista.get(esp)) {
				presupuesto += rep.getPresupuesto();
			}
			esp.setBonificacion(presupuesto*0.2);
		}
	}
	
	// Tarea 3a
	public void guardarDatos() {
		// TODO Codificar tarea 3a
		String filename = JOptionPane.showInputDialog("Itroduce el nombre del fichero","deustorepara.dat");
		File f = new File(filename);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(especialistas);
			oos.writeObject(averias);
			oos.writeObject(reparaciones);
			
		}
		catch (Exception ex) {
			System.err.println("Error al cargar los datos");
			ex.printStackTrace();
		}
		finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Tarea 3b
	public void cargarDatos() throws NoFile {
		// TODO Codificar tarea 3b
		String filename = JOptionPane.showInputDialog("Introduce the File name");
		File f = new File(filename);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			if (!f.exists()) {
				throw new NoFile();
			}
			
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			
			this.especialistas = (ArrayList<Especialista>) ois.readObject();
			this.averias = (ArrayList<Averia>) ois.readObject();
			this.reparaciones = (ArrayList<Reparacion>) ois.readObject();
			recargarMapa(); 
			
			
		}
		catch(Exception e) {
			System.err.println("Error al cargar los datos");
			e.getMessage();
		}
		finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	public static void main(String[] args) {
		DeustoRepara d = new DeustoRepara();
		d.cargaAverias("averias.csv");
		for (Averia averia: d.getAverias()) {
			System.out.println(averia);
		}
	}
	*/
}
