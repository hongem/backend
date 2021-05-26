package com.nuce.service_gara.service.serviceImpl.ExportAndSendEmail;

import com.nuce.service_gara.model.Employee;
import com.nuce.service_gara.repository.EmployeeRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AutoExportAndSend {

    private XSSFWorkbook workbook;

    @Autowired
    private JavaMailSender emailSender;

    private XSSFSheet sheet;


    public AutoExportAndSend(JavaMailSender emailSender) {
        this.workbook = new XSSFWorkbook();
        this.emailSender = emailSender;
    }

    @Autowired
    private EmployeeRepo employeeRepo;

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void writeHeaderLine() {
        sheet = workbook.createSheet("Employee");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, "Employee Information", style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        font.setFontHeightInPoints((short) (10));
        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Employee Id", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "User Name", style);
        createCell(row, 3, "Gender", style);
        createCell(row, 4, "Position", style);
    }

    public void writeDetailLines() {
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        List<Employee> employeeList = employeeRepo.findAll();
        for (Employee employee : employeeList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, employee.getEmployeeId(), style);
            createCell(row, columnCount++, employee.getName(), style);
            createCell(row, columnCount++, employee.getUsername(), style);
            createCell(row, columnCount++, employee.getGender(), style);
            createCell(row, columnCount++, employee.getPosition(), style);
        }
    }

    public void exportAndSend() throws IOException {
        writeHeaderLine();
        writeDetailLines();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        byte[] excelFileAsBytes = bos.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(excelFileAsBytes);
        String to = "hong90662@nuce.edu.vn";
        String subject = "email subject";
        String text = "email text";

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            helper.addAttachment("document.xls", resource);

        } catch (Exception ex) {
            System.out.println("Error");
        }
        emailSender.send(message);

    }


}
