package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final MerchandiseRepository merchandiseRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, CustomerRepository customerRepository, MerchandiseRepository merchandiseRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
        this.merchandiseRepository = merchandiseRepository;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

     // Create order
    public Order createOrder(Order order) {
        //  Additional business logic can be added here.
        // For example:
        // 1. Validate that the customer exists.
        // 2. Calculate the total order amount.
       return orderRepository.save(order);
    }

    // Update order
    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setOrderDate(updatedOrder.getOrderDate());
             order.setCustomer(updatedOrder.getCustomer());
            return orderRepository.save(order);
        }
        return null; // Or throw an exception
    }

     // Delete order by ID
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Get all order details
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

     // Get order detail by ID
    public Optional<OrderDetail> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id);
    }

    // Create order detail
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
         return orderDetailRepository.save(orderDetail);
    }

     // Update order detail
    public OrderDetail updateOrderDetail(Long id, OrderDetail updatedOrderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(id);
        if (existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();
            orderDetail.setOrder(updatedOrderDetail.getOrder());
            orderDetail.setMerchandise(updatedOrderDetail.getMerchandise());
            orderDetail.setQuantity(updatedOrderDetail.getQuantity());
            return orderDetailRepository.save(orderDetail);
        }
        return null; // Or throw an exception
    }

    // Delete order detail by ID
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    //Get all customers
     public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

     // Create customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setCustomerName(updatedCustomer.getCustomerName());
            customer.setCustomerAddress(updatedCustomer.getCustomerAddress());
            return customerRepository.save(customer);
        }
        return null; // Or throw an exception
    }

     // Delete customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Get all merchandise
    public List<Merchandise> getAllMerchandise() {
        return merchandiseRepository.findAll();
    }

    // Get merchandise by ID
    public Optional<Merchandise> getMerchandiseById(Long id) {
        return merchandiseRepository.findById(id);
    }

     // Create merchandise
    public Merchandise createMerchandise(Merchandise merchandise) {
        return merchandiseRepository.save(merchandise);
    }

    // Update merchandise
    public Merchandise updateMerchandise(Long id, Merchandise updatedMerchandise) {
        Optional<Merchandise> existingMerchandise = merchandiseRepository.findById(id);
        if (existingMerchandise.isPresent()) {
            Merchandise merchandise = existingMerchandise.get();
            merchandise.setMerchandiseName(updatedMerchandise.getMerchandiseName());
            merchandise.setUnitPrice(updatedMerchandise.getUnitPrice());
            return merchandiseRepository.save(merchandise);
        }
        return null; // Or throw an exception
    }

    // Delete merchandise by ID
    public void deleteMerchandise(Long id) {
        merchandiseRepository.deleteById(id);
    }
}