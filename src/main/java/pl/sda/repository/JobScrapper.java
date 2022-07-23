package pl.sda.repository;

import pl.sda.model.JobOffer;

import java.util.List;

public interface JobScrapper {

    List<JobOffer> getJobOffers();
}
