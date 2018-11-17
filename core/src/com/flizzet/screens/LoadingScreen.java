package com.flizzet.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.flizzet.assets.AssetInfo;
import com.flizzet.shootout.GameWorld;
import com.flizzet.shootout.ShootoutGame;

/**
 * Loading screen.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LoadingScreen implements Screen {

	private final ShootoutGame game;
	
	/** Default instantiable constructor */
	public LoadingScreen(ShootoutGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		AssetInfo.setUpLists();
		GameWorld.INSTANCE.assets.loadAll();
	}

	@Override
	public void render(float delta) {
		if (GameWorld.INSTANCE.assets.isFinishedLoading()) {
			game.setScreen(new MultiplayerGameScreen(game));
		} else {
			Gdx.app.log("LoadingScreen", "Loading");
		}
	}

	@Override
	public void resize(int width, int height) {
		GameWorld.INSTANCE.getCamera().resize(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
