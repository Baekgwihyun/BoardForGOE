package com.example.demo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.demo.entity.Board;
import com.example.demo.service.ExcelFileService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ExcelFileServiceImpl extends ExcelFileService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ByteArrayInputStream export(List<Board> contacts) {
        try(Workbook workbook = new XSSFWorkbook())
        {
            Sheet sheet=workbook.createSheet("Contacts");

            Row row=sheet.createRow(0);

            //Define Header Style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //creating header cell
            Cell cell = row.createCell(0);
            cell.setCellValue("제목");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("사용자");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("소속학교");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("문의내용");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("연락처");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows for each contact
            for(int i = 0; i < contacts.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(contacts.get(i).getGoeTitle());
                dataRow.createCell(1).setCellValue(contacts.get(i).getGoeUserName());
                dataRow.createCell(2).setCellValue(contacts.get(i).getGoeSchoolName());
                dataRow.createCell(3).setCellValue(contacts.get(i).getGoeHelpContent());
                dataRow.createCell(4).setCellValue(contacts.get(i).getGoeUserPhone());
            }
            logger.debug("contacts{}"+contacts.toString());

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        }
        catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }

    }
}
