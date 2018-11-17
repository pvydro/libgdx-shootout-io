var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var players = [];

/* Launch of server with port 8080 */
server.listen(8080, function() {
  console.log("Server is now running...");
});


/* Called upon a new connection to the servers address */
io.on('connection', function(socket) {

  console.log("Player connected.");

  /* Send the newly connected Client their ID  */
  socket.emit('socketID', { id: socket.id });

  /* Send all the previous Clients to the new Client */
  socket.emit('getPlayers', players);

  /* Send all other connected Clients the newly connected ID */
  socket.broadcast.emit('newPlayer', { id: socket.id });

  /* Add the new player to the Servers player list */
  players.push(new player(socket.id, 0, 0));
  // players[players.length + 1] = new player(socket.id, 0, 0);

  console.log("Player added to server list.");

  /* Called upon a disconnection from the servers address */
  socket.on('disconnect', function() {
    console.log("Player disconnected.");
    socket.broadcast.emit('playerDisconnected', { id: socket.id });
    /* Removes the Client from list of previous Clients */
    /* Loops through all players and remove its corresponding ID */
    for (var i = 0; i < players.length; i++) {
      if (players[i].id == socket.id) {
        players.splice(i, 1);
        console.log("Player removed from server list.");
      }
    }
  });

  socket.on('playerButtonPressed', function(data) {
    data.id = socket.id;

    socket.broadcast.emit('playerButtonPressed', data);
    socket.emit('playerButtonPressed', data);

    for (var i = 0; i < players.length; i++) {
      if (players[i].id == data.id) {
        players[i].x = data.x;
        players[i].y = data.y;
      }
    }
  });


  /* Weapon */
  socket.on('weaponRotated', function(data) {
    data.id = socket.id;

    socket.broadcast.emit('weaponRotated', data);
  })

});

function player(id, x, y) {
  this.id = id;
  this.x = x;
  this.y = y;
}
