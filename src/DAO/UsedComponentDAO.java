/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.UsedComponent;

/**
 *
 * @author pc
 */
public class UsedComponentDAO extends DAO{

    public UsedComponentDAO() {
        super();
    }
    public boolean addUsedComponent(UsedComponent uc, int returnedCarId) {
        boolean result = true;
        try {
            con.setAutoCommit(false);
            if(uc.getId() != 0) {
                String sql = "Update used_component Set quantity = quantity + ?, price = ? Where id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, uc.getQuantity());
                ps.setInt(2, uc.getPrice());
                ps.setInt(3, uc.getId());
                ps.executeUpdate();
            }
            else {
                String sql = "Insert Into used_component(quantity, price, returnedCarId, carComponentId) Values (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, uc.getQuantity());
                ps.setInt(2, uc.getPrice());
                ps.setInt(3, returnedCarId);
                ps.setInt(4, uc.getCpn().getId());
                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    uc.setId(generatedKeys.getInt(1));
                }
            }
            String sql = "Update car_component Set stock = stock - ? Where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uc.getQuantity());
            ps.setInt(2, uc.getCpn().getId());
            ps.executeUpdate();
            con.commit();
           
        } catch (Exception e) {
            result = false;
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
                result = false;
                ex.printStackTrace();
            }
        }
        return result;
    }
    public boolean deleteUsedComponent(UsedComponent uc) {
        boolean result = true;
        try {
            con.setAutoCommit(false);
            String sql = "Delete From used_component Where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uc.getId());
            ps.executeUpdate();
            sql = "Update car_component Set stock = stock + ? Where id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, uc.getQuantity());
            ps.setInt(2, uc.getCpn().getId());
            ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            result = false;
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
                result = false;
                ex.printStackTrace();
            }
        }
        return result;
    }
    
}
