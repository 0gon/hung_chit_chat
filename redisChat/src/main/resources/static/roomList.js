import friend from "/component/friend.js";

var popup = null;
window.receiveValueFromChild = receiveValueFromChild;
window.unlockParent = unlockParent;

$(function () {
    // AJAX 요청 예시 (실제로는 서버 API와 연결하여 데이터를 가져와야 함)
    $.ajax({
        url: "/api/room/getRooms",
        type: "post",
        async: false,
        contentType: "application/json",
        success(result, status, xhr) {
            console.log(result);
            drawRooms(result);
        },
        error(xhr, status, error) {
            console.error(error)
        },
    })

    function drawRooms(rooms) {
        var roomListElement = document.getElementById("room-list");
        rooms.forEach(function (room) {
            var roomItem = document.createElement("div");
            roomItem.classList.add("room-item");

            var span = document.createElement("span");
            span.textContent = room.id;
            span.classList.add("room_id");
            roomItem.appendChild(span);

            var div = document.createElement("div");
            let memberIds = [];
            room.memberRooms.forEach(function (memberRoom) {
                memberIds.push(memberRoom.memberId);
            })

            div.textContent = memberIds.join(',');
            roomItem.appendChild(div);
            roomListElement.appendChild(roomItem);
        });
    }


})

$(document).on("click", ".addFriendBtn", function () {
    openPopup("/popup/addFriend");
});


$(document).on('click', '.makeRoomBtn', function () {
    openPopup("/popup/makeRoom");
})

$(document).on('click', '.room-item', function () {
    let roomId = $(this).find('.room_id').html();
    openRoom(`/popup/room?roomId=${roomId}`, roomId);
})


function openRoom(uri, roomId) {
    let url = $(location).attr('origin') + uri;
    let features = getPopupFeatures();

    // 팝업 창 열기
    let roomPopup = window.open(url, `room_${roomId}`, features);

    // 팝업 차단 여부 확인
    if (!roomPopup || roomPopup.closed || typeof roomPopup.closed === 'undefined') {
        alert('팝업이 차단되었습니다.');
    }
}



function openPopup(uri) {
    lockParent();

    if (popup && !popup.closed) {
        popup.close();
    }


    let url = $(location).attr('origin') + uri;
    let features = getPopupFeatures();

    // 팝업 창 열기
    popup = window.open(url, 'popup_test', features);

    // 팝업 차단 여부 확인
    if (!popup || popup.closed || typeof popup.closed === 'undefined') {
        alert('팝업이 차단되었습니다.');
    }
}


window.addEventListener('unload', () => {
    if (popup && !popup.closed) {
        popup.close();
    }
});

function lockParent() {
    $('#modalOverlay').show();
}

function unlockParent() {
    $('#modalOverlay').hide();
}

function receiveValueFromChild(friendId) {
    unlockParent();
    friend.addFriend(friendId);
    console.log("finished")
}


function getPopupFeatures() {
    // 팝업 창의 너비와 높이
    let popupWidth = 500;
    let popupHeight = 700;

    // 부모 창의 위치와 크기
    let parentLeft = window.screenX || window.screenLeft;
    let parentTop = window.screenY || window.screenTop;
    let parentWidth = window.innerWidth;
    let parentHeight = window.innerHeight;

    // 부모 창의 중앙에서 팝업의 위치를 계산
    let left = parentLeft + Math.ceil((parentWidth - popupWidth) / 2);
    let top = parentTop + Math.ceil((parentHeight - popupHeight) / 2);
    return `width=${popupWidth},height=${popupHeight},left=${left},top=${top}`;
}