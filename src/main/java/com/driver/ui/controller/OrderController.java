package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
//	@Autowired
	OrderService orderService = new OrderServiceImpl();
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto=orderService.getOrderById(id);
		OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(orderDto.getOrderId());
		orderDetailsResponse.setCost(orderDto.getCost());
		orderDetailsResponse.setItems(orderDto.getItems());
		orderDetailsResponse.setStatus(orderDto.isStatus());
		orderDetailsResponse.setUserId(orderDto.getUserId());
		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto order1=new OrderDto();
		order1.setItems(order.getItems());
		order1.setCost(order.getCost());
		order1.setUserId(order.getUserId());

		OrderDto orderDto=orderService.createOrder(order1);
		OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
         orderDetailsResponse.setUserId(orderDto.getUserId());
		 orderDetailsResponse.setStatus(orderDto.isStatus());
		 orderDetailsResponse.setCost(orderDto.getCost());
		 orderDetailsResponse.setItems(orderDto.getItems());
		 orderDetailsResponse.setOrderId(orderDto.getOrderId());

		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto toSend =new OrderDto();
		toSend.setUserId(order.getUserId());
		toSend.setCost(order.getCost());
		toSend.setItems(order.getItems());
		OrderDto orderDto=orderService.updateOrderDetails(id,toSend);
       OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
	   orderDetailsResponse.setOrderId(orderDto.getOrderId());
	   orderDetailsResponse.setItems(orderDto.getItems());
	   orderDetailsResponse.setCost(orderDto.getCost());
	   orderDetailsResponse.setUserId(orderDto.getUserId());
	   orderDetailsResponse.setStatus(orderDto.isStatus());
		return orderDetailsResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		operationStatusModel.setOperationName("Delete");
		operationStatusModel.setOperationResult("success");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDto=orderService.getOrders();
		List<OrderDetailsResponse> orderDetailsResponses=new ArrayList<>();
		for (OrderDto order:orderDto){
			OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
			orderDetailsResponse.setUserId(order.getUserId());
			orderDetailsResponse.setOrderId(order.getOrderId());
			orderDetailsResponse.setStatus(order.isStatus());
			orderDetailsResponse.setCost(order.getCost());
			orderDetailsResponse.setItems(order.getItems());
			orderDetailsResponses.add(orderDetailsResponse);
		}
		return orderDetailsResponses;
	}
}
