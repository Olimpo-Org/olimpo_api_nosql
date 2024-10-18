
package com.example.olimpo_api_nosql.model.mongo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field("_id")
    private String id;

    @Field("comment_id")
    private String commentId;

    @Field("publication_id")
    @NotNull(message = "Publication id cannot be null")
    private String publicationId;

    @NotNull(message = "Sender id cannot be null")
    @Field("sender_id")
    private String senderId;

    @Field("sender_name")
    private String senderName;

    @NotNull(message = "Content cannot be null")
    @Field("content")
    private String content;

    @Field("sender_image")
    private String senderImage;

    public Comment() {
    }
    public Comment(String id, String publicationId, String senderId, String senderName, String content, String senderImage) {
        this.id = id;
        this.publicationId = publicationId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.senderImage = senderImage;
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
    public String getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }
}
