package com.example.eshopay_be.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "oe")
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", schema = "oe", allocationSize = 1)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "required_date")
    private LocalDateTime requiredDate;

    @Column(name = "shipped_date")
    private LocalDateTime shippedDate;

    @Column(name = "freight")
    private Double freight;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "transac_no")
    private String transacNo;

    @Column(name = "transac_date")
    private LocalDateTime transacDate;

    @ManyToOne
    @JoinColumn(name = "ship_via")
    private Shippers shippers;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "fint_code")
    private Bank bank;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails;

}
