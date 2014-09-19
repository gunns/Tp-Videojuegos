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
	private Ladrillos ladrillos;
	private Ladrillos reconstruirLadrillos;
	private Ladrillo ladrilloEnColision;
	
	
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
		this.ladrillos = new Ladrillos(dim, 50, 25, 5);
		this.addComponents(ladrillos.getLadrillos());
		
		
		
		
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
		//this.destruirLadrillos();
		//this.reconstruirLadrillos();
		
	}
	
	
	/*
	INTENTO FALLIDO DE CONSTRUCCION Y RECONSTRUCCION DE LOS LADRILLOS
	
	public void destruirLadrillos(){
	this.removeComponents(this.ladrillos.getLadrillos());	
	}
	
	public void reconstruirLadrillos(){
		this.ladrillos = new Ladrillos(gameDimension, 50, 25, 5);
		this.addComponents(ladrillos.getLadrillos());
	}
	*/
	
	void continueGame(double velocity){
		//this.buildBackground(Color.white);
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
	
	public boolean ladrilloSeRompe(Ladrillo ladrillo) {
		return ( coordPelotitaEnX() >= xLeft(ladrillo)
				&& coordPelotitaEnX() <= xRight(ladrillo)
				&& (coordPelotitaEnY()
				>= yUp(ladrillo))//-(this.barrita.getAppearance().getHeight()/2))
				&& (coordPelotitaEnY()
				<= yDown(ladrillo)));
		
	}
	
	public boolean hayColisionConUnLadrillo(){
		boolean b = false;
		//boolean encontre = false;
		for(int n = 1;(n <  (this.ladrillos.getLadrillos().size())); n++)
			{
			if(this.ladrilloSeRompe(this.ladrillos.getLadrillos().get(n)))
				{
				//encontre = true;
				this.ladrilloEnColision = this.ladrillos.getLadrillos().get(n);
				this.ladrillos.getLadrillos().remove(this.ladrillos.getLadrillos().get(n));
				b = true;
				}
			}
		return b;
	}
	
	
	
	
	public boolean colisionIzquierda(Pelotita pelotita){
		return(
				modulo( coordPelotitaEnX() - xLeft(ladrilloEnColision))
				<=
				modulo( coordPelotitaEnY() - yUp(ladrilloEnColision))
				&&
				(modulo(coordPelotitaEnX() - xLeft(ladrilloEnColision))
				<=
				modulo( coordPelotitaEnY() - yDown(ladrilloEnColision)))
				&&
				(modulo(coordPelotitaEnX() - xLeft(ladrilloEnColision))
				<=
				modulo(coordPelotitaEnX() - xRight(ladrilloEnColision)))				
				);
		
		//return(((ladrilloEnColision.getY() + ladrilloEnColision.getAppearance().getHeight()) - (pelotita.getY() - pelotita.getAppearance().getHeight())) > ((ladrilloEnColision.getY()+ ladrilloEnColision.getAppearance().getHeight()) - (pelotita.getY())));
		//return((modulo(ladrilloEnColision.getY() - pelotita.getY())) >  (modulo((ladrilloEnColision.getY() + ladrilloEnColision.getAppearance().getHeight()) - pelotita.getY())) );
		//return((modulo(ladrilloEnColision.getY() - pelotita.getY())) <  (modulo((ladrilloEnColision.getY() + ladrilloEnColision.getAppearance().getHeight()) - pelotita.getY())) );
		 //ladrilloEnColision.getY() == (pelotita.getY() - pelotita.getAppearance().getHeight())
	}
	public boolean colisionSuperior(Pelotita pelotita){
		return(
				( modulo(coordPelotitaEnY() - yUp(this.ladrilloEnColision))
				<=
				  modulo(coordPelotitaEnY() - yDown(this.ladrilloEnColision)))
				&&
				(modulo (coordPelotitaEnY() - yUp(this.ladrilloEnColision))
				<=
				modulo (coordPelotitaEnX() - xLeft(this.ladrilloEnColision)))
				&&
				(modulo (coordPelotitaEnY() - yUp(this.ladrilloEnColision))
				<=
				modulo(coordPelotitaEnX() - xRight(this.ladrilloEnColision)))
				);
		
		
		//return((modulo(ladrilloEnColision.getY() - pelotita.getY())) <  (modulo((ladrilloEnColision.getY() + ladrilloEnColision.getAppearance().getHeight()) - pelotita.getY())) );
		//return (((ladrilloEnColision.getY() ) - (pelotita.getY() - pelotita.getAppearance().getHeight())) < (ladrilloEnColision.getY() - (pelotita.getY())));
				//( ladrilloEnColision.getY() == (pelotita.getY()));
	}
	
	public boolean colisionDerecha(Pelotita pelotita){
		return(
				(modulo( coordPelotitaEnX() - (xRight(this.ladrilloEnColision)))
				<=
				modulo( coordPelotitaEnY() - yDown(this.ladrilloEnColision)))
				&&
				(modulo( coordPelotitaEnX() - (xRight(this.ladrilloEnColision)))
				<=
				modulo( coordPelotitaEnX() - (xLeft(this.ladrilloEnColision))))
				&&
				(modulo(coordPelotitaEnX() - (xRight(this.ladrilloEnColision)))
				<=
				modulo(coordPelotitaEnY() - (yUp(this.ladrilloEnColision))))
				);
		//return((modulo(ladrilloEnColision.getX() - pelotita.getX())) <  (modulo((ladrilloEnColision.getX() + ladrilloEnColision.getAppearance().getWidth()) - pelotita.getX())) );
		//return (((ladrilloEnColision.getX() + ladrilloEnColision.getAppearance().getWidth()) - (pelotita.getX() - pelotita.getAppearance().getWidth())) < ((ladrilloEnColision.getX()+ ladrilloEnColision.getAppearance().getWidth()) - (pelotita.getX())));
	}
	public boolean colisionInferior(Pelotita pelotita){
		
		return(
				modulo(coordPelotitaEnY() - yDown(ladrilloEnColision))
				<=
				modulo(coordPelotitaEnX() - xLeft(ladrilloEnColision))
				&&
				(modulo( coordPelotitaEnY() - yDown(ladrilloEnColision))
				<=
				modulo(coordPelotitaEnX() - xLeft(ladrilloEnColision)))
				&&
				(modulo( coordPelotitaEnY() - yDown(ladrilloEnColision))
				<=
				modulo(coordPelotitaEnX() - xRight(ladrilloEnColision)))
				
				);
		//return((modulo(ladrilloEnColision.getX() - pelotita.getX())) >  (modulo((ladrilloEnColision.getX() + ladrilloEnColision.getAppearance().getWidth()) - pelotita.getX())) );
		
		//return ((ladrilloEnColision.getX() - (pelotita.getX() - pelotita.getAppearance().getWidth())) > (ladrilloEnColision.getX() - (pelotita.getX())));
	}
	
	
	public double xLeft(Ladrillo ladrillo){
		return (ladrillo.getX()); // - (ladrillo.getAppearance().getWidth())));
	}
 
	public double xRight(Ladrillo ladrillo){
		return((ladrillo.getX() + (ladrillo.getAppearance().getWidth())));
	}
	public double yUp(Ladrillo ladrillo){
		return(ladrillo.getY() - (ladrillo.getAppearance().getHeight()));
	}
	public double yDown(Ladrillo ladrillo){
		return(ladrillo.getY() + (ladrillo.getAppearance().getHeight()));
	}
	
	
	public double coordPelotitaEnXRight(){
		return(this.pelotita.getX() + (this.pelotita.getAppearance().getWidth()));
	}
	
	public double coordPelotitaEnXLeft(){
		return(this.pelotita.getX());
	}
	
	
	
	public double coordPelotitaEnYUp(){
		return(this.pelotita.getY() + (this.pelotita.getAppearance().getHeight()));
	}
	
	public double coordPelotitaEnYDown(){
		return(this.pelotita.getY());
	}
	
	public double coordPelotitaEnY(){
		return(this.pelotita.getY());
	}
	
	public double coordPelotitaEnX(){
		return(this.pelotita.getX());
	}
	
	
	public int modulo(double  n){
		int retornar = (int)n;
		if(retornar < 0){retornar = retornar * (-1);}
		return retornar;
		
	}
	
	
	
	
	
}
