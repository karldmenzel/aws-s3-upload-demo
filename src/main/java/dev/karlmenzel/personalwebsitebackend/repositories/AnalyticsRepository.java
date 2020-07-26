package dev.karlmenzel.personalwebsitebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.karlmenzel.personalwebsitebackend.entities.AnalyticsEntity;

@Transactional
@Repository
public interface AnalyticsRepository extends JpaRepository<AnalyticsEntity, Integer> {
    
    @Modifying
    @Query(value = "UPDATE AnalyticsEntity counter SET counter.count = counter.count + 1 WHERE counter.name = \'totalSiteVisits\'")
    void incrementSiteVisitorsCounter();

    AnalyticsEntity getSiteVisitsCountEntityByName(String name);
}