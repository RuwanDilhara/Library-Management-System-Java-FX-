package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String adminID;
    private String name;
    private String address;
    private String contact;
    private Role role;
    private String email;
    private String password;

}
