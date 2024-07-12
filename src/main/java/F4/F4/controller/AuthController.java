package F4.F4.controller;

import F4.F4.dto.AccessTokenResponseDTO;
import F4.F4.service.F4AuthService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  @Autowired
  private F4AuthService authService;

//  @GetMapping("/")
//  public String home() {
//    return "home";
//  }
  @GetMapping("/authorize")
  public String login() {
    String authorizeUrl = authService.getAuthorizeUrl();
    return "redirect:" + authorizeUrl;
  }

  @GetMapping("/callback")
  public String callback(@RequestParam String code, @RequestParam String state, Model model) {
    AccessTokenResponseDTO accessTokenResponse = authService.getAccessToken(code);
    model.addAttribute("accessToken", Objects.requireNonNull(accessTokenResponse).getAccess_token());
    return "result";
  }

  @GetMapping("/show-send-form")
  public String showSend() {
    String authorizeUrl = authService.getShowSendUrl();
    return "redirect:" + authorizeUrl;
  }
}
