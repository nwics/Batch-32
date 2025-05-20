package com.example.eshopay_be.service.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.CartItemRepository;
import com.example.eshopay_be.dao.CartRepository;
import com.example.eshopay_be.dao.ProductsRepository;
import com.example.eshopay_be.dto.CartDTO;
import com.example.eshopay_be.dto.CartItemDTO;
import com.example.eshopay_be.model.CartItem;
import com.example.eshopay_be.model.CartItemId;
import com.example.eshopay_be.model.Carts;
import com.example.eshopay_be.model.Products;
import com.example.eshopay_be.model.Users;
import com.example.eshopay_be.service.CartService;

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
            dto.setDiscount(item.getDiscount());
            dto.setSubTotal(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            dto.setProductDTO(ProductServiceImpl.mapToDto(item.getProducts()));
            return dto;
        }).collect(Collectors.toList());

        return new CartDTO(
                carts.getCartId(), carts.getUserId(), itemDTOs, null);
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
    public CartDTO getCartByUserId(Long userId) {
        try {
            Carts foundCarts = cartRepository.findByUserId(userId);
            if (foundCarts == null) {
                throw new RuntimeException("No cart found for user ID: " + userId);
            }
            return mapToDTO(foundCarts);
        } catch (Exception e) {
            throw new RuntimeException("error to get carts id : " + e.getMessage());
        }
    }

    @Override
    public CartDTO createCart(Long userId) {
        try {
            Carts foundCarts = cartRepository.findByUserId(userId);
            if (foundCarts != null) {
                throw new RuntimeException("cart by user id alredy exist");
            }

            Carts newCarts = new Carts();
            newCarts.setUserId(userId);
            newCarts.setCreateDate(LocalDateTime.now());
            newCarts.setCartItems(new ArrayList<>());

            Carts saved = cartRepository.save(newCarts);
            return mapToDTO(saved);

        } catch (Exception e) {
            throw new RuntimeException("error to create cart " + e.getMessage());
        }
    }

    @Override
    public CartDTO addItemToCart(Long cartId, Long productId, Integer quantity) {

        try {
            Carts foundCarts = cartRepository.findByCartId(cartId);
            if (foundCarts == null) {
                throw new RuntimeException("cart by cart id not found");
            }

            Products foundProducts = productsRepository.findByProductId(productId);
            if (foundProducts == null) {
                Optional<Products> regularProduct = productsRepository.findById(productId);
                if (!regularProduct.isPresent()) {
                    throw new RuntimeException("product does not exist in database");
                }
                throw new RuntimeException("product is marked as deleted");
            }

            CartItemId itemId = new CartItemId();
            itemId.setCartId(foundCarts.getCartId());
            itemId.setProductId(productId);
            Optional<CartItem> existingItem = cartItemRepository.findById(itemId);

            CartItem cartItem;
            if (existingItem.isPresent()) {
                cartItem = existingItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cartItem = new CartItem();
                cartItem.setId(itemId);
                cartItem.setCarts(foundCarts);
                cartItem.setProducts(foundProducts);
                cartItem.setQuantity(quantity);
                cartItem.setCreateDate(LocalDateTime.now());
                cartItem.setUnitPrice(foundProducts.getUnitPrice());

            }
            cartItemRepository.save(cartItem);

            // Carts updateCart = cartRepository.findByCartId(foundCarts.getCartId());
            return mapToDTO(foundCarts);

        } catch (Exception e) {
            throw new RuntimeException("error add cart item : " + e.getMessage());
        }
    }

    @Override
    public CartDTO updateCartItem(Long cartId, Long productId, Integer quantity) {
        CartItemId itemId = new CartItemId(cartId, productId);
        Optional<CartItem> foundCartItem = cartItemRepository.findById(itemId);
        if (!foundCartItem.isPresent()) {
            throw new RuntimeException("cart item not found");
        }
        CartItem item = foundCartItem.get();
        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return mapToDTO(item.getCarts());
    }

    @Override
    public void removeCart(Long cartId, Long productId, Integer quantity) {

        CartItemId itemId = new CartItemId(cartId, productId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(quantity);
        cartItemRepository.save(item);
        // return mapToDTO(item.getCarts());
    }

    @Override
    public void cleanCart(Long cartId, Long productId) {
        CartItemId itemId = new CartItemId(cartId, productId);
        Optional<CartItem> foundCartItem = cartItemRepository.findById(itemId);
        if (!foundCartItem.isPresent()) {
            throw new RuntimeException("cart item not found");
        }
        cartItemRepository.delete(foundCartItem.get());
    }

}
