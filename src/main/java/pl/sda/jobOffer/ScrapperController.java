package pl.sda.jobOffer;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ScrapperController {

    private final JobOfferService jobOfferService;

    public ScrapperController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @SneakyThrows
    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        List<JobOffer> offers = jobOfferService.findAll();

        modelMap.addAttribute("offers", offers);

        return "main-page";
    }

    @GetMapping("/filtered")
    public String filterJobOffers(@ModelAttribute("filterParamsDto") FilterParamsDto filterParamsDto, ModelMap modelMap) {
        List<JobOffer> offers = jobOfferService.filterByParams(filterParamsDto);
        modelMap.addAttribute("offers", offers);
        return "main-page-filtered";
    }

    @ModelAttribute("filterParamsDto")
    public void filterParamsDto(Model model) {
        model.addAttribute(new FilterParamsDto());
    }
}

    // TODO

    // dodać inny serwis? justJoinit?

