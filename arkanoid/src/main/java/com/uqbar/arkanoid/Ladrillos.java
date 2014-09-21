package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import com.uqbar.vainilla.appearances.Rectangle;

public class Ladrillos {
	double alturaLadrillo;
	double anchoLadrillo;
	ArrayList<Ladrillo> ladrillos;
	
	
	
	
	public Ladrillos(Dimension dim, double anchoLadrillo, double alturaLadrillo, int filas, int valor){
		this.ladrillos = new ArrayList<Ladrillo>();
		this.anchoLadrillo = anchoLadrillo;
		this.alturaLadrillo = alturaLadrillo;
		this.colocarLadrillos(dim, filas, valor);
		
		
		}
	
	public void colocarLadrillos(Dimension dim, int filas, int valor){
		
		double m = dim.getWidth();
		
		double espacioCostados = anchoLadrillo;
		double espacioArriba = 100;
		
		
		
			for(double veces = 0; (veces < filas); veces ++)
				{
		for(double inicio = espacioCostados; (inicio) < (m - espacioCostados) ; inicio = inicio + anchoLadrillo + 2)
			{
			colocarLadrillo(dim, inicio , veces, espacioArriba, valor);
			}
		espacioArriba = espacioArriba + 2;
				}
		
		
	}
		
	private void colocarLadrillo(Dimension dim, double inicio, double filas, double espacioArriba, int valor) {
		Ladrillo ladrillo = new Ladrillo(dim, (inicio), (espacioArriba + (filas * this.alturaLadrillo)), this.randomColor(), valor);
		this.ladrillos.add(ladrillo);
		
	}	
	
	
	public Color randomColor(){
		ArrayList<Color> aleatorio = new ArrayList<Color>();
		aleatorio.add(Color.red);
		aleatorio.add(Color.blue);
		aleatorio.add(Color.orange);
		aleatorio.add(Color.gray);
		aleatorio.add(Color.green);
		aleatorio.add(Color.black);
		aleatorio.add(Color.CYAN);
		aleatorio.add(Color.magenta);
		aleatorio.add(Color.pink);
		
		Random rand = new Random();//creamos una instancia de Random 
		int posAleatoria = rand.nextInt(aleatorio.size() - 1);//obtenemos una posicion entre 0 y el tamano del arreglo 
		Color c = aleatorio.get(posAleatoria);
		return c;
		// en lugar de object pones el tipo de dato que maneja tu array 
	}
	
	

	public ArrayList<Ladrillo> getLadrillos() {
		return ladrillos;
	}

	public void setLadrillos(ArrayList<Ladrillo> ladrillos) {
		this.ladrillos = ladrillos;
	}
	
	
	
		
	}
