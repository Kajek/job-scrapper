package pl.sda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sda.jobOffer.JobOfferRepository;
import pl.sda.jobScrapper.BulldogJobJobScraper;
import pl.sda.jobScrapper.JobScraper;
import pl.sda.jobScrapper.JustJoinItJobScraper;
import pl.sda.jobScrapper.NoFluffJobsJobScraper;
import pl.sda.jobOffer.JobOfferService;
import pl.sda.jobOffer.JobOfferServiceImpl;

import java.util.List;

@Configuration
public class SpringConfig {

    // tworzenie implementacji konkretnych job Scrapperów i wstrzykiwanie/wskazywanie ich jako bean,
    // jeśli tego nie zrobię, jest konflkit pokazujący kilka takich samych beanow tego samego typu

    @Bean
    List<JobScraper> jobScrappers(){
        return List.of(new JustJoinItJobScraper(), new NoFluffJobsJobScraper(),new BulldogJobJobScraper());
    }

    @Bean
    JobOfferService jobOfferService(List<JobScraper> jobScrapers, JobOfferRepository jobOfferRepository){
        return new JobOfferServiceImpl(jobScrapers, jobOfferRepository);
    }



}
