/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import database.database;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Programmer
 */
public class selectdeletetable extends javax.swing.JDialog {

    /**
     * Creates new form selectdeletetable
     */
    DefaultTableModel model1 = new DefaultTableModel();
    database db = new database();
    public selectdeletetable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        db.dbconnect();
        model1 = (DefaultTableModel) jTable1.getModel();
        loaddatatotable();
        loaddatatocombobox();
        jTable1.setModel(model1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void loaddatatotable() {
//        cleardataintable();
        String sql ="Select p_id,p_name,p_price,categories.c_name,categories.c_id From products "
                    + "left join categories on products.c_id = categories.c_id Where p_id order by p_id";
        Object [] data;
        try {
            Statement stmt = (Statement) database.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                cleardataintable();
            }
            while (rs.next()) {
                data = new Object[5];
                data[0] = false;
                data[1] = rs.getString(1);
                data[2] = rs.getString(2);
                data[3] = rs.getString(3);
                data[4] = rs.getString(4);
                model1.addRow(data);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
//        jTable1.setRowSelectionInterval(0,0);
    }
    
    public void loaddatatocombobox() 
    {
        if(jComboBoxC_id.getItemCount()>1)
        {    
            for(int i=jComboBoxC_id.getItemCount()-1; i>=0; i--)
            {
                jComboBoxC_id.removeItemAt(i);
            }
        }  
        try {
                Statement stmt = (Statement) database.con.createStatement();
                String sql = "select * from categories order by c_id";
                ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String id = Integer.toString(rs.getInt("c_Id"));
                jComboBoxC_id.addItem(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tablers2xml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void DeleteCheck(String a)
    {
        try 
        {
            Statement stmt = (Statement) database.con.createStatement();
            String sql = "Delete From products Where p_id = '"+a+"' ";
            stmt.execute(sql);
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(tablers2xml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void cleardataintable()
    {
        int rowCount = model1.getRowCount();
//        for (int i = rowCount - 1; i >= 0; i--) 
//        {
//            model1.removeRow(i);
//        }
        if (rowCount > 0) {
            for (int i = 0; i < rowCount; i++) {
                model1.removeRow(0);
            }
        }
    }  
    
    private void update()
    {
        try
        {
            int p_id = Integer.parseInt(jTextFieldP_ID.getText());
            String p_name = jTextFieldP_Name.getText();
            String p_price = jTextFieldP_price.getText();
            String c_id = jComboBoxC_id.getSelectedItem().toString();
            String sql = "update products set p_name=?, p_price=?, c_id=? where p_id=?";
            PreparedStatement p = (PreparedStatement) database.con.prepareStatement(sql);
            p.setString(1, p_name);
            p.setDouble(2, Double.parseDouble(p_price));
            p.setString(3, c_id);
            p.setInt(4, p_id);
            p.executeUpdate();
            p.close();
            clearalldata();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void add()
    {
        try {
            String sql = "insert into products (p_name,p_price,c_id) values (?,?,?) ";
            PreparedStatement prm = (PreparedStatement) database.con.prepareStatement(sql);
            prm.setString(1, jTextFieldP_Name.getText());
            prm.setString(2, jTextFieldP_price.getText());
            prm.setString(3, jComboBoxC_id.getSelectedItem().toString());
            prm.executeUpdate();
            prm.close();
            clearalldata();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void combogetCname()
    {
        try {
            Statement stmt = (Statement) database.con.createStatement();
            String sql = "select * from Categories where C_id = '"+ jComboBoxC_id.getSelectedItem() +"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                jTextFieldC_Name.setText(rs.getString("C_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(tablers2xml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteFromcheckboxintable()
    {
        //GET SELECTED ROW
        for(int i=0;i<jTable1.getRowCount();i++)
        {
            Boolean checked = Boolean.valueOf(jTable1.getValueAt(i, 0).toString());
            String colP_ID = jTable1.getValueAt(i, 1).toString(); 
            String colP_name = jTable1.getValueAt(i, 2).toString(); 
            //DISPLAY
            if(checked)
            {
                DeleteCheck(colP_ID);
                JOptionPane.showMessageDialog(null," ลบ   "+ colP_ID+"   "+colP_name);
            }
        }
        cleardataintable();
        loaddatatotable();
    }
    
    private void Clicktabletotext(MouseEvent evt)
    {
        if(evt.getClickCount()==2)
        {    
            try {
    //                jComboBoxC_id.setSelectedIndex(jTable1.getSelectedRow());
                Statement stmt = (Statement) database.con.createStatement();
                String sql = "select p_id,p_name,p_price,products.c_id,categories.c_name from products "
                        + "left join categories on products.c_id = categories.c_id where p_id = '"+ jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString() +"'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    jTextFieldP_ID.setText(rs.getString("p_id"));
                    jTextFieldP_Name.setText(rs.getString("p_name"));
                    jTextFieldP_price.setText(rs.getString("p_price"));
                    jTextFieldC_Name.setText(rs.getString("c_name"));
                    clearcombobox();
                    loaddatatocombobox();
                    jComboBoxC_id.setSelectedIndex(Integer.parseInt(rs.getString("products.c_id"))-1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(tablers2xml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    private void clearcombobox()
    {
        if(jComboBoxC_id.getItemCount()>1)
        {    
            for(int i=jComboBoxC_id.getItemCount()-1; i>=0; i--)
            {
                jComboBoxC_id.removeItemAt(i);
            }
        }  
    }
    
    private void clearalldata()
    {
        cleardataintable();
        clearcombobox();
        loaddatatotable();
        loaddatatocombobox();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonDel = new javax.swing.JButton();
        jTextFieldP_ID = new javax.swing.JTextField();
        jTextFieldP_Name = new javax.swing.JTextField();
        jTextFieldP_price = new javax.swing.JTextField();
        jTextFieldC_Name = new javax.swing.JTextField();
        jButtonupdate = new javax.swing.JButton();
        jComboBoxC_id = new javax.swing.JComboBox<>();
        jButtonadd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "คลิกเพื่อลบ", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonDel.setText("ลบ");
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });

        jTextFieldP_ID.setEditable(false);
        jTextFieldP_ID.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldP_ID.setText("jTextField1");

        jTextFieldP_Name.setText("jTextField2");

        jTextFieldP_price.setText("jTextField3");

        jTextFieldC_Name.setEditable(false);
        jTextFieldC_Name.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldC_Name.setText("jTextField4");

        jButtonupdate.setText("แก้ไข");
        jButtonupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonupdateActionPerformed(evt);
            }
        });

        jComboBoxC_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxC_idActionPerformed(evt);
            }
        });

        jButtonadd.setText("เพิ่ม");
        jButtonadd.setSelected(true);
        jButtonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonDel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldP_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldP_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldP_price, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxC_id, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldC_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jTextFieldP_price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxC_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldP_Name, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldP_ID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldC_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        deleteFromcheckboxintable();
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        Clicktabletotext(evt);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonupdateActionPerformed
        update();
    }//GEN-LAST:event_jButtonupdateActionPerformed

    private void jComboBoxC_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxC_idActionPerformed
        combogetCname();
    }//GEN-LAST:event_jComboBoxC_idActionPerformed

    private void jButtonaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddActionPerformed
        add();
    }//GEN-LAST:event_jButtonaddActionPerformed

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
            java.util.logging.Logger.getLogger(selectdeletetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selectdeletetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selectdeletetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selectdeletetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                selectdeletetable dialog = new selectdeletetable(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonadd;
    private javax.swing.JButton jButtonupdate;
    private javax.swing.JComboBox<String> jComboBoxC_id;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldC_Name;
    private javax.swing.JTextField jTextFieldP_ID;
    private javax.swing.JTextField jTextFieldP_Name;
    private javax.swing.JTextField jTextFieldP_price;
    // End of variables declaration//GEN-END:variables
}
