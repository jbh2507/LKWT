const roomManager = (function (){

    var roomMapper = {};
    const lobbyname = null; // 로비를 운용한다면 해당 방 이름 기입 

    function joinRoom(roomName, socket){
        // 이미 다른 방에 접속 중이라면 방에 접속을 해제함
        // if(socket.curRoom !== null) exitRoom(socket);

        // 들어가려는 방이 roomManager에 없다면 추가
        if(!roomMapper[roomName]) roomManager[roomName] = [];

        roomMapper[roomName].push(socket);

        if(!socket[curRoom]) socket[curRoom] = [];
        socket[curRoom].push(roomName);
    };

    function exitRoom(roomName,socket){

        // roomManager 탐색해서 해당 방에서 소켓 제거
        if(!roomMapper[roomName]) return;
        let room = roomMapper[roomName];
        for(let i=0; i<room.length; i++){
            if(room[i] === socket){
                room.splice(i, 1);
            }
        }

        // curRoom에서 현재 방 제거
        if(!socket[curRoom]) return;
        let curRoom = socket[curRoom];
        for(let i=0; i<curRoom.length; i++){
            if(curRoom[i] === roomName){
                roomcurRoom.splice(i, 1);
            }
        }
    }

    return {joinRoom, exitRoom};
})();

module.exports = roomManager;