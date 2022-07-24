package pl.sda.jobScrapper;

import pl.sda.jobOffer.JobOffer;

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
