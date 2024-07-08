import friend from "/component/friend.js";

var popup = null;

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
    }, 1000); // 예시로 1초 후에 데이터를 가져오는 것으로 설정


    friend.addFriend("asdf");
})

$(document).on("click", ".addFriendBtn", openPopup);


function openPopup() {
    let url = $(location).attr('origin') + "/test";
    makePopup();
}

function makePopup() {

    if (popup && !popup.closed) {
        popup.close();
    }
    
    // 팝업 창의 너비와 높이
    let popupWidth = 500;
    let popupHeight = 700;

    // 화면 중앙에서 팝업의 위치를 계산 - 위치 수동조정
    let left = Math.ceil((window.screen.width - popupWidth) / 2) - 50;
    let top = Math.ceil((window.screen.height - popupHeight) / 2) - 50;

    // 팝업 창 열기
    popup = window.open('', 'popup_test', `width=${popupWidth},height=${popupHeight},left=${left},top=${top}`);

    // 팝업 차단 여부 확인
    if (!popup || popup.closed || typeof popup.closed === 'undefined') {
        alert('팝업이 차단되었습니다.');
    } else {
        // 팝업이 정상적으로 열렸을 때, 팝업 내용 추가
        const popupContent = `
        <html>
        <head>
          <title>팝업</title>
          <style>
            body { text-align: center; padding: 20px; }
            button { padding: 10px 20px; font-size: 16px; }
          </style>
        </head>
        <body>
          <h1>팝업 내용</h1>
          <p>이곳에 팝업 내용을 추가할 수 있습니다.</p>
          <button onclick="closePopup()">팝업 닫기</button>
          <script>
            function closePopup() {
              window.close();
            }
          </script>
        </body>
        </html>
      `;

        popup.document.write(popupContent);
    }
}


window.addEventListener('unload', () => {
    if (popup && !popup.closed) {
        popup.close();
    }
});


$(document).on('click', '.test', function() {
    console.log(popup);
    if (popup && !popup.closed) {
        popup.close();
    }
})