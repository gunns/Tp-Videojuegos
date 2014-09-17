package com.uqbar.arkanoid;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;

public class Pelotita extends GameComponent<ArkanoidScene>{


	private boolean playState = true;
	private double velocity;
	private Barrita barrita;
	public boolean estaCreciendoX;
	public boolean isEstaCreciendoX() {
		return estaCreciendoX;
	}
	public void setEstaCreciendoX(boolean estaCreciendoX) {
		this.estaCreciendoX = estaCreciendoX;
	}
	public boolean isEstaCreciendoY() {
		return estaCreciendoY;
	}
	public void setEstaCreciendoY(boolean estaCreciendoY) {
		this.estaCreciendoY = estaCreciendoY;
	}
	public boolean estaCreciendoY;

	public Pelotita(double velocity){
		this.setAppearance(new Circle(Color.black,15));
		this.setX(400);
		this.setY(550);
		this.playState=true;
		this.velocity=velocity;
		this.estaCreciendoX=true;
		this.estaCreciendoY=false;
	}
	@Override
	public void update(DeltaState deltaState) {

		if (this.getScene().getPlayState()) {
			if (deltaState.isKeyPressed(Key.A)) {
				this.getScene().stop();
			}else {
				mover(deltaState);
			}
			if(this.getScene().hayColision()){
				this.estaCreciendoY=false;
			}

		} else if (deltaState.isKeyPressed(Key.S)) {
			this.getScene().newGame(250);
		}else if(deltaState.isKeyPressed(Key.A)){
			this.getScene().continueGame(250);
		}
	}
	private void mover(DeltaState deltaState) {
		if (this.estaCreciendoX){
			this.setX(this.getX()+this.getScene().getVelocity()* deltaState.getDelta());
			if(this.getX()>=800-this.getAppearance().getWidth()){
				this.estaCreciendoX=false;
			}
		}
		if(!this.estaCreciendoX){
			this.setX(this.getX()-this.getScene().getVelocity()* deltaState.getDelta());
			if(this.getX()<=0){
				this.estaCreciendoX=true;
			}
		}
		if (this.estaCreciendoY){
			this.setY(this.getY()+this.getScene().getVelocity()* deltaState.getDelta());
		}
		
		if(!this.estaCreciendoY){
			this.setY(this.getY()-this.getScene().getVelocity()* deltaState.getDelta());
			if(this.getY()<=0){
				this.estaCreciendoY=true;
			}
		}
	}

}
