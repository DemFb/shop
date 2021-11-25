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

    public Product getById(int id) {
        String sql = "SELECT * FROM products WHERE id=?;";
        Product list = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Product.class), id);
        return list;
    }

    /**
     *
     * @param id
     * @returnn function which delete the product by it's id with insert command.
     *
     */

    public int deleteById(int id) {
        String sql = "DELETE FROM products WHERE id=?;";
        return jdbcTemplate.update(sql, id);
    }

    /**
     *
     * @param product
     * @return function which creqte a new product by the insert command and update the getters in the jdbcTemplate.
     * @throws Exception
     *
     */

    public int createProduct(Product product) throws Exception{
        String sql = "INSERT INTO products (name, rating, createdAt, categoryId, price) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getName(), product.getRating(), product.getCreatedAt(), product.getCategoryId(), product.getPrice());
    }
}

