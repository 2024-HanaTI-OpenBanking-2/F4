package F4.F4.controller;

import F4.F4.dto.AccessTokenResponseDTO;
import F4.F4.entity.F4Customer;
import F4.F4.service.F4AuthService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String showSend() {
        String authorizeUrl = authService.getShowSendUrl();
        return "redirect:" + authorizeUrl;
    }


    @GetMapping("/authorize")
    public String authorize() {
        String authorizeUrl = authService.getAuthorizeUrl();
        return "redirect:" + authorizeUrl;
    }

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam String code, @RequestParam String state) {
        AccessTokenResponseDTO accessTokenResponse = authService.getAccessToken(code);
        if (accessTokenResponse != null) {
            Map<String, String> response = new HashMap<>();
            response.put("redirectUrl", "http://localhost:8081/tokenResult?access_token=" + accessTokenResponse.getAccess_token() + "&state=" + state);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error obtaining access token");
        }
    }

    @GetMapping("/tokenResult")
    public String result(HttpServletRequest request, @RequestParam(required = false) String access_token, @RequestParam(required = false) String state, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && access_token != null) {
            F4Customer customer = (F4Customer) session.getAttribute("customer");
            System.out.println(customer);
            if (customer != null) {
                authService.updateAccessToken(customer.getCustomerId(), access_token);
                model.addAttribute("customerId", customer.getCustomerId());
                model.addAttribute("accessToken", access_token);
                return "result";
            }
        }
        return "redirect:/login";
    }


}
