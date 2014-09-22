package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class Cartel extends GameComponent<ArkanoidScene>{
	
Dimension gameDimension;
	
	
	
	
	
	
	public Cartel(Dimension dim){
		this.gameDimension = dim;
		//Font f = Font.TYPE1_FONT: Font.ITALIC;
		
		//Font font = new Font("Verdana", Font.BOLD, 12);
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		Label label =new Label(font ,Color.green ,"      GANASTE!\n Presione P para Juego Nuevo");
        //Label label = new JLabel ("New Image") ;
		//label.setFont(font);
		//label.setForeground(Color.Gray.darker());
		//this.setAppearance((new Circle(Color.black, 30)));
		this.setAppearance(label);
		
		this.setX(dim.getHeight()/2);
		this.setY(dim.getWidth()/2);
				//Font.ITALIC, Color.red, ((Integer)this.puntos).toString()));
		
	}
	
	public Cartel(Dimension dim, Integer puntaje){
		this.gameDimension = dim;
		//Font f = Font.TYPE1_FONT: Font.ITALIC;
		
		//Font font = new Font("Verdana", Font.BOLD, 12);
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		String cadena = new String("              PERDISTE\n       tu puntaje: "+ (puntaje.toString()) + " \n Presione P para Juego Nuevo");
		Label label =new Label(font ,Color.magenta ,cadena); 
        //Label label = new JLabel ("New Image") ;
		//label.setFont(font);
		//label.setForeground(Color.Gray.darker());
		//this.setAppearance((new Circle(Color.black, 30)));
		this.setAppearance(label);
		
		this.setX(dim.getHeight()/2);
		this.setY(dim.getWidth()/2);
		
	}
	
	
	public void update(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.P)){
			//this.moverALaDerecha(deltaState);
			this.getScene().newGame();
			this.getScene().removeComponent(this);
		if(deltaState.isKeyPressed(Key.A)){
			this.getScene().setPlayState(false);
		}
	}
	
	
	}
}
