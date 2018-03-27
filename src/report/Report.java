/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import com.sun.prism.j2d.J2DPipeline;
import database.database;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Programmer
 */
public class Report {
        database db = new database();
        private String rootPath = "/root/report/";
        protected SimpleDateFormat humanFmt;
        protected SimpleDateFormat sqlFmt;
     
        public Report(String path) 
        {
            db.dbconnect();
            humanFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            sqlFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            rootPath = path;

    //        if (cl.setting != 0) {
    //            alert1 = "Data not found !!!";
    //            alert2 = "System Error !!!";
    //        }
        }
        
        public void viewReport(String reportName, Map parameters) {
        try {
            JasperReport jasperReport = getCompiledReport(reportName);
            if (jasperReport == null) {
                JOptionPane.showMessageDialog(null,"File not Found: " + reportName + ".jasper");
            } else {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, database.con);

                int pageSize = jasperPrint.getPages().size();

                if (pageSize > 0) {
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    JDialog jviewer = new JDialog(new JFrame(), true);
                    jviewer.setTitle(reportName + " Print");
                    jviewer.setSize(1024, 768);
                    jviewer.getContentPane().add(viewer.getContentPane());
                    jviewer.setLocationRelativeTo(null);
                    jviewer.setVisible(true);
                } else {
//                    msn.showMessageInformation(alert1);
                }
            }
        } catch (Exception ex) {
//            msn.showMessageError("<html>"+alert2+"(viewReport)<br>" + ex +"</html>");
            ex.getMessage();
        }
    }
        public JasperReport getCompiledReport(String reportName) throws JRException {
        JasperReport jasperReport = null;
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource(rootPath + reportName + ".jasper"));

        } catch (NullPointerException ex) {
            jasperReport = null;
        }
        return jasperReport;
    }
}
