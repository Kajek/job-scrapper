package pl.sda.jobOffer;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository {

    //extends JpaRepository<JobOffer, String>
    // dodać zależność SpringData

    List<JobOffer> getAll();
    void replaceAll(List<JobOffer> jobOffers);

}
