const app = require('express')();
const http = require('http').createServer(app);
const io = require('socket.io')(http);
const req = require('request');
const rm = require('./roomManager');

const apiURI = "http://localhost:8080/api";

http.listen(3000, function(){
    console.log('listening on *:3000');
});

io.on("connect", function (socket){
    var id = socket.id;
    socket.curRoom = null;

    console.log("socket("+id+") is connected");

    socket.on("setClass", function(data){
        console.log("setClass: "+data+"\t"+id)
        rm.joinRoom(data, socket);
    });

    // question 등록
    socket.on("question", function(data){
        console.log(data + "\trequest question");

        var questionVO = {'cno':data.cno, 'content':data.content, 'category':data.category};

        var option = {url:apiURI+"/question", form: questionVO, json:true};

        req.post(option, function(err,httpResponse,body){
            console.log(body+"\t id");

            socket.emit("question", body);

            data.qno = body;
            console.log(data);
            
            var roomMems = rm.getRoomMember(socket.curRoom);
            roomMems.forEach(s => {
                s.emit("requestAnswer", data);
            });
        });

    
    });

    // answer 등록
    socket.on("answer", function (data){
        console.log(data + "\tresponse answer");

        var answer = {qno:data.qno, username:data.userName, indicator:data.indicator};

        var ansComment = null;
        if(data.comment.length != 0 || data.tag.length != 0 ) ansComment = {comment:data.comment, tag:data.tag};

        var answerVO = {answer, ansComment};

        var option = {url:apiURI+"/answer", form: answerVO};

        req.post(option);

        var roomMaster = rm.getRoomMaster(socket.curRoom);

        roomMaster.emit("answer", answerVO);

    });

    socket.on("disconnect", function (socket){
        rm.exitRoom(socket);

        console.log("socket("+id+") is disconnected");
    });
});