package com.example.olimpo_api_nosql.service.messageFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.Message;
import com.example.olimpo_api_nosql.repository.messageFlow.ChatRepository;
import com.example.olimpo_api_nosql.repository.messageFlow.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public List<Message> getAllMessagesOfAChat(String chatId) {
        if (!chatRepository.verifyIfChatExists(chatId)) {
            ExceptionThrower.throwNotFoundException("Chat not found");
        }
        List<Message> messages = messageRepository.getFromChatOrderBySentAtAsc(chatId);
        if (messages.isEmpty()) {
            ExceptionThrower.throwNotFoundException("Messages not found");
        }
        return messages;
    }

    public Message sendMessage(Message message) {
        try {
            if (!chatRepository.verifyIfChatExists(message.getChatId())) {
                ExceptionThrower.throwBadRequestException("Chat not found");
            }
            return messageRepository.create(message);
        } catch (Exception e) {
            ExceptionThrower.throwBadRequestException("Message not sent");
        }
        return null;
    }

    public Message deleteMessage(String messageId) {
        return messageRepository.delete(messageId);
    }
}
