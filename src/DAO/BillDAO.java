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
import model.Car;
import model.CarComponent;
import model.Client;
import model.ReturnedCar;
import model.Service;
import model.UsedComponent;
import model.UsedService;

/**
 *
 * @author pc
 */
public class BillDAO extends DAO{

    public BillDAO() {
        super();
    }

    public Bill searchBill(int billId) {
        Bill bill = new Bill();
        Car car = new Car();
        Client client = new Client();
        ReturnedCar rc = new ReturnedCar();
        ArrayList<UsedComponent> luc = new ArrayList<UsedComponent>();
        ArrayList<UsedService> lus = new ArrayList<UsedService>();
        try {
            // get information of bill
            String sql = "SELECT createDate, amount, clientId FROM bill WHERE id = ? And statusBill = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill.setId(billId);
                bill.setCreateDate(rs.getDate("createDate"));
                bill.setAmount(rs.getInt("amount"));
                int clientId = rs.getInt("clientId");
                // get information of client object
                sql = "Select fullName, phone, address From client where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, clientId);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    client.setFullname(rs2.getString("fullName"));
                    client.setPhone(rs2.getString("phone"));
                    client.setAddress(rs2.getString("address"));
                    bill.setClient(client);
                }
            } else {
                return null;
            }
            // get id, carId for query car object and usedComponent/Service
            sql = "Select id, carId From returned_car Where billId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();
            if (rs.next()) {
                rc.setId(rs.getInt("id"));
                int carId = rs.getInt("carId");
                //get car object
                sql = "Select carLicensePlate From car Where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, carId);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    car.setCarLicensePlate(rs2.getString("carLicensePlate"));
                    rc.setCar(car);
                }
                sql = "Select id, quantity, price, carComponentId From used_component Where returnedCarId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, rc.getId());
                rs2 = ps.executeQuery();
                while (rs2.next()) {
                    UsedComponent uc = new UsedComponent();
                    uc.setId(rs2.getInt("id"));
                    uc.setQuantity(rs2.getInt("quantity"));
                    uc.setPrice(rs2.getInt("price"));
                    sql = "Select name, type From car_component Where id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, rs2.getInt("carComponentId"));
                    ResultSet rs3 = ps.executeQuery();
                    if (rs3.next()) {
                        CarComponent cpn = new CarComponent();
                        cpn.setId(rs2.getInt("carComponentId"));
                        cpn.setName(rs3.getString("name"));
                        cpn.setType(rs3.getString("type"));
                        uc.setCpn(cpn);
                    }
                    luc.add(uc);
                }
                rc.setLuc(luc);
                sql = "Select id, quantity, price, serviceId From used_service Where returnedCarId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, rc.getId());
                rs2 = ps.executeQuery();
                while (rs2.next()) {
                    UsedService us = new UsedService();
                    us.setId(rs2.getInt("id"));
                    us.setQuantity(rs2.getInt("quantity"));
                    us.setPrice(rs2.getInt("price"));
                    sql = "Select name From service Where id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, rs2.getInt("serviceId"));
                    ResultSet rs3 = ps.executeQuery();
                    if (rs3.next()) {
                        Service sv = new Service();
                        sv.setId(rs2.getInt("serviceId"));
                        sv.setName(rs3.getString("name"));
                        us.setSv(sv);
                    }
                    lus.add(us);
                }
                rc.setLus(lus);
                bill.setRc(rc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;
    }

    public boolean payBill(int billId) {
        boolean result = true;
        try {
            con.setAutoCommit(false);
            String sql = "Update bill Set statusBill = 1 Where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, billId);
            ps.executeUpdate();
            con.commit(); // chỗ cmt
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
                con.setAutoCommit(true); // chỗ cmt
            } catch (Exception ex) {
                result = false;
                ex.printStackTrace();
            }
        }
        return result;
    }

    public boolean updateBillTotalAmount(Bill bill) {
        boolean result = true;
        try {
            con.setAutoCommit(false);
            String sql = "Update bill Set amount = ? Where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bill.getAmount());
            ps.setInt(2, bill.getId());
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
