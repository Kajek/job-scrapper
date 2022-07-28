package pl.sda.jobOffer;

import java.util.List;

public interface JobOfferService {

    List<JobOffer> findAll();
    List<JobOffer> filterByParams(FilterParamsDto filterParamsDto);
}
