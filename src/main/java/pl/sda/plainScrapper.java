package pl.sda;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class plainScrapper {

    public static void main(String[] args) throws Exception {
        //ten connect przerobic?

        Document document = Jsoup.connect("https://nofluffjobs.com/pl/java?criteria=seniority%3Djunior&page=1").get();

        for (Element jobOffer: document.getElementsByClass("posting-list-item")){

            String title = jobOffer.getElementsByTag("h3").text();
            String company = jobOffer.getElementsByClass("d-block").text();
            String location = jobOffer.getElementsByClass("mr-1").text();
            String link = jobOffer.attr("href");
            String salary = jobOffer.getElementsByAttributeValue("data-cy", "salary ranges on the job offer listing").text();
            String [] salarySplit = salary.split("-");
            System.out.println(title);
            System.out.println(company);
            System.out.println(location);
            System.out.println("https://nofluffjobs.com"+link);
            System.out.println(salary);
            System.out.println(Arrays.toString(salarySplit));
            System.out.println("======================================================");
        }
//        for (Element jobOffer: document.getElementsByTag("nfj-search-results")){
//            List<String> nextButtons = jobOffer.getElementsByClass("page-link").stream().map(e -> e.text()).collect(Collectors.toList());;
//            System.out.println(nextButtons);
//            if(nextButtons.contains("»")){
//                System.out.println("nie dupa");
//            }else{
//                System.out.println("dupa");
//            }
//        }






    }
}
