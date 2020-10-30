package main;

import jdbc.TestJDBC;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Starter {


    public Starter() {
    }

    public static void testJasper() throws JRException {
//        final String REPORT_PATTERN = "src/main/resources/jrxml/detcalls.jrxml";
//        final String REPORT_PATTERN = "src/main/resources/jrxml/test.jrxml";
        final String REPORT_PATTERN = "src/main/resources/jrxml/report1.jrxml";

        File reportPattern = new File(REPORT_PATTERN);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("number1", 1000);
        ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();
        JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        String REPORT_PDF = "D:/report/detcalls.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, REPORT_PDF);

    }

    public static void createPDF() throws JRException, SQLException, ClassNotFoundException, ParseException {
        final String REPORT_PATTERN = "src/main/resources/jrxml/report_f16.jrxml";
        File reportPattern = new File(REPORT_PATTERN);
        JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("DATE1", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        parameters.put("DATE2", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        TestJDBC jdbc = new TestJDBC();
        ArrayList<Integer> arrayCustCode = jdbc.getArrayCustCode();

        for (Integer custCode : arrayCustCode) {
            jdbc.setFirmName(custCode);
            parameters.put("custCode", jdbc.getFirmName());
            parameters.put("numDog", jdbc.getNomDog());
            ArrayList<DataBean> dataBeanList = jdbc.getDataBeanList(custCode);
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            String REPORT_PDF = "D:/report/" + custCode + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, REPORT_PDF);
        }
        jdbc.closeConnection();
    }
//
//    public static void createXLS() throws JRException, SQLException, ClassNotFoundException, ParseException {
//        final String REPORT_PATTERN = "src/main/resources/jrxml/oborotka.jrxml";
//        File reportPattern = new File(REPORT_PATTERN);
//        JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("DATE1", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
//        parameters.put("DATE2", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
//
//
//        ArrayList<DataBean> dataBeanList = new ArrayList<>();
//        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        String REPORT_PDF = "D:/report/oborotka.xlsx";
//
//        JRXlsxExporter exporter = new JRXlsxExporter();
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, REPORT_PDF);
//        exporter.exportReport();
//
//        REPORT_PDF = "D:/report/oborotka.pdf";
//        JasperExportManager.exportReportToPdfFile(jasperPrint, REPORT_PDF);


//        for (Integer custCode : arrayCustCode) {
//            jdbc.setFirmName(custCode);
//            parameters.put("custCode", jdbc.getFirmName());
//            parameters.put("numDog", jdbc.getNomDog());
//            dataBeanList = jdbc.getDataBeanList(custCode);
//
//            JRXlsExporter jrXlsExporter = new JRXlsExporter();
//            jrXlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            jrXlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput("D:/report/oborotka" + custCode + ".xls"));
//            SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
//            xlsReportConfiguration.setOnePagePerSheet(false);
//            xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
//            xlsReportConfiguration.setDetectCellType(false);
//            xlsReportConfiguration.setWhitePageBackground(false);
//            jrXlsExporter.setConfiguration(xlsReportConfiguration);
//
//            jrXlsExporter.exportReport();
//        }

//    }


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        System.out.println("Начало генерации отчёта");
        try {
            new Starter().testJasper();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        WriterOSV writerOSV = new WriterOSV();
//        writerOSV.writeFile();

//        ModelOSVOld model = new ModelOSVOld();


        System.out.println("Генерация отчёта завершена");
    }
}
