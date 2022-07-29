package pl.sda.jobOffer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobOfferServiceImplTest {

//    private static final JobOfferService jobOfferService;

//    @BeforeAll
//    static void init() {
//        jobOfferService = new JobOfferService;
//    }
    @Test
    void findAll() {
        final JobOfferRepository jobOfferRepository;
        JobOfferEntity[] jobOffersEnity = {(new JobOfferEntity("random offer name",
                "Random company", "Kraków",
                Double.MIN_VALUE, Double.MAX_VALUE, "randomLInk"))};
        JobOffer[] jobOffers = {(new JobOffer("random offer name",
                "Random company", "Kraków",
                Double.MIN_VALUE, Double.MAX_VALUE, "randomLInk"))};


    }

    @Test
    void filterByParams() {

        FilterParamsDto filterParamsDto = new FilterParamsDto("Kraków", null, null);
//        JobOffer[] jobOffersExpected = jobOfferService.filterByParams(filterParamsDto).toArray(new JobOffer[0]);
        JobOffer[] jobOffers = {(new JobOffer("random offer name",
                "Random company", "Kraków",
                Double.MIN_VALUE, Double.MAX_VALUE, "randomLInk"))};

//        assertThat(jobOffers).isEqualTo(jobOffersExpected);

    }
}