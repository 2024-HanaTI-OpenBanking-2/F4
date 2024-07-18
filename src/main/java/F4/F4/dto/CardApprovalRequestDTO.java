package F4.F4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardApprovalRequestDTO {
    private String customerCardId;
    private String accessTokenId;
}