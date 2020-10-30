package main;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class WriterOSV {
    ModelOSV modelOSV;
    ArrayList<Integer> codeAdmList;

    public WriterOSV() throws SQLException, ClassNotFoundException {
        this.modelOSV = new ModelOSV();
        codeAdmList = modelOSV.getCodeAdmList();
        modelOSV.setOsvData(codeAdmList);
    }

    public void writeFile() throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Ведомость");
        sheet.getPrintSetup().setLandscape(true); //ориентация страницы
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.setFitToPage(true);
        sheet.getPrintSetup().setFitWidth((short) 1);
        sheet.getPrintSetup().setFitHeight((short) 1);
        sheet.setMargin(HSSFSheet.LeftMargin, 0);        //установка размера левого поля
        sheet.setMargin(HSSFSheet.RightMargin, 0);        //установка размера правого поля


        XSSFCellStyle header_style1 = getHeaderStyle1(book);
        XSSFCellStyle header_style2 = getHeaderStyle2(book);
        XSSFCellStyle header_style3 = getHeaderStyle3(book);
        XSSFCellStyle table_style = getHeaderStyle4(book);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 22));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 19));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 19));

        sheet.addMergedRegion(new CellRangeAddress(9, 12, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(9, 12, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(9, 11, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 4, 15));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 16, 17));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 18, 20));
        sheet.addMergedRegion(new CellRangeAddress(9, 11, 21, 23));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 4, 9));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 10, 15));

        sheet.addMergedRegion(new CellRangeAddress(11, 11, 4, 7));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 8, 9));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 10, 13));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 14, 15));
        sheet.addMergedRegion(new CellRangeAddress(10, 12, 16, 16));
        sheet.addMergedRegion(new CellRangeAddress(10, 12, 17, 17));
        sheet.addMergedRegion(new CellRangeAddress(10, 12, 18, 18));
        sheet.addMergedRegion(new CellRangeAddress(10, 12, 19, 19));
        sheet.addMergedRegion(new CellRangeAddress(10, 12, 20, 20));
        sheet.addMergedRegion(new CellRangeAddress(13, 13, 21, 23));

        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 1000);

        XSSFCell cell = row.createCell(20);
        cell.setCellValue("Приложение к приказу\nгенерального директора\nРУП Белтелеком\n09.2020 №");
        cell.setCellStyle(header_style1);

        row = sheet.createRow(1);
        cell = row.createCell(1);
        cell.setCellValue("Оборотно-сальдовая ведомость по __________________филиалу РУП \"Белтелеком\"");
        cell.setCellStyle(header_style2);

        row = sheet.createRow(2);
        cell = row.createCell(1);
        cell.setCellValue("Профиль запуска: _________________ филиал все;");
        cell.setCellStyle(header_style3);

        row = sheet.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue("Внешний вид: Агрегированный;");
        cell.setCellStyle(header_style3);

        row = sheet.createRow(4);
        cell = row.createCell(1);
        cell.setCellValue("За период с __-__-20__ 00:00:00 по __-__-20__ 23:59:59;");
        cell.setCellStyle(header_style3);

        row = sheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("Финансовый регион: ________________________;");
        cell.setCellStyle(header_style3);

        row = sheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("Биллинговая группа: Все;");
        cell.setCellStyle(header_style3);

        row = sheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("Метод взаиморасчетов: Авансовый;");
        cell.setCellStyle(header_style3);

        row = sheet.createRow(8);
        cell = row.createCell(1);
        cell.setCellValue("Тип клиента: Все.");
        cell.setCellStyle(header_style3);

        /////////////////////////////////////////////////////////////////

        row = sheet.createRow(9);
        cell = row.createCell(0);
        cell.setCellValue("№ п/п");
        cell.setCellStyle(table_style);

        cell = row.createCell(1);
        cell.setCellValue("Финансовый регион (биллинговая группа): ");
        cell.setCellStyle(table_style);

        cell = row.createCell(2);
        cell.setCellValue("Входящий остаток");
        cell.setCellStyle(table_style);

        cell = row.createCell(3);
        cell.setCellStyle(table_style);

        cell = row.createCell(4);
        cell.setCellValue("Начисления");
        cell.setCellStyle(table_style);
        cell = row.createCell(5);
        cell.setCellStyle(table_style);
        cell = row.createCell(6);
        cell.setCellStyle(table_style);
        cell = row.createCell(7);
        cell.setCellStyle(table_style);
        cell = row.createCell(8);
        cell.setCellStyle(table_style);
        cell = row.createCell(9);
        cell.setCellStyle(table_style);
        cell = row.createCell(10);
        cell.setCellStyle(table_style);
        cell = row.createCell(11);
        cell.setCellStyle(table_style);
        cell = row.createCell(12);
        cell.setCellStyle(table_style);
        cell = row.createCell(13);
        cell.setCellStyle(table_style);
        cell = row.createCell(14);
        cell.setCellStyle(table_style);
        cell = row.createCell(15);
        cell.setCellStyle(table_style);

        cell = row.createCell(16);
        cell.setCellValue("Платежи");
        cell.setCellStyle(table_style);
        cell = row.createCell(17);
        cell.setCellStyle(table_style);

        cell = row.createCell(18);
        cell.setCellValue("Нереализационные корректировки");
        cell.setCellStyle(table_style);
        cell = row.createCell(19);
        cell.setCellStyle(table_style);
        cell = row.createCell(20);
        cell.setCellStyle(table_style);

        cell = row.createCell(21);
        cell.setCellValue("Исходящий остаток");
        cell.setCellStyle(table_style);
        cell = row.createCell(22);
        cell.setCellStyle(table_style);
        cell = row.createCell(23);
        cell.setCellStyle(table_style);


        row = sheet.createRow(10);
        cell = row.createCell(0);
        cell.setCellStyle(table_style);

        cell = row.createCell(1);
        cell.setCellStyle(table_style);

        cell = row.createCell(2);
        cell.setCellStyle(table_style);

        cell = row.createCell(3);
        cell.setCellStyle(table_style);

        cell = row.createCell(4);
        cell.setCellValue("Начисления с учетом сторонних услуг");
        cell.setCellStyle(table_style);
        cell = row.createCell(5);
        cell.setCellStyle(table_style);
        cell = row.createCell(6);
        cell.setCellStyle(table_style);
        cell = row.createCell(7);
        cell.setCellStyle(table_style);
        cell = row.createCell(8);
        cell.setCellStyle(table_style);
        cell = row.createCell(9);
        cell.setCellStyle(table_style);

        cell = row.createCell(10);
        cell.setCellValue("Реализационные корректировки ");
        cell.setCellStyle(table_style);
        cell = row.createCell(11);
        cell.setCellStyle(table_style);
        cell = row.createCell(12);
        cell.setCellStyle(table_style);
        cell = row.createCell(12);
        cell.setCellStyle(table_style);
        cell = row.createCell(14);
        cell.setCellStyle(table_style);
        cell = row.createCell(15);
        cell.setCellStyle(table_style);

        cell = row.createCell(16);
        cell.setCellValue("Всего платежей");
        cell.setCellStyle(table_style);
        cell = row.createCell(17);
        cell.setCellValue("в т.ч. платежи по сторонним услугам");
        cell.setCellStyle(table_style);
        cell = row.createCell(18);
        cell.setCellValue("дебетовые");
        cell.setCellStyle(table_style);
        cell = row.createCell(19);
        cell.setCellValue("кредитовые");
        cell.setCellStyle(table_style);
        cell = row.createCell(20);
        cell.setCellValue("по сторонним услугам            ( + / - )");
        cell.setCellStyle(table_style);

        cell = row.createCell(21);
        cell.setCellStyle(table_style);
        cell = row.createCell(22);
        cell.setCellStyle(table_style);
        cell = row.createCell(23);
        cell.setCellStyle(table_style);


        row = sheet.createRow(11);
        cell = row.createCell(0);
        cell.setCellStyle(table_style);

        cell = row.createCell(1);
        cell.setCellStyle(table_style);

        cell = row.createCell(2);
        cell.setCellStyle(table_style);

        cell = row.createCell(3);
        cell.setCellStyle(table_style);

        cell = row.createCell(4);
        cell.setCellValue("начислено услуг");
        cell.setCellStyle(table_style);
        cell = row.createCell(5);
        cell.setCellStyle(table_style);
        cell = row.createCell(6);
        cell.setCellStyle(table_style);
        cell = row.createCell(7);
        cell.setCellStyle(table_style);

        cell = row.createCell(8);
        cell.setCellValue("прочее");
        cell.setCellStyle(table_style);
        cell = row.createCell(9);
        cell.setCellStyle(table_style);

        cell = row.createCell(10);
        cell.setCellValue("по начислению услуг");
        cell.setCellStyle(table_style);
        cell = row.createCell(11);
        cell.setCellStyle(table_style);
        cell = row.createCell(12);
        cell.setCellStyle(table_style);
        cell = row.createCell(13);
        cell.setCellStyle(table_style);

        cell = row.createCell(14);
        cell.setCellValue("прочее");
        cell.setCellStyle(table_style);
        cell = row.createCell(15);
        cell.setCellStyle(table_style);

        cell = row.createCell(16);
        cell.setCellStyle(table_style);
        cell = row.createCell(17);
        cell.setCellStyle(table_style);
        cell = row.createCell(18);
        cell.setCellStyle(table_style);
        cell = row.createCell(19);
        cell.setCellStyle(table_style);
        cell = row.createCell(20);
        cell.setCellStyle(table_style);
        cell = row.createCell(21);
        cell.setCellStyle(table_style);
        cell = row.createCell(22);
        cell.setCellStyle(table_style);
        cell = row.createCell(23);
        cell.setCellStyle(table_style);


        row = sheet.createRow(12);
        row.setHeight((short) 1000);
        cell = row.createCell(0);
        cell.setCellStyle(table_style);

        cell = row.createCell(1);
        cell.setCellStyle(table_style);

        cell = row.createCell(2);
        cell.setCellValue("дебет");
        cell.setCellStyle(table_style);

        cell = row.createCell(3);
        cell.setCellValue("кредит");
        cell.setCellStyle(table_style);

        cell = row.createCell(4);
        cell.setCellValue("всего с НДС по ставке 25%");
        cell.setCellStyle(table_style);

        cell = row.createCell(5);
        cell.setCellValue("всего с НДС по ставке 20%");
        cell.setCellStyle(table_style);


        cell = row.createCell(6);
        cell.setCellValue("не облагаемые НДС");
        cell.setCellStyle(table_style);


        cell = row.createCell(7);
        cell.setCellValue("всего начислено без сторонних услуг");
        cell.setCellStyle(table_style);


        cell = row.createCell(8);
        cell.setCellValue("возмещение стоимости имущества (без НДС)");
        cell.setCellStyle(table_style);


        cell = row.createCell(9);
        cell.setCellValue("по сторонним услугам");
        cell.setCellStyle(table_style);


        cell = row.createCell(10);
        cell.setCellValue("всего с НДС по ставке 25%");
        cell.setCellStyle(table_style);


        cell = row.createCell(11);
        cell.setCellValue("всего с НДС по ставке 20%");
        cell.setCellStyle(table_style);

        cell = row.createCell(12);
        cell.setCellValue("не облагаемые НДС");
        cell.setCellStyle(table_style);

        cell = row.createCell(13);
        cell.setCellValue("всего реализацион-ных корректировок без сторонних услуг");
        cell.setCellStyle(table_style);

        cell = row.createCell(14);
        cell.setCellValue("возмещение стоимости имущества (без НДС)");
        cell.setCellStyle(table_style);

        cell = row.createCell(15);
        cell.setCellValue("по сторонним услугам ");
        cell.setCellStyle(table_style);

        cell = row.createCell(16);
        cell.setCellStyle(table_style);
        cell = row.createCell(17);
        cell.setCellStyle(table_style);
        cell = row.createCell(18);
        cell.setCellStyle(table_style);
        cell = row.createCell(19);
        cell.setCellStyle(table_style);
        cell = row.createCell(20);
        cell.setCellStyle(table_style);

        cell = row.createCell(21);
        cell.setCellValue("дебет");
        cell.setCellStyle(table_style);

        cell = row.createCell(22);
        cell.setCellValue("кредит");
        cell.setCellStyle(table_style);

        cell = row.createCell(23);
        cell.setCellValue("свернутое сальдо");
        cell.setCellStyle(table_style);


        row = sheet.createRow(13);
        row.setHeight((short) 1000);
        cell = row.createCell(0);
        cell.setCellStyle(table_style);
        cell = row.createCell(1);
        cell.setCellStyle(table_style);
        cell = row.createCell(2);
        cell.setCellStyle(table_style);
        cell = row.createCell(3);
        cell.setCellStyle(table_style);
        cell = row.createCell(4);
        cell.setCellStyle(table_style);
        cell = row.createCell(5);
        cell.setCellStyle(table_style);
        cell = row.createCell(6);
        cell.setCellStyle(table_style);
        cell = row.createCell(7);
        cell.setCellValue("(гр.5+гр.6+гр.7)");
        cell.setCellStyle(table_style);
        cell = row.createCell(8);
        cell.setCellStyle(table_style);
        cell = row.createCell(9);
        cell.setCellStyle(table_style);
        cell = row.createCell(10);
        cell.setCellStyle(table_style);
        cell = row.createCell(11);
        cell.setCellStyle(table_style);
        cell = row.createCell(12);
        cell.setCellStyle(table_style);
        cell = row.createCell(13);
        cell.setCellValue("(гр.11+гр.12+гр.13)");
        cell.setCellStyle(table_style);
        cell = row.createCell(14);
        cell.setCellStyle(table_style);
        cell = row.createCell(15);
        cell.setCellStyle(table_style);
        cell = row.createCell(16);
        cell.setCellStyle(table_style);
        cell = row.createCell(17);
        cell.setCellStyle(table_style);
        cell = row.createCell(18);
        cell.setCellStyle(table_style);
        cell = row.createCell(19);
        cell.setCellStyle(table_style);
        cell = row.createCell(20);
        cell.setCellStyle(table_style);
        cell = row.createCell(21);
        cell.setCellValue("(гр.24=гр.4-гр.3-гр.8-гр.9-гр.10-гр.14-гр.15-гр.16+гр.17-гр.19+гр.20+гр.21)");
        cell.setCellStyle(table_style);
        cell = row.createCell(22);
        cell.setCellStyle(table_style);
        cell = row.createCell(23);
        cell.setCellStyle(table_style);

        row = sheet.createRow(14);
        for (int i = 0; i < 24; i++) {
            cell = row.createCell(i);
            cell.setCellValue(i + 1);
            cell.setCellStyle(table_style);

        }

        int num = 1;
