package ca.sheridancollege.lec51_H2andJDBCTemplate.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String degree;
    private final String[] DEGREES = {"Bachelors", "Masters", "PhD", "Advanced Diploma"};
}
