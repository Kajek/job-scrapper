package pl.sda.service;

import pl.sda.dto.LocationDto;
import pl.sda.dto.SalaryDto;
import pl.sda.model.JobOffer;

import java.util.List;

public interface JobOfferService {

    List<JobOffer> getAll();
    List<JobOffer> filterByLocation(LocationDto locationDto);
    List<JobOffer> filterBySalary(SalaryDto salaryDto);
}
