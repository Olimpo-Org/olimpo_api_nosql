package com.example.olimpo_api_nosql.repository.feedFlow;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.olimpo_api_nosql.model.mongo.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    private final MongoTemplate mongoTemplate;
    private final PublicationRepository publicationRepository;

    public CommentRepository(MongoTemplate mongoTemplate, PublicationRepository publicationRepository) {
        this.mongoTemplate = mongoTemplate;
        this.publicationRepository = publicationRepository;
    }

    public Comment create(Comment comment) {
        Boolean verification = publicationRepository.verifyIfPublicationExists(comment.getPublicationId());
        if (!verification) {
            throw new RuntimeException("Publication not found");
        }
        String generatedId = NanoIdUtils.randomNanoId();
        comment.setId(generatedId);
        return mongoTemplate.save(comment);
    }

    public List<Comment> getAllOfPublication(String publicationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("publicationId").is(publicationId));
        return mongoTemplate.find(query, Comment.class);
    }
}
