/**
 * 
 */
var client;
function setConnected(connected){
	$("#open-chat").prop("disabled",connected);
	$("#disconnect").prop("disabled",!connected);
	if(connected){
		$("#chat").show();
	}else{
		$("#chat").hide();
	}
	$("#greetings").html("");
}

function connect(){
	client=Stomp.over(new SockJS("/myWebsocket"));
	client.connect({}, (frame)=>{
		//console.log(frame);
		setConnected(true);
		//구독
		client.subscribe("/topic/greetings",function(msg){
			//메세지 수신
			var greeting=JSON.parse(msg.body);
			
			var tag=`
				<div class="question flex end">
					<div class="user-question">
						${greeting.content}
					</div>
				</div>
			`;
			showMessage(tag);
		});
	});
}

function sendContent(){
	var content=$("#content").val().trim();
	console.log(content.length);
	if(content.length<3){
		alert("이름을 2글자이상 입력하세요");
		return;
	}
	var data={content:content};
	client.send("/app/hello", {}, JSON.stringify(data));
	event.preventDefault();
}
function showMessage(tag){
	$("#greetings").append(tag);
	$("#content").val("");
}
function disconnect(){
	if(client!=null)client.disconnect(function(){
		setConnected(false);
	});
}
 $(function(){
	 $("#chat.form").submit((e)=>e.preventDefault());
	 //$("a").click((e)=>e.preventDefault());
	 $( "#open-chat" ).click(connect);
	 //$( "#connect" ).click(()=>connect());
   	 $( "#disconnect" ).click(disconnect);
     $( "#send" ).click(sendContent);
 });