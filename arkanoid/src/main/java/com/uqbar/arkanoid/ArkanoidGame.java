package com.uqbar.arkanoid;

import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;


public class ArkanoidGame extends Game {

	private Dimension dimension;
	private GameScene scene;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(800 , 600);
		
	}	

	

	@Override
	protected void setUpScenes() {
		this.scene = new ArkanoidScene(dimension,300);
		this.setCurrentScene(scene);
		

		
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Arkanoid";
	}
	
	public static void main(String[] args) {
		new DesktopGameLauncher(new ArkanoidGame()).launch();
	}

}
