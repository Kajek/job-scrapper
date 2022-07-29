package pl.sda.jobScrapper;

import pl.sda.jobOffer.JobOffer;
import pl.sda.jobOffer.JobOfferEntity;

import java.util.List;


public class JustJoinItJobScrapper implements JobScrapper {

    //mock
    @Override
    public List<JobOffer> getJobOffers() {
        return List.of();
    }


    // tutaj będzie ciężej bo chyba musze wejść do każdej oferty, nie da się wyciągnąć z listy ogłoszeń
    // albo nie:
    // class="jss233" - title
    // class="jss250 jss1249" - salary moze byc Undisclosed Salary
    // class="jss239" - company
    // class="jss240" - location ew 235 Fully Remote
    // pomyśleć czy do nazwy firmy chcę się bawić we wchodzenie do każdego ogłoszenia
}
