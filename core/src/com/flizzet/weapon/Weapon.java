package com.flizzet.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.assets.AssetInfo;
import com.flizzet.entity.Entity;
import com.flizzet.player.Player;
import com.flizzet.shootout.GameWorld;
import com.flizzet.utils.Direction;
import com.flizzet.weapon.bullet.Bullet;

/**
 * Weapon held by {@link Player}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Weapon extends Entity {

	private final Player player;
	private Texture image;
	/* Positioning */
	private boolean flipX = false;
	private boolean flipY = false;
	private float yOffset = -5;
	private float previousRotation = 0;
	/* Properties */
	protected float maximumFireRate = .3f;
	protected float fireRate = maximumFireRate;
	
	/** Default instantiable constructor */
	public Weapon(Player player) {
		this.player = player;
		
		this.image = GameWorld.INSTANCE.assets.get(AssetInfo.TEST_WEAPON_1, Texture.class);
		this.adjustBoundsToImage(image);
	}

	@Override
	public void update(float delta) {
		/* Set position */
		this.setCenterX(player.getCenterX());
		this.setCenterY(player.getCenterY() + yOffset);
		/* Set origin */
		this.setOriginX(this.getHalfWidth());
		this.setOriginY(this.getHalfHeight());
		/* Make alterations based on the weapon rotation */
		if (this.getRotation() < 270 && this.getRotation() > 90) {
			/* Set flip */
			flipX = true;
			flipY = true;
			/* Set player direction */
			player.setDirection(Direction.RIGHT);
		} else {
			flipX = true;
			flipY = false;
			player.setDirection(Direction.LEFT);
		}
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		/* Draw the Weapon with the rotation applied */
		batch.draw(image, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(),
				1, 1, this.getRotation(), 0, 0, (int) this.getWidth(), (int) this.getHeight(), flipX, flipY);
	}
	
	/** Tells whether or not the weapon has rotated since its last emit to the server */
	public boolean hasRotated() {
		if (previousRotation != this.getRotation()) {
			previousRotation = this.getRotation();
			return true;
		}
		return false;
	}
	
	/** Fires a weapon */
	public void shoot() {
		/* Reset fire rate */
		fireRate = maximumFireRate;

		Gdx.app.log("ClientWeapon", "Shot");
		Bullet bullet = new Bullet(this.getRotation(), 100);
		bullet.setCenterX(this.getCenterX());
		bullet.setCenterY(this.getCenterY());
		GameWorld.INSTANCE.getEntityContainer().add(bullet);
	}
	
	public void setFlipX(boolean isFlipX)	{ this.flipX = isFlipX; }
	
	public boolean isFlipX()				{ return this.flipX; }

}
