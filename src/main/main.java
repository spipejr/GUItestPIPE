/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import login.login;
import table.selectdeletetable;
import table.tabletoex;

/**
 *
 * @author Programmer
 */
public class main extends javax.swing.JFrame {
    
    private userpermission userper = new userpermission();
    
    public main() {
        initComponents();
//        loadlogin();
        time time = new time();
        Timer timer = new Timer(500,time);
        timer.start();
        String space = "     ";
        jLabeltest.setText(userper.UserName +space+ userper.UserSurname +space+ userper.UserAddress);
    }
    
    public void loadlogin()
    {
        this.setVisible(false);
        login frm = new login(this, true);
        frm.setVisible(true);
        this.setVisible(true);
        String User = frm.getLoginUser();
        userper.GetUserAction(User);
    }
    class time implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            try
            {
                SimpleDateFormat formatTIMETH = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("th","TH"));
                Date now = new Date();
                String strDate = formatTIMETH.format(now);
                jLabeltime.setText(strDate);
                
                SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
                String strTime = formattime.format(now);
                Date in = formattime.parse("8:30:00");
                Date out = formattime.parse("17:30:00");
                Date timenow = formattime.parse(strTime);
                 
                if (timenow.after(in) && timenow.before(out)) 
                {
                    jLabelstatus.setText("ทำงาน");
                    jLabelstatus.setForeground(Color.red);
                }else
                {
                    jLabelstatus.setText("เลิกงานแล้ว");
                    jLabelstatus.setForeground(Color.green);
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
            
            
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButtonexit = new javax.swing.JButton();
        jLabeltime = new javax.swing.JLabel();
        jLabelstatus = new javax.swing.JLabel();
        jLabeltest = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenufile = new javax.swing.JMenu();
        jMenuItemtestformat = new javax.swing.JMenuItem();
        jMenuItemtesttable = new javax.swing.JMenuItem();
        jMenuItemtablers2xml = new javax.swing.JMenuItem();
        Jmenuitemselectdeletetable = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuFood = new javax.swing.JMenu();
        jMenuItemFood = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar3.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar3.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar4.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar4.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar5.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar5.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar6.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar6.add(jMenu10);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setName(""); // NOI18N

        jButtonexit.setText("exit");
        jButtonexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonexitActionPerformed(evt);
            }
        });

        jLabeltime.setBackground(new java.awt.Color(255, 255, 255));
        jLabeltime.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabeltime.setText("time");

        jLabelstatus.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelstatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelstatus.setText("สถานะ");

        jLabeltest.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonexit)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabeltime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabeltest, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabeltime, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabeltest, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonexit)
                .addContainerGap())
        );

        jMenufile.setText("table&form");

        jMenuItemtestformat.setText("testformat");
        jMenuItemtestformat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemtestformatActionPerformed(evt);
            }
        });
        jMenufile.add(jMenuItemtestformat);

        jMenuItemtesttable.setText("testtable");
        jMenuItemtesttable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemtesttableActionPerformed(evt);
            }
        });
        jMenufile.add(jMenuItemtesttable);

        jMenuItemtablers2xml.setText("testtablerx2ml");
        jMenuItemtablers2xml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemtablers2xmlActionPerformed(evt);
            }
        });
        jMenufile.add(jMenuItemtablers2xml);

        Jmenuitemselectdeletetable.setText("selectdeletetableมีครบ");
        Jmenuitemselectdeletetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmenuitemselectdeletetableActionPerformed(evt);
            }
        });
        jMenufile.add(Jmenuitemselectdeletetable);
        jMenufile.add(jSeparator1);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenufile.add(jMenuItemExit);

        jMenuBar1.add(jMenufile);

        jMenuFood.setText("555");

        jMenuItemFood.setText("555");
        jMenuItemFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFoodActionPerformed(evt);
            }
        });
        jMenuFood.add(jMenuItemFood);

        jMenuBar1.add(jMenuFood);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonexitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonexitActionPerformed

    private void jMenuItemtestformatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemtestformatActionPerformed
        if(userper.Usersession.equals("Y"))
        {
            format form = new format(this,true);
//            this.setVisible(false);
            form.setVisible(true);
//            this.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "ไม่มีสิทธิ์");
        }
        
    }//GEN-LAST:event_jMenuItemtestformatActionPerformed

    private void jMenuItemtablers2xmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemtablers2xmlActionPerformed
        if(userper.Usersession.equals("Y"))
        {
            table.tablers2xml form = new table.tablers2xml();
//            this.setVisible(false);
            form.setVisible(true);
//            this.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "ไม่มีสิทธิ์");
        }
    }//GEN-LAST:event_jMenuItemtablers2xmlActionPerformed

    private void jMenuItemtesttableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemtesttableActionPerformed
        if(userper.Usersession.equals("Y"))
        {
            tabletoex form = new tabletoex(this,true);
            form.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "ไม่มีสิทธิ์");
        }
    }//GEN-LAST:event_jMenuItemtesttableActionPerformed

    private void jMenuItemFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFoodActionPerformed
        if(userper.Usersession.equals("Y"))
        {
            F5555 form = new F5555(this,true);
            form.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "ไม่มีสิทธิ์");
        }
        
        
        
    }//GEN-LAST:event_jMenuItemFoodActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void JmenuitemselectdeletetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmenuitemselectdeletetableActionPerformed
        if(userper.Usersession.equals("Y"))
        {
            selectdeletetable form = new selectdeletetable(this,true);
            form.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "ไม่มีสิทธิ์");
        }
    }//GEN-LAST:event_JmenuitemselectdeletetableActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Jmenuitemselectdeletetable;
    private javax.swing.JButton jButtonexit;
    private javax.swing.JLabel jLabelstatus;
    private javax.swing.JLabel jLabeltest;
    private javax.swing.JLabel jLabeltime;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenu jMenuFood;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemFood;
    private javax.swing.JMenuItem jMenuItemtablers2xml;
    private javax.swing.JMenuItem jMenuItemtestformat;
    private javax.swing.JMenuItem jMenuItemtesttable;
    private javax.swing.JMenu jMenufile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
