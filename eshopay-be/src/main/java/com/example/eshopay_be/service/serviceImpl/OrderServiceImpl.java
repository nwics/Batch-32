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
import com.example.eshopay_be.exception.BankNotFoundException;
import com.example.eshopay_be.exception.CartNotFoundException;
import com.example.eshopay_be.exception.EmployeeNotFoundException;
import com.example.eshopay_be.exception.LocationNotFoundException;
import com.example.eshopay_be.exception.OrderNotFoundException;
import com.example.eshopay_be.exception.ProductNotFoundException;
import com.example.eshopay_be.exception.ResourceNotFoundException;
import com.example.eshopay_be.exception.ShipperNotFoundException;
import com.example.eshopay_be.exception.UserNotFoundException;
import com.example.eshopay_be.model.Bank;
import com.example.eshopay_be.model.Employee;
import com.example.eshopay_be.model.Location;
import com.example.eshopay_be.model.OrderDetails;
import com.example.eshopay_be.model.OrderDetailsId;
import com.example.eshopay_be.model.Orders;
import com.example.eshopay_be.model.Products;
import com.example.eshopay_be.model.Shippers;
import com.example.eshopay_be.model.Users;
import com.example.eshopay_be.service.CartService;
import com.example.eshopay_be.service.OrderService;
import com.example.eshopay_be.service.ProductsService;
import com.example.eshopay_be.util.ErrorMessage;

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
    private final BankRepository bankRepository;

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
    @Transactional
    public OrderDTO createOrder(Long userId, CheckoutReqDTO checkoutReqDTO) {
        try {
            CartDTO cart = cartService.getCartByUserId(userId);
            if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
                throw new CartNotFoundException(ErrorMessage.Cart.CART_EMPTY + userId);
            }

            Users users = usersRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(ErrorMessage.User.USER_NOT_FOUND + userId));
            Shippers shippers = shipperRepository.findById(checkoutReqDTO.getShipperId()).orElseThrow(
                    () -> new ShipperNotFoundException(
                            ErrorMessage.shipper.SHIPPER_NOT_FOUND + checkoutReqDTO.getShipperId()));
            Location location = locationRepository.findById(checkoutReqDTO.getLocationId()).orElseThrow(
                    () -> new LocationNotFoundException(
                            ErrorMessage.Location.LOCATION_NOT_FOUND + checkoutReqDTO.getLocationId()));
            Employee employee = employeeRepository.findById(checkoutReqDTO.getEmployeeId()).orElseThrow(
                    () -> new EmployeeNotFoundException(
                            ErrorMessage.Employee.EMPLOYEE_NOT_FOUND + checkoutReqDTO.getLocationId()));
            Bank bank = bankRepository.findById(checkoutReqDTO.getBankCode())
                    .orElseThrow(() -> new BankNotFoundException(
                            ErrorMessage.Bank.BANK_NOT_FOUND + checkoutReqDTO.getBankCode()));

            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setShippers(shippers);
            orders.setEmployee(employee);
            orders.setLocation(location);
            orders.setBank(bank);
            orders.setOrderDate(LocalDateTime.now());
            orders.setRequiredDate(LocalDateTime.now().plusDays(3));
            // orders.setShipName(checkoutReqDTO.getShipName());
            // orders.setCreateDate(LocalDateTime.now());
            // orders.setTransacDate(LocalDateTime.now());
            // String noTransac = "XXX"+userId+"000";
            // orders.setTransacNo(noTransac);
            // orders.setShippedDate(LocalDateTime.now().plusHours(7));
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
                        () -> new ProductNotFoundException(
                                ErrorMessage.Product.PRODUCT_NOT_FOUND + cartItemDTO.getProductId())));
                orderDetails.setUnitPrice(cartItemDTO.getProductDTO().getUnitPrice());
                orderDetails
                        .setDiscount(cartItemDTO.getDiscount() != null ? cartItemDTO.getDiscount() : BigDecimal.ZERO);
                orderDetails.setQuantity(cartItemDTO.getQuantity());

                orderDetailRepository.save(orderDetails);

                BigDecimal unitPrice = cartItemDTO.getProductDTO().getUnitPrice();
                if (unitPrice == null) {
                    throw new ProductNotFoundException(
                            ErrorMessage.Product.PRODUCT_UNIT_PRICE_NULL + cartItemDTO.getProductId());
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

            throw new OrderNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId) {

        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(ErrorMessage.Order.ORDER_NOT_FOUND + orderId));
        return mapToDTO(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(Long userId) {

        List<Orders> orders = orderRepository.findByUsersUserId(userId);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
