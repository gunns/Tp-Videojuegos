package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Ladrillo extends GameComponent<ArkanoidScene>{
	

	private Dimension gameDimension;
	
	
		public Ladrillo( Dimension dim, double coordX, double coordY, Color col){
			this.setAppearance(new Rectangle(col,50,25));
			this.gameDimension= dim;
			this.setX(coordX);// -this.getAppearance().getWidth());
			this.setY(coordY);// - this.getAppearance().getHeight());
		}
		
		public void update(DeltaState deltaState) {
			if (this.getScene().ladrilloSeRompe(this))
			{
				//this.destroy();
				this.getScene().removeComponent(this);
			}
		}
		
		
		
	

		
		

}