package pl.sda.repository.impl;

import org.springframework.stereotype.Component;
import pl.sda.model.JobOffer;
import pl.sda.repository.JobOfferRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryJobOfferRepository implements JobOfferRepository {

    private List<JobOffer> jobOffers = List.of();

    @Override
    public List<JobOffer> getAll() {
        return Collections.unmodifiableList(jobOffers);
    }

    @Override
    public void replaceAll(List<JobOffer> jobOffers) {
        this.jobOffers = new ArrayList<>(jobOffers);
    }
}
