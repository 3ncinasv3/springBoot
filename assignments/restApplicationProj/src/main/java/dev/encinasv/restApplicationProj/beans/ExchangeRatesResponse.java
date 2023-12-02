package dev.encinasv.restApplicationProj.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ExchangeRatesResponse {
    private Map<String, Double> rates;
}
