package pl.sda.jobOffer;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/listOffers")
    public String listJobOffers(
            ModelMap modelMap,
            @ModelAttribute("filterParamsDto") FilterParamsDto filterParamsDto,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        List<JobOffer> offersList = jobOfferService.filterByParams(filterParamsDto);;
        Page<JobOffer> offers = jobOfferService.findPaginated(PageRequest.of(currentPage - 1, pageSize), offersList);

        modelMap.addAttribute("offers", offers);

        int totalPages = offers.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "main-page-paginated";
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

