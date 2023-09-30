package ca.sheridancollege.fahadjan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

	private Long id;
	private String name;
	private int marks;
	private int average;
	private int age;

	
}
