package com.taco.web;

import javax.validation.Valid;

import com.taco.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.taco.domain.Order;
import com.taco.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid Order order,
            final Errors errors,
            final SessionStatus sessionStatus,
            // final Authentication authentication
            final @AuthenticationPrincipal User user
    ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }


        // final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // final User user = (User) authentication.getPrincipal();

        // final User user = (User) authentication.getPrincipal();
        order.setUser(user);

        orderRepo.save(order);

        sessionStatus.setComplete();

        return "redirect:/";
    }

}
