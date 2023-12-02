package ca.sheridancollege.lec111_restfulService.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
}
