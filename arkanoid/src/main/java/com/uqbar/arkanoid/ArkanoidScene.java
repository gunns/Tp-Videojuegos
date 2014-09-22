package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Graphics2D;

import java.awt.Font;

//import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
//import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
//import com.uqbar.vainilla.events.constants.Key;

public class ArkanoidScene extends GameScene {
	
	private int puntos;
	private Puntaje puntaje;
	private int puntajeParaVida;
	private int nuevoPuntajeParaVida;
	private boolean apareceCartel;
	
	private Vidas vidas;

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
		
		
		this.apareceCartel = false;
		this.puntajeParaVida = 2000;
		this.nuevoPuntajeParaVida = puntajeParaVida;
		
		this.velocity=velocity;
		this.gameDimension= dim;
		this.vidas =new Vidas(3,dim);
		this.addComponent(vidas);
		this.puntaje = new Puntaje(dim);
		this.addComponent(puntaje);
		this.buildBackground(Color.white);
		this.barrita=new Barrita(this.gameDimension, playState);
		this.pelotita= new Pelotita(this.gameDimension);
		pelotita.newGamePos(barrita);
		this.addComponent(pelotita);
		this.addComponent(barrita);
		
		this.ladrillos = new Ladrillos(dim, 50, 25, 5, 50);
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
	
	
	
	public void ganarVida(){
		if(this.puntaje.getPuntos() >= this.nuevoPuntajeParaVida)
		{
			this.vidas.agregarVida();
		//this.vidas.setVida(this.vidas.getVida()+1);
		this.nuevoPuntajeParaVida = this.nuevoPuntajeParaVida + this.puntajeParaVida;
	}
		}
	
	protected void youLose(){
		this.vidas.setVida(this.vidas.getVida() - 1);
		if(this.vidas.getVida() == 0){
			this.cartelLose();
			this.apareceCartel = true;
			this.stop();
			this.setPlayState(false);
			
		}
		else{
//		Throw message here if someone lose
		//this.buildBackground(Color.white);
		
		this.resetBall();
		}
	}
	
	public void newGame(){
		this.reconstruirLadrillos();
		resetBall();
		this.removeComponent(this.puntaje);
		this.puntaje = new Puntaje(gameDimension);
		this.addComponent(puntaje);
		this.removeComponent(this.vidas);
		this.vidas =new Vidas(3,gameDimension);
		this.addComponent(vidas);
		this.apareceCartel = false;
		
		//this.setPlayState(false);
		
	}
	
	private void resetBall() {
		newTry(250);
		setSystemPause(false);		
	}


	void newTry(double velocity) {
		this.buildBackground(Color.white);
		this.barrita.setX(800/2-this.barrita.getAppearance().getWidth());
		this.barrita.setY(600-(this.barrita.getAppearance().getHeight())-5);
		this.pelotita.newGamePos(barrita);
		
		this.velocity = velocity;
		this.playState = false;
		
		//this.destruirLadrillos();
		//this.reconstruirLadrillos();
	}
	
	
	/*	*/
	//INTENTO FALLIDO DE CONSTRUCCION Y RECONSTRUCCION DE LOS LADRILLOS
	//Este metodo esta mal
	//public void destruirLadrillos(){
	//this.removeComponents(this.ladrillos.getLadrillos());	
	//}
	
	public void reconstruirLadrillos(){
		this.ladrillos = new Ladrillos(gameDimension, 50, 25, 5, 50);
		this.addComponents(ladrillos.getLadrillos());
	}

	
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
                return ( this.pelotita.getX()+(this.pelotita.getAppearance().getWidth()/2)>=this.barrita.getX()
                                && this.pelotita.getX()+(this.pelotita.getAppearance().getWidth()/2)<=this.barrita.getX()+(this.barrita.getAppearance().getWidth())
                                && this.pelotita.getY()+(this.pelotita.getAppearance().getHeight())
                                >this.barrita.getY())
                                && (this.pelotita.getY()+(this.pelotita.getAppearance().getHeight())
                                <this.barrita.getY()+this.barrita.getAppearance().getHeight()/2);
                                }
	
	/*
	public boolean hayColision(){
		return ( this.pelotita.getX()-(this.pelotita.getAppearance().getWidth())>=this.barrita.getX()-(this.barrita.getAppearance().getWidth()/2)
				&& this.pelotita.getX()-(this.pelotita.getAppearance().getWidth())<=this.barrita.getX()+(this.barrita.getAppearance().getWidth())
				&& this.pelotita.getY()+(this.pelotita.getAppearance().getHeight()/2)
				>this.barrita.getY())//-(this.barrita.getAppearance().getHeight()/2))
				&& this.pelotita.getY()+(this.pelotita.getAppearance().getHeight()/2)
				<this.barrita.getY()+(this.barrita.getAppearance().getHeight()/2);
	}
	*/
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
		for(int n = 0;(n <  (this.ladrillos.getLadrillos().size())); n++)
			{
			if(this.ladrilloSeRompe(this.ladrillos.getLadrillos().get(n)))
				{
				//encontre = true;
				this.ladrilloEnColision = this.ladrillos.getLadrillos().get(n);
				this.ladrillos.getLadrillos().get(n).sumarPuntaje(this);
				this.ganarVida();
				this.ladrillos.getLadrillos().remove(this.ladrillos.getLadrillos().get(n));
				b = true;
				if(this.ladrillos.getLadrillos().size() == 0)
					{
					this.cartelWin();
					this.apareceCartel = true;
					
					this.stop();
					this.setPlayState(false);
					}
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
	
	
public void cartelWin(){
	this.buildBackground(Color.white);
	this.addComponent(new Cartel(this.gameDimension));
	this.setPlayState(false);
}

public void cartelLose(){
	this.buildBackground(Color.white);
	this.addComponent(new Cartel(this.gameDimension, this.puntaje.getPuntos()));
	this.setPlayState(false);
}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	public Puntaje getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(Puntaje puntaje) {
		this.puntaje = puntaje;
	}


	public Vidas getVidas() {
		return vidas;
	}


	public void setVidas(Vidas vidas) {
		this.vidas = vidas;
	}


	public boolean isApareceCartel() {
		return apareceCartel;
	}


	public void setApareceCartel(boolean apareceCartel) {
		this.apareceCartel = apareceCartel;
	}


	public boolean pelotitaPegadaALaBarrita() {
		/*setX(barrita.getX() + barrita.getAppearance().getWidth() / 2
					- getAppearance().getWidth() / 2);
			setY(barrita.getY() - getAppearance().getWidth());
		  */
		

			
					return(this.pelotita.getY()// + pelotita.getAppearance().getWidth() ==
							==(
									barrita.getY() 
									- pelotita.getAppearance().getWidth()
								));
	}

	
	
	
	
}
