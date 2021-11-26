package com.shop.shop.services;

import com.shop.shop.models.Category;
import com.shop.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.minidev.json.JSONObject;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    FiltrationFunctions filter = new FiltrationFunctions();

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

    public List<Product> filter(String type, String rate, String date) {
        /**
         * The filtration system
         *
         * @param type : the list of all types to filter
         * @param rate : the list of all rates to filter
         * @param date : the list of all dates to filter
         *
         * @return the list of all products filtered
         */
        String sql = "SELECT * FROM products WHERE ";

        Boolean categoryParam = false;
        Boolean ratingParam = false;

        // Generation of categories SQL param
        if (type != null) {
            List<Integer> categoriesId = filter.getCategoryIds(type, getCategories());
            String SQLcategories = filter.getCategorySqlParam(categoriesId);
            
            if (SQLcategories != null) {
                categoryParam = true;
                sql += SQLcategories;
            }
        }

        // Generation of rating SQL param
        if (rate != null) {
            String ratingType = filter.getRatingType(rate);

            // Values rating type
            if (ratingType == "values") {
                List<Integer> rates = filter.getRates(rate);
                String SQLrates = filter.getRatingSqlParam(rates);
                
                if (SQLrates != null) sql += (categoryParam ? " AND " : "") + SQLrates;
            }
            // Range rating type
            else if (ratingType == "range") {
                char startRange = rate.charAt(1);
                char endRange = rate.charAt(rate.length() - 2);

                sql += (categoryParam ? " AND " : "") + "rating BETWEEN " + startRange + " AND " + endRange;
            }
            // Lower rating type
            else if (ratingType == "lower") {
                String rateNb = rate.substring(2, rate.length() - 1);
                sql += (categoryParam ? " AND " : "") + "rating <= " + rateNb;
            }
            // Upper rating type
            else if (ratingType == "upper") {
                String rateNb = rate.substring(1, rate.length() - 2);
                sql += (categoryParam ? " AND " : "") + "rating >= " + rateNb;
            }
        }     

        // Generation of rating SQL param
        if (date != null) {
            String dateType = filter.getDateType(date);

            // Values date type
            if (dateType == "values") {
                String[] dates = date.split(",");
                String SQLdates = filter.getDateSqlParam(dates);
                
                if (SQLdates != null) sql += (categoryParam ? " AND " : ratingParam ? " AND " : "") + SQLdates;
            }
            // Range date type
            else if (dateType == "range") {
                String startRange = date.substring(1, 11);
                String endRange = date.substring(12, date.length() - 1);

                sql += (categoryParam ? " AND " : ratingParam ? " AND " : "") + "createdAt BETWEEN \"" + startRange + "\" AND \"" + endRange + "\"";
            }
            // Lower date type
            else if (dateType == "lower") {
                String dateNb = date.substring(2, date.length() - 1);
                sql += (categoryParam ? " AND " : ratingParam ? " AND " : "") + "createdAt <= \"" + dateNb + "\"";
            }
            // Upper date type
            else if (dateType == "upper") {
                String dateNb = date.substring(1, date.length() - 2);
                sql += (categoryParam ? " AND " : ratingParam ? " AND " : "") + "createdAt >= \"" + dateNb + "\"";
            }
        }
        List<Product> filteredList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));

        return filteredList;
    }
    public List<Category> getCategories() {
        return jdbcTemplate.query("SELECT * FROM categories", BeanPropertyRowMapper.newInstance(Category.class));
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
    public int createProduct(Product product) throws Exception {
        /**
         * @param product
         * function which creqte a new product by the insert command and update the getters in the jdbcTemplate.
         * @return the product created in the database (1 for succes and 0 to fail)
         * @throws Exception
         */
        String sql = "INSERT INTO products (name, rating, createdAt, categoryId, price) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getName(), product.getRating(), product.getCreatedAt(), product.getCategoryId(), product.getPrice());
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
    public List<Product> listSearchName(String name, Integer rating, Float price, String type) {
        /**
         * This function search the product by name, rating, price and type
         *
         * @return the searched product
         */
        String sql = "SELECT * FROM products WHERE name=? AND rating=? AND price=? AND type=?;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), name, rating, price, type);
        return list;
    }

    public List<Product> paginationProduct(String range) {
        /**
         *
         * This function use sql command, then split two selected values
         * @param range
         * @return the product list pagination
         */
        int start = Integer.parseInt(range.split(",")[0]);
        int end = Integer.parseInt(range.split(",")[1]);
        String sql = "SELECT * FROM products LIMIT ?, ?;";
        List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), start, end);
        return list;
    }
}