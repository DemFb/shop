package com.shop.shop.controllers;

import com.shop.shop.models.Product;
import com.shop.shop.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("")
    public List<Product> index(Model model){
        List<Product> list = productDao.listAll();
        return list;
    }

    @GetMapping("/{id}")
    public @ResponseBody Product getById(@PathVariable int id) {
        Product product = productDao.getById(id);
        return product;
    }

    /**
     *
     * @param id
     * @return Method delete which use deleteById function for delete the product selected by their id
     *
     */

    @DeleteMapping("/{id}")
    public @ResponseBody
    int deleteById(@PathVariable int id) {
        int product = productDao.deleteById(id);
        return product;
    }

    /**
     *
     * @param product
     * @return Method post which use the createProduct function for create a new product
     * @throws Exception
     *
     */
    @PostMapping("")
    public int createProduct(@Validated @RequestBody Product product ) throws Exception {
         return productDao.createProduct(product);
    }
}
