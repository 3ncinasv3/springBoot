package ca.sheridancollege.BookStoreAssignmentencinasv.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookOrderItem {
    private Book book;
    private Integer quantity = 1;
}
