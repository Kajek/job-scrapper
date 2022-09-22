package pl.sda.jobOffer;


import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import pl.sda.jobScrapper.JobScraper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class JobOfferServiceImpl implements JobOfferService {

    private final List<JobScraper> jobScrapers;
    private final JobOfferRepository jobOfferRepository;


    public JobOfferServiceImpl(List<JobScraper> jobScrapers, JobOfferRepository jobOfferRepository) {
        this.jobScrapers = jobScrapers;
        this.jobOfferRepository = jobOfferRepository;
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.HOURS)
    public void populateRepository() {
        jobOfferRepository.deleteAllInBatch();
        List<JobOfferEntity> jobOffersEntityList = jobScrapers
                .stream()
                .flatMap(e -> e.getJobOffers().stream())
                .map(e -> JobOfferEntity.from(e))
                .collect(Collectors.toList());
        jobOfferRepository.saveAll(jobOffersEntityList);
        System.out.println("populating repository");
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferRepository
                .findAll()
                .stream()
                .map(e -> JobOffer.from(e))
                .collect(Collectors.toList());
    }



    @Override
    public List<JobOffer> filterByParams(FilterParamsDto filterParamsDto) {

        QJobOfferEntity qJobOfferEntity = QJobOfferEntity.jobOfferEntity;

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

        BooleanExpression offersExpression = qJobOfferEntity.location.containsIgnoreCase(filterParamsDto.getLocation())
                                                            .and(qJobOfferEntity.minSalary.goe(minSalary))
                                                            .and(qJobOfferEntity.maxSalary.loe(maxSalary));

        Iterable<JobOfferEntity> offers = jobOfferRepository.findAll(offersExpression);

        List<JobOfferEntity> offersFilteredByParams = new ArrayList<>();
        for( JobOfferEntity joe: offers){
            offersFilteredByParams.add(joe);
        }

        return offersFilteredByParams
                .stream()
                .map(e -> JobOffer.from(e))
                .collect(Collectors.toList());
    }

    @Override
    public Page<JobOffer> findPaginated(Pageable pageable, List<JobOffer> jobOffers) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<JobOffer> list;

        if (jobOffers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, jobOffers.size());
            list = jobOffers.subList(startItem, toIndex);
        }

        Page<JobOffer> jobOfferPage
                = new PageImpl<JobOffer>(list, PageRequest.of(currentPage, pageSize), jobOffers.size());

        return jobOfferPage;
    }












    // paginacja z wykorzystaniem interfejsu Querydsl
    @Override
    public Page<JobOffer> filterByPararara(FilterParamsDto filterParamsDto, Pageable pageable) {

        QJobOfferEntity qJobOfferEntity = QJobOfferEntity.jobOfferEntity;

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

        BooleanExpression offersExpression = qJobOfferEntity.location.containsIgnoreCase(filterParamsDto.getLocation())
                .and(qJobOfferEntity.minSalary.goe(minSalary))
                .and(qJobOfferEntity.maxSalary.loe(maxSalary));

        return jobOfferRepository.findAll(offersExpression, pageable).map(entity -> JobOffer.from(entity));


    }
}


