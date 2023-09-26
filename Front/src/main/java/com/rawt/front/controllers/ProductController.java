package com.rawt.front.controllers;
import com.rawt.front.config.FeignClients;
import com.rawt.front.service.ProductsService;
import com.rawt.model.ProductShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductsService productsService;
    private final FeignClients.ImagesClient imagesClient;

    public ProductController(ProductsService productsService, FeignClients.ImagesClient imagesClient) {
        this.productsService = productsService;
        this.imagesClient = imagesClient;
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new ProductShort());
        return "productForm";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductShort product, @RequestParam("image") MultipartFile imageFile) {
        ResponseEntity<String> response = imagesClient.imagesPost(imageFile);
        if(response.getStatusCode().is2xxSuccessful() && response.hasBody()){
            product.setImageId(response.getBody());
        }
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
    public String editProduct(@PathVariable String productId, @ModelAttribute ProductShort product, @RequestParam("image") MultipartFile imageFile) {
        if(!imageFile.isEmpty()){
            ResponseEntity<String> response = imagesClient.imagesPost(imageFile);
            if(response.getStatusCode().is2xxSuccessful() ){
                product.setImageId(response.getBody());
            }
        }
        productsService.updateProduct(productId, product);
        return "redirect:/admin";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable String productId) {
        productsService.deleteProductById(productId);
        return "redirect:/admin";
    }
}

