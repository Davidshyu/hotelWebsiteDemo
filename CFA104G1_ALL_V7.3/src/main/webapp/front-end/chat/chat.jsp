<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>線上客服</title>
<%@ include file= "/back-end/framework/include.file" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/servicechat.css" type="text/css" />
<style type="text/css">
</style>
</head>
<body onload="connect();" onunload="disconnect();">
<div class="card direct-chat direct-chat-primary m-0 p-0">
              <div class="card-header ui-sortable-handle" style="cursor: move;">
                <h3 class="card-title" style="color: #5D0000; font-weight: bold;">彌生溫泉度假酒店</h3>
                <div class="card-tools">
			        <button type="button" class="btn btn-tool close-room" onclick="disconnect();" style="position: fixed;right: 15px;top: 15px; pedding: 0 10px !important;">
			        <i class="fas fa-times"></i>
			        </button>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <!-- Conversations are loaded here -->
                <div id="chat-list" class="direct-chat-messages" style="height: 490px;">
                </div>
                <!--/.direct-chat-messages-->
              </div>
              <!-- /.card-body -->
              <div class="card-footer">
                  <div class="input-group">
					<input id="message" class="form-control" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMsg();" /> 
                    <span class="input-group-append">
                      	<input type="submit" id="sendMessage" class="btn" value="送出" onclick="sendMsg();" />
						<input type="button" id="connect" class="button" value="重新連線" onclick="connect();" /> 
						<input type="button" id="disconnect" class="button" value="關閉" onclick="disconnect();" />
                    </span>
                  </div>
              </div>
              <!-- /.card-footer-->
</div>
</body>
<%@ include file= "/back-end/framework/includeJs.file" %>
<script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("chat-list");
	var self = '${userName}';
	var CustomerService = 'G1CS';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				var jsonObj = {
						"type" : "history",
						"sender" : self,
						"receiver" : CustomerService,
						"message" : ""
					};
				console.log(jsonObj);
				webSocket.send(JSON.stringify(jsonObj));
				
				
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var MsgInfo = makeMessage(historyData);
					messagesArea.innerHTML += MsgInfo;
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				console.log(jsonObj);
				var MsgInfo = makeMessage(jsonObj);
				messagesArea.innerHTML += MsgInfo;
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				//refreshFriendList(jsonObj);
			}
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function makeMessage(sendData) {
		var res='';
		if (sendData.sender === self) {
			res=getUserMsgRight(sendData);
		} else {
			res=getCSMsgLeft(sendData);
		}
		
		return res;
	}
	//<!-- Message. Default to the left -->
	function getCSMsgLeft(Msg){
		console.log("getCSMsgLeft:"+Msg.message);
		res='<div class="direct-chat-msg">'
	       +' <div class="direct-chat-infos clearfix">'
	       +'   <span class="direct-chat-name float-left">彌生客服</span>'
	       +'   <span class="direct-chat-timestamp float-right">' + Msg.time + '</span>'
	       +' </div>'
	       +' <img class="direct-chat-img" src="<%=request.getContextPath()+"/resources/images/favicon.png" %>" alt="message user image">'
	       +' <div class="direct-chat-text">' + Msg.message + ' </div>' 
	       +'</div>';
      	return res;
	}
	//<!-- Message to the right -->
	function getUserMsgRight(Msg){
		console.log("getUserMsgRight:"+Msg.message);
		res='<div class="direct-chat-msg right">'
		       +' <div class="direct-chat-infos clearfix">'
		       +'   <span class="direct-chat-name float-right">'+self+'</span>'
		       +'   <span class="direct-chat-timestamp float-left">' + Msg.time + '</span>'
		       +' </div>'
		       +' <img class="direct-chat-img" src="<%=request.getContextPath()+"/resources/images/who.png" %>" alt="message user image">'
		       +' <div class="direct-chat-text">' + Msg.message +' </div>' 
		       +'</div>';
      	return res;
	}
	function sendMsg() {
		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		console.log("Send Message!");
		if (message === "") {
			alert("請輸入訊息!");
			inputMessage.focus();
		} else {
			var send_time = moment().format('MM/DD HH:mm:ss');
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : CustomerService,
				"message" : message,
				"time" : send_time
			};
			console.log(jsonObj);
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
		window.parent.CloseModal(window.frameElement);
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>