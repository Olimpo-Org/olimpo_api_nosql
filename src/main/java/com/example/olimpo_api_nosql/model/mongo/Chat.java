package com.example.olimpo_api_nosql.model.mongo;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Document(collection = "chats")
public class Chat {
    @Id
    @Field("_id")
    private ObjectId id;

    @Field("chatId")
    private String chatId;

    @NotNull(message = "Community id cannot be null")
    @Field("community_id")
    private String communityId;

    @NotNull(message = "Users ids cannot be null")
    @Field("users_ids")
    private List<String> usersIds;

    @NotNull(message = "Chat name cannot be null")
    @Field("chat_name")
    private String chatName;

    @NotNull(message = "Chat owners cannot be null")
    @Field("chat_owners")
    private List<String> chatOwners;

    @NotNull(message = "Channel type cannot be null")
    @Field("channel_type")
    private String channelType;

    @Field("created_at")
    private Date createdAt;

    public Chat() {
    }

    public Chat(ObjectId id, String chatId, String communityId, List<String> usersIds, String chatName, List<String> chatOwners, String channelType, Date createdAt) {
        this.id = id;
        this.chatId = chatId;
        this.communityId = communityId;
        this.usersIds = usersIds;
        this.chatName = chatName;
        this.chatOwners = chatOwners;
        this.channelType = channelType;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public List<String> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<String> usersIds) {
        this.usersIds = usersIds;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public List<String> getChatOwners() {
        return chatOwners;
    }

    public void setChatOwners(List<String> chatOwners) {
        this.chatOwners = chatOwners;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
