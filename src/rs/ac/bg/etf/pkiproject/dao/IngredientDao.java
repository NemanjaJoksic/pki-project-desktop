/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static rs.ac.bg.etf.pkiproject.dao.util.Fields.NAME_SQL;
import static rs.ac.bg.etf.pkiproject.dao.util.Queries.GET_ALL_INGREDIENTS;
import rs.ac.bg.etf.pkiproject.db.DB;

/**
 *
 * @author Nemanja
 */
public class IngredientDao {
    
    public List<String> getAllIngredients() {
        List<String> ingredients = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_INGREDIENTS);
            while(rs.next()) {
                ingredients.add(rs.getString(NAME_SQL));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ingredients;
    }
    
}
