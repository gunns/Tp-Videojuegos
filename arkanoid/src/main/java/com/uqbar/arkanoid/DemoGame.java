package com.uqbar.arkanoid;

import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;


public class DemoGame extends Game {

	private Dimension dimension;
	private GameScene scene;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(800 , 600);
		
	}	

	

	@Override
	protected void setUpScenes() {
		this.scene = new DemoScene(dimension,250);
		this.setCurrentScene(scene);
		

		
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Demo de Vainilla";
	}
	
	public static void main(String[] args) {
		new DesktopGameLauncher(new DemoGame()).launch();

	}

}
