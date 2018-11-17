package com.flizzet.shootout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.screens.LoadingScreen;

public class ShootoutGame extends Game {
	
	private GameWorld world;
	public SpriteBatch batch;
	public ShapeRenderer sr;
	
	@Override public void create() {
		/* Create required objects */
		world = GameWorld.INSTANCE;
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		
		/* Set to the initial screen */
		this.setScreen(new LoadingScreen(this));
	}

	@Override public void render() {
		/* Clear screen */
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(world.getCamera().getCombinedMatrix());
		batch.begin();
		
		/* Update and render GameWorld */
		world.update(Gdx.graphics.getDeltaTime());
		world.render(batch, sr);
		
		/* Renders screen */
		super.render();
		batch.end();
	}
	
	@Override public void resize(int width, int height) {
		world.getCamera().resize(width, height);
	}
	
	@Override public void dispose() {
		batch.dispose();
		sr.dispose();
		screen.dispose();
	}
}
