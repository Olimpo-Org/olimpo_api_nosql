package com.example.olimpo_api_nosql.repository.feedFlow;

import com.example.olimpo_api_nosql.model.postgres.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}