package com.flizzet.weapon.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.assets.AssetInfo;
import com.flizzet.entity.Entity;
import com.flizzet.shootout.GameWorld;
import com.flizzet.weapon.Weapon;

/**
 * Bullet that flies out of {@link Weapon}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Bullet extends Entity {

	private float radians = 0f;
	private float speed = 100f;
	private Texture image;
	
	/** Default instantiable constructor */
	public Bullet(float degrees, float speed) {
		this.setRotation(degrees + 180f);
		this.radians = (float) Math.toRadians(degrees + 90f);
		
		/* Set image */
		this.image = GameWorld.INSTANCE.assets.get(AssetInfo.BULLET, Texture.class);
		this.adjustBoundsToImage(image);
	}

	@Override
	public void update(float delta) {
		/* Set origin X and Y */
		this.setOriginX(this.getHalfWidth());
		this.setOriginY(this.getHalfHeight());
		
		/* Move towards the angle */
		float xChange = delta * (speed * (float) Math.sin(-radians));
		float yChange = delta * (speed * (float) Math.cos(-radians));
		this.setX(this.getX() + xChange);
		this.setY(this.getY() + yChange);
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		batch.draw(image, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(),
				1, 1, this.getRotation(), 0, 0, (int) this.getWidth(), (int) this.getHeight(), false, false);
	}

}
