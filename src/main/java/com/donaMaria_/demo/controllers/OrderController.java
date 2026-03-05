package com.donaMaria_.demo.controllers;

import com.donaMaria_.demo.Dtos.ReqOrderDto;
import com.donaMaria_.demo.Dtos.ResOrderDto;
import com.donaMaria_.demo.Dtos.UpdateOrderDto;
import com.donaMaria_.demo.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetails user,
                                         @RequestBody ReqOrderDto data) {
        String email = user.getUsername();

            try {
                ResOrderDto res = service.createOrder(data, email);
                return ResponseEntity.ok(res);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }

    @PatchMapping("/cancelar/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> cancelOrder(@AuthenticationPrincipal UserDetails user,
                                         @PathVariable Long id) {
        String email = user.getUsername();

        try {
            ResOrderDto res = service.cancelOrder(id, email);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            service.deleteOrder(id);
            return ResponseEntity.ok("deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeStatusOrder(@PathVariable Long id, @RequestBody @Valid UpdateOrderDto data){
        try {
            ResOrderDto res = service.updateOrderStatus(id, data);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders(){
        try {
            List<ResOrderDto> orders =  service.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getMyOrders(@AuthenticationPrincipal UserDetails user){
        String email = user.getUsername();

    }
}
