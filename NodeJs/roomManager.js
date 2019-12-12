const roomManager = (function (){

    var roomMapper = {};
    var lobby = null; // 로비 운용할 시 작성

    function joinRoom(roomName, socket){
        // 이미 다른 방에 접속 중이라면 방에 접속을 해제함
        if(socket.curRoom !== null) exitRoom(socket);

        // 들어가려는 방이 roomManager에 없다면 추가
        if(!roomMapper[roomName]) roomMapper[roomName] = {master:null, users:[]};

        roomMapper[roomName].users.push(socket);

        socket.curRoom = roomName;
    } // joinRoom()

    function exitRoom(socket){
        var roomName = socket.curRoom;
        
        if(!roomName) return;

        // roomManager 탐색해서 해당 방에서 소켓 제거
        let room = roomMapper[roomName].users;
        for(let i=0; i<room.length; i++){
            if(room[i] === socket){
                room.splice(i, 1);
            }
        }

        // curRoom에서 현재 방 제거
        socket[curRoom] = lobby;

        console.log("room "+roomName+"'s curMem")
        room.forEach(e => {
            console.log("\t"+e.id);
        });
    } // exitRoom()

    function getRoomMember(roomName){
        let room = roomMapper[roomName].users;

        if(!room) return [];

        return room;
    } //getRoomMember()

    function setRoomMaster(roomName, socket){
        var room = roomMapper[roomName];
        if(!room) room = {master:null, users:[socket]};

        room.master = socket.id;
    }

    function getRoomMaster(roomName){
        var room = roomMapper[roomName];
        if(!room) return null;

        return room.master;
    }

    return {joinRoom, exitRoom, getRoomMember, setRoomMaster, getRoomMaster};
})();

module.exports = roomManager;