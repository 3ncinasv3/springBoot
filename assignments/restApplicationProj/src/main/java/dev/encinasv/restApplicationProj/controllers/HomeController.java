package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.database.DatabaseAccess;
import dev.encinasv.restApplicationProj.service.ExchangeRateService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
public class HomeController {
    private final ExchangeRateService exchangeRateService;
    private final DatabaseAccess da;
    public HomeController(ExchangeRateService exchangeRateService, DatabaseAccess da) {
        this.exchangeRateService = exchangeRateService;
        this.da = da;
    }
    // Landing page logic
    @GetMapping("/")
    public String index(Model model) {
//        Map<String, Object> exchangeData = exchangeRateService.getAllExchangeRates();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Class<? extends SecurityContext> authentication1 = SecurityContextHolder.getContext().getClass();
//        System.out.println(authentication1);
        String userName = authentication.getName();
        Object principal = authentication.getPrincipal();
        String role = (String) authentication.getCredentials();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println(principal);
//        System.out.println(role);
//        System.out.println(authorities);
        System.out.println(userName);
        if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
            String name = oAuth2User.getAttribute("name");

            System.out.println("Name: " + name);
            model.addAttribute("userName", name);
        } else {
            model.addAttribute("userName", userName);
        }
        model.addAttribute("baseCode", exchangeRateService.getAllExchangeRates().get("baseCode"));
        model.addAttribute("authorities", authorities);
        model.addAttribute("role", role);
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

    //Login and registration logic
    @GetMapping("login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String registrationPage() {
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, @RequestParam String firstName) {
        da.addUser(username, password, firstName);
        Long userId = da.findUserAccount(username).getUserId();
        da.addRole(userId, 1L);
        return "redirect:/";
    }

}
