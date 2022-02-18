package com.lcf;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lcf
 * @create 2022-01-19 10:18
 */
public class ExcelWriteTest {
    private final String PATH="E:\\JAR\\";

    @Test
    public void write03() throws IOException {
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("test1");
        //创建行(0:1)第一行
        Row row1 = sheet.createRow(0);
        //创建单元格  第一行第一列
        Cell cell00 = row1.createCell(0);
        cell00.setCellValue("测试数据");
        //第一行第二个单元格
        Cell cell01 = row1.createCell(1);
        cell01.setCellValue("testData");

        //第二行
        Row row2 = sheet.createRow(1);
        //第二行第一个单元格
        Cell cell10 = row2.createCell(0);
        cell10.setCellValue("时间");
        //第二行第二个单元格
        Cell cell11 = row2.createCell(1);
        cell11.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        //生成Excel（IO流）
        //03版本的Excel是.xsl结尾
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "POI03测试.xls");
        workbook.write(fileOutputStream);

        //关闭流
        fileOutputStream.close();
        System.out.println("03Excel输出完毕");
    }

    @Test
    public void write07() throws IOException {
        //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("test1");
        //创建行(0:1)第一行
        Row row1 = sheet.createRow(0);
        //创建单元格  第一行第一列
        Cell cell00 = row1.createCell(0);
        cell00.setCellValue("测试数据");
        //第一行第二个单元格
        Cell cell01 = row1.createCell(1);
        cell01.setCellValue("testData");

        //第二行
        Row row2 = sheet.createRow(1);
        //第二行第一个单元格
        Cell cell10 = row2.createCell(0);
        cell10.setCellValue("时间");
        //第二行第二个单元格
        Cell cell11 = row2.createCell(1);
        cell11.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        //生成Excel（IO流）
        //03版本的Excel是.xsl结尾
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "POI07测试.xlsx");
        workbook.write(fileOutputStream);

        //关闭流
        fileOutputStream.close();
        System.out.println("07Excel输出完毕");
    }

    /**
     * 缺点 最多只能写入65536行 抄书会报异常
     * 优点 过程中写入内存 不操作磁盘 最后一次性写入磁盘 速度快
     * @throws IOException
     */
    @Test
    public void bigData03Write() throws IOException {
        long begin = System.currentTimeMillis();
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int i = 0; i < 65536; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }
        System.out.println("over");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "bigDataWrite.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end-begin)/1000);
    }

    /**
     * 缺点 耗时较长 会占用大量的内存 也可能会内存溢出
     * 优点 可以写入大量数据 例如20万
     * @throws IOException
     */
    @Test
    public void bigData07Write() throws IOException {
        long begin = System.currentTimeMillis();
        //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int i = 0; i < 65539; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }
        System.out.println("over");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "bigDataWrite07.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end-begin)/1000);
    }

    @Test
    public void bigData07WritePro() throws IOException {
        long begin = System.currentTimeMillis();
        //创建工作簿
        Workbook workbook = new SXSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int i = 0; i < 100000; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }
        System.out.println("over");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "bigDataWrite07Pro.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        //清除临时文件
        ((SXSSFWorkbook)workbook).dispose();
        long end = System.currentTimeMillis();
        System.out.println((double) (end-begin)/1000);
    }



}
