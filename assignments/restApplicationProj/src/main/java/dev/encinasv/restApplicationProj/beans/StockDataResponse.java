package dev.encinasv.restApplicationProj.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDataResponse {
    private Double currentPrice;
    private Double priceChange;
    private Double percentageChange;
    private Double highestPrice;
    private Double lowestPrice;
    private Double openingPrice;
    private Double previousClose;
    private Integer timestamp;
    private String symbol;

}
