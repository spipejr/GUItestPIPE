/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Programmer
 */
    

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class database {
    public static Connection con;
    public static String HostName = "localhost";
    public static String DbName = "test";
    public static String UserName = "root";
    public static String Password = "000000";
    public static String PortNumber = "3306";
    public void dbconnect() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + HostName + ":" + PortNumber + "/" + DbName+"?characterEncoding=utf-8", UserName, Password);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Connection Error !!!", JOptionPane.WARNING_MESSAGE);
        }
    }
}