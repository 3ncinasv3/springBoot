package ca.sheridancollege.menegonj.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private Long isbn; 
	private String title;
	private String author;
	private double price; 
	private String description;
	private int quantity; 
}
