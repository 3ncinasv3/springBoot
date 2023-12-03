package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.service.ExchangeRateService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {
    private final ExchangeRateService exchangeRateService;

    public HomeController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    // Landing page logic
    @GetMapping
    public String index(Model model) {
//        Map<String, Object> exchangeData = exchangeRateService.getAllExchangeRates();
        model.addAttribute("baseCode", exchangeRateService.getAllExchangeRates().get("baseCode"));
        model.addAttribute("exchangeRates", exchangeRateService.getAllExchangeRates().get("exchangeRates"));
        return "index";
    }
    @GetMapping("/changeBaseCurrency")
    public String changeBaseCurrency(@RequestParam String baseCurrency, Model model) {
        Map<String, Object> newExchangeRates = exchangeRateService.getAllExchangeRatesByBaseCode(baseCurrency);
        model.addAttribute("baseCode", newExchangeRates.get("baseCode"));
        model.addAttribute("exchangeRates", newExchangeRates.get("exchangeRates"));

        return "index"; // Return the same view with updated data
    }

//    @GetMapping("/convert")
//    public String convertCurrency(@RequestParam String sourceCurrency,
//                                  @RequestParam String targetCurrency,
//                                  Model model) {
//        Double conversionRate = exchangeRateService.convertCurrency(sourceCurrency, targetCurrency);
//        model.addAttribute("sourceCurrency", sourceCurrency);
//        model.addAttribute("targetCurrency", targetCurrency);
//        model.addAttribute("conversionRate", conversionRate);
//        model.addAttribute("baseCode", exchangeRateService.getAllExchangeRates().get("baseCode"));
//        model.addAttribute("exchangeRates", exchangeRateService.getAllExchangeRates().get("exchangeRates"));
//        return "index";
//    }

    // Conversion Rate page logic
    @GetMapping("conversionRate")
    public String conversionRate() {
        return "conversionRate";
    }
    @GetMapping("/conversion")
    public String conversion(@RequestParam String sourceCurrency,
                                 @RequestParam String targetCurrency,
                                 Model model) {

        Double conversionRate = exchangeRateService.convertCurrency(sourceCurrency, targetCurrency);
        model.addAttribute("sourceCurrency", sourceCurrency);
        model.addAttribute("targetCurrency", targetCurrency);
        model.addAttribute("conversionRate", conversionRate);
        model.addAttribute("baseCode", exchangeRateService.getAllExchangeRates().get("baseCode"));
        model.addAttribute("exchangeRates", exchangeRateService.getAllExchangeRates().get("exchangeRates"));
        return "conversionRate";
    }

    //Conversion Calculator Logic
    @GetMapping("conversionCalculator")
    public String conversionCalculator() {


        return "conversionCalculator";
    }
    @GetMapping("/conversionCalc")
    public String conversionCalc(@RequestParam String sourceCurrency,
                                 @RequestParam String targetCurrency,
                                 @RequestParam Double total,
                                 Model model) {
        Double conversionTotal = exchangeRateService.convertCurrencyTotal(sourceCurrency, targetCurrency, total);
        model.addAttribute("sourceCurrency", sourceCurrency);
        model.addAttribute("targetCurrency", targetCurrency);
        model.addAttribute("conversionTotal", conversionTotal);
        model.addAttribute("conversionRate", exchangeRateService.convertCurrency(sourceCurrency, targetCurrency));
        model.addAttribute("baseCode", exchangeRateService.getAllExchangeRates().get("baseCode"));
        model.addAttribute("exchangeRates", exchangeRateService.getAllExchangeRates().get("exchangeRates"));

        return "conversionCalculator";

    }


    //Login logic
    @GetMapping("login")
    public String login() {
        return "login";
    }

}
