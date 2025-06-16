package com.chat.chatService.Controller;


import com.chat.chatService.Model.MessageEntity;
import com.chat.chatService.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/send")
    public void RecieveMessage(MessageEntity message){
        chatService.processAndBroadcastMessage(message);
    }


//    @GetMapping("/start")
//    public String helloController() throws InterruptedException {
//        chatService.executeThread();
//        return "Threaad  Executed";
//    }
}
