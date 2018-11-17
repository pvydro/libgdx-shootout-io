package com.flizzet.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.flizzet.interfaces.Updatable;

/**
 * Abstract class for extension of concrete cameras.
 *
 * @author Pedro Dutra (2017)
 * @version 1.2
 */
public abstract class Camera implements Updatable {

    protected OrthographicCamera cam;
    protected Viewport viewport;

    protected float height = 200;
    protected float pixelsPerUnit = Gdx.graphics.getHeight() / height;
    protected float width = Gdx.graphics.getWidth() / pixelsPerUnit;

    /**
     * Creates camera
     */
    public void createCamera() {
    	cam = new OrthographicCamera(width, height);
        resetPosition();
    }

    @Override
    public void update(float delta) { cam.update(); }

    /**
     * Sets the camera back to its starting position
     */
    public void resetPosition() {
    	cam.position.x = 0;//width / 2;
    	cam.position.y = 0;//width / 2;
    }

    /** Required resize method to be controlled by the Main class */
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(this.width / 2, this.height / 2, 0);
    }
    
    public OrthographicCamera getOrthoCamera()  { return cam; }
    public float getWidth() 					{ return width; }
    public float getHeight()					{ return height; }
    public Vector3 getMousePos()				{ return cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)); }
    public Vector3 getMousePos(int pointer)		{ return cam.unproject(new Vector3(Gdx.input.getX(pointer), Gdx.input.getY(pointer), 0)); }
    public Matrix4 getCombinedMatrix() 			{ return cam.combined; }
    public float getX()							{ return cam.position.x - (width / 2); }
    public float getY()							{ return cam.position.y - (height / 2); }
    public float getCenterX()					{ return cam.position.x; }
    public float getCenterY()					{ return cam.position.y; }
    public float getPpu()						{ return this.pixelsPerUnit; }
    
    /** Set height or "scale" */
    public void setHeight(float newHeight)		{ this.height = newHeight; }
    public void setX(float newX)				{ cam.position.x = newX; }
    public void setY(float newY)				{ cam.position.y = newY; }
}