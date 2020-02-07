
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Tablero extends JPanel implements  KeyListener, ActionListener{
	private ImageIcon tituloImagen;
	// Linked List of snake in X, Y
	private Serpiente serpienteX = new Serpiente();
	private Serpiente serpienteY = new Serpiente();
	private int manzanaX;
	private int manzanaY;
	private int manzanas;
	
	
	//Directions the snake takes depending on the keys press
	private boolean izquierda=false;
	private boolean derecha =false;
	private boolean arriba=false;
	private boolean abajo=false;
	
	//snake head and body 
	private ImageIcon cabeza;
	private ImageIcon cuerpo;
	
	//apple
	private ImageIcon manzana;
	
	// timer to stop the game and to start depending if you loose 
	private Timer tiempo;
	private int delay= 100;
	private int movimientos=0;
	
	// constructur
	public Tablero() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		// we create an apple in the board
		crearManzana();
		tiempo = new Timer(delay,this); 
		tiempo.start();
		manzanas = 0;
		
	}
	// fuction to make the build the snake when you eat an apple
	public void crecerSerpiente() {
		nodoSerpiente current = serpienteX.cabeza();
		nodoSerpiente currentY = serpienteY.cabeza();
		// depending of the move 
		if(derecha) {//right
			if(current.pos > 800) {
				serpienteX.insertFirst(25);
			}else {
				serpienteX.insertFirst(serpienteX.cabeza().pos+25);
			}
			serpienteY.insertFirst(serpienteY.cabeza().pos);
		}

		if(izquierda) {//left
			if(current.pos < 50) {
				serpienteX.insertFirst(825);
			}else {
				serpienteX.insertFirst(serpienteX.cabeza().pos-25);
			}
			serpienteY.insertFirst(serpienteY.cabeza().pos);
		}
		
		if(arriba) {//up
			serpienteX.insertFirst(serpienteX.cabeza().pos);
			if(currentY.pos < 100) {
				serpienteY.insertFirst(625);
			}else {
				serpienteY.insertFirst(serpienteY.cabeza().pos-25);
			}
		}
		if(abajo) {//down
			serpienteX.insertFirst(serpienteX.cabeza().pos);
			if(currentY.pos > 600) {
				serpienteY.insertFirst(75);
			}else {
				serpienteY.insertFirst(serpienteY.cabeza().pos+25);
			}
		}
	}
	// fuction to paint
	public void paint(Graphics g) {
		// if the game just starts
		if(movimientos==0) {
			// add to the snake in x the positions
			this.serpienteX.insertAtLast(100);
			this.serpienteX.insertAtLast(75);
			this.serpienteX.insertAtLast(50);
			
			// add in the snake y the positions
			this.serpienteY.insertAtLast(100);
			this.serpienteY.insertAtLast(100);
			this.serpienteY.insertAtLast(100);
		}
		// increment the movements to indicate that the game alredy started
		movimientos ++;
		//set the mini bord
		g.setColor(Color.orange);
		g.drawRect(0, 0, 905, 700);
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		// create the image of the banner
		tituloImagen = new ImageIcon("snaketitle.jpg");
		tituloImagen.paintIcon(this, g, 25, 11);
		// board of the mini bord
		g.setColor(Color.white);
		g.drawRect(24,74,851, 577);
		// backgorund of the board
		g.setColor(Color.gray);
		g.fillRect(25,75,850,575);
		// drawing the head of the snake
		nodoSerpiente current1 = this.serpienteX.cabeza();
		nodoSerpiente current2 = this.serpienteY.cabeza();
		// depending on the movement of the snake we add the different heads
		for(int i=0; i<this.serpienteX.size(); i++) {
			if(i==0) {
				cabeza= new ImageIcon("cabeza.png");
				if (derecha) {
					cabeza= new ImageIcon("cabeza.png");
				}else if(izquierda) {
					cabeza= new ImageIcon("cabezaIzquierda.png");
				}else if(abajo) {
					cabeza= new ImageIcon("cabezaAbajo.png");
				}else if(arriba){
					cabeza = new ImageIcon("cabezaArriba.png");
				}
				
				cabeza.paintIcon(this, g, current1.pos, current2.pos);
				current1 = current1.next;
				current2 = current2.next;
			}
			if(i!=0) {
				cuerpo= new ImageIcon("cuerpo.png");
				cuerpo.paintIcon(this, g, current1.pos, current2.pos);
				current1 = current1.next;
				current2 = current2.next;
			}
		}
		//draw the apple
		manzana=new ImageIcon("comida.png");
		manzana.paintIcon(this, g, manzanaX, manzanaY);
		
		// search for collions with the snake and the apple
		if(this.serpienteX.cabeza().pos<=manzanaX+12 && this.serpienteX.cabeza().pos>=manzanaX-12 && 
				this.serpienteY.cabeza().pos <= manzanaY+12 && this.serpienteY.cabeza().pos >= manzanaY-12) {
			crearManzana();
			crecerSerpiente();
			manzanas++; //score 
		}
		
		//search for collions with the snake
		nodoSerpiente currentX = serpienteX.cabeza().next;
		nodoSerpiente currentY = serpienteY.cabeza().next;
		for(int i=0; i<this.serpienteX.size()-1;i++) {
			if(serpienteX.cabeza().pos == currentX.pos && serpienteY.cabeza().pos == currentY.pos){
				//GAME OVER
				tiempo.stop();
				JOptionPane.showMessageDialog(null, "GAME OVER \n Score: "+ manzanas);
				movimientos=0;
				crearManzana();
				tiempo = new Timer(delay,this);
				tiempo.start();
				manzanas = 0;
				serpienteX = new Serpiente();
				serpienteY = new Serpiente();
				izquierda=false;
				derecha =false;
				arriba=false;
				abajo=false;
			}
			currentX = currentX.next;
			currentY = currentY.next;
		}
	}
	// fuction of the movement of the snake
	public void moverSerpiente() {
		nodoSerpiente current = serpienteX.cabeza();
		nodoSerpiente currentY = serpienteY.cabeza();
		if(derecha) { //right
			serpienteX.removeLast();
			serpienteY.removeLast();
			if(current.pos > 800) {
				serpienteX.insertFirst(25);
			}else {
				serpienteX.insertFirst(serpienteX.cabeza().pos+25);
			}
			serpienteY.insertFirst(serpienteY.cabeza().pos);
		}

		if(izquierda) {//left
			serpienteX.removeLast();
			serpienteY.removeLast();
			if(current.pos < 50) {
				serpienteX.insertFirst(825);
			}else {
				serpienteX.insertFirst(serpienteX.cabeza().pos-25);
			}
			serpienteY.insertFirst(serpienteY.cabeza().pos);
		}
		
		if(arriba) {//up
			serpienteX.removeLast();
			serpienteY.removeLast();
			serpienteX.insertFirst(serpienteX.cabeza().pos);
			if(currentY.pos < 100) {
				serpienteY.insertFirst(625);
			}else {
				serpienteY.insertFirst(serpienteY.cabeza().pos-25);
			}
		}
		if(abajo) {//down
			serpienteX.removeLast();
			serpienteY.removeLast();
			serpienteX.insertFirst(serpienteX.cabeza().pos);
			if(currentY.pos > 600) {
				serpienteY.insertFirst(75);
			}else {
				serpienteY.insertFirst(serpienteY.cabeza().pos+25);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		tiempo.start();
		moverSerpiente();
		this.repaint();
	}
	// fuction to  create the random positions of the apple
	public void crearManzana() {
		Random r = new Random();
		manzanaX = (r.nextInt(28)*25)+100; //random  positions between 100 y 800 and multiples of 25
		manzanaY = (r.nextInt(20)*25)+100; //random positions between 100 y 600 and multiples de 25
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// press right key
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			movimientos++;
			derecha=true;
			if(!izquierda) {
				derecha=true;
			}else {
				derecha=false;
				izquierda=true;
			}
			arriba=false;
			abajo=false;
		}
		//  press left key
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			movimientos++;
			izquierda=true;
			if(!derecha) {
				izquierda=true;
			}else {
				izquierda=false;
				derecha=true;
			}
			arriba=false;
			abajo=false;
		}
		// press up key
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			movimientos++;
			arriba=true;
			if(!abajo) {
				arriba=true;
			}else {
				arriba=false;
				abajo=true;
			}
			derecha=false;
			izquierda=false;
		}
		// press down key
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			movimientos++;
			abajo=true;
			if(!arriba) {
				abajo=true;
			}else {
				abajo=false;
				arriba=true;
			}
			derecha=false;
			izquierda=false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
