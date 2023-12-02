package dev.encinasv.restApplicationProj.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExchangeRateService {
    @Value("${exchange.api.url}")
    private String apiUrl;

//    @Value("${exchange.api.access-key}")
//    private String accessKey;

    public Map<String, Double> getAllExchangeRates() {
        String baseCurrency = "CAD";
        String fullUrl = apiUrl + "/latest/" + baseCurrency;
//                +"/latest?access_key=" + accessKey + "&base=" + baseCurrency;

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
        Map<String, Double> exchangeRates = (Map<String, Double>) response.get("conversion_rates");

        return exchangeRates;

    }


}
