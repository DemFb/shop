package com.shop.shop.controllers;

import com.shop.shop.models.Product;
import com.shop.shop.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")

class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("")
    public List<Product> index(Model model,
        @RequestParam(name="type", required = false) String typeFilter,
        @RequestParam(name="rating", required = false) String ratingFilter,
        @RequestParam(name="createdat", required = false) String dateFilter
    ) {
        /**
         * The index route
         * 
         * @return the list of all products
         * 
         * @param model :        the model
         * @param typeFilter :   the type filter method
         * @param ratingFilter : the rating filter method
         * @param dateFilter :   the date filter method
         */

        List<Product> list;

        // If url contains filter, call the filter function
        if (typeFilter != null || ratingFilter != null || dateFilter != null) {
            /* DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(dateFilter) */;
            
            list = productDao.filter(typeFilter, ratingFilter, dateFilter);
        } else {
            list = productDao.listAll();
        }


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
