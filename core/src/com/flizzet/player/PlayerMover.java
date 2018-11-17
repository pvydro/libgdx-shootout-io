package com.flizzet.player;

import com.flizzet.interfaces.Updatable;

/**
 * Moves the player towards its required position.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerMover implements Updatable {

	private final Player player;
	private float xVel = 0f;
	private float yVel = 0f;
	private float targetXVel = 0f;
	private float targetYVel = 0f;
	private float speed = 3;
	
	/** Default instantiable constructor */
	public PlayerMover(Player player) {
		this.player = player;
	}

	@Override
	public void update(float delta) {
		/* Move velocities towards target velocities */
		xVel += (targetXVel - xVel) / 2;
		yVel += (targetYVel - yVel) / 2;
		/* Set player state based on velocities */
		if (Math.abs(xVel) > .2f || Math.abs(yVel) > .2f) {
			player.setState(PlayerState.WALKING);
		} else {
			player.setState(PlayerState.IDLE);
		}
		/* Add velocities to player position */
		player.setX(player.getX() + xVel);
		player.setY(player.getY() + yVel);
	}

	/** Moves the player upwards */
	public void moveUp() {
		targetYVel = speed;
	}
	
	/** Moves the player downwards */
	public void moveDown() {
		targetYVel = -speed;
	}
	
	/** Moves the player right */
	public void moveRight() {
		targetXVel = speed;
	}
	
	/** Moves the player left */
	public void moveLeft() {
		targetXVel = -speed;
	}
	
	/** Stops the player on the X axis */
	public void slowDownHorizontally() {
		targetXVel = 0;
	}
	
	/** Stops the player on the Y axis */
	public void slowDownVertically() {
		targetYVel = 0;
	}
	
}
