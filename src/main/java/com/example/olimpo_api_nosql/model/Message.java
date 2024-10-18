package com.example.olimpo_api_nosql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "Message")
public class Message {

    @Id
    @Field("_id")
    private String id;

    @Field("message_id")
    private String messageId;

    @NotNull(message = "Chat id cannot be null")
    @Field("chat_id")
    private String chatId;

    @NotNull(message = "Sender id cannot be null")
    @Field("sender_id")
    private String senderId;

    @NotNull(message = "Sender name cannot be null")
    @Field("sender_name")
    private String senderName;

    @NotNull(message = "Content cannot be null")
    @Max(value = 600, message = "Content cannot be longer than 600 characters")
    @Field("content")
    private String content;

    @Field("sent_at")
    private Date sentAt;

    // Constructors
    public Message(String chatId, String senderId, String senderName, String content, Date sentAt) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Message() {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
