package controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.sql.*;

public class XLSXCreator {
//    public static void main(String[] args) {
//        new XLSXCreator().export();
//    }

    public void export() {
        String jdbcURL = "jdbc:mysql://localhost:3306/StudentsDB";
        String username = "root";
        String password = "123123";

        String excelFilePath = "Students-export.xlsx";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM students";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("StudentsDB");

            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            statement.close();

        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("First Name");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Last Name");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Age");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Birth day");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Faculty");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            int age = result.getInt("age");
            Date birthdayDate = result.getDate("birthday_date");
            String faculty = result.getString("faculty");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(firstName);

            cell = row.createCell(columnCount++);
            cell.setCellValue(lastName);

            cell = row.createCell(columnCount++);

            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);

            cell.setCellValue(birthdayDate);

            cell = row.createCell(columnCount++);
            cell.setCellValue(age);

            cell = row.createCell(columnCount);
            cell.setCellValue(faculty);

        }
    }
}
