package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fine {
    private String fineId;
    private String borrowRecordId;
    private String memberId;
    private String memberName;
    private LocalDate paymentDate;
    private Double amount;
}
