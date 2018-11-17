package com.flizzet.player;

import com.flizzet.weapon.ClientWeapon;

/**
 * Player for online playing.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ClientPlayer extends Player {
	
	public static final ClientPlayer INSTANCE = new ClientPlayer();
	private PlayerController controller = new PlayerController(this);
	
	/** Default instantiable constructor */
	private ClientPlayer() {
		weapon = new ClientWeapon(this);
	}
	
	@Override public void update(float delta) {
		controller.update(delta);
		super.update(delta);
	}
	

	public PlayerController getController()		{ return this.controller; }

}
