package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import deustorepara.DeustoRepara;
import deustorepara.Especialista;
import deustorepara.NoFile;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	protected DeustoRepara datos;
	protected JButton botonEspecialistas;
	protected JButton botonAverias;
	protected JButton botonEspecialistaMes;
	protected JButton botonRecalcular;
	
	protected JButton botonReparaciones;
	protected JLabel labelLogo;
	protected VentanaPlantilla ventanaPlantilla;
	protected VentanaAverias ventanaAverias;
	protected VentanaAsignaciones ventanaAsignaciones;
	
	public VentanaPrincipal() {
		this.datos = new DeustoRepara();
		this.ventanaPlantilla = new VentanaPlantilla(datos);
		this.ventanaAverias = new VentanaAverias(datos);
		this.ventanaAsignaciones = new VentanaAsignaciones(datos);
				
		JPanel panelBotonesVentanas = new JPanel();
		JPanel panelBotonesAcciones = new JPanel();
		
		botonEspecialistas = new JButton("Gestionar especialistas");
		botonAverias = new JButton("Ver averías");
		botonReparaciones = new JButton("Asignar reparaciones");
		botonEspecialistaMes = new JButton("Especialista del mes");
		botonRecalcular = new JButton("Recalcular bonificaciones");
		
		botonEspecialistas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaPlantilla.actualizarLista();
				ventanaPlantilla.setVisible(true);
			}
		});
		
		botonAverias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAverias.actualizarLista();
				ventanaAverias.setVisible(true);
			}
		});
		
		botonReparaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAsignaciones.actualizarListas();
				ventanaAsignaciones.setVisible(true);
			}
		});
		
		botonEspecialistaMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Especialista delMes = datos.especialistaDelMes();
				if (delMes != null) {
					JOptionPane.showMessageDialog(null, delMes.toString(), "Especialista del mes...", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		botonRecalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datos.actualizarBonificaciones();
				JOptionPane.showMessageDialog(null, "Bonificaciones actualizadas", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		panelBotonesVentanas.add(botonEspecialistas);
		panelBotonesVentanas.add(botonAverias);
		panelBotonesVentanas.add(botonReparaciones);
		panelBotonesAcciones.add(botonEspecialistaMes);
		panelBotonesAcciones.add(botonRecalcular);
		
		labelLogo = new JLabel(new ImageIcon("deustorepara.png"));
		
		this.add(panelBotonesVentanas, BorderLayout.NORTH);
		this.add(panelBotonesAcciones, BorderLayout.SOUTH);
		this.add(labelLogo, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("DeustoRepara");
		this.pack();
		this.setVisible(true);
		
		// TAREA 3c
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					datos.cargarDatos();
				} catch (NoFile e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				datos.guardarDatos();
				
			}
			
		});
	}
	
	public static void main(String[] args) {
		new VentanaPrincipal();
	}
}

