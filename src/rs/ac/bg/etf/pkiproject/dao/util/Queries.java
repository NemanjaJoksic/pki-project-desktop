/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.dao.util;

/**
 *
 * @author Nemanja
 */
public class Queries {
    
    public static final String GET_ALL_INGREDIENTS = "SELECT DISTINCT(NAME) FROM INGREDIENT";
    
    public static final String GET_USER_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?";
    
    public static final String INSERT_USER = "INSERT INTO USER VALUES(?, ?, ?, ?, ?, ?, ?)";
    
    public static final String UPDATE_USER_PASSWORD = "UPDATE USER SET PASSWORD = ? WHERE USERNAME = ?";
    
    public static final String UPDATE_USER = "UPDATE USER SET NAME = ?, SURNAME = ?, PHONE_NUMBER = ?"
                                           + ", ADDRESS = ?, EMAIL = ? WHERE USERNAME = ?";
    
    public static final String GET_ALL_RESTAURANTS = "SELECT * FROM RESTAURANT";
    
    public static final String GET_COMMENTS_FOR_RESTAURANT = "SELECT * FROM COMMENT_R WHERE ID_R = ?";
    
    public static final String SEARCH_RESTAURANTS = "SELECT * FROM RESTAURANT WHERE NAME LIKE ? "
                    + "AND LOCATION LIKE ? AND KITCHEN_TYPE LIKE ? AND RAITING >= ? AND RAITING <= ?";
    
    public static final String GET_ALL_FOOD = "SELECT * FROM FOOD";
    
    public static final String GET_ALL_FOOD_FOR_RESTAURANT = "SELECT hf.ID, f.NAME, f.DESCRIPTION, hf.PRICE"
                    + " FROM FOOD f, HAS_FOOD hf WHERE f.ID = hf.ID_F AND hf.ID_R = ?";
    
    public static final String GET_COMMENTS_FOR_FOOD = "SELECT * FROM COMMENT_F WHERE ID_HF = ?";
    
    public static final String SEARCH_FOOD = "SELECT hf.ID, f.NAME, f.DESCRIPTION, hf.PRICE FROM FOOD f, HAS_FOOD hf\n"
                    + "WHERE f.ID = hf.ID_F AND hf.ID_R = ? AND f.NAME LIKE ? AND f.KITCHEN_TYPE LIKE ?\n"
                    + "AND (SELECT COUNT(*) FROM INGREDIENT i, HAS_INGREDIENT hi\n"
                    + "WHERE i.ID = hi.ID_I AND hi.ID_F = f.ID AND i.NAME IN (#ING)) = ?";
    
    public static final String INSERT_COMMENT_FOR_RESTAURANT = "INSERT INTO COMMENT_R VALUES (?, ?, ?, ?)";
    
    public static final String INSERT_COMMENT_FOR_FOOD = "INSERT INTO COMMENT_F VALUES (?, ?, ?, ?)";
    
    public static final String GET_ORDERS_FOR_USER = "SELECT o.ID, o.USERNAME, o.TOTAL_PRICE, o.STATUS,"
                    + " i.QUANTITY,  hf.PRICE, f.NAME AS F_NAME, f.DESCRIPTION, r.NAME AS R_NAME"
                    + " FROM ORDERS o, ITEM i, HAS_FOOD hf, FOOD f, RESTAURANT r \n"
                    + "WHERE i.ID_O = o.ID AND i.ID_HF = hf.ID AND hf.ID_F = f.ID AND hf.ID_R = r.ID AND o.USERNAME = ?";
    
    public static final String INSERT_ORDER = "INSERT INTO ORDERS VALUES(?, ?, ?, 'Aktivna')";

    public static final String INSERT_ITEM = "INSERT INTO ITEM VALUES(?, ?, ?, ?)";
    
}
