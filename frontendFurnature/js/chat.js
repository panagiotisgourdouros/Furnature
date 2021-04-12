

$("#actualModal").load("chat-modal.html", function () {
  
  var socket;
  var count = 0;

    var messageFormChat = document.querySelector("#messageFormChat");
    var messageInputChat = document.querySelector("#messageChat");
    var messageArea = document.querySelector("#messageArea");
    var connectingElement = document.querySelector(".connecting");

    var stompClient = null;

    let userDetails;
    let username;

    var colors = [
      "#2196F3",
      "#32c787",
      "#00BCD4",
      "#ff5652",
      "#ffc107",
      "#ff85af",
      "#FF9800",
      "#39bbb0",
    ];

    $("#chatModalButtonOpen").click(function () {
      
      if (localStorage.getItem("authResponse") != null) {
        $("#chatModal").modal("show");
        connect();
      }else{
        $(".message").html("Please Login in order to Chat!");
        window.setTimeout(function () {
          window.location.href = "login-form.html";
        }, 3000);
      }
     
    });

    $("#chatModalButtonClose").click(function () {
      socket.close();
      $("#chatModal").modal("hide");
      location.reload();
    });

    function connect() {
      console.log(">>>>>>connecting...");
      socket = new SockJS("http://localhost:8080/ws");
      stompClient = Stomp.over(socket);
      stompClient.connect({}, onConnected, onError);
    }

    function onConnected() {
      userDetails=JSON.parse(localStorage.getItem("authResponse"));
      username= userDetails.username;
      if (count === 0) {
        console.log(">>>>>>>>OnConnected");
        connectingElement.textContent = "Chatting as " + username;
        // Subscribe to the Public Topic
        stompClient.subscribe("/topic/public", onMessageReceived, {
          id: userDetails.id,
        });

        // Tell your username to the server
        stompClient.send(
          "/chat.addUser",
          {},
          JSON.stringify({ sender: username, type: "JOIN" })
        );

        connectingElement.classList.add("hidden");
        count++;
      }
    }

    function onError() {
      connectingElement.textContent =
        "Could not connect to WebSocket server, try again!";
      connectingElement.style.color = "red";
    }

    function sendMessage() {
      userDetails=JSON.parse(localStorage.getItem("authResponse"));
      username= userDetails.username;
      var messageContent = messageInputChat.value.trim();
      if (messageContent && stompClient) {
        var chatMessage = {
          sender: username,
          content: messageInputChat.value,
          type: "CHAT",
        };
        stompClient.send(
          "/app/chat.sendMessage",
          {},
          JSON.stringify(chatMessage)
        );

        messageInputChat.value = "";
      }
    }

    function onMessageReceived(payload) {
      var message = JSON.parse(payload.body);
      var messageElement = document.createElement("li");

      if (message.type === "JOIN") {
        messageElement.classList.add("event-message");
        message.content = message.sender + " joined!";
      } else if (message.type === "LEAVE") {
        messageElement.classList.add("event-message");
        message.content = message.sender + " left!";
      } else {
        messageElement.classList.add("chat-message");
      }
      var avatarElement = document.createElement("i");
      var avatarText = document.createTextNode(message.sender[0]);
      avatarElement.appendChild(avatarText);
      avatarElement.style["background-color"] = getAvatarColor(message.sender);
      messageElement.appendChild(avatarElement);

      var usernameElement = document.createElement("span");
      var usernameText = document.createTextNode(message.sender);
      usernameElement.appendChild(usernameText);
      console.log(usernameText);
      messageElement.appendChild(usernameElement);

      var textElement = document.createElement("p");
      var messageText = document.createTextNode(message.content);
      textElement.appendChild(messageText);
      messageElement.appendChild(textElement);

      messageArea.appendChild(messageElement);
      messageArea.scrollTop = messageArea.scrollHeight;
    }

    function getAvatarColor(messageSender) {
      var hash = 0;
      for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
      }
      var index = Math.abs(hash % colors.length);
      return colors[index];
    }

    $("#messageFormSend").click(function () {
      console.log("clicked");
      sendMessage();
    });

    /* $("#chatModal").modal({
        backdrop: "static",
        keyboard: false,
      });*/

    $("#messageChat").keypress(function (e) {
      var key = e.which;
      if (key == 13) {
        $("#messageFormSend").click();
        return false;
      }
    });
  
 
});



