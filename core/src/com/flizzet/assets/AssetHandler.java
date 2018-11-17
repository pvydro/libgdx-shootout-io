package com.flizzet.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Handles and manages loading and getting assets using an {@link AssetManager}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AssetHandler {

	public static final AssetHandler INSTANCE = new AssetHandler();
	private AssetManager manager = new AssetManager();
	
	/** Default instantiable constructor */
	public AssetHandler() {}
	
	/** Loads all assets using their AssetDescriptors */
	public void loadAll() {
		/* Load every constant file */
			// Player
		for (String s : AssetInfo.ALL_IMAGES) {
			manager.load(s, Texture.class);
		}
	}
	
	/** Disposes all assets for closing of application, prevents memory leaks */
	public void dispose() {
		manager.dispose();
	}

	public AssetManager getManager()	{ return this.manager; }
	public boolean isFinishedLoading()	{ return manager.update(); }
	
	public <T> T get (String fileName, Class<T> type) {
		return manager.get(fileName, type);
	}
	
}
