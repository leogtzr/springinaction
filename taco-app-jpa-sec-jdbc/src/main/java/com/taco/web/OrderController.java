package com.taco.web;

import javax.validation.Valid;

import com.taco.domain.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.taco.domain.Order;
import com.taco.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
// @ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

    private OrderRepository orderRepo;
    private OrderProps props;
    // the following property is set by using:
    // taco.orders.pageSize = XXX
    // or: export TACO_ORDERS_PAGESIZE=10
    // private int pageSize;

    public OrderController(final OrderRepository orderRepo, final OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
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

    @GetMapping
    public String ordersForUser(
            final @AuthenticationPrincipal User user,
            final Model model
    ) {

        final Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }

//    public void setPageSize(final int pageSize) {
//        this.pageSize = pageSize;
//    }

    @PutMapping("/{orderId}")
    public Order putOrder(final @RequestBody Order order) {
        return orderRepo.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(final @PathVariable("orderId") Long orderId, final @RequestBody Order patch) {

        final Order order = orderRepo.findById(orderId).get();

        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }

        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") final Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (final EmptyResultDataAccessException ex) {}
    }

}
