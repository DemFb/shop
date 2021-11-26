package com.shop.shop.controllers;

import com.shop.shop.models.Category;
import com.shop.shop.models.Product;
import com.shop.shop.services.CategoryDao;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/{id}")
    public @ResponseBody
    Category getById(@PathVariable int id) {
        /**
         * The single category route
         *
         * @param id : the category's id
         *
         * @return the wanted category
         */
        return categoryDao.getById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody int update(@PathVariable int id, @RequestBody JSONObject requestBody) {
        /**
         * The update category route
         *
         * @param id :          the category's id
         * @param requestBody : the body of the request
         *
         * @return the update status
         */
        return categoryDao.update(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody int deleteById(@PathVariable int id) {
        /**
         * The deletion category route
         *
         * @param id : the category's id
         *
         * @return the deletion status
         */
        return categoryDao.deleteById(id);
    }

    @PostMapping("")
    /**
     * Method post which use the createCategory function for create a new category
     * @param category
     * @return category created in the database
     * @throws Exception
     */
    public int createCategory(@Validated @RequestBody Category category ) throws Exception {
        return categoryDao.createCategory(category);
    }
}
