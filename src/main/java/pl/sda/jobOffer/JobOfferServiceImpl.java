package pl.sda.jobOffer;


import org.springframework.scheduling.annotation.Scheduled;
import pl.sda.jobScrapper.JobScrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class JobOfferServiceImpl implements JobOfferService {

    private final List<JobScrapper> jobScrappers;
    private final JobOfferRepository jobOfferRepository;


    public JobOfferServiceImpl(List<JobScrapper> jobScrappers, JobOfferRepository jobOfferRepository) {
        this.jobScrappers = jobScrappers;
        this.jobOfferRepository = jobOfferRepository;
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.HOURS)
    public void populateRepository() {
        jobOfferRepository.deleteAllInBatch();
        List<JobOfferEntity> jobOffersEntityList = jobScrappers
                .stream()
                .flatMap(e -> e.getJobOffers().stream())
                .map(e -> JobOfferEntity.from(e))
                .collect(Collectors.toList());
        jobOfferRepository.saveAll(jobOffersEntityList);
        System.out.println("populating repository");
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferRepository.
                findAll().
                stream().
                map(e -> JobOffer.from(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobOffer> filterByLocation(LocationDto locationDto) {
//        return jobOfferRepository.findByLocation(locationDto.getLocation()).stream()
//                .map(e -> JobOffer.from(e))
//                .collect(Collectors.toList()); //problem z uwzględnieniem wielkości liter i końcówek PL
        List<JobOffer> allOffers = findAll();
        List<JobOffer> offersFilteredByLocation = new ArrayList<>();
        for (JobOffer jobOffer: allOffers){
            if(jobOffer.getLocation().toLowerCase().startsWith(locationDto.getLocation().toLowerCase())){
                offersFilteredByLocation.add(jobOffer);
            }
        }
        return offersFilteredByLocation;
    }

    @Override
    public List<JobOffer> filterBySalary(SalaryDto salaryDto) {
        Double minSalary;
        if (salaryDto.getMinSalary() == null) {
            minSalary = Double.MIN_VALUE;
        } else {
            minSalary = salaryDto.getMinSalary();
        }
        Double maxSalary;
        if (salaryDto.getMaxSalary() == null) {
            maxSalary = Double.MAX_VALUE;
        } else {
            maxSalary = salaryDto.getMaxSalary();
        }
        return jobOfferRepository.findByMinSalaryGreaterThanEqualAndMaxSalaryLessThanEqual(minSalary, maxSalary).stream()
                .map(e -> JobOffer.from(e))
                .collect(Collectors.toList());
    }

//    rozwiązanie bez wykorzystania funkcjonalności springa
//    @Override
//    public List<JobOffer> filterBySalary(SalaryDto salaryDto) {
//        List<JobOffer> allOffers = findAll();
//        List<JobOffer> offersFilteredBySalary = new ArrayList<>();
//        Double minSalary;
//        if (salaryDto.getMinSalary() == null) {
//            minSalary = Double.MIN_VALUE;
//        } else {
//            minSalary = salaryDto.getMinSalary();
//        }
//        Double maxSalary;
//        if (salaryDto.getMaxSalary() == null) {
//            maxSalary = Double.MAX_VALUE;
//        } else {
//            maxSalary = salaryDto.getMaxSalary();
//        }
//        for (JobOffer jobOffer : allOffers) {
//            if ((jobOffer.getMinSalary() >= minSalary) && (jobOffer.getMaxSalary() <= maxSalary)) {
//                offersFilteredBySalary.add(jobOffer);
//            }
//        }
//        return offersFilteredBySalary;
//    }

    // tutaj można dodać filtrację ++
    // co filtrować? - chyba najłatwiej liste ofert którą już mam pobraną ++
    // jak filtorwać - podając kryterium? np dla lokacji - nazwa miasta albo zdalna - zrobić dto? ++
    // filtrowanie przez salary chyba muszę rozbić Dto.salary na min i max/  z modelu tez jakoś to wyciągać - .split(" ") na salary?.ifContains("-").usunąć PLN przy wyciągnaiu
}
