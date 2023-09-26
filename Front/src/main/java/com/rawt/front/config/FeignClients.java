package com.rawt.front.config;

import com.rawt.api.ImagesApi;
import com.rawt.api.ProductsApi;
import com.rawt.api.RecommendationsApi;
import org.springframework.cloud.openfeign.FeignClient;

public class FeignClients {

    @FeignClient(url = "${shop.productsUrl}", name = "products")
    public interface ProductsClient extends ProductsApi {
    }

    @FeignClient(url = "${shop.productsUrl}", name = "images")
    public interface ImagesClient extends ImagesApi {
    }

    @FeignClient(url = "${shop.recommendationsUrl}", name = "recommendations")
    public interface RecommendationsClient extends RecommendationsApi {
    }
}
