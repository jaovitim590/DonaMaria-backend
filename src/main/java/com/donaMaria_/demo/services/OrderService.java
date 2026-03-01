package com.donaMaria_.demo.services;

import com.donaMaria_.demo.Dtos.ReqOrderDto;
import com.donaMaria_.demo.Dtos.ResOrderDto;
import com.donaMaria_.demo.Dtos.ResOrderItemDto;
import com.donaMaria_.demo.Dtos.UpdateOrderDto;
import com.donaMaria_.demo.exceptions.RecursoNaoEncontradoException;
import com.donaMaria_.demo.models.Order;
import com.donaMaria_.demo.models.OrderItem;
import com.donaMaria_.demo.models.OrderStatus;
import com.donaMaria_.demo.models.User;
import com.donaMaria_.demo.repositories.OrderRepository;
import com.donaMaria_.demo.repositories.ProductRepository;
import com.donaMaria_.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderItemService orderItemService;
    private final ProductRepository productRepository;
    private final UserService userService;

    public OrderService(OrderRepository repository, OrderItemService orderItemService,
                        ProductRepository productRepository, UserService userService) {
        this.repository = repository;
        this.orderItemService = orderItemService;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public ResOrderDto createOrder(ReqOrderDto dto) {
        User user = userService.findById(dto.userId());

        Order order = new Order();
        order.setUser(user);
        order.setOrderNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        order.setCreate_date(Instant.now());

        Order savedOrder = repository.save(order);

        List<OrderItem> items = dto.items().stream()
                .map(itemDto -> orderItemService.createOrderItem(itemDto, savedOrder))
                .toList();

        BigDecimal total = items.stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        savedOrder.setTotal(total);
        savedOrder.setItems(items);
        repository.save(savedOrder);

        return toResOrderDto(savedOrder);
    }

    public void deleteOrder(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("order"));

        repository.delete(order);
    }

    public ResOrderDto updateOrderStatus(Long id, UpdateOrderDto dto) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("order"));

        java.util.Optional.ofNullable(dto.status())
                .ifPresent(order::setStatus);

        return toResOrderDto(repository.save(order));
    }

    public List<ResOrderDto> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(this::toResOrderDto)
                .toList();
    }

    public ResOrderDto getOrderById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("order"));

        return toResOrderDto(order);
    }

    public Long countOrders() {
        return repository.count();
    }

    private ResOrderDto toResOrderDto(Order order) {
        List<ResOrderItemDto> itemDtos = order.getItems() == null ? List.of() :
                order.getItems().stream()
                        .map(item -> new ResOrderItemDto(
                                item.getId(),
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPrice(),
                                item.getProduct().getCategory(),
                                item.getQuantity()
                        ))
                        .toList();

        return new ResOrderDto(
                order.getId(),
                order.getOrderNumber(),
                order.getUser().getId(),
                order.getUser().getName(),
                order.getDate(),
                order.getStatus(),
                order.getTotal(),
                itemDtos
        );
    }

    public boolean validateOrder(ReqOrderDto data, String email){
        User user = userService.findByEmail(email);

        return data.userId().equals(user.getId());
    }
}