import { getCookie } from "/util.js";

let memberId = getCookie("member_id");
let nickName = getCookie("member_nickName");

function wsOpen(ws, deliveMsgToRoom) {
    return new WebSocket("ws://" + location.host + "/ws_chating" + location.search);
    // wsEvt(ws, deliveMsgToRoom);
}

function wsEvt(ws, deliveMsgToRoom) {
    //소켓이 열리면 동작
    ws.onopen = function (e) {
        console.log('웹소켓 연결...')
    }

    //서버로부터 데이터 수신 (메세지를 전달 받음)
    ws.onmessage = function (e) {
        var msgJson = e.data; // 전달 받은 데이터
        var msg = JSON.parse(msgJson);
        console.log('msg', msg);

        deliveMsgToRoom(msg);
    }


    ws.onclose = function () {
        console.log('웹소켓 종료...')
    }
}

export { wsOpen , wsEvt };