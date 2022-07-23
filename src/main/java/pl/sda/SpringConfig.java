package pl.sda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sda.repository.JobOfferRepository;
import pl.sda.repository.JobScrapper;
import pl.sda.repository.impl.JustJoinItJobScrapper;
import pl.sda.repository.impl.NoFluffJobsJobScrapper;
import pl.sda.service.JobOfferService;
import pl.sda.service.impl.JobOfferServiceImpl;

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
