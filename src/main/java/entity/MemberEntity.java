package entity;

import util.enums.MemberGenderType;
import util.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberEntity {

    @Id
    private String id;

    private String name;
    private String address;
    private String email;
    private String contact;
    private LocalDate membershipDate;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private Integer borrowedBookCount ;

    @Enumerated(EnumType.STRING)
    private MemberGenderType gender;
    private String imageUrl;
}
