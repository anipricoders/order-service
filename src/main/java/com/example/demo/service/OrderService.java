package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create Order
    public Order createOrder(Order order) {

        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    // Get All Orders
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    // Get Order By ID
    public Order getOrderById(Long id) {

        return orderRepository.findById(id).orElse(null);
    }

    // Update Order
    public Order updateOrder(Long id, Order order) {

        Order existingOrder =
                orderRepository.findById(id).orElse(null);

        if (existingOrder == null) {
            return null;
        }

        existingOrder.setUserId(order.getUserId());
        existingOrder.setProductId(order.getProductId());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotalAmount(order.getTotalAmount());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    // Delete Order
    public String deleteOrder(Long id) {

        Order existingOrder =
                orderRepository.findById(id).orElse(null);

        if (existingOrder == null) {
            return "Order not found";
        }

        orderRepository.deleteById(id);

        return "Order deleted successfully";
    }
}