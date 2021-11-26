package com.shop.shop.services;

import com.shop.shop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> listAll() {
        /**
         * Search and return the all list of categories
         *
         * @return the list of all categories
         */
        String sql = "SELECT * FROM categories;";
        List<Category> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Category.class));
        return list;
    }
}
