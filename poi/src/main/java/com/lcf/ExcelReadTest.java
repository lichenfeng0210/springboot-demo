package com.lcf;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Date;

/**
 * @author lcf
 * @create 2022-01-19 16:15
 */
public class ExcelReadTest {
    private final String PATH="E:\\JAR\\";

    @Test
    public void testRead07() throws Exception {
        //获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH + "POI07测试.xlsx");
        //获取工作簿
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //得到表
        Sheet sheet = workbook.getSheetAt(0);
        //得到行
        Row row = sheet.getRow(0);
        //得到列
        Cell cell = row.getCell(0);

        //读取值事一定要注意类型
        System.out.println(cell.getStringCellValue());

        fileInputStream.close();
    }


    @Test
    public void testCellType() throws Exception {
        //获取文件
        FileInputStream fileInputStream = new FileInputStream(PATH + "航油1-5月加油量加油架次.xlsx");
        //获取工作簿
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //获取表
        Sheet sheet = workbook.getSheet("Sheet1");

        //获取标题内容
        Row rowTitle = sheet.getRow(0);
        if (rowTitle!=null){
            //获取一行中有多少个单元格
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                //获取单元格
                Cell cell = rowTitle.getCell(cellNum);
                if (cell!=null){
                    //获取类型
                    int cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + " | ");
                }
            }
            System.out.println();
        }

        //获取表中的信息
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 0; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null){
                //获取一行中有多少个单元格
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    System.out.print("["+(rowNum+1)+":"+(cellNum+1)+"]");
                    Cell cell = rowData.getCell(cellNum);
                    if (cell!=null){
                        int cellType = cell.getCellType();
                        String cellValue = "";
                        switch (cellType){
                            case HSSFCell.CELL_TYPE_STRING:
                                System.out.print("[STRING]");
                                cellValue = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                System.out.print("[BOOLEAN]");
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                System.out.print("[NUMERIC]");
                                if (HSSFDateUtil.isCellDateFormatted(cell)){
                                    System.out.print("[DATE]");
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime().toString("yyyy-MM-dd");
                                }else {
                                    //不是日期格式，则防止数字过长时以科学计数法显示
                                    System.out.print("[TO_STRING]");
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cellValue = cell.toString();
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                System.out.print("[BLANK]");
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                System.out.print("[TYPE_ERROR]");
                                break;
                        }
                        System.out.println(cellValue);
                    }
                }
            }
        }
        fileInputStream.close();
    }


}
