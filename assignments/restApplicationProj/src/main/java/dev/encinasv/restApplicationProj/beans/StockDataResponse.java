package dev.encinasv.restApplicationProj.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDataResponse {

    private String open;
    private String high;
    private String low;
    private String close;

}
