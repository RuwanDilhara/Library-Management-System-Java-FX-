package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Fines")
public class FineEntity {
    @Id
    private String fineId;
    private String borrowRecordId;
    private String memberId;
    private String memberName;
    private LocalDate paymentDate;
    private Double amount;
}
