package pl.sda.repository.impl;

import org.springframework.stereotype.Component;
import pl.sda.model.JobOffer;
import pl.sda.repository.JobScrapper;

import java.util.List;


public class JustJoinItJobScrapper implements JobScrapper {

    //mock
    @Override
    public List<JobOffer> getJobOffers() {
        return List.of();
    }


    // tutaj będzie ciężej bo chyba musze wejść do każdej oferty, nie da się wyciągnąć z listy ogłoszeń
    // albo nie:
    // class="jss239" - title
    // pomyśleć czy do nazwy firmy chcę się bawić we wchodzenie do każdego ogłoszenia
}
