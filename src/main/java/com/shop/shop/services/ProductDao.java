package com.shop.shop.services;

import com.shop.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductDao {
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
}

