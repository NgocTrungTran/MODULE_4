package com.milo.productthymeleaf.service;

import com.milo.productthymeleaf.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    private static final Map<Long, Product> products;

    static {

        products = new HashMap<> ();
        products.put(1L, new Product(1, "Iphone 13 Pro", 32000000,"Còn mới", "Apple"));
        products.put(2L, new Product(2, "Samsung Ultra A22", 25999000, "Còn mới", "Samsung"));
        products.put(3L, new Product(3, "Nokia 1080", 220000, "Đã qua sử dụng, còn 99%", "Nokia"));
        products.put(4L, new Product(4, "Redmi M11", 7900000, "Còn mới", "Xiaomi"));
        products.put(5L, new Product(5, "Oppo Rendo 5", 10999000, "Còn mới", "Oppo"));
        products.put(6L, new Product(6, "Vsmart Live", 4999000, "Còn mới", "VSmart"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<> ( products.values());
    }

    @Override
    public void save(Product product) {

        products.put(product.getId (), product);
    }

    @Override
    public Product findById(long id) {
        return products.get(id);
    }

    @Override
    public void update(long id, Product product) {
        products.put(id, product);

    }

    @Override
    public void remove(long id) {
        products.remove(id);
    }
}
