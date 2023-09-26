package com.rawt.productrepo.repository;

import com.rawt.model.ProductShort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OnStartupListener {

    private final ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void feedDatabase(){
        productRepository.deleteAll();
        List<ProductShort> products = new ArrayList<>(Arrays.asList(
                new ProductShort().title("Product A").shortDescription("Short description of product A").price(19.99f),
                new ProductShort().title("Product B").shortDescription("Short description of product B").price(29.99f),
                new ProductShort().title("Product C").shortDescription("Short description of product C").price(39.99f),
                new ProductShort().title("Product D").shortDescription("Short description of product D").price(49.99f),
                new ProductShort().title("Product E").shortDescription("Short description of product E").price(59.99f)
        ));
        productRepository.saveAll(products);
    }
}
