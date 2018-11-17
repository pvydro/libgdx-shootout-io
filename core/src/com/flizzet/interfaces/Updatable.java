package com.flizzet.interfaces;

/**
 * Updates an object every frame.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public interface Updatable {

	/** Updates the object by deltaTime */
	public abstract void update(float delta);

}
