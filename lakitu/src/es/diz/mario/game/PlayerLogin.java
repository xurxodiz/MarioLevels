package es.diz.mario.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	protected PlayerGame game;
	
	protected JLabel lbl;
	protected JTextField txt;
	protected JButton but;

	public PlayerLogin(PlayerGame game) {
		super(new GridBagLayout());		
		this.game = game;
		
		
		addLabel();
		addTextField();
		addButton();
		
		setSize(640,480);
		setVisible(true);
	}
	
	public void addLabel() {
		lbl = new JLabel("What's your name?");
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 1;
		cons.ipady = 50;
		cons.weightx = 0;
		add(lbl, cons);
	}
	
	public void addTextField() {
		txt = new JTextField(50);
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 2;
		cons.weightx = 0;
		cons.fill = GridBagConstraints.NONE;
		add(txt, cons);
		txt.requestFocus();
	}
	
	public void addButton() {
		JButton but = new JButton();
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_pressed(e);
			}
		});
		but.setText("Play!");
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 3;
		cons.weightx = 0;
		add(but, cons);
	}
	
	public void button_pressed(ActionEvent e) {
		game.setName(txt.getText());
	}
	
}
