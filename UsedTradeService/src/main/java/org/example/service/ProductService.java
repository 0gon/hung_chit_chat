package org.example.service;

import org.example.entity.Product;
import org.example.exception.ProductNotFoundException;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    /**
     * 상품 생성
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * 상품 전체 조회
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 상품 단건 조회
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    /**
     * 상품 삭제
     */
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