//        for (int i = 15; i<15 + codeAdmList.size(); i++){
        for (int i = 15; i < 15 + modelOSV.getOsvDataList().size(); i++) {
            ArrayList<OSVData> data = modelOSV.getOsvDataList();
            for (OSVData osvData : data) {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(num);
                cell.setCellStyle(table_style);

                cell = row.createCell(1);
                cell.setCellValue(osvData.russ);
                cell.setCellStyle(table_style);

                cell = row.createCell(2);
                cell.setCellValue(osvData.debet);
                cell.setCellStyle(table_style);

                cell = row.createCell(3);
                cell.setCellValue(osvData.kredet);
                cell.setCellStyle(table_style);

                cell = row.createCell(4);
                cell.setCellValue(osvData.nds_25_n);
                cell.setCellStyle(table_style);

                cell = row.createCell(5);
                cell.setCellValue(osvData.nds_20_n);
                cell.setCellStyle(table_style);

                cell = row.createCell(6);
                cell.setCellValue(osvData.nds_0_n);
                cell.setCellStyle(table_style);

                cell = row.createCell(7);
                cell.setCellValue(osvData.vsego_nach);
                cell.setCellStyle(table_style);

                cell = row.createCell(8);
                cell.setCellValue(osvData.odorud);
                cell.setCellStyle(table_style);

                cell = row.createCell(9);
                cell.setCellValue(osvData.storon_usl);
                cell.setCellStyle(table_style);

                cell = row.createCell(10);
                cell.setCellValue(osvData.nds_25_rk);
                cell.setCellStyle(table_style);

                cell = row.createCell(11);
                cell.setCellValue(osvData.nds_20_rk);
                cell.setCellStyle(table_style);

                cell = row.createCell(12);
                cell.setCellValue(osvData.nds_0_rk);
                cell.setCellStyle(table_style);

                cell = row.createCell(13);
                cell.setCellValue(osvData.real_korr);
                cell.setCellStyle(table_style);

                cell = row.createCell(14);
                cell.setCellValue(osvData.real_korr_obor);
                cell.setCellStyle(table_style);

                cell = row.createCell(15);
                cell.setCellValue(osvData.storon_usl_rk);
                cell.setCellStyle(table_style);

                cell = row.createCell(16);
                cell.setCellValue(osvData.vsego_pay);
                cell.setCellStyle(table_style);

                cell = row.createCell(17);
                cell.setCellValue(osvData.vsego_pay_stor);
                cell.setCellStyle(table_style);

                cell = row.createCell(18);
                cell.setCellValue(osvData.debet_nk);
                cell.setCellStyle(table_style);

                cell = row.createCell(19);
                cell.setCellValue(osvData.kredit_nk);
                cell.setCellStyle(table_style);

                cell = row.createCell(20);
                cell.setCellValue(osvData.storon);
                cell.setCellStyle(table_style);

                cell = row.createCell(21);
                cell.setCellValue(osvData.debet_end);
                cell.setCellStyle(table_style);

                cell = row.createCell(22);
                cell.setCellValue(osvData.kredit_end);
                cell.setCellStyle(table_style);

                cell = row.createCell(23);
                cell.setCellValue(osvData.s_end);
                cell.setCellStyle(table_style);

//            cell = row.createCell(2);
//            cell.setCellValue(modelOSV.osvData.saldoBegC10r);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(3);
//            cell.setCellValue(modelOSV.osvData.saldoBegC10);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(8);
//            cell.setCellValue(modelOSV.osvData.vozmOborud);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(9);
//            cell.setCellValue(modelOSV.osvData.storUsl);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(14);
//            cell.setCellValue(modelOSV.osvData.realizProcheeVozm);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(15);
//            cell.setCellValue(modelOSV.osvData.realizProcheeStor);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(16);
//            cell.setCellValue(modelOSV.osvData.platAll);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(17);
//            cell.setCellValue(modelOSV.osvData.platStor);
//            cell.setCellStyle(table_style);
//
////            cell = row.createCell(20);
////            cell.setCellValue(modelOSV.osvData.storUslKorr);
////            cell.setCellStyle(table_style);
//
//            cell = row.createCell(21);
//            cell.setCellValue(modelOSV.osvData.saldoEndC10r);
//            cell.setCellStyle(table_style);
//
//            cell = row.createCell(22);
//            cell.setCellValue(modelOSV.osvData.saldoEndC10);
//            cell.setCellStyle(table_style);


                num++;
            }
        }
        String formula;
        row = sheet.createRow(15 + codeAdmList.size());
        row.setHeight((short) 1000);
        cell = row.createCell(0);
        cell.setCellStyle(table_style);
        cell = row.createCell(1);
        cell.setCellValue("Итого:");
        cell.setCellStyle(table_style);
        cell = row.createCell(2);
        formula = "SUM(C16:C" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(3);
        formula = "SUM(D16:D" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(4);
        formula = "SUM(E16:E" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(5);
        formula = "SUM(F16:F" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(6);
        formula = "SUM(G16:G" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(7);
        formula = "SUM(H16:H" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(8);
        formula = "SUM(I16:I" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(9);
        formula = "SUM(J16:J" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(10);
        formula = "SUM(K16:K" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(11);
        formula = "SUM(L16:L" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(12);
        formula = "SUM(M16:M" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(13);
        formula = "SUM(N16:N" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(14);
        formula = "SUM(O16:O" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(15);
        formula = "SUM(P16:P" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(16);
        formula = "SUM(Q16:Q" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(17);
        formula = "SUM(R16:R" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(18);
        formula = "SUM(S16:S" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(19);
        formula = "SUM(T16:T" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(20);
        formula = "SUM(U16:U" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(21);
        formula = "SUM(V16:V" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(22);
        formula = "SUM(W16:W" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);
        cell = row.createCell(23);
        formula = "SUM(X16:X" + (15 + codeAdmList.size()) + ")";
        cell.setCellFormula(formula);
        cell.setCellStyle(table_style);


        sheet.setColumnWidth(0, 1450);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 3000);
        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(9, 3000);
        sheet.setColumnWidth(10, 3000);
        sheet.setColumnWidth(11, 3000);
        sheet.setColumnWidth(12, 3000);
        sheet.setColumnWidth(13, 3000);
        sheet.setColumnWidth(14, 4500);
        sheet.setColumnWidth(15, 3000);
        sheet.setColumnWidth(16, 3000);
        sheet.setColumnWidth(17, 3000);
        sheet.setColumnWidth(18, 3000);
        sheet.setColumnWidth(19, 3000);
        sheet.setColumnWidth(20, 3000);
        sheet.setColumnWidth(21, 3000);
        sheet.setColumnWidth(22, 3000);
        sheet.setColumnWidth(23, 3000);
        sheet.setColumnWidth(24, 3000);


        book.write(new FileOutputStream("D:/report/oborotka.xlsx"));
        book.close();

    }


    private static XSSFCellStyle getHeaderStyle1(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
//        font.setFontName("Calibri");
        font.setFontName("Times New Roman");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setWrapText(true);
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//        style.setBorderTop(BorderStyle.THIN);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle getHeaderStyle2(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle getHeaderStyle3(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setItalic(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle getHeaderStyle4(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setWrapText(true);
        return style;
    }

}

