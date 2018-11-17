package com.flizzet.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.flizzet.interfaces.Updatable;

/**
 * Moves the {@link Player}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerController implements Updatable {
	
	public boolean RIGHT_PRESSED = false;
	public boolean LEFT_PRESSED = false;
	public boolean UP_PRESSED = false;
	public boolean DOWN_PRESSED = false;
	
	/** Default instantiable constructor */
	public PlayerController(Player player) {
	}
	
	@Override public void update(float delta) {
		RIGHT_PRESSED = Gdx.input.isKeyPressed(Keys.D);
		LEFT_PRESSED = Gdx.input.isKeyPressed(Keys.A);
		UP_PRESSED = Gdx.input.isKeyPressed(Keys.W);
		DOWN_PRESSED = Gdx.input.isKeyPressed(Keys.S);
	}
	
}
