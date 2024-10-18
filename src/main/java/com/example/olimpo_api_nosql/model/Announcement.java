package com.example.olimpo_api_nosql.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "announcements")
public class Announcement {
    @Id
    @Field("_id")
    private ObjectId id;

    @Field("announcement_id")
    private String announcementId;

    @Field("community_id")
    @NotNull(message = "Community id cannot be null")
    private String communityId;

    @Field("sender_id")
    @NotNull(message = "Sender id cannot be null")
    private String senderId;

    @Field("sender_name")
    private String senderName;

    @Field("images")
    @NotNull(message = "Images cannot be null")
    @Size(min = 1, max = 5, message = "Images must have between 1 and 5 elements")
    private List<String> images;

    @Field("description")
    @NotNull(message = "Description cannot be null")
    private String description;

    @Field("type")
    @NotNull(message = "Type cannot be null")
    private String type;

    @Field("sent_at")
    private Date sentAt;

    public Announcement() {
    }

    public Announcement(ObjectId id, String communityId, String senderId, String senderName,
                        List<String> images, String description, String type, Date sentAt) {
        this.id = id;
        this.communityId = communityId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.images = images;
        this.description = description;
        this.type = type;
        this.sentAt = sentAt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}

