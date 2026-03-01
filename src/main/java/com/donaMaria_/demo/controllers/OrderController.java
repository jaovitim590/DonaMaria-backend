package com.donaMaria_.demo.controllers;

import com.donaMaria_.demo.Dtos.ReqOrderDto;
import com.donaMaria_.demo.Dtos.ResOrderDto;
import com.donaMaria_.demo.services.JwtService;
import com.donaMaria_.demo.services.OrderItemService;
import com.donaMaria_.demo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;
    private final OrderItemService itemService;

    public OrderController(OrderService service, OrderItemService itemService, JwtService jwtService){
        this.service = service;
        this.itemService = itemService;
    }


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetails user,
                                         @RequestBody ReqOrderDto data) {

        String email = user.getUsername();

        if (service.validateOrder(data, email)) {
            try {
                ResOrderDto res = service.createOrder(data);
                return ResponseEntity.ok(res);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
