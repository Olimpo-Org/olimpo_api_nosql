package com.example.olimpo_api_nosql.repository.messageFlow;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.olimpo_api_nosql.model.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {

    private final MongoTemplate mongoTemplate;

    public MessageRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Message create(Message message) {
        String generatedId = NanoIdUtils.randomNanoId();
        message.setMessageId(generatedId);

        return mongoTemplate.save(message);
    }

    public List<Message> getFromChatOrderBySentAtAsc(String chatId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId));
        query.with(Sort.by(Sort.Direction.ASC, "sentAt"));
        return mongoTemplate.find(query, Message.class);
    }

    public boolean verifyIfMessageExists(String messageId) {
        return mongoTemplate.exists(Query.query(Criteria.where("messageId").is(messageId)), Message.class);
    }

    public Message delete(String messageId) {
        return mongoTemplate.findAndRemove(Query.query(Criteria.where("messageId").is(messageId)), Message.class);
    }

}

