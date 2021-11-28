package com.sportstore.productcatalogservice;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductsResource {

    private final ProductCatalogRepository productCatalogRepository;

    @GetMapping
    public List<Product> getProducts(@RequestHeader Map<String, String> headers) {

        //final HttpHeaders headers2 = new HttpHeaders();
        //headers2.set("User-Agent", "eltabo");
        //headers2.set("Authorization", headers.get("authorization"));

        //Create a new HttpEntity
        //final HttpEntity<String> entity = new HttpEntity<String>(headers2);

        //RestTemplate restTemplate = new RestTemplate();
        //Execute the method writing your HttpEntity to the request
        //ResponseEntity<Map> response = restTemplate.exchange("http://localhost:5000/connect/userinfo", HttpMethod.GET, entity, Map.class);

        //System.out.println(response);

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

    @GetMapping("categories")
    public List<String> getCategories() {
        List<Product> products = StreamSupport.stream((productCatalogRepository.findAll().spliterator()), false).collect(Collectors.toList());
        Set<String> categories = new HashSet<>();
        products.forEach(p -> {
            if (!categories.contains(p.getCategory())) {
                categories.add(p.getCategory());
            }
        });
        return categories.stream().toList();
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
