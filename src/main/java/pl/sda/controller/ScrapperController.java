package pl.sda.controller;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sda.dto.LocationDto;
import pl.sda.dto.SalaryDto;
import pl.sda.model.JobOffer;
import pl.sda.service.JobOfferService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScrapperController {

    private final JobOfferService jobOfferService;

    public ScrapperController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @SneakyThrows
    @GetMapping("/index")
    public String index(ModelMap modelMap) {

        List<JobOffer> offers = jobOfferService.getAll();

        modelMap.addAttribute("offers", offers);
        return "index";
    }

    @GetMapping("/")
    public String mainPage(ModelMap modelMap){
        return "main-page";
    }

    @GetMapping("/index/location")
    public String filterByLocation(@ModelAttribute("locationDto") LocationDto locationDto, ModelMap modelMap){
        List<JobOffer> offers = jobOfferService.filterByLocation(locationDto);
        modelMap.addAttribute("offers", offers);
        return "index-location";
    }

    @GetMapping("/index/salary")
    public String filterBySalary(@ModelAttribute("salaryDto") SalaryDto salaryDto, ModelMap modelMap){
        List<JobOffer> offers = jobOfferService.filterBySalary(salaryDto);
        modelMap.addAttribute("offers", offers);

        return "index-salary";
    }

    @ModelAttribute("locationDto")
    public void locationDto(Model model){
        model.addAttribute(new LocationDto());
    }

    @ModelAttribute("salaryDto")
    public void salaryDto(Model model){
        model.addAttribute(new SalaryDto());
    }


    // TODO
    // zrobić button dla no fluff jobs, ew innych stron --
    // link w ofercie przerobić na hyperlink? ++
    // posprzątać strukture ofc - w sumie zrobione ++
    // wysyłać zapytania z .timeout(ileś tam) - w sumie zrobione ++
    // sobota - poprawić strukturę pobieranego obiektu - ustalić na 100% jak ma wygładać model ++
    // dodać inny serwis? justJoinit?
    // zacząć pracować nad BD?




}




// BRUDNOPIS

// Wczesniejszy wygląd metody index - do przerobienia

//        Document document = Jsoup.connect("https://nofluffjobs.com/pl/java?criteria=seniority%3Djunior&page=1").get();
//        List<String> elements = document.getElementsByClass("posting-title__position").stream().map(e -> e.text()).collect(Collectors.toList());
////        List<String> companies = document.getElementsByClass("d-block").stream().map(e->e.text()).collect(Collectors.toList());
//        List<String> companies = document.select("h3").stream().map(e->e.text()).collect(Collectors.toList());
//        modelMap.addAttribute("elements", elements);
//        modelMap.addAttribute("companies", companies);
