package ca.sheridancollege.ex41_thymeleaf.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
  private String prefix;
  private String code;
  private String name;
  private Long id;
}
