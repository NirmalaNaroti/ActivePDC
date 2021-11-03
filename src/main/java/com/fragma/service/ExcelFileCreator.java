package com.fragma.service;

import com.fragma.dto.FTG;
import com.fragma.dto.LRG;
import com.fragma.dto.MainDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Map;

@Service
public class ExcelFileCreator {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelFileCreator.class);

      XSSFWorkbook workbook = new XSSFWorkbook();
    DecimalFormat df = new DecimalFormat("#,###.00");

    public void createAllSheets(String excelFileLocation, MainDto mainDto) throws Exception {

        createFTGSheet(mainDto, "FTG");
        createLRGSheet(mainDto, "LRG");


        FileOutputStream out = new FileOutputStream(excelFileLocation);
        this.workbook.write(out);
        out.close();
        LOG.info(" Excel file written successfully on disk at :" + excelFileLocation);
    }

    private void createFTGSheet(MainDto mainDto, String sheetName) throws Exception {

        LOG.info("***** executing createFTGSheet ****** ");

        Font headingFont = workbook.createFont();
        headingFont.setBold(true);

        XSSFColor orange = new XSSFColor(new java.awt.Color(182, 207, 242));

        XSSFCellStyle headingCellStyle = workbook.createCellStyle();

        headingCellStyle.setFont(headingFont);
        headingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headingCellStyle.setFillForegroundColor(orange);
        headingCellStyle.setBorderBottom(BorderStyle.THIN);
        headingCellStyle.setBorderLeft(BorderStyle.THIN);
        headingCellStyle.setBorderRight(BorderStyle.THIN);
        headingCellStyle.setBorderTop(BorderStyle.THIN);
        headingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headingCellStyle.setWrapText(true);

        XSSFColor lightOrange = new XSSFColor(new java.awt.Color(255, 216, 151));

        XSSFCellStyle MainHeadingCellStyle = workbook.createCellStyle();

        MainHeadingCellStyle.setFont(headingFont);
        MainHeadingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        MainHeadingCellStyle.setFillForegroundColor(lightOrange);
        MainHeadingCellStyle.setBorderBottom(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderLeft(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderRight(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderTop(BorderStyle.THIN);
        MainHeadingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        MainHeadingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        MainHeadingCellStyle.setWrapText(true);

        CellStyle bordersOnly = workbook.createCellStyle();
        bordersOnly.setBorderBottom(BorderStyle.THIN);
        bordersOnly.setBorderLeft(BorderStyle.THIN);
        bordersOnly.setBorderRight(BorderStyle.THIN);
        bordersOnly.setBorderTop(BorderStyle.THIN);
        bordersOnly.setAlignment(HorizontalAlignment.CENTER);
        bordersOnly.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle bordersOnlyRed = workbook.createCellStyle();
        bordersOnlyRed.setBorderBottom(BorderStyle.THIN);
        bordersOnlyRed.setBorderLeft(BorderStyle.THIN);
        bordersOnlyRed.setBorderRight(BorderStyle.THIN);
        bordersOnlyRed.setBorderTop(BorderStyle.THIN);
        bordersOnlyRed.setAlignment(HorizontalAlignment.CENTER);
        bordersOnlyRed.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        font.setFontHeightInPoints((short)10);
        font.setColor(IndexedColors.RED.getIndex());

        bordersOnlyRed.setFont(font);

        CellStyle bordersOnlyGreen = workbook.createCellStyle();
        bordersOnlyGreen.setBorderBottom(BorderStyle.THIN);
        bordersOnlyGreen.setBorderLeft(BorderStyle.THIN);
        bordersOnlyGreen.setBorderRight(BorderStyle.THIN);
        bordersOnlyGreen.setBorderTop(BorderStyle.THIN);
        bordersOnlyGreen.setAlignment(HorizontalAlignment.CENTER);
        bordersOnlyGreen.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont greenFont = workbook.createFont();
        greenFont.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        greenFont.setFontHeightInPoints((short)10);
        greenFont.setColor(IndexedColors.GREEN.getIndex());

        bordersOnlyGreen.setFont(greenFont);



        Sheet sheet = workbook.createSheet(sheetName);

        Row headingRow1 = sheet.createRow(0);
        headingRow1.setHeight((short) 900);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,16));

        createCellAddData(headingRow1, 0, "Active PDCs status report for FTG Portfolioi account Dated: "+mainDto.getTodayDate(), MainHeadingCellStyle);

        int rowNum=1;

        Row headingRow = sheet.createRow(rowNum++);
        headingRow.setHeight((short) 900);

        int headingColmIndx = 0;

        createCellAddData(headingRow, headingColmIndx++, "SL No", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Currency", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Class", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Title", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Trn Ref No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Product Code", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Branch Code ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Remitter Account No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Beneficiary Account No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Instrument No", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "CCY", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Amount", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Value Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Activation Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Status", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, " Dated", headingCellStyle);

        for (Map.Entry<Integer, FTG> tdEntry : mainDto.getFtgMap().entrySet()) {

            Row row = sheet.createRow(rowNum++);
            int cell = 0;

            createCellAddData(row, cell++, String.valueOf(tdEntry.getKey()), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountNumber(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountCurrency(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountClass(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountTitle(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getTrnRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getProductCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBranchCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getRemitterAccountNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBeneficiaryAccountNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getInstrumentNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCcy(), bordersOnly);

            if(tdEntry.getValue().getAmount()== 0)
            {
                createCellAddData(row, cell++,"0.00", bordersOnly);
            }
            else {
                createCellAddData(row, cell++, df.format(tdEntry.getValue().getAmount()), bordersOnly);
            }
            createCellAddData(row, cell++, tdEntry.getValue().getValueDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getActivationDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getStatus(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getDated(), bordersOnly);

        }

        for (int i = 0; i <= sheet.getRow(1).getLastCellNum(); i++) {

            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000);
        }
    }

    private void createLRGSheet(MainDto mainDto, String sheetName) throws Exception {

        LOG.info("***** executing createLRGSheet ****** ");

        Font headingFont = workbook.createFont();
        headingFont.setBold(true);

        XSSFColor orange = new XSSFColor(new java.awt.Color(182, 207, 242));

        XSSFCellStyle headingCellStyle = workbook.createCellStyle();

        headingCellStyle.setFont(headingFont);
        headingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headingCellStyle.setFillForegroundColor(orange);
        headingCellStyle.setBorderBottom(BorderStyle.THIN);
        headingCellStyle.setBorderLeft(BorderStyle.THIN);
        headingCellStyle.setBorderRight(BorderStyle.THIN);
        headingCellStyle.setBorderTop(BorderStyle.THIN);
        headingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headingCellStyle.setWrapText(true);

        XSSFColor lightOrange = new XSSFColor(new java.awt.Color(255, 216, 151));

        XSSFCellStyle MainHeadingCellStyle = workbook.createCellStyle();

        MainHeadingCellStyle.setFont(headingFont);
        MainHeadingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        MainHeadingCellStyle.setFillForegroundColor(lightOrange);
        MainHeadingCellStyle.setBorderBottom(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderLeft(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderRight(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderTop(BorderStyle.THIN);
        MainHeadingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        MainHeadingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        MainHeadingCellStyle.setWrapText(true);

        CellStyle bordersOnly = workbook.createCellStyle();
        bordersOnly.setBorderBottom(BorderStyle.THIN);
        bordersOnly.setBorderLeft(BorderStyle.THIN);
        bordersOnly.setBorderRight(BorderStyle.THIN);
        bordersOnly.setBorderTop(BorderStyle.THIN);
        bordersOnly.setAlignment(HorizontalAlignment.CENTER);
        bordersOnly.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle bordersOnlyRed = workbook.createCellStyle();
        bordersOnlyRed.setBorderBottom(BorderStyle.THIN);
        bordersOnlyRed.setBorderLeft(BorderStyle.THIN);
        bordersOnlyRed.setBorderRight(BorderStyle.THIN);
        bordersOnlyRed.setBorderTop(BorderStyle.THIN);
        bordersOnlyRed.setAlignment(HorizontalAlignment.CENTER);
        bordersOnlyRed.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        font.setFontHeightInPoints((short)10);
        font.setColor(IndexedColors.RED.getIndex());

        bordersOnlyRed.setFont(font);

        CellStyle bordersOnlyGreen = workbook.createCellStyle();
        bordersOnlyGreen.setBorderBottom(BorderStyle.THIN);
        bordersOnlyGreen.setBorderLeft(BorderStyle.THIN);
        bordersOnlyGreen.setBorderRight(BorderStyle.THIN);
        bordersOnlyGreen.setBorderTop(BorderStyle.THIN);
        bordersOnlyGreen.setAlignment(HorizontalAlignment.CENTER);
        bordersOnlyGreen.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont greenFont = workbook.createFont();
        greenFont.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        greenFont.setFontHeightInPoints((short)10);
        greenFont.setColor(IndexedColors.GREEN.getIndex());

        bordersOnlyGreen.setFont(greenFont);



        Sheet sheet = workbook.createSheet(sheetName);

        Row headingRow1 = sheet.createRow(0);
        headingRow1.setHeight((short) 900);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,16));

        createCellAddData(headingRow1, 0, "Active PDCs status report for LRF Portfolioi account Dated: "+mainDto.getTodayDate(), MainHeadingCellStyle);

        int rowNum=1;

        Row headingRow = sheet.createRow(rowNum++);
        headingRow.setHeight((short) 900);

        int headingColmIndx = 0;

        createCellAddData(headingRow, headingColmIndx++, "SL No", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Currency", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Class", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Title", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Trn Ref No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Product Code", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Branch Code ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Remitter Account No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Beneficiary Account No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Instrument No", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "CCY", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Amount", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Value Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Activation Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Status", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, " Dated", headingCellStyle);

        for (Map.Entry<Integer, LRG> tdEntry : mainDto.getLrgMap().entrySet()) {

            Row row = sheet.createRow(rowNum++);
            int cell = 0;

            createCellAddData(row, cell++, String.valueOf(tdEntry.getKey()), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountNumber(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountCurrency(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountClass(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountTitle(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getTrnRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getProductCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBranchCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getRemitterAccountNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBeneficiaryAccountNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getInstrumentNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCcy(), bordersOnly);

            if(tdEntry.getValue().getAmount()== 0)
            {
                createCellAddData(row, cell++,"0.00", bordersOnly);
            }
            else {
                createCellAddData(row, cell++, df.format(tdEntry.getValue().getAmount()), bordersOnly);
            }
            createCellAddData(row, cell++, tdEntry.getValue().getValueDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getActivationDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getStatus(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getDated(), bordersOnly);

        }

        for (int i = 0; i <= sheet.getRow(1).getLastCellNum(); i++) {

            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000);
        }
    }


    public void createCellAddData(Row row, int cellNo, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(cellNo);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cellStyle);
    }

}