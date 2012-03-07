package es.diz.mario.game;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dk.itu.mario.engine.DataRecorder;

public class PlayerSurvey extends JPanel {

	private static final long serialVersionUID = 1L;
	protected DataRecorder recorder;
	protected PlayerGame game;
	
	protected JLabel lbl;
	protected JTextField txt;
	protected JButton butN;
	protected JButton butY;

	public PlayerSurvey(DataRecorder recorder, PlayerGame game) {
		super(new GridBagLayout());		
		this.game = game;
		
		addLabel();
		//addTextField();
		addButtonYes();
		addButtonNo();
	}
	
	public void show() {		
		setSize(640,480);
		setVisible(true);
	}
	
	public void addLabel() {
		String str = "Did you like this level more than the previous one?";
		lbl = new JLabel(str);
		Font newFont = new Font("Arial Rounded MT Bold",
				  lbl.getFont().getStyle(),
				  24);  

		lbl.setFont(newFont);
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 1;
		cons.insets = new Insets(20, 20, 20, 20);
		add(lbl, cons);
	}
	

	public void addButtonYes() {
		butY = new JButton();
		butY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_pressed(true);
			}
		});
		Font newFont = new Font("Arial Rounded MT Bold",
				  butY.getFont().getStyle(),
				  18);  

		butY.setFont(newFont);
		butY.setText("Yes !");
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 2;
		cons.ipadx = 50;
		cons.ipady = 30;
		cons.insets = new Insets(20, 20, 20, 20);
		add(butY, cons);
	}
	
	public void addButtonNo() {
		butN = new JButton();
		butN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_pressed(false);
			}
		});
		Font newFont = new Font("Arial Rounded MT Bold",
				  butN.getFont().getStyle(),
				  18);  

		butN.setFont(newFont);
		butN.setText("No !");
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 3;
		cons.ipadx = 50;
		cons.ipady = 30;
		cons.insets = new Insets(20, 20, 20, 20);
		add(butN, cons);
	}
	
	public void button_pressed(boolean choice) {
		if (choice)
			System.out.println("He liked it");
		else
			System.out.println("He liked it not");
		game.continuePlaying();
	}
	
}
