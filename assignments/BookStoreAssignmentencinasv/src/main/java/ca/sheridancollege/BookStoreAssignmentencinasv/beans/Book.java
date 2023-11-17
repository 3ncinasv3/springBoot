package ca.sheridancollege.BookStoreAssignmentencinasv.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Book {
    private String ISBN;
    private String title;
    private String author;
    private BigDecimal price;
    private String description;
    private String imageUrl;
}
