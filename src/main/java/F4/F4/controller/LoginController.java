package F4.F4.controller;

import F4.F4.entity.F4Customer;
import F4.F4.service.F4CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  @Autowired
  private F4CustomerService f4CustomerService;

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String login(String customerId, String customerPassword, HttpServletRequest request, Model model) {
    F4Customer customer = f4CustomerService.authenticate(customerId, customerPassword);
    if (customer != null) {
      HttpSession session = request.getSession();
      session.setAttribute("customer", customer);
      return "redirect:/home";
    } else {
      model.addAttribute("error", "Invalid username or password.");
      return "login";
    }
  }

  @GetMapping("/profile")
  public String getUserProfile(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false); // 기존 세션 가져오기, 없으면 null 반환
    if (session != null) {
      F4Customer customer = (F4Customer) session.getAttribute("customer");
      if (customer != null) {
        model.addAttribute("customerId", customer.getCustomerId());
        return "profile";
      } else {
        return "redirect:/login"; // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
      }
    } else {
      return "redirect:/login"; // 세션 자체가 없으면 로그인 페이지로 리다이렉트
    }
  }



  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return "redirect:/login";
  }
}
