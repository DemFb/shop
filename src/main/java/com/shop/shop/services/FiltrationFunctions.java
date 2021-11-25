package com.shop.shop.services;
import com.shop.shop.models.Category;

import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.List;

public class FiltrationFunctions {
    public List<Integer> getCategoryIds(String typeParam, List<Category> categories) {
        /**
         * Get the list of all categories to filter on
         *
         * @param typeParam : the URL param
         *
         * @return the list of all categories ID
         */

        // Init the int list to return
        List<Integer> categoriesId = new ArrayList<>();

        // Get list of all categories requested
        String[] types = typeParam.split(",");

        // Loop to fill the "categoriesId" array
        for (Category category : categories) {
            for (String type : types) {
                if (category.getName().equals(type)) categoriesId.add(toIntExact(category.getId()));
            }
        }
        return categoriesId;
    }
    
    public String getRatingType(String rate) {
        /**
          Find the rating type (values, range, lower or upper)

          @param rate the "rating" param from URl
         *
         * @return the rating type (values, range, lower or upper)
         */

        if (rate.length() > 3) {
            char firstChar = rate.charAt(0);
            char secondChar = rate.charAt(1);
            char lastButOneChar = rate.charAt(rate.length() - 2);
    
            if (firstChar == '(' && secondChar == ',') return "lower";
            else if (firstChar == '(' && lastButOneChar == ',') return "upper";
            else if (firstChar == '(') return "range";
            else return "values";
        } else {
            return "values";
        }
    }

    public String getDateType(String date) {
        /**
         * Find the date type (values, range, lower or upper)
         * 
         * @param date the "date" param from URl
         * 
         * @return the date type (values, range, lower or upper)
         */

        if (date.length() > 10) {
            char firstChar = date.charAt(0);
            char secondChar = date.charAt(1);
            char lastButOneChar = date.charAt(date.length() - 2);
    
            if (firstChar == '(' && secondChar == ',') return "lower";
            else if (firstChar == '(' && lastButOneChar == ',') return "upper";
            else if (firstChar == '(') return "range";
            else return "values";
        } else {
            return "values";
        }
    }

    public List<Integer> getRates(String rate) {
        /**
         * Get the list of rates in the "rating" URL param
         * 
         * @param rate : the "rating" param from URl
         * 
         * @return the list of rates in the "rating" URL param
         */

        String[] ratesString = rate.split(",");
        List<Integer> rates = new ArrayList<>();

        for (String rateString : ratesString) {
            rates.add(Integer.parseInt(rateString));
        }
        return rates;
    }

    public String getCategorySqlParam(List<Integer> categoriesId) {
        /**
         * Generate the list of categories id to put in the SQL request
         * 
         * @param categoriesId : the list of the categories id
         * 
         * @return the SQL param of all wanted categories
         */
        StringBuilder sql = new StringBuilder();

        for (int i = 0; i < categoriesId.size(); i++) {
            int id = categoriesId.get(i);

            if (i != 0 ) sql.append(" OR ");
            sql.append("categoryId = ").append(id);

        }
        if (sql.toString().equals("")) return null;
        else return sql.toString();
    }
    public String getRatingSqlParam(List<Integer> rates) {
        /**
         * Generate the list of rates to put them in the SQL request
         * 
         * @param rates : the list of rates
         * 
         * @return the SQL param of all wanted rates
         */
        StringBuilder sql = new StringBuilder();

        for (int i = 0; i < rates.size(); i++) {
            int rate = rates.get(i);

            if (i != 0) sql.append(" OR ");
            sql.append("rating = ").append(rate);
        }
        if (sql.toString().equals("")) return null;
        else return sql.toString();
    }

    public String getDateSqlParam(String[] dates) {
        /**
         * Generate the list of dates to put them in the SQL request
         * 
         * @param dates : the list of dates
         * 
         * @return the SQL param of all wanted dates
         */
        StringBuilder sql = new StringBuilder();

        for (int i = 0; i < dates.length; i++) {
            String date = dates[i];

            if (i != 0) sql.append(" OR ");
            sql.append("createdAt = \"").append(date).append("\"");
        }
        if (sql.toString().equals("")) return null;
        else return sql.toString();
    }
}
