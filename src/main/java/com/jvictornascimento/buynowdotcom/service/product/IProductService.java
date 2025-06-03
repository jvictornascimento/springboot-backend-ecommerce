package com.jvictornascimento.buynowdotcom.service.product;

import com.jvictornascimento.buynowdotcom.dtos.ProductDto;
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
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<ProductDto> getConvertedProducts(List<Product> products);
    ProductDto convertToDto(Product product);
}
