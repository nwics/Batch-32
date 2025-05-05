package com.codeid.eshopper.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeid.eshopper.Service.CategoryService;
import com.codeid.eshopper.Service.ProductsService;
import com.codeid.eshopper.Service.SupplierService;
import com.codeid.eshopper.model.Category;
import com.codeid.eshopper.model.Products;
import com.codeid.eshopper.model.Shippers;
import com.codeid.eshopper.model.Suppliers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("products")
public class ProductsController {

    private final ProductsService productsService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public ProductsController(ProductsService productsService, CategoryService categoryService,
            SupplierService supplierService) {
        this.productsService = productsService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @GetMapping("/index")
    public ModelAndView getAllData() {
        ModelAndView view = new ModelAndView("/products/index");
        view.addObject("products", productsService.gettAllProducts());
        return view;
    }

    @GetMapping("/addForm")
    public ModelAndView addDataProduct() {
        ModelAndView view = new ModelAndView("/products/addForm");
        // Suppliers suppliers = new Suppliers();
        // Category category = new Category();
        // Shippers shippers = new Shippers();
        view.addObject("shippers", new Shippers());
        view.addObject("category", new Category());
        view.addObject("products", new Products());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView saveDataProduct(@ModelAttribute Products products) {
        if (products.getProductId() == null) {
            productsService.createProducts(products);
        } else {
            Optional<Products> exist = productsService.findProductsById(products.getProductId());
        }
        return new ModelAndView();
    }

}
