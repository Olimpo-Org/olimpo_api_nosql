package com.example.olimpo_api_nosql.service.feedFlow;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.olimpo_api_nosql.model.mongo.Publication;
import com.example.olimpo_api_nosql.model.postgres.Advertisement;
import com.example.olimpo_api_nosql.repository.feedFlow.AdvertisementRepository;
import com.example.olimpo_api_nosql.repository.feedFlow.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final AdvertisementRepository advertisementRepository;
    private PublicationService(
            PublicationRepository publicationRepository,
            AdvertisementRepository advertisementRepository) {
        this.publicationRepository = publicationRepository;
        this.advertisementRepository = advertisementRepository;
    }
    public Publication create(Publication publication) {
        publication.setPublicationId(NanoIdUtils.randomNanoId());
        return publicationRepository.create(publication);
    }

    public List<Object> getAllOfCommunity(String communityId) {
        List<Publication> publicationList = publicationRepository.getAllOfCommunity(communityId);
        List<Advertisement> advertisementList = advertisementRepository.findAll();
        if (advertisementList.isEmpty()) {
            return new ArrayList<>(publicationList);
        }
        List<Object> combinedList = new ArrayList<>();
        int adIndex = 0;
        for (int i = 0; i < publicationList.size(); i++) {
            combinedList.add(publicationList.get(i));
            if ((i + 1) % 4 == 0 && adIndex < advertisementList.size()) {
                combinedList.add(advertisementList.get(adIndex));
                adIndex++;
            }
        }
        return combinedList;
    }


    public List<Publication> getAllOfUser(String communityId, String userId) {
        return publicationRepository.getAllOfUser(communityId, userId);
    }

    public boolean verifyIfPublicationExists(String id) {
        return publicationRepository.verifyIfPublicationExists(id);
    }

    public List<String> likePublication(String publicationId, String userId) {
        return publicationRepository.likePublication(publicationId, userId);
    }

    public List<String> unlikePublication(String publicationId, String userId) {
        return publicationRepository.unlikePublication(publicationId, userId);
    }

}
