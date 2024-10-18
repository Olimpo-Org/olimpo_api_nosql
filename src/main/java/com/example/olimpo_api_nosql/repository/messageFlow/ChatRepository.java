package com.example.olimpo_api_nosql.repository.messageFlow;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.olimpo_api_nosql.model.Chat;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
public class ChatRepository  {

    private final MongoTemplate mongoTemplate;

    public ChatRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Chat create(Chat chat) {
        String generatedId = NanoIdUtils.randomNanoId();
        chat.setChatId(generatedId);
        chat.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        return mongoTemplate.save(chat);
    }

    public List<Chat> getAll() {
        return mongoTemplate.findAll(Chat.class);
    }

    public List<Chat> getAllByCommunityId(String communityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        return mongoTemplate.find(query, Chat.class);
    }

    public List<Chat> getAllByUserId(String userId, String communityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        query.addCriteria(Criteria.where("usersIds").is(userId));
        return mongoTemplate.find(query, Chat.class);
    }

    public Chat addUserToChat(String chatId, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId));
        Update update = new Update();
        update.push("usersIds", userId);
        return mongoTemplate.findAndModify(query, update, Chat.class);
    }

    public boolean verifyIfChatExists(String chatId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId));
        return mongoTemplate.exists(query, Chat.class);
    }
    public Chat getChatById(String chatId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId));
        return mongoTemplate.findOne(query, Chat.class);
    }
}

