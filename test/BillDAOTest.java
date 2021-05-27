/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import DAO.BillDAO;
import DAO.DAO;
import model.Bill;
import org.junit.Assert;
import org.junit.Test;
/**
 *
 * @author pc
 */
public class BillDAOTest {
    BillDAO bd = new BillDAO();
    @Test
    public void testSearchBillException1(){
        int key = 10;
        Bill bill = bd.searchBill(key);
        Assert.assertNull(bill);
        Assert.assertEquals(null, bill);
        return;
    }
    @Test
    public void testSearchBillException2(){
        int key = 20;
        Bill bill = bd.searchBill(key);
        Assert.assertNull(bill);
        Assert.assertEquals(null, bill);
        return;
    }
    @Test
    public void testSearchBillStandard1(){
        int key = 1;
        Bill bill = bd.searchBill(key);
        Assert.assertNotNull(bill);
        Assert.assertEquals(key, bill.getId());
        return;
    }
    @Test
    public void testSearchBillStandard2(){
        int key = 3;
        Bill bill = bd.searchBill(key);
        Assert.assertNotNull(bill);
        Assert.assertEquals(key, bill.getId());
        return;
    }
    @Test
    public void testPayBill(){
        Connection con = DAO.con;
        BillDAO bd = new BillDAO();
        int billId = 3;
        try {
            con.setAutoCommit(false);
            boolean res = bd.payBill(billId);
            Assert.assertEquals(true, res);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }
}
