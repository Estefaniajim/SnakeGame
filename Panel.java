
import java.awt.Color;

import javax.swing.JFrame;

public class Panel {
	
	public static void main(String[] args) {
		JFrame panel= new JFrame();
		Tablero tablero= new Tablero();
		panel.setBackground(Color.ORANGE); //background
		panel.setBounds(10, 10, 905, 700); //sizes
		panel.setResizable(false); //the windown cannot be modify
		panel.setVisible(true);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //it closes
		panel.add(tablero); // adds the mini board
	}
}
