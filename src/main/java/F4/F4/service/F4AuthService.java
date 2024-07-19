package F4.F4.service;

import F4.F4.dto.AccessTokenResponseDTO;
import F4.F4.entity.F4AuthCode;
import F4.F4.repository.F4AuthCodeRepository;
import F4.F4.dto.RequestAuthCodeResponseDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import F4.F4.entity.F4Customer;
import F4.F4.repository.F4CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

@Service
public class F4AuthService {
  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  private F4CustomerRepository f4CustomerRepository;

  @Autowired
  private F4AuthCodeRepository f4AuthCodeRepository;

  @Value("${auth.server.url}") // 오픈뱅킹 서버
  private String authServerUrl;

  @Value("${auth.client.id}")
  private String clientId;

  @Value("${auth.client.secret}")
  private String clientSecret;

  @Value("${auth.redirect.uri}") // F4의 콜백 주소
  private String redirectUri;

  public String getAuthorizeUrl() {
    String state = UUID.randomUUID().toString();
    return authServerUrl + "/authorize?response_type=code&client_id=" + clientId +
        "&redirect_uri=" + redirectUri + "&scope=read&state=" + state ;
  }

  public String getShowSendUrl(String customerId) {
    String state = UUID.randomUUID().toString();
    return authServerUrl + "/show-send-form?response_type=code&client_id=" + clientId +
        "&redirect_uri=" + redirectUri + "&scope=read&state=" + state + "&customer_id=" + customerId;
  }

  public AccessTokenResponseDTO getAccessToken(String code) {
    String tokenUrl = authServerUrl + "/token?grant_type=authorization_code&code=" + code +
        "&redirect_uri=" + redirectUri + "&client_id=" + clientId + "&client_secret=" + clientSecret;

    ResponseEntity<AccessTokenResponseDTO> response = restTemplate.getForEntity(tokenUrl, AccessTokenResponseDTO.class);
    return response.getBody(); // 토큰 객체
  }

  @Transactional
  public void updateAccessToken(String customerId, String accessToken) {
    F4Customer customer = f4CustomerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Invalid customer ID: " + customerId));
    customer.setAccessTokenId(accessToken);
    f4CustomerRepository.save(customer);
  }

  public RequestAuthCodeResponseDTO requestAuthCode(String accessToken, String customerId) {
    String url = authServerUrl + "/request-auth-code?access_token=" + accessToken + "&customer_id=" + customerId;
    ResponseEntity<RequestAuthCodeResponseDTO> response = restTemplate.getForEntity(url, RequestAuthCodeResponseDTO.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      return response.getBody(); // URL with auth_code
    } else {
      throw new RuntimeException("Failed to request auth code from OpenBank");
    }
  }

  @Transactional
  public void saveAuthCode(RequestAuthCodeResponseDTO responseDTO) {
    try {
      F4AuthCode f4AuthCode = new F4AuthCode();
      f4AuthCode.setAuthCodeId(responseDTO.getAuthCode());
      f4AuthCode.setAccessTokenId(responseDTO.getAccessToken());
      f4AuthCode.setStateCode(UUID.randomUUID().toString());
      f4AuthCode.setApiTranId(UUID.randomUUID().toString());
      f4AuthCodeRepository.save(f4AuthCode);
    } catch (DataIntegrityViolationException e) {
      throw new RuntimeException("Duplicate auth code or access token", e);
    }
  }

  public String getAccessTokenByCustomerId(String customerId) {
    F4Customer customer = f4CustomerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));
    System.out.println("getAccessTokenByCustomerId : " + customer.getAccessTokenId());
    return customer.getAccessTokenId();
  }
}
