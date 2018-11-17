package com.flizzet.shootout;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.assets.AssetHandler;
import com.flizzet.camera.Camera;
import com.flizzet.camera.MainCamera;
import com.flizzet.containers.Container;
import com.flizzet.entity.Entity;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;

/**
 * GameWorld for all global fields and containers.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GameWorld implements Updatable, Renderable {
	
	public static final GameWorld INSTANCE = new GameWorld();
	private Camera camera = MainCamera.INSTANCE;
	private Container<Entity> entityContainer = new Container<Entity>();
	public AssetHandler assets = AssetHandler.INSTANCE;

	/** Single use constructor */
	private GameWorld() {
		camera.createCamera();
	}

	@Override public void update(float delta) {
		camera.update(delta);
		entityContainer.update(delta);
	}
	
	@Override public void render(SpriteBatch batch, ShapeRenderer sr) {
		entityContainer.render(batch, sr);
	}
	
	public Camera getCamera()						{ return this.camera; }
	public Container<Entity> getEntityContainer()	{ return this.entityContainer; }

}
