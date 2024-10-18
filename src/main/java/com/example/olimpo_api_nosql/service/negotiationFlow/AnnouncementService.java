package com.example.olimpo_api_nosql.service.negotiationFlow;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.olimpo_api_nosql.model.Announcement;
import com.example.olimpo_api_nosql.repository.negotiationFlow.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement create(Announcement announcement) {
        announcement.setAnnouncementId(NanoIdUtils.randomNanoId());
        return announcementRepository.create(announcement);
    }

    public List<Announcement> getAllOfUser(String communityId, String userId) {
        return announcementRepository.getAllOfUser(communityId, userId);
    }

    public List<Announcement> getAllServices(String communityId) {
        return announcementRepository.getAllServicesOfCommunity(communityId);
    }

    public List<Announcement> getAllSales(String communityId) {
        return announcementRepository.getAllSalesOfCommunity(communityId);
    }

    public List<Announcement> getAllDonations(String communityId) {
        return announcementRepository.getAllDonationsOfCommunity(communityId);
    }

    public List<Announcement> getAllOfCommunity(String communityId) {
        return announcementRepository.getAllOfCommunity(communityId);
    }

}
