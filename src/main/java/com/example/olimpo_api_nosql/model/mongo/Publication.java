package com.example.olimpo_api_nosql.model.mongo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field("publication_id")
    private String publicationId;

    @NotNull(message = "Community id cannot be null")
    @Field("community_id")
    private String communityId;

    @NotNull(message = "Sender id cannot be null")
    @Field("sender_id")
    private String senderId;

    @Field("sender_name")
    private String senderName;

    @Field("images")
    private List<String> images;

    @NotNull(message = "Description cannot be null")
    @Max(message = "Description cannot be longer than 300 characters", value = 300)
    @Field("description")
    private String description;

    @Field("likes")
    private List<String> likes;

    public Publication(
            String publicationId,
            String communityId,
            String senderId,
            String senderName,
            List<String> images,
            String description,
            List<String> likes
    ) {
        this.publicationId = publicationId;
        this.communityId = communityId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.images = images;
        this.description = description;
        this.likes = likes;
    }

    public Publication() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
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

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
}
