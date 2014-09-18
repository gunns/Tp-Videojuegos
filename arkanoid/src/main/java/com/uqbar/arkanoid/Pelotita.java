package com.uqbar.arkanoid;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;

public class Pelotita extends GameComponent<ArkanoidScene> {

	private boolean playState = true;
	public boolean estaCreciendoX;
	private Dimension gameDimension;

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

	public Pelotita(Dimension dim) {
		this.setAppearance(new Circle(Color.black, 15));
		this.playState = true;
		this.gameDimension = dim;
		this.estaCreciendoX = true;
		this.estaCreciendoY = false;
	}

	@Override
	public void update(DeltaState deltaState) {

		if (this.getScene().getPlayState()) {
			if (deltaState.isKeyPressed(Key.A)) {
				this.getScene().stop();
				this.getScene().setSystemPause(true);
			} else {
				mover(deltaState);
			}
			if (this.getScene().hayColision()) {
				this.estaCreciendoY = false;
			}

		} else if (deltaState.isKeyPressed(Key.S)) {
			this.getScene().newGame(250);
			this.getScene().setSystemPause(false);
		} else if (deltaState.isKeyPressed(Key.A)) {
			this.getScene().continueGame(250);
			this.getScene().setSystemPause(false);
		}
	}

	private void mover(DeltaState deltaState) {
		if (this.estaCreciendoX) {
			this.setX(this.getX() + this.getScene().getVelocity()
					* deltaState.getDelta());
			if (this.getX() >= this.gameDimension.getWidth() - this.getAppearance().getWidth()) {
				this.estaCreciendoX = false;
			}
		}
		if (!this.estaCreciendoX) {
			this.setX(this.getX() - this.getScene().getVelocity()
					* deltaState.getDelta());
			if (this.getX() <= 0) {
				this.estaCreciendoX = true;
			}
		}
		if (this.estaCreciendoY) {
			this.setY(this.getY() + this.getScene().getVelocity()
					* deltaState.getDelta());
			if (this.getY() >= this.gameDimension.getWidth()) {
				this.getScene().youLose();
			}
		}

		if (!this.estaCreciendoY) {
			this.setY(this.getY() - this.getScene().getVelocity()
					* deltaState.getDelta());
			if (this.getY() <= 0) {
				this.estaCreciendoY = true;
			}
		}
	}

	public void newGamePos(Barrita barrita) {
		if (this.playState) {
			setX(barrita.getX() + barrita.getAppearance().getWidth() / 2
					- getAppearance().getWidth() / 2);
			setY(barrita.getY() - getAppearance().getWidth());
			setEstaCreciendoY(false);
		}
	}

}
