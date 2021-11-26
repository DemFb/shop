package com.shop.shop.services;

import com.shop.shop.models.Category;
import com.shop.shop.models.Product;
import net.minidev.json.JSONObject;
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

    public Category getById(int id) {
        /**
         * Search and return a category by his id
         *
         * @param id : the category's id
         *
         * @return the wanted category
         */
        String sql = "SELECT * FROM categories WHERE id=?;";
        Category category = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Category.class), id);
        return category;
    }

    public int update(int id, JSONObject requestBody) {
        /**
         * Update a category in the database
         *
         * @param id :          the category's id
         * @param requestBody : the body's request
         *
         * @return the status of the update
         */
        Category category = getById(id);

        String key = requestBody.getAsString("key");
        String value = requestBody.getAsString("value");

        if (key.equals("name")) category.setName(value);

        String sql = "UPDATE categories SET name = ? WHERE id = ?;";
        int res = jdbcTemplate.update(sql, category.getName(), id);

        return res;
    }

    public int deleteById(int id) {
        /**
         * Delete a category in the database
         *
         * @param id : the category's id
         *
         * @return the status of the deletion
         */
        String sql = "DELETE FROM categories WHERE id=?;";
        return jdbcTemplate.update(sql, id);
    }

    public int createCategory(Category category) throws Exception {
        /**
         * @param category
         * function which create a new category by the insert command and update the getters in the jdbcTemplate.
         * @return the category created in the database (1 for success and 0 for fail)
         * @throws Exception
         */
        String sql = "INSERT INTO categories (name) VALUES (?)";
        return jdbcTemplate.update(sql, category.getName());
    }
}
