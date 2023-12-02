package dev.encinasv.restApplicationProj.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long userId;
    private String email;
    private String encryptedPassword;
    private Boolean enabled;
}
