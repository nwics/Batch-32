package com.example.eshopay_be.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items", schema = "oe")
public class CartItem extends BaseEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "cart_item_id")
    // private Long cartItemId;
    @EmbeddedId
    private CartItemId id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Products products;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Carts carts;
}
