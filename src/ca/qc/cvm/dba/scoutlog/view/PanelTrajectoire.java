package ca.qc.cvm.dba.scoutlog.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ca.qc.cvm.dba.scoutlog.app.Facade;
import ca.qc.cvm.dba.scoutlog.event.DeleteAllEvent;
import ca.qc.cvm.dba.scoutlog.view.FrameMain.Views;
import ca.qc.cvm.dba.scoutlog.view.util.BackgroundPanel;

public class PanelTrajectoire extends CommonPanel {

	private static final long serialVersionUID = 1L;
	private JLabel departLabel;
	private JComboBox<String>  departField;
	private JLabel arriveeLabel;
	private JComboBox<String> arriveeField;
	Vector<String> choices;

	private JLabel trajet;

	public PanelTrajectoire(int width, int height) throws Exception {
		super(width, height, true, "assets/images/background-data.jpg");
		
	}

	@Override
	protected void jbInit() throws Exception {
		choices = new Vector<String>();
	
		
		for (String planets : Facade.getInstance().getPlanetList()) {
			System.out.println("-"+planets);
			choices.addElement(planets);
			
		}

		departField = new JComboBox<String>(choices);
		departLabel = super.addLabel("Depart : ", 20, 120, 150, 30);
		super.addField(departField, 190, 120, 250, 30);
	    System.out.println(departField.getBounds());
	
		arriveeField = new JComboBox<String>(choices);
		arriveeLabel = super.addLabel("Arivee : ", 460, 120, 150, 30);
		super.addField(arriveeField, 570, 120, 250, 30);

		super.addLabel("Trajet: ", 20, 220, 90, 30);
		trajet = super.addLabel("", 100, 220, 680, 30);
		trajet.setHorizontalAlignment(JLabel.CENTER);
		
		departField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				  System.out.println("Selected: " + departField.getSelectedItem());
			        System.out.println(", Position: " + departField.getSelectedIndex());
			}
					
	});
		
		arriveeField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				  System.out.println("Selected: " + arriveeField.getSelectedItem());
			      System.out.println(", Position: " + arriveeField.getSelectedIndex());
			}
			
					
	});
	
		trajet.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		this.addButton("Calculer", 20, 510, 100, 20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> lstPlanetes = Facade.getInstance().getPlanetList();
				System.out.println("***");
				/*
				 ComboBoxModel model = departField.getModel();
	                int size = model.getSize();
	                for(int i=0;i<size;i++) {
	                    Object element = model.getElementAt(i);
	                    System.out.println("Element at " + i + " = " + element);
	                }
	                */
	                System.out.println(departField.getSelectedIndex());
				int positionLeft=lstPlanetes.indexOf(departField.getModel().getSelectedItem());
				int positionRight=lstPlanetes.indexOf(arriveeField.getModel().getSelectedItem());
				//lstPlanetes.subList(
				System.out.println(positionLeft+" "+positionRight);
				trajet.setText("");
			}
		});
		// Utilisez super.addField et super.addButton et etc pour cr�er votre interface
		// graphique
	}

	/**
	 * Cette m�thode est appel�e automatiquement � chaque fois qu'un panel est
	 * affich� (lorsqu'on arrive sur la page)
	 */
	@Override
	public void resetUI() {
		remplissage();
	}

	public void remplissage() {
		System.out.println("nettoyage");
		choices.clear();
		System.out.println(Facade.getInstance().getPlanetList());
		for (String planets : Facade.getInstance().getPlanetList()) {
			choices.addElement(planets);
		}

	}
}
