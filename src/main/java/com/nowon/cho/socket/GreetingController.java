package com.nowon.cho.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
	
	@MessageMapping("/hello") //prefix+"/hello"
	@SendTo("/topic/greetings") //Greeting 응답을 -> /topic/greetings 구독하는 사용자
	public Greeting greeting(HelloMessage message) {
		System.out.println("수신된 데이터:"+message.getContent());
		return new Greeting(message.getContent());
	}

}
