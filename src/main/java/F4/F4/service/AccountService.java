package F4.F4.service;

import F4.F4.dto.AccountInfoDTO;
import F4.F4.dto.CombinedAccountInfo;
import F4.F4.entity.F4AuthCode;
import F4.F4.repository.F4AuthCodeRepository;
import F4.F4.repository.F4AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${auth.server.url}") // 오픈뱅킹 서버
  private String authServerUrl;

  @Value("${auth.client.id}")
  private String clientId;

  @Value("${auth.client.secret}")
  private String clientSecret;

  @Value("${auth.redirect.uri}") // F4의 콜백 주소
  private String redirectUri;

  @Value("${account.server.url}")
  private String accountServerUrl;

  @Autowired
  private F4AuthCodeRepository f4AuthCodeRepository;

  @Autowired
  private F4AuthenticationRepository f4AuthenticationRepository;

  public CombinedAccountInfo getCombinedAccountInfo(AccountInfoDTO accountInfoDTO) {
    String url = accountServerUrl + "/accountinfo/list";
    System.out.println("getAccountInfoList :" + url);
    System.out.println(accountInfoDTO.getAccess_token());
    System.out.println(accountInfoDTO.getAuth_code());
    CombinedAccountInfo response = restTemplate.postForObject(url, accountInfoDTO, CombinedAccountInfo.class);
    System.out.println(response);
    return response;
  }

  public String getAuthCodeByAccessToken(String accessToken) {
    F4AuthCode f4AuthCode = f4AuthCodeRepository.findByAccessTokenId(accessToken)
        .orElseThrow(
            () -> new RuntimeException("Auth code not found for access token: " + accessToken));
    return f4AuthCode.getAuthCodeId();
  }

}
