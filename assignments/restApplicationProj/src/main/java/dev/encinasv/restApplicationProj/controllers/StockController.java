package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping("stocks")
    public String stocks(Model model) {

        model.addAttribute("stocks", stockService.getStockData("TSLA"));
        System.out.println(stockService.getStockData("TSLA"));

        return "stocks";
    }
    @GetMapping("/changeTicker")
    public String changeTicker(@RequestParam String newTicker, Model model) {
        model.addAttribute("stocks", stockService.getStockData(newTicker));
        System.out.println(stockService.getStockData(newTicker));
        return "stocks";
    }

}
