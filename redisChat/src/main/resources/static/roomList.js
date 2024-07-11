import friend from "/component/friend.js";

var popup = null;
window.receiveValueFromChild = receiveValueFromChild;
window.unlockParent = unlockParent;

$(function () {
    // AJAX 요청 예시 (실제로는 서버 API와 연결하여 데이터를 가져와야 함)
    setTimeout(function () {
        var rooms = [
            { id: 1, name: "방 이름 1" },
            { id: 2, name: "방 이름 2" },
            { id: 3, name: "방 이름 3" }
        ];

        var roomListElement = document.getElementById("room-list");
        rooms.forEach(function (room) {
            var roomItem = document.createElement("div");
            roomItem.classList.add("room-item");
            roomItem.textContent = room.name;
            roomListElement.appendChild(roomItem);
        });
    }, 1); // 예시로 1초 후에 데이터를 가져오는 것으로 설정


})

$(document).on("click", ".addFriendBtn", function () {
    openPopup("/popup/addFriend");
});


$(document).on('click', '.makeRoomBtn', function () {
    openPopup("/popup/makeRoom");
})



function openPopup(uri) {
    lockParent();

    if (popup && !popup.closed) {
        popup.close();
    }

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

    let url = $(location).attr('origin') + uri;

    // 팝업 창 열기
    popup = window.open(url, 'popup_test', `width=${popupWidth},height=${popupHeight},left=${left},top=${top}`);

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