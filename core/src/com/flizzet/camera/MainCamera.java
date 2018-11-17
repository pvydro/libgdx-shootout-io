package com.flizzet.camera;

import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Main OrthographicCamera.
 *
 * @author Pedro Dutra (2017)
 * @version 1.3
 */
public class MainCamera extends Camera {

	public static final MainCamera INSTANCE = new MainCamera();
	
    /** Single use constructor */
    private MainCamera() {}

    @Override
    public void createCamera() {
        super.createCamera();
        viewport = new ExtendViewport(width, height, cam);
        viewport.apply();
    }

}
