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
import model.Bill;
import model.Service;
import model.UsedService;

/**
 *
 * @author pc
 */
public class ServiceDAO extends DAO{
    public ServiceDAO() {
        super();
    }
    public ArrayList<Service> searchService(String name) {
        ArrayList<Service> res = new ArrayList<Service>();
        try {
            String sql = "Select id, name, price From service Where name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            name = "%" + name + "%";
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Service sv = new Service();
                sv.setId(rs.getInt("id"));
                sv.setName(rs.getString("name"));
                sv.setPrice(rs.getInt("price"));
                res.add(sv);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
