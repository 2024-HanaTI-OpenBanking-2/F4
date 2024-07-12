package F4.F4.service;

import F4.F4.dto.AccessTokenResponseDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class F4AuthService {
  private final RestTemplate restTemplate = new RestTemplate();

  // application.properties 파일에서 값을 읽어옴
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

  public String getShowSendUrl() {
    String state = UUID.randomUUID().toString();
    return authServerUrl + "/show-send-form?response_type=code&client_id=" + clientId +
        "&redirect_uri=" + redirectUri + "&scope=read&state=" + state ;
  }

  public AccessTokenResponseDTO getAccessToken(String code) {
    String tokenUrl = authServerUrl + "/token?grant_type=authorization_code&code=" + code +
        "&redirect_uri=" + redirectUri + "&client_id=" + clientId + "&client_secret=" + clientSecret;

    ResponseEntity<AccessTokenResponseDTO> response = restTemplate.getForEntity(tokenUrl, AccessTokenResponseDTO.class);
    return response.getBody();
  }
}
