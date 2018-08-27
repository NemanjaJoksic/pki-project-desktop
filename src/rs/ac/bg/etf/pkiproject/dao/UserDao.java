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
import java.util.UUID;
import static rs.ac.bg.etf.pkiproject.dao.util.Fields.*;
import static rs.ac.bg.etf.pkiproject.dao.util.Queries.*;
import rs.ac.bg.etf.pkiproject.db.DB;
import rs.ac.bg.etf.pkiproject.model.User;

/**
 *
 * @author Nemanja
 */
public class UserDao {
    
    public User getUserByUsername(String username) {
        User user = new User();
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_USERNAME);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setUsername(rs.getString(USERNAME_SQL));
                user.setPassword(rs.getString(PASSWORD_SQL));
                user.setName(rs.getString(NAME_SQL));
                user.setSurname(rs.getString(SURNAME_SQL));
                user.setPhoneNumber(rs.getString(PHONE_NUMBER_SQL));
                user.setAddress(rs.getString(ADDRESS_SQL));
                user.setEmail(rs.getString(EMAIL_SQL));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean createUser(User user) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUser(User user) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUsername());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUserPassword(String username, String newPassword) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_PASSWORD);
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
