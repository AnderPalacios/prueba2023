package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import deustorepara.DeustoRepara;
import deustorepara.Especialista;

public class VentanaPlantilla extends JFrame {
	private static final long serialVersionUID = 1L;
	protected DeustoRepara datos;
	protected JButton botonAnadir;
	protected JButton botonBorrar;
	protected DefaultListModel<Especialista> modeloPlantilla;
	protected JList<Especialista> listaPlantilla;
	
	// TAREA 4 - Atributos
	protected JPanel pnlEspecialistas;
	
	protected JLabel lblNombre;
	protected JTextField txtNombre;
	
	protected JLabel lblPC;
	protected JTextField txtPc;
	
	protected JLabel lblMovil;
	protected JTextField txtMovil;
	
	protected JLabel lblTV;
	protected JTextField txtTV;
	
	protected JLabel lblBonificacion;
	protected JTextField txtBonificacion;
	
	
	protected JButton btnModificar;
	
	public void actualizarLista() {
		modeloPlantilla.clear();
		try {
			datos.getEspecialistas().sort(null);
		} catch (ClassCastException e) {}  // Ignora error de ordenación
		for (Especialista especialista : datos.getEspecialistas()) {
			modeloPlantilla.addElement(especialista);	
		}
	}

	public VentanaPlantilla(DeustoRepara datos) {
		this.datos = datos;
		
		JPanel panelBotones = new JPanel();
		
		botonAnadir = new JButton("Añadir");
		botonBorrar = new JButton("Borrar");
		
		// TAREA 4 - Componentes y contenedores adicionales
		pnlEspecialistas = new JPanel();
		pnlEspecialistas.setLayout(new GridLayout(5,2));
		
		lblNombre = new JLabel("Nombre:");
		pnlEspecialistas.add(lblNombre);
		txtNombre = new JTextField();
		pnlEspecialistas.add(txtNombre);
		
		lblPC = new JLabel("Conocimientos de PC:");
		pnlEspecialistas.add(lblPC);
		txtPc = new JTextField();
		pnlEspecialistas.add(txtPc);
		
		lblMovil = new JLabel("Conocimientos de movil: ");
		pnlEspecialistas.add(lblMovil);
		txtMovil = new JTextField();
		pnlEspecialistas.add(txtMovil);
		
		lblTV = new JLabel("Conocimientos de móvil");
		pnlEspecialistas.add(lblTV);
		txtTV = new JTextField();
		pnlEspecialistas.add(txtTV);
		
		lblBonificacion = new JLabel("Bonificación: ");
		pnlEspecialistas.add(lblBonificacion);
		txtBonificacion = new JTextField();
		pnlEspecialistas.add(txtBonificacion);
		
		
		
		this.add(pnlEspecialistas,BorderLayout.WEST);
		
		
		btnModificar = new JButton("Modificar");
	
		
		botonAnadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Especialista especialista = new Especialista("Especialista", (int)(Math.random()*5), (int)(Math.random()*5), (int)(Math.random()*5), Math.random());
				datos.getEspecialistas().add(especialista);
				actualizarLista();
			}
		});
		
		botonBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Especialista especialista = listaPlantilla.getSelectedValue();
				if (especialista != null) { 
					datos.getEspecialistas().remove(especialista);
					actualizarLista();
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado especialista", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelBotones.add(botonAnadir);
		panelBotones.add(botonBorrar);
		panelBotones.add(btnModificar);
			
		modeloPlantilla = new DefaultListModel<Especialista>();
		listaPlantilla = new JList<Especialista>(modeloPlantilla);
		JScrollPane scrollPlantilla = new JScrollPane(listaPlantilla);
		actualizarLista();
		
		this.add(scrollPlantilla, BorderLayout.CENTER);
		this.add(panelBotones, BorderLayout.SOUTH);
		
		// TAREA 4 - Eventos
		listaPlantilla.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				txtNombre.setText(listaPlantilla.getSelectedValue().getNombre());
				txtPc.setText(listaPlantilla.getSelectedValue().getReparaPC()+"");
				txtMovil.setText(listaPlantilla.getSelectedValue().getReparaMovil()+"");
				txtTV.setText(listaPlantilla.getSelectedValue().getReparaTV()+"");
				txtBonificacion.setText(listaPlantilla.getSelectedValue().getBonificacion()+"");
				
			}
		});
		
		
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (listaPlantilla.getSelectedValue() == null) {
					System.err.println("Selecciona a un especialista");
				}
				else {
					listaPlantilla.getSelectedValue().setNombre(txtNombre.getText());
					listaPlantilla.getSelectedValue().setReparaPC(Integer.parseInt(txtPc.getText()));
					listaPlantilla.getSelectedValue().setReparaMovil(Integer.parseInt(txtMovil.getText()));
					listaPlantilla.getSelectedValue().setReparaTV(Integer.parseInt(txtTV.getText()));
					listaPlantilla.getSelectedValue().setBonificacion(Double.parseDouble(txtBonificacion.getText()));
				}
				
			}
		});
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Plantilla");
		this.setSize(850, 350);
		this.setVisible(false);
	}

	// TAREA 4 - Métodos (si los necesitas)
	
}
