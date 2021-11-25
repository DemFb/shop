package com.shop.shop.services;

import com.shop.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class ProductDao<name> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> listAll() {
        /**
         * Search and return the all list of products
         * 
         * @return the list of all products
         */
        String sql = "SELECT * FROM products;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public Product getById(int id) {
        /**
         * Search and return a product by his id
         * 
         * @param id : the product's id
         * 
         * @return the wanted product
         */
        String sql = "SELECT * FROM products WHERE id=?;";
        Product product = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Product.class), id);
        return product;
    }

    public int update(int id, JSONObject requestBody) {
        /**
         * Update a product in the database
         * 
         * @param id :          the product's id
         * @param requestBody : the body's request
         * 
         * @return the status of the update
         */
        Product product = getById(id);
        

        String key = requestBody.getAsString("key");
        String value = requestBody.getAsString("value");

        if (key.equals("name")) product.setName(value);
        else if (key.equals("rating")) product.setRating(value);
        else if (key.equals("price")) product.setPrice(Float.parseFloat(value));
        
        String sql = "UPDATE products SET name = ?, rating = ?, price = ?  WHERE id = ?;";
        int res = jdbcTemplate.update(sql, product.getName(), product.getRating(), product.getPrice(), id);
        
        return res;
    }
    public int deleteById(int id) {
        /**
         * Delete a product in the database
         * 
         * @param id : the product's id
         * 
         * @return the status of the deletion
         */
        String sql = "DELETE FROM products WHERE id=?;";
        return jdbcTemplate.update(sql, id);
    }


    public List<Product> listSortedAsc() {
        /**
         * Sort the products by ascending id/default
         *
         * @return the list of products sorted by ascending id
         */
        String sql = "SELECT * FROM products ORDER BY id ASC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listSortedDesc() {
        /**
         * Sort the products by descending id
         *
         * @return the list of products sorted by descending id
         */
        String sql = "SELECT * FROM products ORDER BY id DESC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listRatingAsc() {
        /**
         * Sort the products by ascending rating
         *
         * @return the list of products sorted by ascending rating
         */
        String sql = "SELECT * FROM products ORDER BY rating ASC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listRatingDesc() {
        /**
         * Sort the products by descending rating
         *
         * @return the list of products sorted by descending rating
         */
        String sql = "SELECT * FROM products ORDER BY rating DESC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listNameAsc() {
        /**
         * Sort the products by ascending name
         *
         * @return the list of products sorted by ascending name
         */
        String sql = "SELECT * FROM products ORDER BY name ASC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listNameDesc() {
        /**
         * Sort the products by descending name
         *
         * @return the list of products sorted by descending name
         */
        String sql = "SELECT * FROM products ORDER BY name DESC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listPriceDesc() {
        /**
         * Sort the products by descending price
         *
         * @return the list of products sorted by descending price
         */
        String sql = "SELECT * FROM products ORDER BY price DESC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listPriceAsc() {
        /**
         * Sort the products by ascending price
         *
         * @return the list of products sorted by ascending price
         */
        String sql = "SELECT * FROM products ORDER BY price ASC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listCreatedAtAsc() {
        /**
         * Sort the products by ascending createdAt
         *
         * @return the list of products sorted by ascending createdAt
         */
        String sql = "SELECT * FROM products ORDER BY createdAt ASC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listCreatedAtDesc() {
        /**
         * Sort the products by descending createdAt
         *
         * @return the list of products sorted by descending createdAt
         */
        String sql = "SELECT * FROM products ORDER BY createdAt DESC;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }

    public List<Product> listSearchName(String name, Integer rating, Float price, String type) {
        String sql = "SELECT * FROM products WHERE name=? AND rating=? AND price=? AND type=?;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), name, rating, price, type);
        return list;
    }
}

