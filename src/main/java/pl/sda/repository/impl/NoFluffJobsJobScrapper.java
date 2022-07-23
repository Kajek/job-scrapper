package pl.sda.repository.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import pl.sda.model.JobOffer;
import pl.sda.repository.JobScrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class NoFluffJobsJobScrapper implements JobScrapper {
    @Override
    public List<JobOffer> getJobOffers() {
        List<JobOffer> offers = new ArrayList<>();
        boolean thereIsNextPage;
        int pageNum =1;
        do {
            thereIsNextPage = false;
            try {
                Document document = Jsoup.connect("https://nofluffjobs.com/pl/java?criteria=seniority%3Djunior&page=" + pageNum).get();
                for (Element jobOffer : document.getElementsByClass("posting-list-item")) {

                    String title = jobOffer.getElementsByTag("h3").text();
//                String company = jobOffer.getElementsByClass("d-block").text();
                    String location = jobOffer.getElementsByClass("mr-1").text();
                    if(location.isEmpty()){
                        location = "Zdalna";
                    }
                    String company = jobOffer.getElementsByAttributeValue("data-cy", "company name on the job offer listing").text();
                    String salary = jobOffer.getElementsByAttributeValue("data-cy", "salary ranges on the job offer listing").text();
                    String link = jobOffer.attr("href");
                    offers.add(new JobOffer(title, company, location, salary, link));
                }
                thereIsNextPage = checkIfNextPageExists(document);
            } catch (IOException e) {
                System.out.println("Cannot access page");;
            }
            pageNum++;
        }while(thereIsNextPage);

        return offers;
    }

    private boolean checkIfNextPageExists(Document document){

        for (Element jobOffer: document.getElementsByTag("nfj-search-results")){
            List<String> nextButtons = jobOffer.getElementsByClass("page-link").stream().map(e -> e.text()).collect(Collectors.toList());;
            if(nextButtons.contains("»")){
                return true;
            }
        }
        return false;
    }
}
