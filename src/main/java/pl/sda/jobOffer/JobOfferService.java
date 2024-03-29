package pl.sda.jobOffer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobOfferService {

    List<JobOffer> findAll();
    List<JobOffer> filterByParams(FilterParamsDto filterParamsDto);
    Page<JobOffer> findPaginated(Pageable pageable, List<JobOffer> jobOffers);

    Page<JobOffer> filterByPararara(FilterParamsDto filterParamsDto, Pageable pageable);
}
