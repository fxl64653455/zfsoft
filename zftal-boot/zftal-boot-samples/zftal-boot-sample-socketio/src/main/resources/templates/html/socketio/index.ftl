<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Socketio chat</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.1.0/socket.io.js"></script>
<style>
body {
    padding: 20px;
}
#console {
    height: 400px;
    overflow: auto;
}
.username-msg {
    color: orange;
}
.connect-msg {
    color: green;
}
.disconnect-msg {
    color: red;
}
.send-msg {
    color: #888
}
</style>
</head>
<body>
    <h1>Netty-socketio chat demo</h1>
    <br />
    <div id="console" class="well"></div>
    <form class="well form-inline" onsubmit="return false;">
        <input id="from" class="input-xlarge" type="text" placeholder="from. . . " />
        <input id="to" class="input-xlarge" type="text" placeholder="to. . . " />
        <input id="content" class="input-xlarge" type="text" placeholder="content. . . " />
        <button type="button" onClick="sendMessage()" class="btn">Send</button>
        <button type="button" onClick="sendDisconnect()" class="btn">Disconnect</button>
    </form>
</body>
<script type="text/javascript">
    var socket = io.connect('http://localhost:9098');
    socket.on('connect',function() {
        output('<span class="connect-msg">Client has connected to the server!</span>');
    });
     
    socket.on('onSocketEvent', function(data) {
        output('<span class="username-msg"> : ' + data.content + '</span>');
    });
     
    socket.on('disconnect',function() {
        output('<span class="disconnect-msg">The client has disconnected! </span>');
    });
     
    function sendDisconnect() {
        socket.disconnect();
    }
     
    function sendMessage() {
        var from = $("#from").val();
        var to = $("#to").val();
        var content = $('#content').val();
        socket.emit('onSocketEvent', {
            from : from,
            to : to,
            content : content
        });
    }
     
    function output(message) {
        var currentTime = "<span class='time' >" + new Date() + "</span>";
        var element = $("<div>" + currentTime + " " + message + "</div>");
        $('#console').prepend(element);
    }
</script>
</html>