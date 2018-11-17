package com.flizzet.networking;

import com.badlogic.gdx.Gdx;
import com.flizzet.interfaces.Updatable;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Connects the Client to the server.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ClientConnector implements Updatable {
	
	public static final ClientConnector INSTANCE = new ClientConnector();
	
	private ServerPlayerUpdater players = new ServerPlayerUpdater(this);
	private Socket socket;	
	public static final float UPDATE_TIME = 1/60f;
	public static boolean connected = false;

	/** Single use constructor */
	private ClientConnector() {}
	
	/** Updates the server */
	@Override public void update(float delta) {
		if (connected) players.update(delta);
	}

	/** Connects to the Socket.io server */
	public void connectSocket() {
		try {
			socket = IO.socket("http://localhost:8080");
			socket.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Configures socket events */
	public void configSocketEvents() {
		/* Called when this client connects to a server */
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				connected = true;
				/* Print info */
				Gdx.app.log("SocketIO", "Connected to server.");
			}
		});
		
		/* Configure socket player methods */
		players.configPlayerSocketEvents();
	}
	
	public ServerPlayerUpdater getPlayers()				{ return this.players; }
	public Socket getSocket()							{ return this.socket; }
}
