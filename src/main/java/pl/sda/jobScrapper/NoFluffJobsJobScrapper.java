package pl.sda.jobScrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pl.sda.jobOffer.JobOffer;
import pl.sda.jobOffer.JobOfferEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                    String company = jobOffer.getElementsByAttributeValue("data-cy", "company name on the job offer listing").text();
                    String location = jobOffer.getElementsByClass("mr-1").text();
                    if(location.isEmpty()){
                        location = "Zdalna";
                    }
                    String salary = jobOffer.getElementsByAttributeValue("data-cy", "salary ranges on the job offer listing").html().toString().replaceAll("&nbsp;","").trim();
                    String link = "https://nofluffjobs.com" + jobOffer.attr("href");

                    Double minSalary = getMinSalary(salaryList(salary));
                    Double maxSalary = getMaxSalary(salaryList(salary));

                    offers.add(new JobOffer(title, company, location, minSalary, maxSalary, link));
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

    private List<Double> salaryList(String salary){
        List<String> result = null;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(salary);
        if(m.find()) {
            result = new ArrayList<String>();
            result.add(m.group());
            while (m.find()){
                result.add(m.group());
            }
        }
        List<Double> resultInDouble = result.stream().map(e -> Double.valueOf(e)).collect(Collectors.toList());
        if( resultInDouble == null || resultInDouble.isEmpty()){
            resultInDouble.add(0,0.0);
            resultInDouble.add(1,0.0);
        }
        return resultInDouble;
    }

    private Double getMinSalary(List<Double> salaryList){
        if(salaryList.isEmpty()){
            salaryList.add(0,0.0);
        }
        return  salaryList.get(0);
    }
    private Double getMaxSalary(List<Double> salaryList){
        if(salaryList.size() == 1){
            salaryList.add(1,0.0);
        }
        return  salaryList.get(1);
    }
}
