package pl.sda.jobOffer;

import java.util.List;

public interface JobOfferService {

    List<JobOffer> findAll();
    List<JobOffer> filterByLocation(LocationDto locationDto);
    List<JobOffer> filterBySalary(SalaryDto salaryDto);
}
