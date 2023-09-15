 package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;

import deustorepara.*;

public class VentanaAsignaciones extends JFrame {
	private static final long serialVersionUID = 1L;
	protected DeustoRepara datos;
	protected JComboBox<Averia> comboAverias;
	protected JComboBox<Especialista> comboEspecialistas;
	protected JComboBox<Reparacion> comboReparaciones;
	protected JTextArea textoReparaciones;
	protected JButton botonAsignar;
	protected JButton botonAuto;
	protected JButton botonRealizar;
	
	public static boolean esApto(Especialista especialista, Averia averia) {
		Reparable reparable = averia.getReparable();
		int dificultad = reparable.getDificultad();
		
		if ( (reparable instanceof PC && especialista.getReparaPC() < dificultad) 
			 || (reparable instanceof Movil && especialista.getReparaMovil() < dificultad) 
			 || (reparable instanceof TV && especialista.getReparaTV() < dificultad) ) {
			return false;
		} else {
			return true;
		}
	}
	
	public void actualizarListas() {
		comboAverias.removeAllItems();
		for(Averia averia : datos.getAverias()) {
			comboAverias.addItem(averia);
		}
		comboEspecialistas.removeAllItems();
		for (Especialista especialista : datos.getEspecialistas()) {
			comboEspecialistas.addItem(especialista);	
		}
		comboReparaciones.removeAllItems();
		for (Reparacion reparacion : datos.getReparaciones()) {
			comboReparaciones.addItem(reparacion);
		}
	}
	
	public VentanaAsignaciones(DeustoRepara datos) {
		this.datos = datos;
		
		JPanel panelAsignaciones = new JPanel();
		panelAsignaciones.setLayout(new GridLayout(1,3));
		JPanel panelReparaciones = new JPanel();
		panelReparaciones.setLayout(new BorderLayout());
		
		comboAverias = new JComboBox<Averia>();
		panelAsignaciones.add(comboAverias, BorderLayout.NORTH);
		Border bordeAverias = BorderFactory.createTitledBorder("Averías pendientes...");
		comboAverias.setBorder(bordeAverias);
		
		comboEspecialistas = new JComboBox<Especialista>();
		panelAsignaciones.add(comboEspecialistas, BorderLayout.NORTH);
		Border bordeEspecialistas = BorderFactory.createTitledBorder("Especialistas...");
		comboEspecialistas.setBorder(bordeEspecialistas);

		comboReparaciones = new JComboBox<Reparacion>();
		panelReparaciones.add(comboReparaciones, BorderLayout.NORTH);
		Border bordeReparaciones = BorderFactory.createTitledBorder("Reparaciones pendientes...");
		panelReparaciones.setBorder(bordeReparaciones);
		
		textoReparaciones = new JTextArea(20, 10);
		panelReparaciones.add(textoReparaciones, BorderLayout.CENTER);
		
		botonAsignar = new JButton("Asignar");
		botonAuto = new JButton("Asignación automática");
		botonRealizar = new JButton("Realizar todas las reparaciones");
		
		botonAsignar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Averia averia = (Averia) comboAverias.getSelectedItem();
				Especialista especialista = (Especialista) comboEspecialistas.getSelectedItem();
				
				if (averia != null && especialista != null && esApto(especialista, averia)) {
					Reparacion nueva = new Reparacion();
					nueva.setAveria(averia);
					nueva.setEspecialista(especialista);
					nueva.setFecha(new Date());
					comboReparaciones.addItem(nueva);
					datos.getReparaciones().add(nueva);
					comboAverias.removeItem(averia);
					datos.getAverias().remove(averia);
				} else {
					JOptionPane.showMessageDialog(null, "No se ha podido realizar la asignación", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JPanel panelBotones = new JPanel();
		
		panelBotones.add(botonAsignar);
		panelBotones.add(botonAuto);
		panelBotones.add(botonRealizar);
		
		Border bordeAsignaciones = BorderFactory.createTitledBorder("Asignaciones...");
		panelBotones.setBorder(bordeAsignaciones);
		
		panelAsignaciones.add(panelBotones);
			
		this.add(panelAsignaciones, BorderLayout.NORTH);
		this.add(panelReparaciones, BorderLayout.CENTER);

		// TAREA 4d - evento
		botonAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Codificar tarea 4d
				for (Averia averia: datos.getAverias()) {
					for (Especialista especialista: datos.getEspecialistas()) {
						if (esApto(especialista, averia)) {
							Reparacion rep = new Reparacion();
							rep.setAveria(averia);
							rep.setEspecialista(especialista);
							rep.setFecha(null);
							comboReparaciones.addItem(rep);
							comboAverias.removeItem(averia);
							datos.getAverias().remove(averia);
							break;
						}
					}
				}
			}
		} );

		// TAREA 5a - Hilo 
		botonRealizar.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Codificar tarea 5a
				Thread hilo = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						botonRealizar.disable();
						for (Reparacion rep: datos.getReparaciones()) {
							textoReparaciones.setText("Se está prodeciendo su reparación");
							for (int i = 0; i < rep.getAveria().getReparable().getDificultad(); i++) {
								textoReparaciones.setText(i+"");;
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							datos.getReparaciones().remove(rep);
						}
						botonRealizar.enable();
					}
				});
				hilo.start();
			}
		});

		// TAREA 5b - Teclado
		textoReparaciones.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_L) {
					textoReparaciones.setText("");
				}
				
			}
		});
			
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Asignaciones");
		this.setSize(1000, 200);
		this.setVisible(false);
	}

}
