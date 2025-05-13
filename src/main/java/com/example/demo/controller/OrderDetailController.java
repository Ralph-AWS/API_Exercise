package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.MerchandiseDTO;
import com.example.demo.domain.dto.OrderDTO;
import com.example.demo.domain.dto.OrderDetailDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.Merchandise;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/orderdetails")
class OrderDetailController {

    private final OrderService orderService;

    public OrderDetailController(OrderService orderService) {
        this.orderService = orderService;
    }

	@GetMapping
	public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails() {
		List<OrderDetailDTO> orderDetails = orderService.getAllOrderDetails().stream()
			.filter(orderDetail -> orderDetail.getOrder() != null && orderDetail.getMerchandise() != null) // Filter out incomplete data
			.map(orderDetail -> new OrderDetailDTO(
				orderDetail.getNo(),
				new OrderDTO(
					orderDetail.getOrder().getOrderSlipNumber(),
					orderDetail.getOrder().getOrderDate() != null ? orderDetail.getOrder().getOrderDate().toString() : null,
					orderDetail.getOrder().getCustomer() != null ? orderDetail.getOrder().getCustomer().getCustomerName() : null
				),
				new MerchandiseDTO(
					orderDetail.getMerchandise().getMerchandiseNumber(),
					orderDetail.getMerchandise().getMerchandiseName(),
					orderDetail.getMerchandise().getUnitPrice()
				),
				orderDetail.getQuantity()
			))
			.collect(Collectors.toList());
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable("id") Long id) {
	    Optional<OrderDetail> orderDetailOptional = orderService.getOrderDetailById(id);
	    if (orderDetailOptional.isPresent()) {
	        OrderDetail orderDetail = orderDetailOptional.get();

	        if (orderDetail.getOrder() != null) {
	            Hibernate.initialize(orderDetail.getOrder().getCustomer());
	        }
	        if (orderDetail.getMerchandise() != null) {
	            Hibernate.initialize(orderDetail.getMerchandise());
	        }

	        OrderDTO orderDTO = null;
	        if (orderDetail.getOrder() != null) {
	            Order order = orderDetail.getOrder();
	            String customerName = order.getCustomer() != null ? order.getCustomer().getCustomerName() : null;
	            orderDTO = new OrderDTO(
	                order.getOrderSlipNumber(),
	                order.getOrderDate() != null ? order.getOrderDate().toString() : null,
	                customerName
	            );
	        }

	        MerchandiseDTO merchandiseDTO = null;
	        if (orderDetail.getMerchandise() != null) {
	            Merchandise merchandise = orderDetail.getMerchandise();
	            merchandiseDTO = new MerchandiseDTO(
	                merchandise.getMerchandiseNumber(),
	                merchandise.getMerchandiseName(),
	                merchandise.getUnitPrice()
	            );
	        }

	        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
	            orderDetail.getNo(),
	            orderDTO,
	            merchandiseDTO,
	            orderDetail.getQuantity()
	        );

	        return new ResponseEntity<>(orderDetailDTO, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

     @PostMapping
     public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
         OrderDetail createdOrderDetail = orderService.createOrderDetail(orderDetail);
         return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
     }

    
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetail updatedOrderDetail) {
        OrderDetail updated = orderService.updateOrderDetail(id, updatedOrderDetail);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }	

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") Long id) {
        orderService.deleteOrderDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
