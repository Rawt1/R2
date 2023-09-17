package com.rawt.front.controllers;
import com.rawt.front.service.ProductsService;
import com.rawt.model.ProductShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new ProductShort());
        return "productForm";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductShort product) {
        productsService.productsPost(product);
        return "redirect:/admin";
    }

    @GetMapping("/{productId}/edit")
    public String editProductForm(@PathVariable String productId, Model model) {
        ProductShort product = productsService.getProductById(productId);
        model.addAttribute("product", product);
        return "productForm";
    }

    @PostMapping("/{productId}/edit")
    public String editProduct(@PathVariable String productId, @ModelAttribute ProductShort product) {
        productsService.updateProduct(productId, product);
        return "redirect:/admin";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable String productId) {
        productsService.deleteProductById(productId);
        return "redirect:/admin";
    }
}

