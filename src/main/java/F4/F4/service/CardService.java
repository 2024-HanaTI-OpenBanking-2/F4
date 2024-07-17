package F4.F4.service;

import F4.F4.dto.AccessTokenDTO;
import F4.F4.dto.CardApprovalRequestDTO;
import F4.F4.dto.CardCustomerApprovalDTO;
import F4.F4.dto.CustomerCardInfoDTO;
import F4.F4.entity.F4Customer;
import F4.F4.entity.F4CustomerCard;
import F4.F4.repository.F4CustomerCardRepository;
import F4.F4.repository.F4CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service

public class CardService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private F4CustomerCardRepository customerCardRepository;

    @Autowired
    private F4CustomerRepository customerRepository;

    @Value("${account.server.url}")
    private String accountServerUrl;

    public CustomerCardInfoDTO[] getCustomerCardList(String customerId) {
        String accessToken = getCustomerAccessToken(customerId);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(accessToken);
        String openBankUrl = accountServerUrl + "/card/card-list";
        ResponseEntity<CustomerCardInfoDTO[]> response =  restTemplate.postForEntity(openBankUrl, accessTokenDTO, CustomerCardInfoDTO[].class);
        return response.getBody();
    }

    public String getCustomerAccessToken(String customerId) {
        F4Customer customer = customerRepository.findByCustomerId(customerId);
        return customer.getAccessTokenId();
    }

    public List<CardCustomerApprovalDTO> getCustomerApproval(String customerCardId) {
        String url = accountServerUrl + "/card/card-approval";
        CardApprovalRequestDTO cardApprovalRequestDTO = new CardApprovalRequestDTO(customerCardId, null);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CardApprovalRequestDTO> requestEntity = new HttpEntity<>(cardApprovalRequestDTO, headers);
        ResponseEntity<CardCustomerApprovalDTO[]> response = restTemplate.postForEntity(url, requestEntity, CardCustomerApprovalDTO[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            if(response.getBody() != null) {
                return Arrays.asList(response.getBody());
            }else{
                throw new NullPointerException("Response is null in getCardApprovalList");
            }
        } else {
            throw new RuntimeException("Failed to get approval list: " + response.getStatusCode());
        }
    }
}
