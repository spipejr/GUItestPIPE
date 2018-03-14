/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import database.database;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author Programmer
 */
public final class login extends javax.swing.JDialog {

    

    database db = new database();
    String strname;
    String Userid;
    
    public login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        db.dbconnect();
        timeofday time = new timeofday();
        Timer timer = new Timer(500,time);
        timer.start();
    }

    public void login()
    {
        try {
            Statement stmt = (Statement) database.con.createStatement();
            String sql = "SELECT * FROM login WHERE username = '"+jTextFielduser.getText()+"' AND password ='"+jPasswordField1.getText()+"' ";
            ResultSet rs = stmt.executeQuery(sql); 
            Userid = jTextFielduser.getText();
//            String sql = "SELECT * FROM login WHERE username = '"+jTextFielduser.getText()+"' AND password ='"+jPasswordField1.getText()+"' ";
//            PreparedStatement p = (PreparedStatement) database.con.prepareStatement(sql);
//            ResultSet rs = p.executeQuery();

            rs.first();
                if(rs.getRow()==0)
                {

                    JOptionPane.showMessageDialog(null, "ผิด");
                }
                else
                {
                    strname = rs.getString("name")+" "+rs.getString("surname");
                    
                    JOptionPane.showMessageDialog(null, strname+" ถูก " + getCurrentTimeStamp());
                    this.dispose();
                }
		rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   
//    public void keyboardcheck(java.awt.event.KeyEvent evt, String cpname) {
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (cpname.equals("jTextFielduser")) {
//                jPasswordField1.requestFocus();
//            } else if (cpname.equals("jPasswordField1")) {
//                jButtonlogin.requestFocus();
//            }
//        } 
//    }
    
    public String getCurrentTimeStamp() 
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatTH = new SimpleDateFormat("EEEE ที่ dd เดือน MMMM พ.ศ. yyyy", new Locale("th","TH"));
        SimpleDateFormat formatTH2 = new SimpleDateFormat("dd MMMM yyyy", new Locale("th","TH"));
        SimpleDateFormat formatTIMETH = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("th","TH"));
        Date now = new Date();
        String strDate = formatTIMETH.format(now);
        return strDate;
    }

    class timeofday implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            SimpleDateFormat formatTIMETH = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("th","TH"));
            Date now = new Date();
            String strDate = formatTIMETH.format(now);
            jTextFieldTime.setText(strDate);
            jLabeltime.setText("  "+strDate);
            
            Date date;
            try {
                date = formatTIMETH.parse(strDate);
                long different = (date.getTime()); 
                long secondsInMilli = 1000;
//                long minutesInMilli = secondsInMilli * 60;
//                long hoursInMilli = minutesInMilli * 60;
//                long daysInMilli = hoursInMilli * 24;

//                long elapsedDays = different / daysInMilli;
//                different = different % daysInMilli;
//                long elapsedHours = different / hoursInMilli;
//                different = different % hoursInMilli;
//                long elapsedMinutes = different / minutesInMilli;
//                different = different % minutesInMilli;
                long elapsedSeconds = different / secondsInMilli;
                if(elapsedSeconds%2==0)
                {
                    jTextFieldTime.setBackground(Color.white);
                    jTextFieldTime.setForeground(Color.black);
                    jLabeltime.setForeground(Color.white);
                    jPanel2.setBackground(Color.black);
                }else
                {
                    jTextFieldTime.setBackground(Color.black);
                    jTextFieldTime.setForeground(Color.white);
                    jLabeltime.setForeground(Color.black);
                    jPanel2.setBackground(Color.white);
                }
            } catch (ParseException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getLoginUser() 
    {
            return Userid;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFielduser = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButtonlogin = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextFieldTime = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabeltime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jTextFielduser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFielduserKeyPressed(evt);
            }
        });

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jButtonlogin.setText("login");
        jButtonlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonloginActionPerformed(evt);
            }
        });
        jButtonlogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonloginKeyPressed(evt);
            }
        });

        jButton1.setText("exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldTime.setForeground(new java.awt.Color(255, 51, 102));

        jLabel1.setText("login");

        jLabeltime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabeltime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabeltime, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFielduser)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFielduser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonlogin)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jTextFieldTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonloginActionPerformed
        login();
    }//GEN-LAST:event_jButtonloginActionPerformed

    private void jButtonloginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonloginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             login();
         }
    }//GEN-LAST:event_jButtonloginKeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            login();
        }
//        keyboardcheck(evt, "jPasswordField1");
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFielduserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFielduserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            login();
        }
//        keyboardcheck(evt, "jTextFielduser");
    }//GEN-LAST:event_jTextFielduserKeyPressed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                login dialog = new login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabeltime;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFielduser;
    // End of variables declaration//GEN-END:variables
    
}
