package com.flizzet.screens;

import com.badlogic.gdx.Screen;
import com.flizzet.networking.ClientConnector;
import com.flizzet.shootout.ShootoutGame;

/**
 * Gameplay state - To be changed to a {@link Screen} in the future.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MultiplayerGameScreen implements Screen {
	
	private ClientConnector client;
	
	/** Default instantiable constructor */
	public MultiplayerGameScreen(ShootoutGame game) {
	}

	@Override
	public void show() {
		/* Connect client to server */
		client = ClientConnector.INSTANCE;
		client.connectSocket();
		client.configSocketEvents();
	}

	@Override
	public void render(float delta) {
		/*
		 * Updates
		 */
		/* Update the server */
		client.update(delta);
		
		/*
		 * Renders
		 */
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
