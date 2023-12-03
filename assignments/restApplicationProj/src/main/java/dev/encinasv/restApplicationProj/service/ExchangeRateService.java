package dev.encinasv.restApplicationProj.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    @Value("${exchange.api.url}")
    private String apiUrl;

//    @Value("${exchange.api.access-key}")
//    private String accessKey;
    public Map<String, Object> getAllExchangeRates() {
        String baseCurrency = "CAD";
        String fullUrl = apiUrl + "/latest/" + baseCurrency;
//                +"/latest?access_key=" + accessKey + "&base=" + baseCurrency;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
        assert response != null;
        String baseCode = (String) response.get("base_code");
        Map<String, Double> exchangeRates = (Map<String, Double>) response.get("conversion_rates");

        Map<String, Object> result = new HashMap<>();
        result.put("baseCode", baseCode);
        result.put("exchangeRates", exchangeRates);

        return result;
    }

    public Map<String, Object> getAllExchangeRatesByBaseCode(String baseCurrency) {
        String fullUrl = apiUrl + "/latest/" + baseCurrency;
//                +"/latest?access_key=" + accessKey + "&base=" + baseCurrency;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
        assert response != null;
        String baseCode = (String) response.get("base_code");
        Map<String, Double> exchangeRates = (Map<String, Double>) response.get("conversion_rates");

        Map<String, Object> result = new HashMap<>();
        result.put("exchangeRates", exchangeRates);
        result.put("baseCode", baseCode);
        return result;
    }
    public Double convertCurrency(String sourceCurrency, String targetCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String fullUrl = apiUrl + "/pair/" + sourceCurrency + "/" + targetCurrency;
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
        assert response != null;

        return (Double) response.get("conversion_rate");
    }
        public Double convertCurrencyTotal(String sourceCurrency, String targetCurrency, Double total) {
        RestTemplate restTemplate = new RestTemplate();
        String fullUrl = apiUrl + "/pair/" + sourceCurrency + "/" + targetCurrency;
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
        assert response != null;
        Double conversionRate = (Double) response.get("conversion_rate");
        return conversionRate * total;
    }
}
