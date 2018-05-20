
var stompClient = null;

function connect() {
    // connect to websocket endpoint if user is in chat room        
    if (userInChatRoom) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected);
    }        
}

function onConnected() {
    // Subscribe to the chatroom
    //alert("ws connected");
    stompClient.subscribe('/topic/chatroom', onMessageReceived);
}

function onMessageReceived(payload) {
    //alert("message received: " + payload);
    var chatMessage = JSON.parse(payload.body);
    //alert("message: " + chatMessage);
    //alert("content: " + chatMessage.content);
    $("#chat").append("<p>" + chatMessage.sender + " : " + chatMessage.content + "</p>");    
}

$(document).ready(function() {    
    //alert("doc ready");
    $("form").submit(function(e) {
        //alert("form submitted");
        var messageContent = $("#message").val().trim();
        if (stompClient && messageContent) {
            var chatMessage = {
                content: messageContent,
                sender: username                
            };
            stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
            $("#message").val("");
        }
        e.preventDefault();
    });
    connect();
});

