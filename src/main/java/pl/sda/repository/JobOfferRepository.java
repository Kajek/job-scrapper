package pl.sda.repository;

import org.springframework.stereotype.Repository;
import pl.sda.model.JobOffer;

import java.util.List;

@Repository
public interface JobOfferRepository {

    //extends JpaRepository<JobOffer, String>
    // dodać zależność SpringData

    List<JobOffer> getAll();
    void replaceAll(List<JobOffer> jobOffers);

}
