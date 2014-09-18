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
	private boolean playState = false;
	private double velocity;
	private Barrita barrita;
	private boolean systemPause = false;
	
	public ArkanoidScene(Dimension dim, double velocity){
		super();
		this.velocity=velocity;
		this.gameDimension= dim;
		this.buildBackground(Color.white);
		this.barrita=new Barrita(this.gameDimension, playState);
		this.pelotita= new Pelotita(this.gameDimension);
		pelotita.newGamePos(barrita);
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

	
	protected boolean getPlayState() {
		return this.playState;
	}
	protected void setPlayState(boolean playState){
		this.playState = playState;
	}
	protected boolean getSystemPause() {
		return this.systemPause;
	}
	protected void setSystemPause(boolean SysPause){
		this.systemPause = SysPause;
	}
	protected double getVelocity() {
		return this.velocity;
	}
	protected Pelotita getPelotita(){
		return this.pelotita;
	}
	
	protected void youLose(){
//		Throw message here if someone lose
		resetBall();
	}
	
	private void resetBall() {
		newGame(250);
		setSystemPause(false);		
	}


	void newGame(double velocity) {
		this.buildBackground(Color.white);
		this.barrita.setX(800/2-this.barrita.getAppearance().getWidth());
		this.barrita.setY(600-(this.barrita.getAppearance().getHeight())-5);
		this.pelotita.newGamePos(barrita);
		this.velocity = velocity;
		this.playState = false;
		
	}
	void continueGame(double velocity){
		this.buildBackground(Color.white);
		this.velocity=velocity;
		this.playState = true;
	}
	protected void stop() {
		this.velocity= 0d;
		this.playState=false;
		
	}
	
	public boolean hayColision(){
		return ( this.pelotita.getX()-(this.pelotita.getAppearance().getWidth())>=this.barrita.getX()-(this.barrita.getAppearance().getWidth()/2)
				&& this.pelotita.getX()-(this.pelotita.getAppearance().getWidth())<=this.barrita.getX()+(this.barrita.getAppearance().getWidth()/2)
				&& this.pelotita.getY()+(this.pelotita.getAppearance().getHeight()/2)
				>this.barrita.getY())//-(this.barrita.getAppearance().getHeight()/2))
				&& this.pelotita.getY()+(this.pelotita.getAppearance().getHeight()/2)
				<this.barrita.getY()+(this.barrita.getAppearance().getHeight()/2);
	}
}
