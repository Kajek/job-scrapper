package pl.sda.jobOffer;

import java.util.List;

public interface JobOfferService {

    List<JobOffer> getAll();
    List<JobOffer> filterByLocation(LocationDto locationDto);
    List<JobOffer> filterBySalary(SalaryDto salaryDto);
}
