package pl.sda.jobScrapper;

import pl.sda.jobOffer.JobOffer;

import java.util.List;


public class JustJoinItJobScraper implements JobScraper {

    //mock
    @Override
    public List<JobOffer> getJobOffers() {
        return List.of();
    }

}
