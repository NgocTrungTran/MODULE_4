package com.milo.productthymeleaf.service;

import com.milo.productthymeleaf.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(long id);

    void update(long id, Product product);

    void remove(long id);
}
