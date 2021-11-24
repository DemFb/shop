package com.shop.shop.services;

import com.shop.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> listAll() {
        String sql = "SELECT * FROM products;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
        return list;
    }
}
