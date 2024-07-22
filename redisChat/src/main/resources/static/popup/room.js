import { getCookie, getUrlParam } from "/util.js";

window.receiveValueFromParent = receiveValueFromParent;

let memberId = getCookie("member_id");
let nickName = getCookie("member_nickName");
let roomId = null;
let members = [];

$(function () {
    roomId = getUrlParam("roomId");
    members = getUrlParam("members").split(",");
})


function newMsg(type, text) {
    let msg = {
        type,
        memberId,
        nickName,
        text,
        roomId,
        members
    }
    return JSON.stringify(msg);
}

$(document).on('keydown', '#chatting', function (e) {
    if (e.keyCode == 13) { //enter press
        send();
    }
})

$(document).on('click', '#sendBtn', function () {
    send();
})


function send() {
    let text = $('#chatting').val();
    let msg = newMsg("message", text);
    $('#chatting').val("");
    deliveToParent(msg);
}

function deliveToParent(value) {
    if (window.opener) {
        window.opener.receiveValueFromRoom(value);
    } else {
        alert('No opener found!');
    }
}

function receiveValueFromParent(value) {
    drawMsg(value);
}

function drawMsg(e) {
    var msgJson = e.data; // 전달 받은 데이터
    var msg = JSON.parse(msgJson);
    console.log('msg', msg);

    //채팅 메시지를 전달받은 경우
    if (msg.type == "message") {
        if (msg.memberId == memberId) { // 보낸사람이 나일때
            $("#chating").append("<p class='me'>" + msg.text + "</p>");
        } else { // 보낸사람이 다른사람일때
            $("#chating").append("<p class='others'>" + msg.nickName + " : " + msg.text + "</p>");
        }
    }
    //새로운 유저가 입장하였을 경우
    else if (msg.type == "open") {
        if (msg.memberId == memberId) {
            $("#chating").append("<p class='start'>[채팅에 참가하였습니다.]</p>");
        } else {
            $("#chating").append("<p class='start'>[" + msg.nickName + "]님이 입장하였습니다." + "</p>");
        }
    }
    //유저가 퇴장하였을 경우
    else if (msg.type == "close") {
        $("#chating").append("<p class='exit'>[" + msg.nickName + "]님이 퇴장하였습니다." + "</p>");

    }
    else {
        console.warn("unknown type!")
    }
}


