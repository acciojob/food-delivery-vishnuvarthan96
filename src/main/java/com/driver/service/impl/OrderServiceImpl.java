package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());
        orderEntity.setStatus(Boolean.TRUE);
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderRepository.save(orderEntity);
        order.setOrderId(orderEntity.getOrderId());
        order.setStatus(orderEntity.isStatus());
        order.setId(orderEntity.getId());
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setId(orderEntity.getId());
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());
        orderRepository.save(orderEntity);
        order.setOrderId(orderEntity.getOrderId());
        order.setId(orderEntity.getId());
        order.setStatus(orderEntity.isStatus());
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
      OrderEntity orderEntity= orderRepository.findByOrderId(orderId);
      orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDto=new ArrayList<>();
        for(OrderEntity order:orderRepository.findAll()){
            OrderDto orderDto1=new OrderDto();
            orderDto1.setId(order.getId());
            orderDto1.setUserId(order.getUserId());
            orderDto1.setOrderId(order.getOrderId());
            orderDto1.setCost(order.getCost());
            orderDto1.setItems(order.getItems());
            orderDto1.setStatus(order.isStatus());
            orderDto.add(orderDto1);
        }
        return orderDto;
    }
}