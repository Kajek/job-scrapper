package pl.sda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sda.jobOffer.JobOfferRepository;
import pl.sda.jobScrapper.JobScrapper;
import pl.sda.jobScrapper.JustJoinItJobScrapper;
import pl.sda.jobScrapper.NoFluffJobsJobScrapper;
import pl.sda.jobOffer.JobOfferService;
import pl.sda.jobOffer.JobOfferServiceImpl;

import java.util.List;

@Configuration
public class SpringConfig {

    // tworzenie implementacji konkretnych job Scrapperów i wstrzykiwanie/wskazywanie ich jako bean,
    // jeśli tego nie zrobię, jest konflkit pokazujący kilka takich samych beanow tego samego typu

    @Bean
    List<JobScrapper> jobScrappers(){
        return List.of(new JustJoinItJobScrapper(), new NoFluffJobsJobScrapper());
    }

    @Bean
    JobOfferService jobOfferService(List<JobScrapper> jobScrappers, JobOfferRepository jobOfferRepository){
        return new JobOfferServiceImpl(jobScrappers, jobOfferRepository);
    }

}
