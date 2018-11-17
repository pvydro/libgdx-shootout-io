package com.flizzet.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Renders an object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public interface Renderable {

	/** Renders the object using a {@link SpriteBatch} */
	public abstract void render(SpriteBatch batch, ShapeRenderer sr);
	
}
