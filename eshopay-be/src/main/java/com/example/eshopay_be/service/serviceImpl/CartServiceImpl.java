package com.example.eshopay_be.service.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.eshopay_be.dao.CartItemRepository;
import com.example.eshopay_be.dao.CartRepository;
import com.example.eshopay_be.dao.ProductsRepository;
import com.example.eshopay_be.dto.CartDTO;
import com.example.eshopay_be.dto.CartItemDTO;
import com.example.eshopay_be.exception.CartItemNotFoundException;
import com.example.eshopay_be.exception.CartNotFoundException;
import com.example.eshopay_be.exception.ProductNotFoundException;
import com.example.eshopay_be.model.CartItem;
import com.example.eshopay_be.model.CartItemId;
import com.example.eshopay_be.model.Carts;
import com.example.eshopay_be.model.Products;
import com.example.eshopay_be.model.Users;
import com.example.eshopay_be.service.CartService;
import com.example.eshopay_be.util.ErrorMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductServiceImpl productServiceImpl;

    private final ProductsRepository productsRepository;

    private final CartItemRepository cartItemRepository;

    public static CartDTO mapToDTO(Carts carts) {
        List<CartItemDTO> itemDTOs = carts.getCartItems().stream().map(item -> {
            CartItemDTO dto = new CartItemDTO();
            dto.setCartId(item.getId().getCartId());
            dto.setProductId(item.getId().getProductId());
            dto.setQuantity(item.getQuantity());
            dto.setDiscount(item.getDiscount() != null ? item.getDiscount() : BigDecimal.ZERO);
            dto.setSubTotal(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            dto.setProductDTO(ProductServiceImpl.mapToDto(item.getProducts()));
            return dto;
        }).collect(Collectors.toList());
        Integer totalItems = carts.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
        return new CartDTO(
                carts.getCartId(), carts.getUserId(), itemDTOs, totalItems);
    }

    public static Carts mapToModel(CartDTO cartDTO) {
        Carts carts = new Carts();
        carts.setCartId(cartDTO.getCartId());
        carts.setUserId(cartDTO.getUserId());

        if (cartDTO.getItems() != null) {
            List<CartItem> cartItems = cartDTO.getItems().stream().map(itemDto -> {
                CartItem item = new CartItem();

                CartItemId itemId = new CartItemId();
                itemId.setCartId(cartDTO.getCartId());
                itemId.setProductId(itemDto.getProductId());

                item.setId(itemId);
                item.setQuantity(itemDto.getQuantity());
                item.setDiscount(itemDto.getDiscount());
                item.setUnitPrice(itemDto.getSubTotal());
                item.setProducts(ProductServiceImpl.mapToModel(itemDto.getProductDTO()));
                item.setCarts(carts);
                // item.setCreatedOn(LocalDateTime.now());
                return item;
            }).collect(Collectors.toList());

            carts.setCartItems(cartItems);
        }

        return carts;
    }

    @Override
    @Transactional(readOnly = true)
    public CartDTO getCartByUserId(Long userId) {
        try {
            Carts foundCarts = cartRepository.findByUserId(userId);
            if (foundCarts == null) {
                throw new CartNotFoundException(ErrorMessage.Cart.CART_NOT_FOUND + userId);
            }
            return mapToDTO(foundCarts);
        } catch (Exception e) {
            throw new CartNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public CartDTO createCart(Long userId) {
        try {
            Carts foundCarts = cartRepository.findByUserId(userId);
            if (foundCarts != null) {
                throw new CartNotFoundException(ErrorMessage.Cart.CART_ALREADY_EXIST);
            }

            Carts newCarts = new Carts();
            newCarts.setUserId(userId);
            newCarts.setCreateDate(LocalDateTime.now());
            newCarts.setCartItems(new ArrayList<>());

            Carts saved = cartRepository.save(newCarts);
            return mapToDTO(saved);

        } catch (Exception e) {
            throw new CartNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public CartDTO addItemToCart(Long cartId, Long productId, Integer quantity) {

        try {
            Carts foundCarts = cartRepository.findByCartId(cartId);
            if (foundCarts == null) {
                throw new CartNotFoundException(ErrorMessage.Cart.CART_NOT_FOUND);
            }

            Products foundProducts = productsRepository.findByProductId(productId);
            if (foundProducts == null) {
                Optional<Products> regularProduct = productsRepository.findById(productId);
                if (!regularProduct.isPresent()) {
                    throw new ProductNotFoundException(ErrorMessage.Product.PRODUCT_NOT_EXIST);
                }
                throw new ProductNotFoundException(ErrorMessage.Product.PRODUCT_DELETED);
            }

            CartItemId itemId = new CartItemId();
            itemId.setCartId(foundCarts.getCartId());
            itemId.setProductId(productId);
            Optional<CartItem> existingItem = cartItemRepository.findById(itemId);

            CartItem cartItem;
            if (existingItem.isPresent()) {
                cartItem = existingItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setDiscount(BigDecimal.valueOf(0.15));
            } else {
                cartItem = new CartItem();
                cartItem.setId(itemId);
                cartItem.setCarts(foundCarts);
                cartItem.setProducts(foundProducts);
                cartItem.setQuantity(quantity);
                cartItem.setCreateDate(LocalDateTime.now());
                cartItem.setUnitPrice(foundProducts.getUnitPrice());
                cartItem.setDiscount(BigDecimal.valueOf(0.15));

            }
            cartItemRepository.save(cartItem);
            cartItemRepository.flush();

            Carts updateCart = cartRepository.findByCartId(foundCarts.getCartId());
            return mapToDTO(updateCart);

        } catch (Exception e) {
            throw new CartNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public CartDTO updateCartItem(Long cartId, Long productId, Integer quantity) {
        CartItemId itemId = new CartItemId(cartId, productId);
        Optional<CartItem> foundCartItem = cartItemRepository.findById(itemId);
        if (!foundCartItem.isPresent()) {
            throw new CartNotFoundException(ErrorMessage.Cart.CART_NOT_FOUND);
        }
        CartItem item = foundCartItem.get();
        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return mapToDTO(item.getCarts());
    }

    @Override
    public void removeCartItem(Long cartId, Long productId) {

        CartItemId itemId = new CartItemId(cartId, productId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CartItemNotFoundException(ErrorMessage.CartItem.CART_ITEM_NOT_FOUND));

        cartItemRepository.delete(item);
        // item.setQuantity(quantity);
        // cartItemRepository.save(item);
        // return mapToDTO(item.getCarts());
    }

    // @Override
    // public void cleanCart(Long cartId, Long productId) {
    // CartItemId itemId = new CartItemId(cartId, productId);
    // Optional<CartItem> foundCartItem = cartItemRepository.findById(itemId);
    // if (!foundCartItem.isPresent()) {
    // throw new
    // CartItemNotFoundException(ErrorMessage.CartItem.CART_ITEM_NOT_FOUND);
    // }
    // cartItemRepository.delete(foundCartItem.get());
    // }

}
