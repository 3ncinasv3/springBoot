package ca.sheridancollege.menegonj.beans;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	private Long id; 
	@NonNull
	private String username;
	@NonNull
	private String password; 
	@NonNull
	private String email; 
	@NonNull
	private Boolean enabled; 
}
