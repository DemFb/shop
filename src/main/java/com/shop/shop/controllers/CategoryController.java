package com.shop.shop.controllers;

import com.shop.shop.models.Category;
import com.shop.shop.services.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("")
    public List<Category> index(){
        /**
         * The index route
         *
         * @return the list of all categories
         */
        return categoryDao.listAll();
    }
}
