package com.example.eshopay_be.service.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eshopay_be.dao.*;
import com.example.eshopay_be.dto.CartDTO;
import com.example.eshopay_be.dto.CartItemDTO;
import com.example.eshopay_be.dto.CheckoutReqDTO;
import com.example.eshopay_be.dto.OrderDTO;
import com.example.eshopay_be.dto.OrderDetailDTO;
import com.example.eshopay_be.exception.ResourceNotFoundException;
import com.example.eshopay_be.model.Employee;
import com.example.eshopay_be.model.Location;
import com.example.eshopay_be.model.OrderDetails;
import com.example.eshopay_be.model.OrderDetailsId;
import com.example.eshopay_be.model.Orders;
import com.example.eshopay_be.model.Shippers;
import com.example.eshopay_be.model.Users;
import com.example.eshopay_be.service.CartService;
import com.example.eshopay_be.service.OrderService;
import com.example.eshopay_be.service.ProductsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductsService productsService;

    private final ProductsRepository productsRepository;

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UsersRepository usersRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ShipperRepository shipperRepository;
    private final LocationRepository locationRepository;
    private final EmployeeRepository employeeRepository;

    private OrderDTO mapToDTO(Orders order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUsers().getUserId());
        orderDTO.setUserName(order.getUsers().getUserName());

        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setRequiredDate(order.getRequiredDate());
        orderDTO.setShippedDate(order.getShippedDate());
        orderDTO.setShipVia(order.getShippers().getShipperId());
        orderDTO.setShipperName(order.getShippers().getCompanyName());
        orderDTO.setFreight(order.getFreight());
        orderDTO.setTotalDiscount(order.getTotalDiscount());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setPaymentType(order.getPaymentType());
        orderDTO.setBankCode(order.getBank().getFintShortName());
        orderDTO.setStreetAddress(order.getLocation().getStreetAddress());
        List<OrderDetails> details = orderDetailRepository.findByIdOrderId(order.getOrderId());

        List<OrderDetailDTO> detailDTOs = details.stream().map(this::mapToOrderDetailDTO).collect(Collectors.toList());
        orderDTO.setOrderDetails(detailDTOs);
        return orderDTO;

    }

    private OrderDetailDTO mapToOrderDetailDTO(OrderDetails orderDetails) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setOrderId(orderDetails.getId().getOrderId());
        orderDetailDTO.setProductId(orderDetails.getProducts().getProductId());
        orderDetailDTO.setProductName(orderDetails.getProducts().getProductName());
        orderDetailDTO.setUnitPrice(orderDetails.getUnitPrice());
        orderDetailDTO.setQuantity(orderDetails.getQuantity());
        orderDetailDTO.setDiscount(orderDetails.getDiscount());
        orderDetailDTO.setSubtotal(orderDetails.getUnitPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity()))
                .subtract(orderDetails.getDiscount()));
        orderDetailDTO.setSupplierName(orderDetails.getProducts().getSuppliers().getCompanyName());
        return orderDetailDTO;
    }

    @Override
    public OrderDTO createOrder(Long cartId, CheckoutReqDTO checkoutReqDTO) {
        try {
            CartDTO cart = cartService.getCartByUserId(cartId);
            if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
                throw new RuntimeException("Cart is empty or does not exist for user ID: " + cartId);
            }

            Users users = usersRepository.findById(checkoutReqDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("users not found " + checkoutReqDTO.getUserId()));
            Shippers shippers = shipperRepository.findById(checkoutReqDTO.getShipperId()).orElseThrow(
                    () -> new ResourceNotFoundException("shippers not found " + checkoutReqDTO.getShipperId()));
            Location location = locationRepository.findById(checkoutReqDTO.getLocationId()).orElseThrow(
                    () -> new ResourceNotFoundException("location not found " + checkoutReqDTO.getLocationId()));
            Employee employee = employeeRepository.findById(checkoutReqDTO.getEmployeeId()).orElseThrow(
                    () -> new ResourceNotFoundException("employee not found " + checkoutReqDTO.getLocationId()));
            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setShippers(shippers);
            orders.setEmployee(employee);
            orders.setLocation(location);
            orders.setOrderDate(LocalDateTime.now());
            orders.setRequiredDate(LocalDateTime.now().plusDays(3));
            orders.setShipName(checkoutReqDTO.getShipName());
            orders.setPaymentType(checkoutReqDTO.getPaymentType());

            BigDecimal totalDiscount = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;

            Orders saveOrders = orderRepository.save(orders);

            for (CartItemDTO cartItemDTO : cart.getItems()) {
                OrderDetails orderDetails = new OrderDetails();
                OrderDetailsId orderDetailsId = new OrderDetailsId();
                orderDetailsId.setOrderId(saveOrders.getOrderId());
                orderDetailsId.setProductId(cartItemDTO.getProductId());
                orderDetails.setId(orderDetailsId);

                orderDetails.setOrders(saveOrders);
                orderDetails.setProducts(productsRepository.findById(cartItemDTO.getProductId()).orElseThrow(
                        () -> new ResourceNotFoundException("product not found " + cartItemDTO.getProductId())));
                orderDetails.setUnitPrice(cartItemDTO.getProductDTO().getUnitPrice());
                orderDetails
                        .setDiscount(cartItemDTO.getDiscount() != null ? cartItemDTO.getDiscount() : BigDecimal.ZERO);
                orderDetails.setQuantity(cartItemDTO.getQuantity());

                orderDetailRepository.save(orderDetails);

                BigDecimal unitPrice = cartItemDTO.getProductDTO().getUnitPrice();
                if (unitPrice == null) {
                    throw new RuntimeException("unit price is null product id :" + cartItemDTO.getProductId());
                }

                BigDecimal discount = cartItemDTO.getDiscount() != null ? cartItemDTO.getDiscount() : BigDecimal.ZERO;
                BigDecimal subTotal = unitPrice
                        .multiply(BigDecimal.valueOf(cartItemDTO.getQuantity()))
                        .subtract(discount);
                totalAmount = totalAmount.add(subTotal);
                totalDiscount = totalDiscount.add(discount);

                productsService.updateProductStock(cartItemDTO.getProductId(), cartItemDTO.getQuantity());

            }
            saveOrders.setTotalAmount(totalAmount);
            saveOrders.setTotalDiscount(totalDiscount);
            orderRepository.save(saveOrders);

            return mapToDTO(saveOrders);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("error to create orders " + e.getMessage());
        }
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {

        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orders not found " + orderId));
        return mapToDTO(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getOrdersByUserId'");
        List<Orders> orders = orderRepository.findByUsersUserId(userId);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
