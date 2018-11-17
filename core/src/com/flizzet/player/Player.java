package com.flizzet.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.entity.Entity;
import com.flizzet.weapon.Weapon;

/**
 * Player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Player extends Entity {
	
	private PlayerMover mover = new PlayerMover(this);
	protected PlayerAnimator animator = new PlayerAnimator(this);
	protected PlayerState currentState = PlayerState.IDLE;
	protected Weapon weapon = new Weapon(this);
	
	/** Default instantiable constructor */
	public Player() {}
	
	public Player(String id) { 
		this.id = id;
	}

	@Override
	public void update(float delta) {
		animator.update(delta);
		mover.update(delta);
		weapon.update(delta);
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		animator.render(batch, sr);
		weapon.render(batch, sr);
	}
	
	public void setState(PlayerState newState)	{ this.currentState = newState; }
	
	public PlayerState getState()				{ return this.currentState; }
	public PlayerMover getMover()				{ return this.mover; }
	public Weapon getWeapon()					{ return this.weapon; }

}
