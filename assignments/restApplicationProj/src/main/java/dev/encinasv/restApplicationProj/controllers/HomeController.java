package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.service.ExchangeRateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    private final ExchangeRateService exchangeRateService;

    public HomeController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<String, Double> exchangeRates = exchangeRateService.getAllExchangeRates();
        model.addAttribute("exchangeRates", exchangeRates);


        return "index";
    }
}
