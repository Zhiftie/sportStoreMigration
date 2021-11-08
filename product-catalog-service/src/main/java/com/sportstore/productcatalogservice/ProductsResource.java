package com.sportstore.productcatalogservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductsResource {

    private final ProductCatalogRepository productCatalogRepository;

    @GetMapping
    public List<Product> getProducts() {
        return StreamSupport.stream((productCatalogRepository.findAll().spliterator()), false).collect(Collectors.toList());
    }

    @GetMapping("{productId}")
    public Product getProduct(@PathVariable long productId) {
        Optional<Product> optionalProduct = productCatalogRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    @GetMapping("category")
    public List<Product> getProductsByCategory(@RequestParam String productCategory) {
        return new ArrayList<>(productCatalogRepository.findAllByCategoryEquals(productCategory));
    }


    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable long productId) {
        try {
            productCatalogRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product does not exist");
        }
    }

    @PutMapping("{productId}")
    public void updateProduct(@PathVariable long productId, @RequestBody Product product) {
        if (productCatalogRepository.existsById(productId) && productId == product.getProductID()) {
            productCatalogRepository.save(product);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product does not exist");
        }
    }
}
