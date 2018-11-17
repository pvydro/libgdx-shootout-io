package com.flizzet.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;
import com.flizzet.utils.Direction;

/**
 * Abstract Entity class for concrete entities to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.3
 */
public abstract class Entity implements Updatable, Renderable {
    
    protected Rectangle bounds = new Rectangle(0, 0, 16, 16);
    protected String id = "";
    protected Direction direction = Direction.RIGHT;
    protected float rotation = 0f;
    protected float originX = 0f;
    protected float originY = 0f;
    
    public Rectangle getBounds()				{ return bounds; }
    public Direction getDirection()				{ return direction; }
    public String getId() 						{ return id; }
    public float getX() 						{ return bounds.x; }
    public float getY() 						{ return bounds.y; }
    public float getCenterX()					{ return bounds.x + (bounds.width / 2); }
    public float getCenterY()					{ return bounds.y + (bounds.height / 2); }
    public float getWidth() 					{ return bounds.width; }
    public float getHeight() 					{ return bounds.height; }
    public float getHalfWidth()					{ return bounds.width / 2; }
    public float getHalfHeight()				{ return bounds.height / 2; }
    public float getOriginX()					{ return this.originX; }
    public float getOriginY()					{ return this.originY; }
    public float getRotation()					{ return this.rotation; }
	public void setId(String newID)				{ this.id = newID; }
	public void setDirection(Direction d)		{ this.direction = d; }
    public void setX(float newX) 				{ this.bounds.x = newX; }
    public void setY(float newY) 				{ this.bounds.y = newY; }
    public void addToX(float amt)				{ this.bounds.x += amt; }
    public void addToY(float amt)				{ this.bounds.y += amt; }
    public void subtractFromX(float amt)		{ this.bounds.x -= amt; }
    public void subtractFromY(float amt)		{ this.bounds.y -= amt; }
    public void setCenterX(float newX)			{ this.bounds.x = newX - (bounds.width / 2);	 }
    public void setCenterY(float newY)			{ this.bounds.y = newY - (bounds.height / 2); }
    public void setWidth(float newWidth) 		{ this.bounds.width = newWidth; }
    public void setHeight(float newHeight) 		{ this.bounds.height = newHeight; }
    public void setOriginX(float newOriginX)	{ this.originX = newOriginX; }
    public void setOriginY(float newOriginY)	{ this.originY = newOriginY; }
    public void setRotation(float newRotation)	{ this.rotation = newRotation; }
    
    /** Sets bounds to image width and height */
    public void adjustBoundsToImage(Texture image) {
    	this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
    }
    /** Sets bounds to image width and height */
    public void adjustBoundsToImage(TextureRegion image) {
    	this.setWidth(image.getRegionWidth());
    	this.setHeight(image.getRegionHeight());
    }

}
