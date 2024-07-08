

class Friend {
    constructor() {

    }

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
                console.log('xhr', xhr)
                console.log('status', status)
                console.log('result', result)

            },
            error(xhr, status, error) {
                console.log('error', error)
                console.log('status', status)
                console.log('xhr', xhr)

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