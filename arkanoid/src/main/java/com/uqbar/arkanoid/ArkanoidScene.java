package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Graphics2D;

//import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
//import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
//import com.uqbar.vainilla.events.constants.Key;

public class ArkanoidScene extends GameScene {

	private GameComponent<GameScene> backGround;
	private Dimension gameDimension;
	private Pelotita pelotita;
	private boolean playState = true;
	private double velocity;
	private Barrita barrita;
	
	public ArkanoidScene(Dimension dim, double velocity){
		super();
		this.velocity=velocity;
		this.buildBackground(Color.white);
		this.barrita=new Barrita(this.gameDimension);
		this.pelotita= new Pelotita(this.velocity);
		this.gameDimension= dim;
		this.addComponent(pelotita);
		this.addComponent(barrita);
	}


	private void buildBackground(Color color) {
		if (backGround != null) {
			this.removeComponent(this.backGround);
		}
		this.backGround = new GameComponent<GameScene>(new Rectangle(color,800,600),0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);
	}

	protected void stop() {
		this.velocity= 0d;
		this.playState=false;
		
	}
	protected boolean getPlayState() {
		return this.playState;
	}
	protected double getVelocity() {
		return this.velocity;
	}
	void newGame(double velocity) {
		this.buildBackground(Color.white);
		this.pelotita.setX(400);
		this.pelotita.setY(550);
		this.velocity=velocity;
		this.playState = true;
		this.pelotita.setEstaCreciendoY(false);
	}
	void continueGame(double velocity){
		this.buildBackground(Color.white);
		this.velocity=velocity;
		this.playState = true;
	}
	
	public boolean hayColision(){
		return ( this.pelotita.getX()>=this.barrita.getX()-(this.barrita.getAppearance().getWidth()/2)
				&& this.pelotita.getX()<=this.barrita.getX()+(this.barrita.getAppearance().getWidth()/2)
				&& this.pelotita.getY()//+(this.pelotita.getAppearance().getHeight()/2)
				>this.barrita.getY()-(this.barrita.getAppearance().getHeight()/2))
				&& this.pelotita.getY()+(this.pelotita.getAppearance().getHeight()/2)
				<this.barrita.getY()+(this.barrita.getAppearance().getHeight()/2);
	}
}
