package ca.sheridancollege.encinasv.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Song {
  private Long id;
  private String title;
  private String artist;
  private String genre;
  private final String[] GENRES = {"Spoken Word", "Electronic", "Country", "Classical", "Indie"};

  public Song(){
    this.id = (long)100;
    this.title = "Imagine";
    this.artist = "Paul Mcartney";
    this.genre = "Spoken Word";
  }

}
