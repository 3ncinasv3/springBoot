package ca.sheridancollege.BookStoreAssignmentencinasv.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Cart {
    private String email;
    private List<BookOrderItem> bookOrderItem;
}
