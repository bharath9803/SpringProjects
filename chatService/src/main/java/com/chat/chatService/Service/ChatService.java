package com.chat.chatService.Service;

import com.chat.chatService.Model.MessageEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Async
    public void processAndBroadcastMessage(MessageEntity message) {
        // Simulate processing
        System.out.println("Processing message in thread: " + Thread.currentThread().getName());
        System.out.println("From: " + message.getSender() + " | Message: " + message.getContent());

        // Broadcast to all subscribers
        messagingTemplate.convertAndSend("/sends/messages", message);
    }
}
