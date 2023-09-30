package ca.sheridancollege.encinasv.beans;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

@Data
public class ME {
  private String haircolour;
  private int age;
  private String hobbies;

  public String getHairColour() {
    return haircolour;
  }
}
