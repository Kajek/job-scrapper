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
    public List<JobOffer> filterByParams(FilterParamsDto filterParamsDto) {
        Double minSalary;
        if (filterParamsDto.getMinSalary() == null) {
            minSalary = Double.MIN_VALUE;
        } else {
            minSalary = filterParamsDto.getMinSalary();
        }
        Double maxSalary;
        if (filterParamsDto.getMaxSalary() == null) {
            maxSalary = Double.MAX_VALUE;
        } else {
            maxSalary = filterParamsDto.getMaxSalary();
        }
        List<JobOffer> allOffersFilteredBySalary = jobOfferRepository
                .findByMinSalaryGreaterThanEqualAndMaxSalaryLessThanEqual(minSalary, maxSalary)
                .stream()
                .map(e -> JobOffer.from(e))
                .collect(Collectors.toList());;
        List<JobOffer> offersFilteredByParams = new ArrayList<>();

        for (JobOffer jobOffer : allOffersFilteredBySalary) {
            if (jobOffer.getLocation().toLowerCase().contains(filterParamsDto.getLocation().toLowerCase())) {
                offersFilteredByParams.add(jobOffer);
            }
        }
        return offersFilteredByParams;
    }


}

//
//    @Override
//    public List<JobOffer> filterByParams(FilterParamsDto filterParamsDto) {
//        List<JobOffer> allOffers = findAll();
//        List<JobOffer> offersFilteredByParams = new ArrayList<>();
//        Double minSalary;
//        if (filterParamsDto.getMinSalary() == null) {
//            minSalary = Double.MIN_VALUE;
//        } else {
//            minSalary = filterParamsDto.getMinSalary();
//        }
//        Double maxSalary;
//        if (filterParamsDto.getMaxSalary() == null) {
//            maxSalary = Double.MAX_VALUE;
//        } else {
//            maxSalary = filterParamsDto.getMaxSalary();
//        }
//        for (JobOffer jobOffer : allOffers) {
//            if (
//                    jobOffer.getLocation().toLowerCase().contains(filterParamsDto.getLocation().toLowerCase())) {
//                offersFilteredByParams.add(jobOffer);
//            }
//        }
//        return offersFilteredByParams;
//    }










//
//    @Override
//    public List<JobOffer> filterByLocation(FilterParamsDto filterParamsDto) {
////        return jobOfferRepository.findByLocation(locationDto.getLocation()).stream()
////                .map(e -> JobOffer.from(e))
////                .collect(Collectors.toList()); //problem z uwzględnieniem wielkości liter i końcówek PL
//        List<JobOffer> allOffers = findAll();
//        List<JobOffer> offersFilteredByLocation = new ArrayList<>();
//        for (JobOffer jobOffer: allOffers){
//            if(jobOffer.getLocation().toLowerCase().startsWith(filterParamsDto.getLocation().toLowerCase())){
//                offersFilteredByLocation.add(jobOffer);
//            }
//        }
//        return offersFilteredByLocation;
//    }
//
//    @Override
//    public List<JobOffer> filterBySalary(FilterParamsDto filterParamsDto) {
//        Double minSalary;
//        if (filterParamsDto.getMinSalary() == null) {
//            minSalary = Double.MIN_VALUE;
//        } else {
//            minSalary = filterParamsDto.getMinSalary();
//        }
//        Double maxSalary;
//        if (filterParamsDto.getMaxSalary() == null) {
//            maxSalary = Double.MAX_VALUE;
//        } else {
//            maxSalary = filterParamsDto.getMaxSalary();
//        }
//        return jobOfferRepository.findByMinSalaryGreaterThanEqualAndMaxSalaryLessThanEqual(minSalary, maxSalary).stream()
//                .map(e -> JobOffer.from(e))
//                .collect(Collectors.toList());
//    }
