/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.UsedService;

/**
 *
 * @author pc
 */
public class UsedServiceDAO extends DAO{

    public UsedServiceDAO() {
        super();
    }

    public boolean addUsedService(UsedService us, int returnedCarId) {
        boolean result = true;
        try {
            con.setAutoCommit(false);
            if (us.getId() != 0) {
                String sql = "Update used_service Set quantity = ?, price = ? Where id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, us.getQuantity());
                ps.setInt(2, us.getPrice());
                ps.setInt(3, us.getId());
                ps.executeUpdate();
            } else {
                String sql = "Insert Into used_service(quantity, price, returnedCarId, serviceId) Values (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, us.getQuantity());
                ps.setInt(2, us.getPrice());
                ps.setInt(3, returnedCarId);
                ps.setInt(4, us.getSv().getId());
                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    us.setId(generatedKeys.getInt(1));
                }
            }
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

    public boolean deleteUsedService(int usedServiceId) {
        boolean result = true;
        try {
            con.setAutoCommit(false);
            String sql = "Delete From used_service Where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usedServiceId);
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
