package pl.sda.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import pl.sda.dto.LocationDto;
import pl.sda.dto.SalaryDto;
import pl.sda.model.JobOffer;
import pl.sda.repository.JobOfferRepository;
import pl.sda.repository.JobScrapper;
import pl.sda.service.JobOfferService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class JobOfferServiceImpl implements JobOfferService {

    private final List<JobScrapper> jobScrappers;
    private final JobOfferRepository jobOfferRepository;


    public JobOfferServiceImpl(List<JobScrapper> jobScrappers, JobOfferRepository jobOfferRepository){
        this.jobScrappers = jobScrappers;
        this.jobOfferRepository = jobOfferRepository;
    }

    @Scheduled(fixedDelay = 2,timeUnit = TimeUnit.HOURS)
    public void populateRepository(){
        List<JobOffer> jobOffers = jobScrappers.stream().flatMap(e -> e.getJobOffers().stream()).collect(Collectors.toList());
        jobOfferRepository.replaceAll(jobOffers);
        System.out.println("populating repository");
    }


    @Override
    public List<JobOffer> getAll() {
        return jobOfferRepository.getAll();
    }

    @Override
    public List<JobOffer> filterByLocation(LocationDto locationDto) {
        List<JobOffer> allOffers = jobOfferRepository.getAll();
        List<JobOffer> offersFilteredByLocation = new ArrayList<>();
        for (JobOffer jobOffer: allOffers){
            if(jobOffer.getLocation().toLowerCase().startsWith(locationDto.getLocation().toLowerCase())){ //to do zmiany będzie bo czasem jest waszawa pol cos tam cos tam/ pewnie na coinains czy cos takiego
                offersFilteredByLocation.add(jobOffer);
            }
        }
        return offersFilteredByLocation;
    }

    public List<JobOffer> filterBySalary(SalaryDto salaryDto){
        List<JobOffer> allOffers = jobOfferRepository.getAll();
        List<JobOffer> offersFilteredBySalary = new ArrayList<>();
        String minSalary;
        String maxSalary;



        return offersFilteredBySalary;
    }

    // tutaj można dodać filtrację ++
    // co filtrować? - chyba najłatwiej liste ofert którą już mam pobraną ++
    // jak filtorwać - podając kryterium? np dla lokacji - nazwa miasta albo zdalna - zrobić dto? ++
    // filtrowanie przez salary chyba muszę rozbić Dto.salary na min i max/  z modelu tez jakoś to wyciągać - .split(" ") na salary?.ifContains("-").usunąć PLN przy wyciągnaiu
}
