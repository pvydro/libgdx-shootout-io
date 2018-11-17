package com.flizzet.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Utilities for turning spritesheets into Animations.
 * Note that drawing an animation requires you to use delta
 * with an elapsed time.
 * </br>
 * See: https://github.com/libgdx/libgdx/wiki/2D-Animation
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AnimationUtils {

    /** Suppress default constructor for noninstantiability */
    private AnimationUtils() { throw new AssertionError(); }
    
    /** Creates a new animation from a sprite sheet. */
    public static Animation<TextureRegion> newAnimation(Texture spriteSheet, int frameColumns, int frameRows, int framesPerSecond) {
		
		/* Locals */
		float timePerFrame = (float) framesPerSecond/1000;	// Calculate time per frame for animation creation
		int index = 0;
		Animation<TextureRegion> animation;
		
		/* Use the split utility to create a 2D array of TextureRegions using the columns and rows */
	    TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 
	    	spriteSheet.getWidth() / frameColumns,
			spriteSheet.getHeight() / frameRows);
	    	
	    /* Place the regions into a 1D array in the correct order, starting from the top left */
	    TextureRegion[] animFrames = new TextureRegion[frameColumns * frameRows];
	    	
	    for (int i = 0; i < frameRows; i++) {
	    	for (int j = 0; j < frameColumns; j++) {
	    		animFrames[index++] = tmp[i][j];
	    	}
	    }
		
	    animation = new Animation<TextureRegion>(timePerFrame, animFrames);
	        
		return animation;
    }

}
