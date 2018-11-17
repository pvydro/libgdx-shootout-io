package com.flizzet.weapon;

import com.badlogic.gdx.Gdx;
import com.flizzet.player.ClientPlayer;
import com.flizzet.shootout.GameWorld;

/**
 * Weapon for {@link ClientPlayer}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ClientWeapon extends Weapon {
	
	/** Default instantiable constructor */
	public ClientWeapon(ClientPlayer player) {
		super(player);
	}
	
	@Override public void update(float delta) {
		super.update(delta);
		pointToMouse(delta);
		
		/* Adjust fire rate */
		if (fireRate > 0) {
			fireRate -= delta;
		}
		
		/* Shoot if clicked and ready */
		if (fireRate <= 0) {
			if (Gdx.input.isTouched()) {
				shoot();
			}
		}
	}
	
	/** rotates pick towards mouse **/
	private void pointToMouse(float delta) {
		/* Rotating with mouse if right mouse pressed */
		/* Get rotation (radians) based on mouse */
		float radians = (float) Math.atan2(
				GameWorld.INSTANCE.getCamera().getMousePos().y - this.getY() - this.getOriginY(),
				GameWorld.INSTANCE.getCamera().getMousePos().x - this.getX() + this.getOriginX()
						- this.getWidth());
		/* Restrict from rotating in blocked area */
		float degrees = (float) Math.toDegrees(radians);
		degrees = degrees + 180;

		/* Apply rotation to weapon */
		this.setRotation(degrees);
	}

}
