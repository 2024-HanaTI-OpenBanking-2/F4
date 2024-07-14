package F4.F4.controller;

import F4.F4.dto.AccessTokenResponseDTO;
import F4.F4.entity.F4Customer;
import F4.F4.service.F4AuthService;

import java.util.HashMap;
import java.util.Map;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {


    @Autowired
    private F4AuthService authService;


    @GetMapping("/show-send-form") // 본인 인증하기 버튼 클릭시
    public String showSend(HttpServletRequest request, Model model) {
        String customerId = getUserId(request, model);
        String authorizeUrl = authService.getShowSendUrl(customerId);
        return "redirect:" + authorizeUrl;
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


    @GetMapping("/authorize")
    public String authorize() {
        String authorizeUrl = authService.getAuthorizeUrl();
        return "redirect:" + authorizeUrl;
    }

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam(value = "customer_id") String customerId, @RequestParam String code, @RequestParam String state) {
        AccessTokenResponseDTO accessTokenResponse = authService.getAccessToken(code);
        if (accessTokenResponse != null) {
            Map<String, String> response = new HashMap<>();
            response.put("redirectUrl", "http://localhost:8081/tokenResult?access_token=" + accessTokenResponse.getAccess_token() + "&state=" + state + "&customer_id=" + customerId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error obtaining access token");
        }
    }

    @GetMapping("/tokenResult")
    public String result(@RequestParam("customer_id") String customerId, @RequestParam(required = false) String access_token, @RequestParam(required = false) String state, Model model) {
        if (access_token != null) {
                authService.updateAccessToken(customerId, access_token);
                model.addAttribute("customerId", customerId);
                model.addAttribute("accessToken", access_token);
                return "result";
        }
        return "redirect:/login";
    }

}
