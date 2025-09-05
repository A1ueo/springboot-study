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
		<form>
			<input type="text" id="msg">
			<button id="send">send</button>
		</form>
	</div>
	
	<script type="text/javascript">
		const send = document.getElementById('send');
		const msg = document.getElementById('msg');
		
		// websocket 연결
		const socket = new WebSocket('ws://192.168.1.10/chat')
		
		socket.addEventListener('open', function() {
			console.log('소켓 연결 성공');
			
		});
		
		socket.addEventListener('message', (e) => {
			// console.log('메세지 수신');
			console.log(e.data)
		});
		
		socket.addEventListener('close', (e) => {
			console.log('연결 종료');
		});
		
		socket.addEventListener('error', (e) => {
			console.log('에러 발생');
		});
		
		send.addEventListener('click', function(e) {
			e.preventDefault();
			
			const m = msg.value;
			socket.send(m);
			msg.value = '';
		});
	</script>
</body>
</html>