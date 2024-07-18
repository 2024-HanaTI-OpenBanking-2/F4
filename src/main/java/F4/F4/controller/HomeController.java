package F4.F4.controller;

import F4.F4.entity.F4Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String home(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession();
    F4Customer customer = (F4Customer) session.getAttribute("customer");
    if (customer == null) {
      return "redirect:/login";
    }
    model.addAttribute("customer", customer);
    return "home";
  }

  @GetMapping("/after_home")
  public String after_home() {
    return "after_home";
  }
}
