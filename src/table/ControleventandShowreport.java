/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import database.database;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.userpermission;

import report.Report;


/**
 *
 * @author Programmer
 */
    
public class ControleventandShowreport extends javax.swing.JDialog {
    private userpermission userper = new userpermission();
    DefaultTableModel model1 = new DefaultTableModel();
    database db = new database();
    Report r;
    
    final String path = "/report/";
    public ControleventandShowreport(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        db.dbconnect();
        model1 = (DefaultTableModel) jTable1.getModel();
        cleardataintable();
        loaddatatotable();
        
        jTable1.setModel(model1);
        
        
        
    }
    
    
    
    private void loaddatatotable() 
    {
        String sql ="Select * From refundmessage";
        Object [] data;
        try {
            Statement stmt = (Statement) database.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                cleardataintable();
            }
            while (rs.next()) {
                data = new Object[4];
                data[0] = rs.getInt("voidcode");
                data[1] = rs.getString("voidmsg");
                data[2] = rs.getString("chkrefno");
                data[3] = rs.getString("voidget");
                model1.addRow(data);
            }
            rs.close();
            stmt.close();
//            jFormattedTextFieldID.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void cleardataSHOW()
    {
        cleardataintable();
        loaddatatotable();
        clearCode();
        jFormattedTextFieldID.setText("");
        jTextFieldData.setText("");
        jComboBoxvoid.setSelectedItem("YES");
        jComboBoxAudiNo.setSelectedItem("YES");
        jFormattedTextFieldID.requestFocusInWindow();
        
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
    
    private void clearCode()
    {
        jFormattedTextFieldID.setEditable(true);
        jFormattedTextFieldID.setBackground(Color.white);
    }
    
    private void selectCode()
    {
        jFormattedTextFieldID.setEditable(false);
        jFormattedTextFieldID.setBackground(Color.orange);
        jTextFieldData.requestFocusInWindow();
    }
    
    private void Selecttabletotext(String i)
    {
        try {
            Statement stmt = (Statement) database.con.createStatement();
            String sql = "select * from refundmessage "
                    + "where voidcode = '"+ i +"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {   
                jFormattedTextFieldID.setText(rs.getString("voidcode"));
                jTextFieldData.setText(rs.getString("voidmsg"));
//                jComboBoxvoid.setSelectedItem(rs.getString("chkrefno"));
//                jComboBoxAudiNo.setSelectedItem(rs.getString("voidget"));
                if(rs.getString("chkrefno").equalsIgnoreCase("Y"))
                {
                    jComboBoxvoid.setSelectedItem("YES");
                }else
                {
                    jComboBoxvoid.setSelectedItem("NO");
                }
                if(rs.getString("voidget").equalsIgnoreCase("Y"))
                {
                    jComboBoxAudiNo.setSelectedItem("YES");
                }else
                {
                    jComboBoxAudiNo.setSelectedItem("NO");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void Delete()
    {
        if (Checkcode(jFormattedTextFieldID.getText())) 
        {
            String as = "คุณต้องการลบใช่หรือไม่";
            int input = JOptionPane.showConfirmDialog(null, as, null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == JOptionPane.YES_OPTION) 
            {
                    try 
                    {
                        int a = Integer.parseInt(jFormattedTextFieldID.getText());
                        Statement stmt = (Statement) database.con.createStatement();
                        String sql = "Delete From refundmessage Where voidcode = '"+a+"' ";
                        stmt.execute(sql);
                        stmt.close();
                        cleardataSHOW();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
            }else
            {
                jTextFieldData.requestFocusInWindow();
            }
            
        }else
        {
            jFormattedTextFieldID.requestFocusInWindow();
        }
    }
    
     public boolean Checkcode(String code) {
        boolean check = false;
        ResultSet rs = null;
        try {

            Statement stmt = (Statement) database.con.createStatement();
            String sql = "select * from refundmessage where voidcode ='" + code + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }

        } catch (Exception e) {
        }
        return check;
    }
    
    private void update()
    {
        try
        {
            int voidcode = Integer.parseInt(jFormattedTextFieldID.getText());
            String voidmsg = jTextFieldData.getText();
            char[] chkrefno = {jComboBoxvoid.getSelectedItem().toString().charAt(0)};
            char[] voidget = {jComboBoxAudiNo.getSelectedItem().toString().charAt(0)};
            String strchkrefno = String.valueOf(chkrefno);
            String strvoidget = String.valueOf(voidget);
            String sql = "update refundmessage set voidmsg=?, chkrefno=?, voidget=? where voidcode=?";
            PreparedStatement p = (PreparedStatement) database.con.prepareStatement(sql);
            
            p.setString(1, voidmsg);
            p.setString(2, strchkrefno);
            p.setString(3, strvoidget);
            p.setInt(4, voidcode);
            p.executeUpdate();
            p.close();
            
            cleardataSHOW();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void insert()
    {
        try {
            int voidcode = Integer.parseInt(jFormattedTextFieldID.getText());
            String voidmsg = jTextFieldData.getText();
            char[] chkrefno = {jComboBoxvoid.getSelectedItem().toString().charAt(0)};
            char[] voidget = {jComboBoxAudiNo.getSelectedItem().toString().charAt(0)};
            String strchkrefno = String.valueOf(chkrefno);
            String strvoidget = String.valueOf(voidget);
            String sql = "insert into refundmessage (voidcode,voidmsg,chkrefno,voidget) values (?,?,?,?) ";
            PreparedStatement p = (PreparedStatement) database.con.prepareStatement(sql);
            p.setInt(1, voidcode);
            p.setString(2, voidmsg);
            p.setString(3, strchkrefno);
            p.setString(4, strvoidget);
            p.executeUpdate();
            p.close();
            
            cleardataSHOW();
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public boolean testint()
    {
        try
        {
            int a = (Integer.parseInt(jFormattedTextFieldID.getText()));
            a = a +1;
            return true;
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "โปรดระบุตัวเลข");
            return false;
            
        }
    }
    
    private void printReport() {
        r = new Report(path);
        String fileName = "newreport";
        
        String comname = userper.getusername();
        String reportname = "Report Void";
        String Appname = "TestReport";

        Map map = new HashMap();

        map.put("comname", comname);
        map.put("reportname", reportname);
        map.put("appname", Appname);
        r.viewReport(fileName, map);
        
        
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
        jButtonPrint = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldID = new javax.swing.JFormattedTextField();
        jTextFieldData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxAudiNo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxvoid = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCancel = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemDelete = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jButtonPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Printer-icon.png"))); // NOI18N
        jButtonPrint.setText("พิมพ์");
        jButtonPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPrint);
        jButtonPrint.setBounds(500, 0, 100, 80);

        jButtonCancel.setFont(new java.awt.Font("TH SarabunPSK", 1, 18)); // NOI18N
        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cancel-icon.png"))); // NOI18N
        jButtonCancel.setText("ยกเลิก");
        jButtonCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancel.setIconTextGap(0);
        jButtonCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancel);
        jButtonCancel.setBounds(0, 0, 100, 80);

        jButtonSave.setFont(new java.awt.Font("TH SarabunPSK", 0, 18)); // NOI18N
        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Actions-document-save-icon1.png"))); // NOI18N
        jButtonSave.setText("บันทึกข้อมูล");
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSave);
        jButtonSave.setBounds(100, 0, 100, 80);

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete-icons.png"))); // NOI18N
        jButtonDelete.setText("ลบข้อมูล");
        jButtonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete);
        jButtonDelete.setBounds(200, 0, 100, 80);

        jButtonExit.setFont(new java.awt.Font("TH SarabunPSK", 0, 18)); // NOI18N
        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Log-Out-icon.png"))); // NOI18N
        jButtonExit.setText("Exit");
        jButtonExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonExit);
        jButtonExit.setBounds(600, 0, 100, 80);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 700, 80);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(700, 120));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 120));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("TH SarabunPSK", 1, 18)); // NOI18N
        jLabel1.setText("รหัส CODE");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 15, 70, 30);

        jLabel2.setFont(new java.awt.Font("TH SarabunPSK", 1, 18)); // NOI18N
        jLabel2.setText("ตรวจสอบเลขที่บิลอ้างอิง");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(510, 60, 140, 30);

        jFormattedTextFieldID.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldID.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jFormattedTextFieldID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldIDKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldIDKeyTyped(evt);
            }
        });
        jPanel2.add(jFormattedTextFieldID);
        jFormattedTextFieldID.setBounds(100, 13, 40, 30);

        jTextFieldData.setFont(new java.awt.Font("TH SarabunPSK", 0, 18)); // NOI18N
        jTextFieldData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDataKeyPressed(evt);
            }
        });
        jPanel2.add(jTextFieldData);
        jTextFieldData.setBounds(100, 60, 220, 30);

        jLabel3.setFont(new java.awt.Font("TH SarabunPSK", 1, 18)); // NOI18N
        jLabel3.setText("รายละเอียด");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 60, 70, 30);

        jComboBoxAudiNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YES", "NO" }));
        jPanel2.add(jComboBoxAudiNo);
        jComboBoxAudiNo.setBounds(640, 60, 60, 30);

        jLabel4.setFont(new java.awt.Font("TH SarabunPSK", 1, 18)); // NOI18N
        jLabel4.setText("บันทึกเหตุการณ์ Void");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(330, 60, 120, 30);

        jComboBoxvoid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YES", "NO" }));
        jPanel2.add(jComboBoxvoid);
        jComboBoxvoid.setBounds(450, 60, 60, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 80, 700, 120);

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("TH SarabunPSK", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัส Code", "รายละเอียด", "บันทึกหมายเหตุ", "ตรวจสอบบิลที่อ้างอิง"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(0, 200, 700, 280);

        jMenuBar1.setMaximumSize(new java.awt.Dimension(27, 21));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(27, 24));

        jMenu1.setText("File");

        jMenuItemCancel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCancel.setText("ยกเลิก");
        jMenuItemCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCancel);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setText("บันทึก");
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSave);

        jMenuItemDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDelete.setText("ลบข้อมูล");
        jMenuItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDelete);
        jMenu1.add(jSeparator1);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(716, 548));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.dispose();
