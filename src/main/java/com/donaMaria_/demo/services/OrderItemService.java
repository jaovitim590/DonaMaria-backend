package com.donaMaria_.demo.services;

import com.donaMaria_.demo.Dtos.ReqOrderItemDto;
import com.donaMaria_.demo.Dtos.ResOrderItemDto;
import com.donaMaria_.demo.exceptions.RecursoNaoEncontradoException;
import com.donaMaria_.demo.models.Order;
import com.donaMaria_.demo.models.OrderItem;
import com.donaMaria_.demo.models.Product;
import com.donaMaria_.demo.repositories.OrderItemRepository;
import com.donaMaria_.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository repository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public OrderItem createOrderItem(ReqOrderItemDto dto, Order order) {
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("product"));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.quantity());
        item.setCreate_date(Instant.now());

        return repository.save(item);
    }

    public void deleteOrderItem(Long id) {
        OrderItem item = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("orderItem"));

        repository.delete(item);
    }

    public List<ResOrderItemDto> getItemsByOrder(Long orderId) {
        return repository.findByOrderId(orderId)
                .stream()
                .map(item -> new ResOrderItemDto(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getProduct().getCategory(),
                        item.getQuantity()
                ))
                .toList();
    }
}