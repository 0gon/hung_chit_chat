

class Friend {
    constructor() {

    }


    /**
     * alert로 처리중인데, 팝업창이 alert때문에 바로 종료가 안됨.
     * 나중에 다른 방식으로 처리하도록 하기
     * @param {String} friendId 
     */
    addFriend(friendId) {
        let friend = {
            id: friendId,
        }

        $.ajax({
            url: "/api/friend/addFriend",
            type: "post",
            async: false,
            contentType: "application/json",
            data: JSON.stringify(friend),
            success(result, status, xhr) {
                alert(result);
            },
            error(xhr, status, error) {
                alert("addFriend error");
            },
        })
    }

    getFriends(memberId) {
        let member = {
            id: memberId,
        }

        $.ajax({
            url: "",
            type: "post",
            async: false,
            contentType: "application/json",
            data: JSON.stringify(member),
            success(result, status, xhr) {

            },
            error(xhr, status, error) {

            },
        })
    }
}

const friend = new Friend();

export default friend;