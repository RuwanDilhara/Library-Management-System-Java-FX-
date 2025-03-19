package controller.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportBookComponentController implements Initializable {

    @FXML
    private ComboBox<String> cmbReportType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxLoader();
    }

    private void comboBoxLoader(){
        ObservableList<String> reportTypesList = FXCollections.observableArrayList();
        reportTypesList.add("All Available Books");
        reportTypesList.add("All Borrowed Books");
        reportTypesList.add("All OverDue Books");
        cmbReportType.setItems(reportTypesList);
    }
    private void reportLoader(String reportTemplateName,String pdfName){
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/"+reportTemplateName);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DriverManager.getConnection("jdbc:mysql://localhost:3306/lmsdb", "root", "12345"));

            JasperExportManager.exportReportToPdfFile(jasperPrint,pdfName);

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {
        if (cmbReportType.getValue().equals("All Available Books")){
            reportLoader("Available Book Report.jrxml","Available Books.pdf");
        }else if (cmbReportType.getValue().equals("All Borrowed Books")){
            reportLoader("All Borrowed book.jrxml","Borrowed Books.pdf");
        }else {
            reportLoader("All OverDue Books.jrxml","OverDue Books.pdf");
        }

    }




}