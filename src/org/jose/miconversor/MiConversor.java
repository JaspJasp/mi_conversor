package org.jose.miconversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.Color;

public class MiConversor {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	
	public enum Moneda{
		pesos_dolar,
		pesos_euros,
		pesos_libras,
		dolar_pesos,
		euros_pesos,
		libras_pesos
	}
	
	public double dolar = 17.85;
	public double libra = 20.15;
	public double euro = 18.10;
	
	
	public double valorInput = 0.00;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 255, 255));
		frame.setBackground(new Color(0, 255, 255));
		frame.setBounds(100, 100, 450, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(10, 10, 96, 19);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 52, 96, 21);
		frame.getContentPane().add(cmb);
		
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(127, 52, 129, 21);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(127, 13, 85, 16);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir(){
		if (Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			switch (moneda) {
			case pesos_dolar:
				PesosAMoneda(dolar);
				break;
			case pesos_euros:
				PesosAMoneda(euro);
				break;
			case pesos_libras:
				PesosAMoneda(libra);
				break;
			case dolar_pesos:
				MonedaAPesos(dolar);
				break;
			case euros_pesos:
				MonedaAPesos(euro);
				break;
			case libras_pesos:
				MonedaAPesos(libra);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
		}
		
		}
		
	}
	
	public void PesosAMoneda(double moneda) {
		double resultado = valorInput / moneda;
		lbl.setText(Redondear(resultado));
	}
	
	public void MonedaAPesos(double moneda) {
		double resultado = valorInput * moneda;
		lbl.setText(Redondear(resultado));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.000");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto){
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("¡Sólo ingrese números!");
			return false;
		}
	}
}
