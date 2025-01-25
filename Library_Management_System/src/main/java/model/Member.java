package model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private String memberID;
    private String name;
    private String contact;
    private LocalDate memberShipDate;
    private String email;
    private String password;
}
