/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.service;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.etf.pkiproject.dao.CommentDao;
import rs.ac.bg.etf.pkiproject.dao.FoodDao;
import rs.ac.bg.etf.pkiproject.dao.IngredientDao;
import rs.ac.bg.etf.pkiproject.model.Comment;
import rs.ac.bg.etf.pkiproject.model.Food;

/**
 *
 * @author Nemanja
 */
public class FoodService {
    
    private final IngredientDao ingredientDao = new IngredientDao();
    private final FoodDao foodDao = new FoodDao();
    private final CommentDao commentDao = new CommentDao();
    
    public List<Food> getAllFoodForRestaurant(String restaurantId) {
        return foodDao.getAllFoodForRestaurant(restaurantId);
    }
    
    public List<Food> searchFood() {
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("1", "Glavno jelo 1", "Ovakvo ukrštanje kultura kroz kuhinje, naglašava Ognjevićeva, pokazuje da smo zemlja na raskrsnici puteva koja povezuje Orijent i Evropu, Mediteran i sever, odnosno zapad kontinenta.", 1000));
        foodList.add(new Food("2", "Glavno jelo 2", "Ovakvo ukrštanje kultura kroz kuhinje, naglašava Ognjevićeva, pokazuje da smo zemlja na raskrsnici puteva koja povezuje Orijent i Evropu, Mediteran i sever, odnosno zapad kontinenta.", 1000));
        return foodList;
    }
    
    public List<Food> searchFood(String restaurantId, String namePattern, String kitchenType, List<String> ingredients) {
        return foodDao.searchFood(restaurantId, namePattern, kitchenType,
                parseIngredientListToCsvString(ingredients), ingredients.size());
    }
    
    public List<Comment> getCommentsForFood(String foodId) {
        return commentDao.getCommentsForFood(foodId);
    }
    
    public boolean sendComment(String username, String restaurantId, String message) {
        return commentDao.insertCommentForFood(username, restaurantId, message);
    }
    
    public List<String> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }
    
    private String parseIngredientListToCsvString(List<String> ingredients) {
        if(ingredients.isEmpty()) return "";
        String ingredientsCsv = "";
        for(int i = 0; i < ingredients.size() - 1; i++) {
            String ingredient = ingredients.get(i);
            ingredientsCsv += "'" + ingredient + "',";
        }
        ingredientsCsv += "'" + ingredients.get(ingredients.size() - 1) + "'";
        return ingredientsCsv;
    }
    
}
