package ca.sheridancollege.encinasv.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {
    private String id;
    private String model;
    private Long price;
}
