package ca.sheridancollege.menegonj.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table("motherboard")
public class Motherboard {
	@Id
	private Long id; 
	private String model; 
	private String manufacturer;
	private double price; 
	private String description;
	private String socketType; 
}
