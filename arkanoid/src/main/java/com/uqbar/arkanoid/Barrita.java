package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Barrita extends GameComponent<ArkanoidScene>{

	public boolean estaCreciendoX;
	private Dimension gameDimension;
	private boolean playState = true;
	
	public Barrita(Dimension dim, boolean playState){
		
		
		
		this.setAppearance(new Rectangle(Color.black,80,10));
		this.gameDimension= dim;
		this.playState = playState;
		this.setX(this.gameDimension.getWidth()/2-this.getAppearance().getWidth());
		this.setY(this.gameDimension.getHeight()-(this.getAppearance().getHeight())-5);
	}
	
	protected boolean getPlayState() {
		return this.playState;
	}
	protected void setPlayState(boolean playState){
		this.playState = playState;
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
		if(!this.getScene().getSystemPause()){
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()- (this.getScene().getVelocity() + (this.getScene().getVelocity()/4) )* deltaState.getDelta());
					this.getScene().getPelotita().newGamePos(this);
				}
			}else{
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()-(this.getScene().getVelocity() + (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}
		}
	}

	private void moverALaDerecha(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlFinal()){
					this.setX(this.getX()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
					this.getScene().getPelotita().newGamePos(this);
				}
			}else{
				if (this.noLlegoAlFinal()){
					this.setX(this.getX()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}
		}
	}
	private boolean noLlegoAlFinal() {
		return this.getX()+this.getAppearance().getWidth()<=gameDimension.getWidth();
	}
	private boolean noLlegoAlComienzo(){
		return this.getX()>=0;
	}
	
}