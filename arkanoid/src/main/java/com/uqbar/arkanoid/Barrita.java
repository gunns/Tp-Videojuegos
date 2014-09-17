package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Barrita extends GameComponent<ArkanoidScene>{

	public boolean estaCreciendoX;
	private double velocity;
	private Dimension gameDimension;
	
	public Barrita(Dimension dim){
		this.setAppearance(new Rectangle(Color.black,50,10));
		this.gameDimension= dim;
		this.setX(800/2-this.getAppearance().getWidth());
		this.velocity=300;
		this.setY(600-(this.getAppearance().getHeight())-5);
	}
	
	public void update(DeltaState deltaState) {
		if (deltaState.isKeyBeingHold(Key.RIGHT)){
			this.moverALaDerecha(deltaState);
		}
		else if(deltaState.isKeyBeingHold(Key.LEFT)){
			this.moverALaIzquierda(deltaState);
		}
	}

	private void moverALaIzquierda(DeltaState deltaState) {
		if (this.noLlegoAlComienzo()){
			this.setX(this.getX()-this.getScene().getVelocity()* deltaState.getDelta());
		}
	}

	private void moverALaDerecha(DeltaState deltaState) {
		if (this.noLlegoAlFinal()){
			this.setX(this.getX()+this.getScene().getVelocity()* deltaState.getDelta());
		}
		
		
	}
	private boolean noLlegoAlFinal() {
		return this.getX()+this.getAppearance().getWidth()<=800;
	}
	private boolean noLlegoAlComienzo(){
		return this.getX()>=0;
	}
	
}