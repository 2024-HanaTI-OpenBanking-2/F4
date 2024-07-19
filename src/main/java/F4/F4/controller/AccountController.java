package F4.F4.controller;

import F4.F4.dto.AccessTokenResponseDTO;
import F4.F4.dto.AccountInfoDTO;
import F4.F4.dto.AccountInfoResponseDTO;
import F4.F4.dto.CombinedAccountInfo;
import F4.F4.entity.F4AuthCode;
import F4.F4.entity.F4Customer;
import F4.F4.service.AccountService;
import F4.F4.service.F4AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

@Controller
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private F4AuthService authService;


  @GetMapping("/accountinfo")
  public String getAccountInfo(HttpServletRequest request, Model model) {
    System.out.println("in the accountinfo");
    String customerId = getUserId(request, model); // 사용자 ID 추출 로직
    if (customerId == null) {
      return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }
    try {
      String accessToken = authService.getAccessTokenByCustomerId(customerId);
      String authCode = accountService.getAuthCodeByAccessToken(accessToken);
      AccountInfoDTO accountInfoDTO = new AccountInfoDTO(accessToken, authCode);
      CombinedAccountInfo combinedAccountInfo = accountService.getCombinedAccountInfo(accountInfoDTO);
      System.out.println("return after_home");
      HttpSession session = request.getSession();
      model.addAttribute("customer", session.getAttribute("customer"));
      model.addAttribute("combinedAccountInfo", combinedAccountInfo);
      return "after_home"; // Thymeleaf 뷰 이름 반환
    } catch (Exception e) {
      model.addAttribute("error", "Server error: " + e.getMessage());
      return "error"; // 에러 페이지 반환
    }
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
