package com.shop.shop.controllers;

import com.shop.shop.models.Product;
import com.shop.shop.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")

class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("")
    public List<Product> index(Model model){
        /**
         * The index route
         * 
         * @return the list of all products
         */
        List<Product> list = productDao.listAll();
        return list;
    }

    @RequestMapping("/{id}")
    public @ResponseBody Product getById(@PathVariable int id) {
        /**
         * The single product route
         * 
         * @param id : the product's id
         * 
         * @return the wanted product
         */
        Product product = productDao.getById(id);
        return product;
    }

    @PutMapping("/{id}")
    public @ResponseBody int update(@PathVariable int id, @RequestBody JSONObject requestBody) {
        /**
         * The update product route
         * 
         * @param id :          the product's id
         * @param requestBody : the body of the request
         * 
         * @return the update status
         */
        return productDao.update(id, requestBody);
    }
    @DeleteMapping("/{id}")
    public @ResponseBody int deleteById(@PathVariable int id) {
        /**
         * The deletion product route
         * 
         * @param id :          the product's id
         * 
         * @return the deletion status
         */
        int product = productDao.deleteById(id);
        return product;
    }
}
