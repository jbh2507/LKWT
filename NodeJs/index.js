const app = require('express')();
const http = require('http').createServer(app);
const io = require('socket.io')(http);
const req = require('request');
const rm = require('./roomManager');

// 10.10.10.132:8080/api
const apiURI = "http://localhost:8080/api";

http.listen(3000, function(){
    console.log('listening on *:3000');
});

io.on("connect", function (socket){
    var id = socket.id;
    socket.curRoom = null;

    console.log("socket("+id+") is connected");

    socket.on("setStudent", function(data){
        console.log("setStudent: "+data+"\t"+id)
        rm.joinRoom(data, socket);
    });

    socket.on("setTeacher", function(data){
        console.log("setTeacher: "+data+"\t"+id)

        rm.joinRoom(data, socket);
        rm.setRoomMaster(data, socket);
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
        console.log("========= response answer");
        console.log(data);
        console.log("");

        var answer = {'qno':data.qno, 'username':data.userName, 'indicator':data.indicator , comment:data.comment, tag:data.tag};

        var ansComment = null;
        if(data.comment.length != 0 || data.tag.length != 0 ) ansComment = {comment:data.comment, tag:data.tag};

        var answerVO = {answer, ansComment};

        var option = {url:apiURI+"/answer", form: answer};

        req.post(option);

        var roomMaster = rm.getRoomMaster(socket.curRoom);

        io.to(roomMaster).emit("answer", answerVO);
    });

    socket.on("disconnect", function (socket){
        rm.exitRoom(socket);

        console.log("socket("+id+") is disconnected");
    });
});