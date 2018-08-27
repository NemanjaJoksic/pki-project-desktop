/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static rs.ac.bg.etf.pkiproject.dao.util.Fields.*;
import static rs.ac.bg.etf.pkiproject.dao.util.Queries.*;
import rs.ac.bg.etf.pkiproject.db.DB;
import rs.ac.bg.etf.pkiproject.model.Comment;
import rs.ac.bg.etf.pkiproject.model.Food;

/**
 *
 * @author Nemanja
 */
public class CommentDao {
    
    public List<Comment> getCommentsForRestaurant(String restaurantId) {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_COMMENTS_FOR_RESTAURANT);
            ps.setString(1, restaurantId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setUsername(rs.getString(USERNAME_SQL));
                comment.setMessage(rs.getString(MESSAGE_SQL));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }
    
    public List<Comment> getCommentsForFood(String foodId) {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_COMMENTS_FOR_FOOD);
            ps.setString(1, foodId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setUsername(rs.getString(USERNAME_SQL));
                comment.setMessage(rs.getString(MESSAGE_SQL));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }
    
    public boolean insertCommentForRestaurant(String username, String restaurantId, String message) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT_FOR_RESTAURANT);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, restaurantId);
            ps.setString(3, username);
            ps.setString(4, message);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean insertCommentForFood(String username, String foodId, String message) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT_FOR_FOOD);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, foodId);
            ps.setString(3, username);
            ps.setString(4, message);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
