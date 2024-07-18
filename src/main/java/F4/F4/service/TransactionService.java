package F4.F4.service;

import F4.F4.dto.AccountNumDTO;
import F4.F4.dto.BankStatementDTO;
import F4.F4.dto.TransactionDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {
  private final RestTemplate restTemplate = new RestTemplate();
  public List<BankStatementDTO> getBankTransactionList(TransactionDTO transactionDTO) {
    String bankUrl = "http://localhost:8080/account/transaction_list/acnt_num";
    AccountNumDTO accountNumDTO = new AccountNumDTO(transactionDTO.getAccountNum());
    List<BankStatementDTO> bankResult = List.of(
        restTemplate.postForObject(bankUrl, accountNumDTO, BankStatementDTO[].class));
    return bankResult;
  }
}
