package com.example.olimpo_api_nosql.service.messageFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.mongo.Chat;
import com.example.olimpo_api_nosql.repository.messageFlow.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat create(Chat chat) {
        try {
            return chatRepository.create(chat);
        } catch (Exception e) {
            ExceptionThrower.throwBadRequestException("Chat not created");
            return null;
        }
    }

    public Chat addUserToChat(String chatId, String userId) {
        return chatRepository.addUserToChat(chatId, userId);
    }

    public List<Chat> getAll() {
        return chatRepository.getAll();
    }

    public List<Chat> getAllByCommunityId(String communityId) {
        return chatRepository.getAllByCommunityId(communityId);
    }

    public List<Chat> getAllByUserId(String userId, String communityId) {
        return chatRepository.getAllByUserId(userId, communityId);
    }

    public Chat getChatById(String chatId) {
        return chatRepository.getChatById(chatId);
    }
    public boolean verifyIfChatExists(String chatId) {
        return chatRepository.verifyIfChatExists(chatId);
    }


}
