package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Label;

public class Puntaje extends GameComponent<ArkanoidScene> {
	Integer puntos;
	Dimension gameDimension;
	
	
	
	public Puntaje(Dimension dim){
		this.puntos = 0;
		this.gameDimension = dim;
		//Font f = Font.TYPE1_FONT: Font.ITALIC;
		
		//Font font = new Font("Verdana", Font.BOLD, 12);
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		Label label =new Label(font ,Color.green ,(this.puntos).toString());
        //Label label = new JLabel ("New Image") ;
		//label.setFont(font);
		//label.setForeground(Color.Gray.darker());
		//this.setAppearance((new Circle(Color.black, 30)));
		this.setAppearance(new Label(font ,Color.red ,"puntos:" + (this.puntos).toString()));
		
		this.setX(20);
		this.setY(20);
				//Font.ITALIC, Color.red, ((Integer)this.puntos).toString()));
		
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		this.setAppearance(new Label(font ,Color.green ,"score " +(this.puntos).toString()));
	
	}
	
	
	
	
	
	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	
	

}
