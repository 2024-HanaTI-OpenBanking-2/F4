package F4.F4.dto;

import lombok.Data;

@Data
public class BankStatementDTO {
  private String transactionNo;
  private String accountNum;
  private Double amount;
  private String tranDate;
  private String transactionType;
  private String tranTime;
  private String inoutType;
  private String printContent;
  private String tranAmt;
  private String afterBalanceAmt;
  private String branchName;
}