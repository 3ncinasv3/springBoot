package ca.sheridan.lec91_securityLoginLogout.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItem {
   private Book book;
   private Integer quantity;


}