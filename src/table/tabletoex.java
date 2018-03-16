/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import database.database;
import java.io.FileOutputStream;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 *
 * @author Programmer
 */
public class tabletoex extends javax.swing.JDialog {
    
    DefaultTableModel model1 = new DefaultTableModel(0,0);
    database db = new database();

    /**
     * Creates new form table
     */
    public tabletoex(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        db.dbconnect();
        model1 = (DefaultTableModel) jTabledata.getModel();
        loaddatatotable();
        jTabledata.setModel(model1);
    }
    
    private void loaddatatotable() {
        
        String sql ="Select p_id,p_name,p_price,categories.c_name,categories.c_id From products "
                    + "left join categories on products.c_id = categories.c_id Where p_id;";
        
        try {
            Statement stmt = (Statement) database.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
//                cleartblGroupName();
            }
            while (rs.next()) {
                String[] data = new String[4];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                model1.addRow(data);
            }
            
            
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        jTabledata.setRowSelectionInterval(0, 0);
    }
    
    private void toExcell()
    {
        List<Forlistbeantoex>list = new ArrayList<Forlistbeantoex>();
        Forlistbeantoex bean = new Forlistbeantoex();
        try {
            Statement stmt = (Statement) database.con.createStatement();
            String sql ="Select p_id,p_name,p_price,categories.c_name,categories.c_id From products "
                    + "left join categories on products.c_id = categories.c_id Where p_id;";
            ResultSet rs = stmt.executeQuery(sql); 
            
            
            while(rs.next())
			{
				bean = new Forlistbeantoex();
				bean.setP_id(rs.getInt("p_id"));
				bean.setP_name(rs.getString("p_name"));
				bean.setP_price(rs.getDouble("p_price"));
				bean.setC_name(rs.getString("c_name"));
				list.add(bean);
			}
			for(Forlistbeantoex aaa : list)
			{
				System.out.println(aaa.getP_id());
				System.out.println(aaa.getP_name());
				System.out.println(aaa.getP_price());
				System.out.println(aaa.getC_name());
			}
                String usercom = System.getProperty("user.name"); //get username ของเครื่องเรา
                String filename = "C:/Users/"+usercom+"/Desktop/output.xls" ;
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("FirstSheet");  
                HSSFRow rowhead = sheet.createRow((short)0);
                rowhead.createCell(0).setCellValue("p_id");
                rowhead.createCell(1).setCellValue("p_name");
                rowhead.createCell(2).setCellValue("p_price");
                rowhead.createCell(3).setCellValue("c_name");
                int i = 1;
                for(Forlistbeantoex bbb : list)
                {
                    HSSFRow row = sheet.createRow((short)i);
                    row.createCell(0).setCellValue(bbb.getP_id());
                    row.createCell(1).setCellValue(bbb.getP_name());
                    row.createCell(2).setCellValue(bbb.getP_price());
                    row.createCell(3).setCellValue(bbb.getC_name());
                    i++;
                }


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");
        
        
        
        
        } catch (Exception ex) {
            Logger.getLogger(tabletoex.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jButtonExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabledata = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtontoExcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jButtonExit.setForeground(new java.awt.Color(153, 0, 0));
        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jTabledata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabledata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabledataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabledata);

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        jButtontoExcel.setText("toExcel");
        jButtontoExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontoExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtontoExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addGap(0, 37, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtontoExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jTabledataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabledataMouseClicked
        if(evt.getClickCount()==2)
        {    try {
//                jComboBox1.setSelectedIndex(jTable.getSelectedRow());
                Statement stmt = (Statement) database.con.createStatement();
                String sql = "select p_id,p_name,p_price,categories.c_name from products "
                        + "left join categories on products.c_id = categories.c_id where p_id = '"+ jTabledata.getValueAt(jTabledata.getSelectedRow(), 0).toString() +"'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    jLabel1.setText(rs.getString("p_id"));
                    jLabel2.setText(rs.getString("p_name"));
                    jLabel3.setText(rs.getString("p_price"));
                    jLabel4.setText(rs.getString("c_name"));

                }
            } catch (SQLException ex) {
                Logger.getLogger(tablers2xml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }//GEN-LAST:event_jTabledataMouseClicked

    private void jButtontoExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontoExcelActionPerformed
        toExcell();
    }//GEN-LAST:event_jButtontoExcelActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(tabletoex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabletoex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabletoex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabletoex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tabletoex dialog = new tabletoex(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtontoExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabledata;
    // End of variables declaration//GEN-END:variables
}
