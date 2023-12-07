package dev.encinasv.restApplicationProj.service;

import dev.encinasv.restApplicationProj.beans.StockDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class StockService {

    @Value("${finnhub.api.url}")
    private String apiUrl;
    @Value("${finnhub.api.key}")
    private String apiKey;

    public StockDataResponse getStockData(String symbol) {
        String fullUrl = apiUrl + "/quote?symbol=" + symbol + "&token=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);

        if (response != null) {
            return mapToStockDataResponse(response, symbol);
        }

        return null;
    }

    private StockDataResponse mapToStockDataResponse(Map<String, Object> response, String symbol) {
        Double currentPrice = (Double) response.get("c");
        Double priceChange = (Double) response.get("d");
        Double percentageChange = (Double) response.get("dp");
        Double highestPrice = (Double) response.get("h");
        Double lowestPrice = (Double) response.get("l");
        Double openingPrice = (Double) response.get("o");
        Double previousClose = (Double) response.get("pc");
        Integer timestamp = (Integer) response.get("t");

        StockDataResponse stockDataResponse = new StockDataResponse();
        stockDataResponse.setCurrentPrice(currentPrice);
        stockDataResponse.setPriceChange(priceChange);
        stockDataResponse.setPercentageChange(percentageChange);
        stockDataResponse.setHighestPrice(highestPrice);
        stockDataResponse.setLowestPrice(lowestPrice);
        stockDataResponse.setOpeningPrice(openingPrice);
        stockDataResponse.setPreviousClose(previousClose);
        stockDataResponse.setTimestamp(timestamp);
        stockDataResponse.setSymbol(symbol);


        return stockDataResponse;
    }




//    public Map<String, Object> getStockMostRecentDailyHighLow() {
//        String fullUrl = apiUrl;
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);
////        System.out.println(response);
//        if (response != null && response.containsKey("Time Series (5min)")) {
//            Map<String, Map<String, String>> timeSeriesData = (Map<String, Map<String, String>>) response.get("Time Series (5min)");
//            System.out.println(timeSeriesData);
//
//            if (!timeSeriesData.isEmpty()) {
//                // Get the most recent timestamp
//                String mostRecentTimestamp = timeSeriesData.keySet().iterator().next();
//
//                // Get the data for the most recent timestamp
//                Map<String, String> mostRecentData = timeSeriesData.get(mostRecentTimestamp);
//
//                // Extract open, high, low, and close values
//                String open = mostRecentData.get("1. open");
//                String high = mostRecentData.get("2. high");
//                String low = mostRecentData.get("3. low");
//                String close = mostRecentData.get("4. close");
////                System.out.print(open + high + low + close);
//
//                // Create a result map
//                Map<String, Object> result = new HashMap<>();
//                result.put("mostRecentTimestamp", mostRecentTimestamp);
//                result.put("open", open);
//                result.put("high", high);
//                result.put("low", low);
//                result.put("close", close);
////                System.out.println(result);
//
//                return result;
//            }
//        }
//        return null;
//    }


//        assert response != null;

//        String baseCode = (String) response.get("base_code");
//        Map<String, Double> exchangeRates = (Map<String, Double>) response.get("conversion_rates");
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("baseCode", baseCode);
//        result.put("exchangeRates", exchangeRates);


}
