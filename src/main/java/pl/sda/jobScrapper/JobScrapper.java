package pl.sda.jobScrapper;

import pl.sda.jobOffer.JobOffer;

import java.util.List;

public interface JobScrapper {

    List<JobOffer> getJobOffers();
}
