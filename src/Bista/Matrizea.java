package Bista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Eredua.MatrizeKudeatzailea;

public class Matrizea extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JLabel lblLerroak;
	private JLabel lblZutabeak;
	private JPanel panel;
	
	public Matrizea() {
		setBounds(100, 100, 650, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getLblLerroak(), BorderLayout.WEST);
		getContentPane().add(getLblZutabeak(), BorderLayout.NORTH);
		getContentPane().add(getPanel(100), BorderLayout.CENTER);
		MatrizeKudeatzailea.getEma().addObserver(this);
	}
	
	public void matrizeaSortu() {
		for (int i=0; i<60; i++) {
			for (int j=0; j<100; j++) {
				panel.add(getLabelN(i,j));
			}
		}
	}
	
	public void update(java.util.Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	private JLabel getLblLerroak() {
		if (lblLerroak == null) {
			lblLerroak = new JLabel("");
		}
		return lblLerroak;
	}
	
	private JLabel getLblZutabeak() {
		if (lblZutabeak == null) {
			lblZutabeak = new JLabel("");
			lblZutabeak.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblZutabeak;
	}
	private JPanel getPanel(int tamaina) {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(tamaina, tamaina, 0, 0));
		}
		return panel;
	}
	private JLabel getLabelN(int ler, int zut) {
		JLabel labelN=null;
		if (labelN == null) {
			labelN = new JLabel("");
			labelN.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return labelN;
	}
}
