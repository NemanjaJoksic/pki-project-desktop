/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.context;

import java.util.HashMap;
import java.util.Map;
import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_ATTR;
import static rs.ac.bg.etf.pkiproject.context.Context.FOOD_ATTR;

/**
 *
 * @author Nemanja
 */
public class Session {
    
    private Map<String, Object> attributes = new HashMap<>();
    
    public void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }
    
    public Object getAttribute(String key) {
        return attributes.get(key);
    }
    
    public void removeAttribute(String key) {
        attributes.remove(key);
    }
    
    public void clearSession() {
        attributes.remove(RESTAURANT_ATTR);
        attributes.remove(FOOD_ATTR);
    }
    
}
