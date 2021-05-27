/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CarComponent;

/**
 *
 * @author pc
 */
public class CarComponentDAO extends DAO{
    public CarComponentDAO() {
        super();
    }
    public ArrayList<CarComponent> searchComponent(String name) {
        ArrayList<CarComponent> res = new ArrayList<CarComponent>();
        try {
            String sql = "Select id, name, price, type, stock From car_component Where name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            name = "%" + name + "%";
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                CarComponent cpn = new CarComponent();
                cpn.setId(rs.getInt("id"));
                cpn.setName(rs.getString("name"));
                cpn.setPrice(rs.getInt("price"));
                cpn.setType(rs.getString("type"));
                cpn.setStock(rs.getInt("stock"));
                res.add(cpn);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
