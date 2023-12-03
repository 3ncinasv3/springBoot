package dev.encinasv.restApplicationProj.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    @Value("${stock.api.url}")
    private String apiUrl;

    public Map<String, Object> getStockMostRecentDailyHighLow() {
//        String baseTicker = "TSLA";
        String fullUrl = apiUrl;
////                +"/latest?access_key=" + accessKey + "&base=" + baseCurrency;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
        if (response != null && response.containsKey("Time Series (60min)")) {
            Map<String, Map<String, String>> timeSeriesData = (Map<String, Map<String, String>>) response.get("Time Series (60min)");

            if (!timeSeriesData.isEmpty()) {
                // Get the most recent timestamp
                String mostRecentTimestamp = timeSeriesData.keySet().iterator().next();

                // Get the data for the most recent timestamp
                Map<String, String> mostRecentData = timeSeriesData.get(mostRecentTimestamp);

                // Extract open, high, low, and close values
                String open = mostRecentData.get("1. open");
                String high = mostRecentData.get("2. high");
                String low = mostRecentData.get("3. low");
                String close = mostRecentData.get("4. close");
                System.out.print(open + high + low + close);

                // Create a result map
                Map<String, Object> result = new HashMap<>();
//                result.put("mostRecentTimestamp", mostRecentTimestamp);
                result.put("open", open);
                result.put("high", high);
                result.put("low", low);
                result.put("close", close);
                System.out.println(result);

                return result;
            }
        }
        return null;
    }

//        assert response != null;

//        String baseCode = (String) response.get("base_code");
//        Map<String, Double> exchangeRates = (Map<String, Double>) response.get("conversion_rates");
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("baseCode", baseCode);
//        result.put("exchangeRates", exchangeRates);


}
