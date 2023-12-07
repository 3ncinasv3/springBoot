package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/secure/user/account")
@RestController
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping("/get")
    public ResponseEntity<?> callEndPointForData() {
        return ResponseEntity.ok(exchangeRateService.getAllExchangeRates());
    }

//
//    private final String apiUrl = "http://api.exchangeratesapi.io/v1/latest?access_key=a962808602fbbf60d24ceda69229a2ed";
//
//    @GetMapping
//    public ExchangeRatesResponse getRates() {
//        WebClient webClient = WebClient.create(apiUrl);
//
//        // Use WebClient to make a GET request to the API
//        return webClient.get()
//                .retrieve()
//                .bodyToMono(ExchangeRatesResponse.class)
//                .block();  // blocking for simplicity; consider using reactive programming for non-blocking
//    }
//    http://api.exchangeratesapi.io/v1/latest?access_key=a962808602fbbf60d24ceda69229a2ed
}
