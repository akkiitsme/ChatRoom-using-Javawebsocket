package com.chatroom.chatmessenger;

import com.chatroom.chattingroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    @Autowired
    private final ChatMessageRepository chatMessageRepository;
    @Autowired
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage message){
        var chatId = chatRoomService.getChatRoomId(message.getSenderId(),message.getRecipientId(),true)
                .orElseThrow();
        message.setChatId(chatId);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> findChatMessages(String senderId,String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId,false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }

}
