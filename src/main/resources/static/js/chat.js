const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let newMessages = new Map();

function connectToChat(username) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + username, function (response) {
            let data = JSON.parse(response.body);
            if (selectedUser === data.fromLogin) {
                render(data.message, data.fromLogin);
            } else {
                newMessages.set(data.fromLogin, data.message);
                $('#usernameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">+1</span>');
            }
        });
    });
}

function sendMsg(from, text) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        fromLogin: from,
        message: text
    }));
}

function registration() {
    let username = document.getElementById("username").value;
    $.get(url + "/registration/" + username, function (response) {
        connectToChat(username);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}

function selectUser(username) {
    console.log("selecting users: " + username);
    selectedUser = username;
    let isNew = document.getElementById("newMessage_" + username) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + username);
        element.parentNode.removeChild(element);
        render(newMessages.get(username), username);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + username);
}

function fetchAll() {
    $.get(url + "/fetchAllUsers", function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')"><li>\n' +
                '                                <a href="#">\n' +
                '                                    <div class="message-avatar"><i class="status-icon status-online"></i><img src="images/user-avatar-small-03.jpg" alt="" /></div>\n' +
                '\n' +
                '                                    <div class="message-by">\n' +
                '                                        <div class="message-by-headline">\n' +
                '                                            <h5>'+users[i]+'</h5>\n' +
                '                                            <span>4 hours ago</span>\n' +
                '                                        </div>\n' +
                '                                        <p>Thanks for reaching out. I\'m quite busy right now on many</p>\n' +
                '                                    </div>\n' +
                '                                </a>\n' +
                '                            </li>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}