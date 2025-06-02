package com.jvictornascimento.buynowdotcom.service.product;

import com.jvictornascimento.buynowdotcom.model.Product;
import com.jvictornascimento.buynowdotcom.request.AddProductRequest;
import com.jvictornascimento.buynowdotcom.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product updateProduct(ProductUpdateRequest request, Long productId);
    Product getProductByID(Long productId);
    void deleteProduct(Long productId);

    List<Product> getAllProducts();
    List<Product> getProductByCategoryAndBrand(String category, String brand);
    List<Product> getProductByCategor(String category);
    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);

}
