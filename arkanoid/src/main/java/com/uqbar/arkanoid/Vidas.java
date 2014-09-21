package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Label;

public class Vidas extends GameComponent<ArkanoidScene> {
		Integer vida;
		Dimension gameDimension;
		
		
		
		public Vidas(Integer cant, Dimension dim){
			this.vida = cant;
			this.gameDimension = dim;
			//Font f = Font.TYPE1_FONT: Font.ITALIC;
			
			//Font font = new Font("Verdana", Font.BOLD, 12);
			Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
			Label label =new Label(font ,Color.blue ,(this.vida).toString());
	        //Label label = new JLabel ("New Image") ;
			//label.setFont(font);
			//label.setForeground(Color.Gray.darker());
			//this.setAppearance((new Circle(Color.black, 30)));
			this.setAppearance(new Label(font ,Color.red ,"puntos:" + (this.vida).toString()));
			
			this.setX(this.gameDimension.getWidth() -100);
			this.setY(20);
					//Font.ITALIC, Color.red, ((Integer)this.puntos).toString()));
			
		}
		
		
		public Integer getVida() {
			return vida;
		}


		public void setVida(Integer vida) {
			this.vida = vida;
		}


		@Override
		public void update(DeltaState deltaState) {
			Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
			this.setAppearance(new Label(font ,Color.blue ,"vidas " +(this.vida).toString()));
		
		}


		public void agregarVida() {
			// TODO Auto-generated method stub
			this.vida = vida + 1;
			//arkanoidScene.getPuntaje().setPuntos((arkanoidScene.getPuntaje().getPuntos() + this.valor));
		}
		
		
		

		
		

	}
