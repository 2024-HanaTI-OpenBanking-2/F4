package F4.F4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCustomerApprovalDTO {
    private String approvalNumber;
    private Instant approvalDate; // 일자
    private Double approvalAmount; // 금액
    private String merchantId; // 가맹점 이름
    private Double benefitAmount; // 혜택금액
    private String approvalStatusCode;
    private String paymentCategory; // 카테고리
    private String customerCardId;
}