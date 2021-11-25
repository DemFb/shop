package com.shop.shop.controllers;

import com.shop.shop.models.Product;
import com.shop.shop.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    /**
     *
     * @param id
     * @return Method delete which use deleteById function for delete the product selected by their id
     *
     */


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
        int product = productDao.deleteById(id);
        return product;
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
    public List<Product> sortAsc(Model model){
        List<Product> list = productDao.listSortedAsc();
        return list;
    }

    @GetMapping("/desc")
    /**
     * The sorted by descending id route
     *
     * @return the list of all products sorted by descending id
     */
    public List<Product> sortDesc(Model model){
        List<Product> list = productDao.listSortedDesc();
        return list;
    }

    @RequestMapping("/asc=rating")
    @ResponseBody
    /**
     * The sorted by ascending rating route
     *
     * @return the list of all products sorted by ascending rating
     */
    public List<Product> listRatingAsc(Model model) {
        List<Product> list = productDao.listRatingAsc();
        return list;
    }

    @RequestMapping(value = "/desc=rating")
    @ResponseBody
    /**
     * The sorted by descending rating route
     *
     * @return the list of all products sorted by descending rating
     */
    public List<Product> listRatingDesc(Model model) {
        List<Product> list = productDao.listRatingDesc();
        return list;
    }

    @RequestMapping(value = "/asc=name")
    @ResponseBody
    /**
     * The sorted by ascending name route
     *
     * @return the list of all products sorted by ascending name
     */
    public List<Product> listNameAsc(Model model) {
        List<Product> list = productDao.listNameAsc();
        return list;
    }

    @RequestMapping(value = "/desc=name")
    @ResponseBody
    /**
     * The sorted by descending name route
     *
     * @return the list of all products sorted by descending name
     */
    public List<Product> listNameDesc(Model model) {
        List<Product> list = productDao.listNameDesc();
        return list;
    }

    @RequestMapping(value = "/asc=price")
    @ResponseBody
    /**
     * The sorted by ascending name price
     *
     * @return the list of all products sorted by ascending price
     */
    public List<Product> listPriceAsc(Model model) {
        List<Product> list = productDao.listPriceAsc();
        return list;
    }

    @RequestMapping(value = "/desc=price")
    @ResponseBody
    /**
     * The sorted by descending name price
     *
     * @return the list of all products sorted by descending price
     */
    public List<Product> listPriceDesc(Model model) {
        List<Product> list = productDao.listPriceDesc();
        return list;
    }

    @RequestMapping(value = "/asc=createdAt")
    @ResponseBody
    /**
     * The sorted by ascending created date
     *
     * @return the list of all products sorted by ascending created date
     */
    public List<Product> listCreatedAtAsc(Model model) {
        List<Product> list = productDao.listCreatedAtAsc();
        return list;
    }

    @RequestMapping(value = "/desc=createdAt")
    @ResponseBody
    /**
     * The sorted by descending created date
     *
     * @return the list of all products sorted by descending created date
     */
    public List<Product> listCreatedAtDesc(Model model) {
        List<Product> list = productDao.listCreatedAtDesc();
        return list;
    }
}
