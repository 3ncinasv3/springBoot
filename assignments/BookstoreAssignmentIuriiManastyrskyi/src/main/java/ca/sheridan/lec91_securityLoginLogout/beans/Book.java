package ca.sheridan.lec91_securityLoginLogout.beans;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {

    private Long id;
    private Long isbn;
    private String bookName;
    private String serialName;
    private String authorName;
    private String category;

    private Integer quantity;
    
}
