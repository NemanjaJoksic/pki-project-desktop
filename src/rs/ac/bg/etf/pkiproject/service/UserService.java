/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.service;

import rs.ac.bg.etf.pkiproject.dao.UserDao;
import rs.ac.bg.etf.pkiproject.model.User;

/**
 *
 * @author Nemanja
 */
public class UserService {
    
    private final UserDao userDao = new UserDao();
    
    public boolean loginUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if(user != null && password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean registerUser(User user) {
        if(userDao.getUserByUsername(user.getUsername()) != null) {
            return false;
        }
        userDao.createUser(user);
        return true;
    }
    
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
    
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
    
    
    public boolean updateUserPassword(String username, String newPassword) {
        return userDao.updateUserPassword(username, newPassword);
    }
    
}
