package com.example.blum.persistance;

import com.example.blum.Products;
import com.example.blum.model.ProductNew;

import java.util.List;

public interface IRepository {

    List<ProductNew> getProducts();
    List<ProductNew> getProductsByCategory(int categoryId);
    List<Products> getProductsOldByCategory(int categoryId);
    ProductNew getProductById(int id);
    void subscribe(IChangesObserver observer);
    void unsubscribe(IChangesObserver observer);
}
