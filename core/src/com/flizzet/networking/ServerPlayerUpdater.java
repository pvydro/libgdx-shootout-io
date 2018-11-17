package com.flizzet.networking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.interfaces.Updatable;
import com.flizzet.player.ClientPlayer;
import com.flizzet.player.Player;
import com.flizzet.shootout.GameWorld;

import io.socket.emitter.Emitter;

/**
 * Handlers updating the player in the Server.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ServerPlayerUpdater implements Updatable {
	
	private final ClientConnector connector;
	
	private float timer = 0;
	
	/** Default instantiable constructor */
	public ServerPlayerUpdater(ClientConnector connector) {
		this.connector = connector;
	}
	
	@Override public void update(float delta) {
		/* Tell the server if the ClientPlayer has updated */
		timer += delta;
		if (timer >= ClientConnector.UPDATE_TIME) {
			JSONObject playerData = new JSONObject();
			JSONObject weaponData = new JSONObject();
			try {
				/* Send key presses to server */
				playerData.put("x", ClientPlayer.INSTANCE.getX());
				playerData.put("y", ClientPlayer.INSTANCE.getY());
				playerData.put("leftPressed", ClientPlayer.INSTANCE.getController().LEFT_PRESSED);
				playerData.put("rightPressed", ClientPlayer.INSTANCE.getController().RIGHT_PRESSED);
				playerData.put("upPressed", ClientPlayer.INSTANCE.getController().UP_PRESSED);
				playerData.put("downPressed", ClientPlayer.INSTANCE.getController().DOWN_PRESSED);
				connector.getSocket().emit("playerButtonPressed", playerData);
				
				/* Send weapon rotation to server */
				if (ClientPlayer.INSTANCE.getWeapon().hasRotated()) {
					weaponData.put("rotation", ClientPlayer.INSTANCE.getWeapon().getRotation());
					connector.getSocket().emit("weaponRotated", weaponData);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			timer = 0;
		}
	}
	
	/** Creates methods for a Player in the server */
	public synchronized void configPlayerSocketEvents() {
		connector.getSocket()
		.on("socketID", new Emitter.Listener() {
			@Override public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					/* Get ID */
					String id = data.getString("id");
					/* Log ID */
					Gdx.app.log("SocketIO", "My ID: " + id);
					/* Set ID to ClientPlayer */
					ClientPlayer.INSTANCE.setId(id);
					/* Add ClientPlayer to Entity Container */
					GameWorld.INSTANCE.getEntityContainer().add(ClientPlayer.INSTANCE);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).on("newPlayer", new Emitter.Listener() {
			@Override public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					/* Print the ID of the new player */
					String playerId = data.getString("id");
					Gdx.app.log("SocketIO", "New player with ID: " + playerId);
					
					/* Create the new player and set its id */
					Player newPlayer = new Player(playerId);
					/* Add the new player to the Container<Entity> */
					GameWorld.INSTANCE.getEntityContainer().add(newPlayer);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).on("playerButtonPressed", new Emitter.Listener() {
			@Override public void call(Object... args) {
				
				JSONObject data = (JSONObject) args[0];
				try {
					/* Save all data from JSON */
					boolean leftPressed = data.getBoolean("leftPressed");
					boolean rightPressed = data.getBoolean("rightPressed");
					boolean downPressed = data.getBoolean("downPressed");
					boolean upPressed = data.getBoolean("upPressed");
					String playerId = data.getString("id");
					
					/* Get player from Container<Entity> using ID */
					// FIXME Could cause desynchronization
					try {
						Player movedPlayer = ((Player) GameWorld.INSTANCE.getEntityContainer().getById(playerId));
						/* Apply new data to player */
						if (leftPressed && rightPressed)	{ movedPlayer.getMover().slowDownHorizontally(); }
						else if (leftPressed) 				{ movedPlayer.getMover().moveLeft(); }
						else if (rightPressed)				{ movedPlayer.getMover().moveRight(); }
						else 								{ movedPlayer.getMover().slowDownHorizontally(); }
						
						if (upPressed && downPressed)		{ movedPlayer.getMover().slowDownVertically(); }
						else if (downPressed)				{ movedPlayer.getMover().moveDown(); }
						else if (upPressed)					{ movedPlayer.getMover().moveUp(); }
						else								{ movedPlayer.getMover().slowDownVertically(); }
					} catch (NullPointerException e) {
						e.printStackTrace();
						return;
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		})
		.on("weaponRotated", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					String playerId = data.getString("id");
					float rotation = ((Double) data.getDouble("rotation")).floatValue();
					
					((Player) GameWorld.INSTANCE.getEntityContainer().getById(playerId))
					.getWeapon().setRotation(rotation);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		})
		
		.on("playerDisconnected", new Emitter.Listener() {
			@Override public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					/* Print the ID of the old player */
					String oldPlayerId = data.getString("id");
					Gdx.app.log("SocketIO", "Disconnected player with ID: " + oldPlayerId);

					/* Remove the player from the GameWorld entityContainer */
					GameWorld.INSTANCE.getEntityContainer().remove(GameWorld.INSTANCE.getEntityContainer().getById(oldPlayerId));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).on("getPlayers", new Emitter.Listener() {
			@Override public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				try {
					for (int i = 0; i < objects.length(); i++) {
						/* Create a player */
						Player newPlayer = new Player();
						/* Find its position */
						Vector2 position = new Vector2();
						position.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
						position.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
						/* Set its position according to newly found position */
						newPlayer.setX(position.x);
						newPlayer.setY(position.y);
						/* Set its id according to the data */
						newPlayer.setId(objects.getJSONObject(i).getString("id"));
						
						/* Add the old player to the Client EntityContainer */
						GameWorld.INSTANCE.getEntityContainer().add(newPlayer);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
