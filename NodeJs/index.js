const app = require('express')();
const http = require('http').createServer(app);
const io = require('socket.io')(http);
const req = require('request');

http.listen(3000, function(){
    console.log('listening on *:3000');
});

io.on