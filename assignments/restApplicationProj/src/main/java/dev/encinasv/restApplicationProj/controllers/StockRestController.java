package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.beans.StockDataResponse;
import dev.encinasv.restApplicationProj.service.StockService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRestController {
    private final StockService stockService;

    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

//    @GetMapping
//    public String getStocks(Model model, HttpSession session) {
//        List<String> selectedTickers = getSelectedTickersFromSession(session);
//
//        // If there are selected tickers, retrieve stock data for each ticker
//        List<StockDataResponse> stockDataList = new ArrayList<>();
//        for (String ticker : selectedTickers) {
//            StockDataResponse stockData = stockService.getStockData(ticker);
//            if (stockData != null) {
//                stockDataList.add(stockData);
//            }
//        }
//        model.addAttribute("stocks", stockDataList);
//        return "stocksAjax";
//    }
    @GetMapping
    public List<StockDataResponse> getStocks(HttpSession session) {
        List<String> selectedTickers = getSelectedTickersFromSession(session);

        // If there are selected tickers, retrieve stock data for each ticker
        List<StockDataResponse> stockDataList = new ArrayList<>();
        for (String ticker : selectedTickers) {
            StockDataResponse stockData = stockService.getStockData(ticker);
            if (stockData != null) {
                stockDataList.add(stockData);
            }
        }

        return stockDataList;
    }

    @GetMapping("/add")
    public List<String> addTicker(@RequestParam String newTicker, HttpSession session) {
        // Retrieve selected tickers from the session
        List<String> selectedTickers = getSelectedTickersFromSession(session);

        // Add the new ticker to the list
        selectedTickers.add(newTicker);

        // Update the session attribute with the modified list
        session.setAttribute("selectedTickers", selectedTickers);

        return selectedTickers;
    }

    public List<String> getSelectedTickersFromSession(HttpSession session) {
        List<String> selectedTickers = (List<String>) session.getAttribute("selectedTickers");

        // If the session attribute doesn't exist, create a new list
        if (selectedTickers == null) {
            selectedTickers = new ArrayList<>();
            session.setAttribute("selectedTickers", selectedTickers);
        }

        return selectedTickers;
    }
}
