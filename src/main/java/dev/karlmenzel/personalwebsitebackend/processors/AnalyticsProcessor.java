package dev.karlmenzel.personalwebsitebackend.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.karlmenzel.personalwebsitebackend.repositories.AnalyticsRepository;

@Component
public class AnalyticsProcessor {

    public final AnalyticsRepository analyticsRepository;
    
    @Autowired
    public AnalyticsProcessor(AnalyticsRepository ar){
        analyticsRepository = ar;
    }

    public String getSiteVisitsCount(){
        analyticsRepository.incrementSiteVisitorsCounter();
        return analyticsRepository.getSiteVisitsCountEntityByName("totalSiteVisits").getCount();
    }
}