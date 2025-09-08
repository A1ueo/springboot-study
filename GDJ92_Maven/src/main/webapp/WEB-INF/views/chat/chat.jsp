<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Chat Page</h1>
	<div>
		<button onclick="connect()">Connect</button>
	</div>
	<div>
		<form>
			<input type="text" id="msg">
			<button id="send">send</button>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
	
	<script type="text/javascript">
		function connect() {
			const socket = new SockJS('/ws');
			stompClient = Stomp.over(socket);
			
			stompClient.connect({}, function(frame) {
				console.log('Connected: ' + frame);
				
				stompClient.subscribe('/topic/messages', function(message) {
					console.log(message.body);
				});
			});
		}
	</script>
</body>
</html>