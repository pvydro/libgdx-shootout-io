package com.flizzet.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.assets.AssetInfo;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;
import com.flizzet.shootout.GameWorld;
import com.flizzet.utils.AnimationUtils;
import com.flizzet.utils.Direction;

/**
 * Animates the {@link Player}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerAnimator implements Renderable, Updatable {

	private final Player player;
	private Texture walkSheet;
	private Animation<TextureRegion> walkAnim;
	private TextureRegion currentFrame;
	private float stateTime;
	
	/** Default instantiable constructor */
	public PlayerAnimator(Player player) {
		this.player = player;
		
		this.walkSheet = GameWorld.INSTANCE.assets.get(AssetInfo.PLAYER_WALKING_SHEET, Texture.class);
		
		/* Create animations */
		this.walkAnim = AnimationUtils.newAnimation(walkSheet, 10, 1, 50);
		this.walkAnim.setPlayMode(PlayMode.LOOP);
		
		/* Adjust player size */
		player.adjustBoundsToImage(walkAnim.getKeyFrame(0));
	}

	@Override
	public void update(float delta) {
		/* Increase stateTime */
		stateTime += delta;
		
		/* Set current frame based on PlayerState */
		if (player.getState() == PlayerState.IDLE) {
			currentFrame = walkAnim.getKeyFrame(0);
		} else if (player.getState() == PlayerState.WALKING) {
			currentFrame = walkAnim.getKeyFrame(stateTime);
		}
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		/* Flip if necessary */
		if (player.getDirection() == Direction.LEFT) {
			currentFrame.flip(true, false);
		}
		/* Draw */
		batch.draw(currentFrame, player.getX(), player.getY());
		/* Reset flip if necessary */
		if (currentFrame.isFlipX()) {
			currentFrame.flip(true, false);
		}
	}	

}
