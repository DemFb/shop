package com.shop.shop.controllers;

import com.shop.shop.models.Product;
import com.shop.shop.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

class HomeController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/")
    public List<Product> index(Model model){
        List<Product> list = productDao.listAll();
        return list;
    }

    @RequestMapping("/{id}")
    public @ResponseBody Product getById(@PathVariable int id) {
        Product product = productDao.getById(id);
        return product;
    }
}
