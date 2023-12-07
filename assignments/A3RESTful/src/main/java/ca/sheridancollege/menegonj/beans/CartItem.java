package ca.sheridancollege.menegonj.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CartItem {
    private Long id;
    private String type; // To identify the type of hardware component
    private String model;
    private String manufacturer;
    private double price;
    private String description;
}
