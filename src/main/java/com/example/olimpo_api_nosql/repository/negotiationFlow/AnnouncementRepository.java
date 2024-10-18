package com.example.olimpo_api_nosql.repository.negotiationFlow;

import com.example.olimpo_api_nosql.model.Announcement;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementRepository {

    private final MongoTemplate mongoTemplate;

    public AnnouncementRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Announcement create(Announcement announcement) {
        announcement.setAnnouncementId(String.valueOf(System.currentTimeMillis()));
        return mongoTemplate.save(announcement);
    }

    public List<Announcement> getAllOfCommunity(String communityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        return mongoTemplate.find(query, Announcement.class);
    }

    public List<Announcement> getAllOfUser(String communityId, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Announcement.class);
    }

    public List<Announcement> getAllServicesOfCommunity(String communityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        query.addCriteria(Criteria.where("type").is("service"));
        return mongoTemplate.find(query, Announcement.class);
    }

    public List<Announcement> getAllSalesOfCommunity(String communityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        query.addCriteria(Criteria.where("type").is("sale"));
        return mongoTemplate.find(query, Announcement.class);
    }

    public List<Announcement> getAllDonationsOfCommunity(String communityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("communityId").is(communityId));
        query.addCriteria(Criteria.where("type").is("donation"));
        return mongoTemplate.find(query, Announcement.class);
    }
}
