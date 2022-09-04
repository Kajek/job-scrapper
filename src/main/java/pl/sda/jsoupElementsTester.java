package pl.sda;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class jsoupElementsTester {

    public static void main(String[] args) throws Exception {


        Document document = Jsoup.connect("https://bulldogjob.pl/companies/jobs/s/skills,Java/experienceLevel,junior").get();
        int count = 0;
        for (Element jobOffer: document.getElementsByClass("mb-2")){
            count++;
            String title = jobOffer.getElementsByClass("mb-3").text();
            String company = jobOffer.getElementsByClass("my-2").text();
            String location = jobOffer.getElementsByClass("rounded-t-lg").text(); //wyedytować > 1 element "więcej niż 1 lokacja"
            String link = jobOffer.getElementsByClass("absolute").attr("href");
            String salary = jobOffer.getElementsByClass("text-purple").text();

//            String salary = jobOffer.getElementsByAttributeValue("data-cy", "salary ranges on the job offer listing").html().toString().replaceAll("&nbsp;","").trim();

            System.out.println(count + "...");
            System.out.println(title.substring(0, title.length()/2));
            System.out.println(company.substring(0, company.length()/2));
            System.out.println(location);
            System.out.println(link);
            System.out.println(salary.replaceAll("[^0-9.-]", "").replaceAll("\\s+",""));

            System.out.println("======================================================");
        }

        for (Element pages: document.getElementsByClass("pagination-bar")){
            List<Integer> nextButtons = pages.getElementsByClass("mx-1").stream().map(e -> e.text()).map(e -> Integer.parseInt(e)).collect(Collectors.toList());;
            String page = pages.getElementsByClass("mx-1").text();

            System.out.println(page);
            System.out.println(nextButtons);
        }

    }
}
