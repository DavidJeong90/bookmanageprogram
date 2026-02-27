package domain.rent;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentVO {
    private long rentId;
    private long bookId;
    private long userId;
    private Timestamp rentTime;
    private Timestamp returnTime;
}
