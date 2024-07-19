package F4.F4.controller;

import F4.F4.dto.CardCustomerApprovalDTO;
import F4.F4.dto.CustomerCardInfoDTO;
import F4.F4.entity.F4Customer;
import F4.F4.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/card-list")
    public ResponseEntity<CustomerCardInfoDTO[]> getCustomerCardsList(HttpServletRequest request, Model model) {
        CustomerCardInfoDTO[] response = null;
        try {
            String customerId = getUserId(request, model);
            response = cardService.getCustomerCardList(customerId);

        } catch (Exception e) {
            System.out.println("getCustomerCardsList Failed to get" + e);
        }
        HttpSession session = request.getSession();
        model.addAttribute("customer", session.getAttribute("customer"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/card-approval")
    public ResponseEntity<List<CardCustomerApprovalDTO>> getCardApprovalList(
            @RequestParam("customer-card-id") String customerCardId
    ){
        List<CardCustomerApprovalDTO> response = null;
        try{
            response = cardService.getCustomerApproval(customerCardId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    public String getUserId(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); // 기존 세션 가져오기, 없으면 null 반환
        if (session != null) {
            F4Customer customer = (F4Customer) session.getAttribute("customer");
            if (customer != null) {
                model.addAttribute("customerId", customer.getCustomerId());
                return customer.getCustomerId();
            } else {
                return "redirect:/login"; // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            }
        } else {
            return "redirect:/login"; // 세션 자체가 없으면 로그인 페이지로 리다이렉트
        }
    }
}
