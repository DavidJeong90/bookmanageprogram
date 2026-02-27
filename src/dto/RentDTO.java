package dto;

import java.security.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO {
    private long rentId;
    private long bookId;
    private long userId;
    private Timestamp rentTime;
    private Timestamp returnTime;
}
