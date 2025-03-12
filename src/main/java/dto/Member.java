package dto;

import util.enums.MemberGenderType;
import util.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;
    private LocalDate membershipDate;
    private MemberStatus status;
    private Integer borrowedBookCount ;
    private MemberGenderType gender;
    private String imageUrl;

}
