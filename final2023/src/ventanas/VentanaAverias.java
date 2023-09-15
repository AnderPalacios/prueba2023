package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import deustorepara.Averia;
import deustorepara.DeustoRepara;

public class VentanaAverias extends JFrame {
	private static final long serialVersionUID = 1L;
	protected DeustoRepara datos;
	protected DefaultListModel<Averia> modeloAverias;
	protected JList<Averia> listaAverias;
	protected JButton botonCargar;
	
	public void actualizarLista() {
		modeloAverias.clear();
		for(Averia averia : datos.getAverias()) {
			modeloAverias.addElement(averia);
		}
	}
	
	public VentanaAverias(DeustoRepara datos) {
		this.datos = datos;
		
		modeloAverias = new DefaultListModel<Averia>();
		listaAverias = new JList<Averia>(modeloAverias);
		JScrollPane scrollAverias = new JScrollPane(listaAverias);
		
		this.add(scrollAverias, BorderLayout.CENTER);
		
		JPanel panelBoton = new JPanel();
		
		botonCargar = new JButton("Cargar averías");
		
		botonCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datos.cargaAverias("averias.csv");	
				actualizarLista();
			}
		});
		
		panelBoton.add(botonCargar);
		
		this.add(panelBoton, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Averías");
		this.setSize(850, 350);
		this.setVisible(false);
	}

}
