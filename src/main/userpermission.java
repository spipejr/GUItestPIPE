/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Programmer
 */
public class userpermission {
    String Userid = "";
    String UserNamename = "";
    String UserSurname = "" ;
    String UserAddress = "" ;
    String Usersession = "Y" ;
    database db = new database();
    public boolean  GetUserAction(String Userid2) {
        try {
            Statement stmt = (Statement) database.con.createStatement();
            String SQLQuery = "select  *from login Where(username= '" + Userid2 + "')";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            
                if (rec.getRow() == 0) {
                    JOptionPane.showMessageDialog(null,"รหัสผู้ใช้งาน (Username) และรหัสผ่าน (Password) ไม่ถูกต้อง !!! ");
                    return false ;
                } else 
                {

                    Userid = rec.getString("username") ;
                    UserNamename = rec.getString("name") ;
                    UserSurname = rec.getString("surname") ;
                    UserAddress = rec.getString("address") ;
                    Usersession = rec.getString("session") ;

                    return true ;
                }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false ;
        }
        
    }
    public String getusername()
    {
        return UserNamename;
    }
}
