package com.shop.shop.controllers;

import com.shop.shop.models.Product;
import com.shop.shop.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.JSONObject;

import java.util.List;

@RestController
@RequestMapping("/products")

class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("")
    public List<Product> index(
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
        return productDao.getById(id);
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
         * @param id : the product's id
         * 
         * @return the deletion status
         */
        return productDao.deleteById(id);
    }


    @PostMapping("")
    /**
     * Method post which use the createProduct function for create a new product
     * @param product
     * @return product created in the database
     * @throws Exception
     */
    public int createProduct(@Validated @RequestBody Product product ) throws Exception {
         return productDao.createProduct(product);
    }


    @GetMapping("/asc")
    /**
     * The sorted by ascending id/default route
     *
     * @return the list of all products sorted by ascending id
     */
    public List<Product> sortAsc(){
        return productDao.listSortedAsc();
    }

    @GetMapping("/desc")
    /**
     * The sorted by descending id route
     *
     * @return the list of all products sorted by descending id
     */
    public List<Product> sortDesc(){
        return productDao.listSortedDesc();
    }

    @RequestMapping("/asc=rating")
    @ResponseBody
    /**
     * The sorted by ascending rating route
     *
     * @return the list of all products sorted by ascending rating
     */
    public List<Product> listRatingAsc() {
        return productDao.listRatingAsc();
    }

    @RequestMapping(value = "/desc=rating")
    @ResponseBody
    /**
     * The sorted by descending rating route
     *
     * @return the list of all products sorted by descending rating
     */
    public List<Product> listRatingDesc() {
        return productDao.listRatingDesc();
    }

    @RequestMapping(value = "/asc=name")
    @ResponseBody
    /**
     * The sorted by ascending name route
     *
     * @return the list of all products sorted by ascending name
     */
    public List<Product> listNameAsc() {
        return productDao.listNameAsc();
    }

    @RequestMapping(value = "/desc=name")
    @ResponseBody
    /**
     * The sorted by descending name route
     *
     * @return the list of all products sorted by descending name
     */
    public List<Product> listNameDesc() {
        return productDao.listNameDesc();
    }

    @RequestMapping(value = "/asc=price")
    @ResponseBody
    /**
     * The sorted by ascending name price
     *
     * @return the list of all products sorted by ascending price
     */
    public List<Product> listPriceAsc() {
        return productDao.listPriceAsc();
    }

    @RequestMapping(value = "/desc=price")
    @ResponseBody
    /**
     * The sorted by descending name price
     *
     * @return the list of all products sorted by descending price
     */
    public List<Product> listPriceDesc() {
        return productDao.listPriceDesc();
    }

    @RequestMapping(value = "/asc=createdAt")
    @ResponseBody
    /**
     * The sorted by ascending created date
     *
     * @return the list of all products sorted by ascending created date
     */
    public List<Product> listCreatedAtAsc() {
        return productDao.listCreatedAtAsc();
    }

    @RequestMapping(value = "/desc=createdAt")
    @ResponseBody
    /**
     * The sorted by descending created date
     *
     * @return the list of all products sorted by descending created date
     */
    public List<Product> listCreatedAtDesc() {
        return productDao.listCreatedAtDesc();
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    /**
     * The search route
     *
     * @return the product list search
     */
        public List<Product> listSearchName(@RequestParam String name, @RequestParam Integer rating, @RequestParam Float price, @RequestParam String type) {
            return productDao.listSearchName(name, rating, price, type);
        }
    }
