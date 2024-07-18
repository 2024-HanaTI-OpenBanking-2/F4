package F4.F4.controller;

import F4.F4.dto.InsuranceDTO;
import F4.F4.dto.InsuranceResponseDTO;
import F4.F4.entity.F4Customer;
import F4.F4.service.F4AuthService;
import F4.F4.service.InsuranceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsuranceController {

  @Autowired
  private InsuranceService insuranceService;
  @Autowired
  private F4AuthService authService;

  @GetMapping("/insurance")
  public ResponseEntity<List<InsuranceResponseDTO>> getInsuranceList(HttpServletRequest request, Model model) {
    String customerId = getUserId(request, model); // 사용자 ID 추출 로직
    String accessToken = authService.getAccessTokenByCustomerId(customerId); // 액세스 토큰 조회

    InsuranceDTO insuranceDTO = new InsuranceDTO(accessToken); // DTO 생성

    List<InsuranceResponseDTO> InsuranceList = insuranceService.requestInsuranceList(insuranceDTO); // 서비스 호출
    HttpSession session = request.getSession();
    model.addAttribute("customer", session.getAttribute("customer"));
    return ResponseEntity.ok(InsuranceList); // 결합된 계좌 정보 응답
  }
  public String getUserId(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false); // 기존 세션 가져오기, 없으면 null 반환
    if (session != null) {
      F4Customer customer = (F4Customer) session.getAttribute("customer");
      if (customer != null) {
        model.addAttribute("customerId", customer.getCustomerId());
        return customer.getCustomerId();
      } else {
        return null; // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
      }
    } else {
      return null; // 세션 자체가 없으면 로그인 페이지로 리다이렉트
    }
  }
}
