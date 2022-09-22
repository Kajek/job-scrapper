package pl.sda.jobScrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pl.sda.jobOffer.JobOffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BulldogJobJobScraper implements JobScraper {
    @Override
    public List<JobOffer> getJobOffers() {
        List<JobOffer> offers = new ArrayList<>();
        int pageNum =1;
        List<String> pages = null;
        do {
            try {
                Document document = Jsoup.connect("https://bulldogjob.pl/companies/jobs/s/skills,Java/experienceLevel,junior/page," + pageNum).get();
                pages = getListOfPages(document);
                for (Element jobOffer : document.getElementsByClass("mb-2")) {

                    String title = jobOffer.getElementsByClass("mb-3").text();
                    String company = jobOffer.getElementsByClass("my-2").text();
                    String location = jobOffer.getElementsByClass("rounded-t-lg").text();
                    if(location.isEmpty() | location.equals("Remote")){
                        location = "Zdalna";
                    }
                    String salaryObj = jobOffer.getElementsByClass("text-purple").text();
                    String salary = salaryObj.replaceAll("[^0-9.-]", "").replaceAll("\\s+","");
                    String link = jobOffer.getElementsByClass("absolute").attr("href");

                    Double minSalary = getMinSalary(salaryList(salary));
                    Double maxSalary = getMaxSalary(salaryList(salary));

                    offers.add(new JobOffer(
                            title.substring(0, title.length()/2),
                            company.substring(0, company.length()/2),
                            location,
                            minSalary,
                            maxSalary,
                            link));
                }
            } catch (IOException e) {
                System.out.println("Cannot access page");;
            }
            pageNum++;
        }while(pageNum != Objects.requireNonNull(pages).size());

        removeEmptyOffers(offers);

        return offers;
    }

    private List<String> getListOfPages(Document document){
        List<String> pages = new ArrayList<>();
        for (Element jobOffer: document.getElementsByClass("pagination-bar")){
            pages = jobOffer.getElementsByClass("mx-1").stream().map(e -> e.text()).collect(Collectors.toList());
        }
        return pages;
    }


    private List<Double> salaryList(String salary){
        List<String> result = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(salary);
        if(salary.length() >= 1){
            result = Arrays.asList(salary.split("-"));
        }

        List<Double> resultInDouble = result.stream().map(e -> Double.valueOf(e)).collect(Collectors.toList());
        if( resultInDouble.isEmpty()){
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

    private void removeEmptyOffers(List<JobOffer> jobOffers){
        jobOffers.removeIf(jo -> jo.getTitle().isEmpty());
    }
}
