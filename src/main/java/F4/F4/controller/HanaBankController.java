package F4.F4.controller;

import F4.F4.dto.BankStatementDTO;
import F4.F4.dto.TransactionDTO;
import F4.F4.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HanaBankController {

  @Autowired
  private TransactionService transactionService;

  //  @PostMapping("/account/transaction_list/acnt_num")
//  public ResponseEntity<List<BankStatementDTO>> getBankTransaction(@RequestBody TransactionDTO transactionDTO) {
//    System.out.println(transactionDTO.getAccessToken());
//    System.out.println(transactionDTO.getAccountNum());
//    return ResponseEntity.ok(transactionService.getBankTransactionList(transactionDTO));
//  }
  @GetMapping("/account/transaction_list/acnt_num")
  public ResponseEntity<List<BankStatementDTO>> getBankTransaction() {
    TransactionDTO transactionDTO = new TransactionDTO("3b50edb81786451132df95348e6ed363458312eda1c1ad48ae109e4bea763c736a010a00e1de4500e05cad0d372a8e40a121c5c30d779e7d5a3007bc27ff914c", "account1");
    return ResponseEntity.ok(transactionService.getBankTransactionList(transactionDTO));
  }
}
