package F4.F4.service;

import F4.F4.dto.AccountInfoDTO;
import F4.F4.dto.CombinedAccountInfo;
import F4.F4.dto.InsuranceDTO;
import F4.F4.dto.InsuranceResponseDTO;
import F4.F4.repository.F4AuthCodeRepository;
import F4.F4.repository.F4AuthenticationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InsuranceService {
  private final RestTemplate restTemplate = new RestTemplate();


  @Value("${openbank.server.url}")
  private String openbankServerUrl;


  public List<InsuranceResponseDTO> requestInsuranceList(InsuranceDTO insuranceDTO) {
    String url = openbankServerUrl + "/api/insurance/list";
    System.out.println("get InsuranceUrl :" + url);
    List<InsuranceResponseDTO> response = restTemplate.postForObject(url, insuranceDTO, List.class);
    System.out.println(response);
    return response;
  }

}
