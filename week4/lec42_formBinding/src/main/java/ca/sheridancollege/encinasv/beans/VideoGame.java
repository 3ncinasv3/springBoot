package ca.sheridancollege.encinasv.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data

public class VideoGame {
  private Long id;
  private String title;
  private String publisher;
  private String platform;
  private String[] platforms = {"PC", "Xbox", "PS"};
  private BigDecimal price;

  public VideoGame() {
    this.id = (long) 50;
    this.title = "Duke Nukem 64";
    this.publisher = "Rare Ware";
    this.price = BigDecimal.valueOf(50);
  }
}
