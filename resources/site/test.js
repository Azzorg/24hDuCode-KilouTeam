var socket= new WebSocket('ws://localhost:8350');

socket.onopen = function (event) {
	alert("Connected: " + socket.protocol);
  socket.send("Here's some text that the server is urgently awaiting!"); 
};


//socket.close();