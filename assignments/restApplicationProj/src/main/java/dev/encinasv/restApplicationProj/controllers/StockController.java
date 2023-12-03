package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping("stocks")
    public String stocks(Model model) {
        model.addAttribute("stocks", stockService.getStockMostRecentDailyHighLow());
        System.out.println(stockService.getStockMostRecentDailyHighLow());

        return "stocks";
    }

}
