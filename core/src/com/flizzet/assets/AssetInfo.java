package com.flizzet.assets;

import java.util.ArrayList;

/**
 * Holds paths of all assets for the {@link AssetDescriptors}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AssetInfo {
	
	public static ArrayList<String> ALL_IMAGES = new ArrayList<String>();
	
	/* Constants */
		// Player
	public static String PLAYER_WALKING_SHEET;
	public static String TEST_WEAPON_1;
	public static String BULLET;
	
	/** Suppress default constructor for noninstantiability */
	private AssetInfo() {
		throw new AssertionError();
	}
	
	/** Sets up the ArrayLists of Textures. */
	public static void setUpLists() {
		ALL_IMAGES.add(PLAYER_WALKING_SHEET = "player/walking.png");
		ALL_IMAGES.add(TEST_WEAPON_1 = "weapons/test_weapon_1.png");
		ALL_IMAGES.add(BULLET = "objects/bullet.png");
	}
	
}