//        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount()==2)
        {  
            Selecttabletotext(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            selectCode();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        Delete();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        if(jFormattedTextFieldID.getText().equals("")&&jTextFieldData.getText().equals("")&&jComboBoxvoid.getSelectedIndex()!=1&&jComboBoxAudiNo.getSelectedIndex()!=1)
        {
            cleardataSHOW();
        }else
        {
            int input = JOptionPane.showConfirmDialog(null, "ต้องการยกเลิกข้อมูลใช่หรือไม่", null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == JOptionPane.YES_OPTION) 
            {
                cleardataSHOW();
            }
        }
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jFormattedTextFieldIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldIDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            if(jFormattedTextFieldID.getText().equals(""))
            {
                jFormattedTextFieldID.requestFocusInWindow();
            }else
            {
                if(testint())
                {
                    if(Checkcode(jFormattedTextFieldID.getText()))
                    {   
                        Selecttabletotext(jFormattedTextFieldID.getText());
                        selectCode();
                    }else
                    {
                        jTextFieldData.requestFocusInWindow();
                    }
                }
            }
            
        }
    }//GEN-LAST:event_jFormattedTextFieldIDKeyPressed

    private void jTextFieldDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            if(jTextFieldData.getText().equals(""))
            {
                cleardataSHOW();
            }else
            {
                if(jFormattedTextFieldID.getText().length()>0)
                {
                    jFormattedTextFieldID.requestFocusInWindow();
                }else
                {
                    jFormattedTextFieldID.requestFocusInWindow();
                    cleardataSHOW();
                }
            }
        }
    }//GEN-LAST:event_jTextFieldDataKeyPressed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if (Checkcode(jFormattedTextFieldID.getText())) 
        {
            String as = "คุณต้อง แก้ไข ข้อมูลใช่หรือไม่";
            int input = JOptionPane.showConfirmDialog(null, as, null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == JOptionPane.YES_OPTION) 
            {
                update();
            }else
            {
                jTextFieldData.requestFocusInWindow();
            }
        }else
        {
            if(jFormattedTextFieldID.getText().equals(""))
            {   
                cleardataSHOW();
            }else
            {
                String as = "คุณต้อง เพิ่ม ข้อมูลใหม่ ใช่หรือไม่";
                int input = JOptionPane.showConfirmDialog(null, as, null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (input == JOptionPane.YES_OPTION) 
                {
                    insert();
                }else
                {
                    jFormattedTextFieldID.requestFocusInWindow();
                }
            }
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jMenuItemCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelActionPerformed
        if(jFormattedTextFieldID.getText().equals("")&&jTextFieldData.getText().equals("")&&jComboBoxvoid.getSelectedIndex()!=1&&jComboBoxAudiNo.getSelectedIndex()!=1)
        {
            cleardataSHOW();
        }else
        {
            int input = JOptionPane.showConfirmDialog(null, "ต้องการยกเลิกข้อมูลใช่หรือไม่", null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == JOptionPane.YES_OPTION) 
            {
                cleardataSHOW();
            }
        }
    }//GEN-LAST:event_jMenuItemCancelActionPerformed

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        if (Checkcode(jFormattedTextFieldID.getText())) 
        {
            String as = "คุณต้อง แก้ไข ข้อมูลใช่หรือไม่";
            int input = JOptionPane.showConfirmDialog(null, as, null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == JOptionPane.YES_OPTION) 
            {
                update();
            }else
            {
                jTextFieldData.requestFocusInWindow();
            }
        }else
        {
            String as = "คุณต้อง เพิ่ม ข้อมูลใหม่ ใช่หรือไม่";
            int input = JOptionPane.showConfirmDialog(null, as, null,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (input == JOptionPane.YES_OPTION) 
            {
                insert();
            }else
            {
                
                jFormattedTextFieldID.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jMenuItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteActionPerformed
        Delete();
    }//GEN-LAST:event_jMenuItemDeleteActionPerformed

    private void jFormattedTextFieldIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldIDKeyTyped
        char a = evt.getKeyChar();
        if(a<'0'||a>'9')
        {
            evt.consume();
        }
    }//GEN-LAST:event_jFormattedTextFieldIDKeyTyped

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        printReport();
    }//GEN-LAST:event_jButtonPrintActionPerformed

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
            java.util.logging.Logger.getLogger(ControleventandShowreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControleventandShowreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControleventandShowreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControleventandShowreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ControleventandShowreport dialog = new ControleventandShowreport(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBoxAudiNo;
    private javax.swing.JComboBox<String> jComboBoxvoid;
    private javax.swing.JFormattedTextField jFormattedTextFieldID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemCancel;
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldData;
    // End of variables declaration//GEN-END:variables
}
