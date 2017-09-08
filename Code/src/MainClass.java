import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class MainClass extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton enviar,verificar;
	private JLabel dados;
	private JTextField var1,var2,var,inferencia;
	private Background construtorImage = null;
	JRadioButton atomo,sentenca,analise;
	private static int x,y;
	private static List<String> baseDados = new ArrayList<String>();
	private static final String[] opcoes = {"  v","  ^","  - >"};
	private JComboBox tipo = new JComboBox(opcoes);
	private static final String[] op = {"   ","  ~"};
	private static final String[] option = {"   ","  ~","  ~~"};
	private JComboBox tipo0 = new JComboBox(op);
	private JComboBox tipo2 = new JComboBox(op);
	private JComboBox tipo00 = new JComboBox(option);
	
	public MainClass(int resp){
		super("SISTEMA DE INFERÊNCIA");
		ImageIcon icone = new ImageIcon("icone.png");
		setIconImage(icone.getImage());
		construtorImage = new Background("capa.jpg");
		construtorImage.setSize(350,650);
		panel = new JPanel();
		panel.setLayout(null);

		x = 50;
		y = 30;
		baseDados = BaseDeDados.getBase();
		if(!baseDados.isEmpty()){
			for(int i=0;i<baseDados.size();i++){
				dados = new JLabel();
				dados.setText(baseDados.get(i));
				dados.setBounds(x, y, 250, 60);	
				panel.add(dados);
				y = y + 20;
			}
		}
				
		atomo = new JRadioButton("<html><font color = #A50000>Átomo</font></html>", true);
		atomo.setFont(new Font("Goudy Stout", Font.BOLD,13));
		atomo.setBounds(20, 350, 135, 40);
		atomo.setOpaque(false);
		sentenca = new JRadioButton("<html><font color = #A50000>Sentença</font></html>", false);
		sentenca.setFont(new Font("Goudy Stout", Font.BOLD,13));
		sentenca.setBounds(20, 400, 170, 40);
		sentenca.setOpaque(false);
		analise = new JRadioButton("<html><font color = #A50000>Inferência</font></html>", false);
		analise.setFont(new Font("Goudy Stout", Font.BOLD,13));
		analise.setBounds(20, 450, 202, 40);
		analise.setOpaque(false);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(atomo);
		group1.add(sentenca);
		group1.add(analise);
		GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.gridx = 0;
		constraints1.gridx = 1;

		tipo00.setBounds(140, 355, 50, 30);
		tipo00.setBackground(Color.white);
		panel.add(tipo00);
		
		var = new JTextField();
		var.setBounds(195, 355, 30, 30);
		panel.add(var);

		tipo0.setBounds(170, 405, 40, 30);
		tipo0.setBackground(Color.white);
		panel.add(tipo0);
		
		var1 = new JTextField();
		var1.setBounds(210, 405, 22, 31);
		panel.add(var1);

		tipo.setBounds(231, 405, 50, 30);
		tipo.setBackground(Color.white);
		panel.add(tipo);

		tipo2.setBounds(281, 405, 40, 30);
		tipo2.setBackground(Color.white);
		panel.add(tipo2);
		
		var2 = new JTextField();
		var2.setBounds(321, 405, 22, 31);
		panel.add(var2);
		
		inferencia = new JTextField();
		inferencia.setBounds(207, 455, 30, 30);
		panel.add(inferencia);
		
		enviar = new JButton(new ImageIcon("adicionar.png"));
		enviar.setBounds(35, 500, 120, 30);
		enviar.setBackground(Color.white);
		panel.add(enviar);
		EventoAdicionar handler1 = new EventoAdicionar();
		enviar.addActionListener(handler1);
		
		verificar = new JButton(new ImageIcon("resultado.png"));
		verificar.setBounds(180, 500, 120, 30);
		verificar.setBackground(Color.white);
		panel.add(verificar);
		EventoVerificar handler2 = new EventoVerificar();
		verificar.addActionListener(handler2);
	
		if(resp == 0){
			
			ImageIcon veredito = new ImageIcon("false.png");
			JLabel label3 = new JLabel(veredito);
			label3.setBounds(35, 545, 265, 40);
			panel.add(label3);
			
		}else if(resp == 1){
			
			ImageIcon veredito = new ImageIcon("true.png");
			JLabel label4 = new JLabel(veredito);
			label4.setBounds(35, 545, 265, 40);
			panel.add(label4);			
		}
		
		panel.add(analise);
		panel.add(atomo, constraints1);
		panel.add(sentenca, constraints1);
		panel.add(construtorImage);

		this.setContentPane(panel);
		this.setSize(365,640);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private class EventoAdicionar implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == enviar) {
				if(atomo.isSelected()==true){
					String atom = null;
					if(var.getText().charAt(0)<65 || var.getText().charAt(0)>90){
						JOptionPane.showMessageDialog(null,"OPÇÂO NÂO SUPORTADA", "ERRO",JOptionPane.ERROR_MESSAGE);
					}else if(tipo00.getSelectedIndex()==0){
						BaseDeDados.addAtomo(var.getText().charAt(0));
						BaseDeDados.addBase(var.getText());
						MainClass m = new MainClass(2); 
						MainClass.this.dispose();
					}else if(tipo00.getSelectedIndex()==1){
						atom = "~" + var.getText().charAt(0);
						BaseDeDados.addBase(atom);
						BaseDeDados.addNegacao(var.getText().charAt(0));
						MainClass m = new MainClass(2); 
						MainClass.this.dispose();
					}else{
						atom = "~~" + var.getText();	
						BaseDeDados.addAtomo(var.getText().charAt(0));
						BaseDeDados.addBase(atom);						
						MainClass m = new MainClass(2); 
						MainClass.this.dispose();
					}
				}else if(sentenca.isSelected()==true){
					if(var1.getText().charAt(0)<65 || var1.getText().charAt(0)>90 || var2.getText().charAt(0)<65 && var2.getText().charAt(0)>90){
						JOptionPane.showMessageDialog(null,"OPÇÂO NÂO SUPORTADA", "ERRO",JOptionPane.ERROR_MESSAGE);						
					}else{
						String sent = "";
						boolean s1 = false,s2=false;
						char v1,v2;
						switch(tipo0.getSelectedIndex()){
						case 0:
							s1 = true;
							break;
						case 1:
							s1 = false;
							sent = "~";
							break;
						}
						sent += var1.getText().charAt(0);
						v1 = var1.getText().charAt(0);
						v2 = var2.getText().charAt(0);
						switch(tipo.getSelectedIndex()){
						case 0:
							sent+=" v ";
							break;
						case 1:
							sent+=" ^ ";
							break;
						case 2:
							sent+=" - > ";
							break;							
						}
						switch(tipo2.getSelectedIndex()){
						case 0:
							s2 = true;
							break;
						case 1:
							s2 = false;
							sent+="~";
							break;
						}
						sent += var2.getText().charAt(0);
						switch(tipo.getSelectedIndex()){
						case 0:
							BaseDeDados.addConjuncao(v1, v2, s1, s2);
							break;
						case 1:
							if(tipo0.getSelectedIndex()==0) BaseDeDados.addAtomo(v1);
							else BaseDeDados.addNegacao(v1);
							if(tipo2.getSelectedIndex()==0) BaseDeDados.addAtomo(v2);
							else BaseDeDados.addNegacao(v2);
							break;
						case 2:
							if(s1 == true) BaseDeDados.addConjuncao(v1, v2, false, s2);
							else BaseDeDados.addConjuncao(v1, v2, true, s2);
							break;							
						}
						BaseDeDados.addBase(sent);
						MainClass m = new MainClass(2);
						MainClass.this.dispose();
					}
				}
			}
		}
	}
	private class EventoVerificar implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == verificar) {
				if(analise.isSelected()==true){
					if(inferencia.getText().charAt(0)<65 || inferencia.getText().charAt(0)>90){
						JOptionPane.showMessageDialog(null, "ERRO","OPÇÂO NÂO SUPORTADA",JOptionPane.ERROR_MESSAGE);						
					}else{
						boolean result;
						result = BaseDeDados.verificarDado(inferencia.getText().charAt(0));
						if(result){
							MainClass m = new MainClass(1);
						}else{
							MainClass m = new MainClass(0);
						}
						MainClass.this.dispose();
					}
				}
			}
		}
	}
}
