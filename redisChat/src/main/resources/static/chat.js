var ws;
let member = {
    id: get_cookie("member_id"),
    nickName: get_cookie("member_nickName"),
}

function newMsg(type, text) {
    let obj = { type, ...member.nickName, text }
    console.log('obj', obj)
    return JSON.stringify(obj);
}

function wsOpen() {
    //websocket을 지정한 URL로 연결
    ws = new WebSocket("ws://" + location.host + "/ws_chating");
    wsEvt();
}

function wsEvt() {
    //소켓이 열리면 동작
    ws.onopen = function (e) {
        console.log('웹소켓 연결...')
    }

    //서버로부터 데이터 수신 (메세지를 전달 받음)
    ws.onmessage = function (e) {
        var msgJson = e.data; // 전달 받은 데이터
        console.log('msg', msgJson);


        if (msgJson != null && msgJson.trim() != '') {
            var msg = JSON.parse(msgJson);

            //채팅 메시지를 전달받은 경우
            if (msg.type == "message") {
                if (msg.userName == 1) {
                    $("#chating").append("<p class='me'>" + msg.text + "</p>");
                } else {
                    $("#chating").append("<p class='others'>" + msg.name + " : " + msg.text + "</p>");
                }

            }
            //새로운 유저가 입장하였을 경우
            else if (msg.type == "open") {
                if (msg.userName == name) {
                    $("#chating").append("<p class='start'>[채팅에 참가하였습니다.]</p>");
                } else {
                    $("#chating").append("<p class='start'>[" + msg.name + "]님이 입장하였습니다." + "</p>");
                }
            }
            //유저가 퇴장하였을 경우
            else if (msg.type == "close") {
                $("#chating").append("<p class='exit'>[" + msg.name + "]님이 퇴장하였습니다." + "</p>");

            }
            else {
                console.warn("unknown type!")
            }
        }

        ws.onclose = function () {
            console.log('웹소켓 종료...')
        }
    }

    document.addEventListener("keypress", function (e) {
        if (e.keyCode == 13) { //enter press
            send();
        }
    });
}

function chatName() {
    name = $("#userName").val();
    if (name == null || name.trim() == "") {
        alert("사용자 이름을 입력해주세요.");
        $("#userName").focus();
    } else {
        wsOpen();
        $("#chatting").attr("disabled", false);
        $("#sendBtn").show();
        $("#startBtn").hide();
    }
}

function send() {
    var obj = {
        type: "message",
        name: name,
        msg: $("#chatting").val()
    }
    //서버에 데이터 전송
    ws.send(JSON.stringify(obj))
    $('#chatting').val("");
}




function get_cookie(name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value ? value[2] : null;
}