package com.eg.mp.util;

import com.eg.mp.Application;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.SpringApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yujiezhang on 2017/4/18.
 */

public class ReadExcel {
    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     */
    public HashMap read(String str) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        HashMap map = new HashMap();
        String errorMsg = "";

        try {
            //同时支持Excel 2003、2007
            File excelFile = new File(str); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
            String values = "";
            String cellValue = null;
            int rFlag = 1;//设置一个总行数的标记
            //遍历每个Sheet
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
                //遍历每一行
                for (int r = 1; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                    //遍历每一列
                    for (int c = 0; c < cellCount; c++) {
                        Cell cell = row.getCell(c);
                        //批量导入房主时需要检验电话和身份证信息
//                        errorMsg = HouseOwnerCheck(c, cell, errorMsg, s, r);
                        int cellType = cell.getCellType();
                        switch (cellType) {
                            case Cell.CELL_TYPE_STRING: //文本
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC: //数字、日期
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = fmt.format(cell.getDateCellValue()); //日期型
                                } else {
                                    DecimalFormat df = new DecimalFormat("0");//使用DecimalFormat类对科学计数法格式的数字进行格式化
                                    cellValue = String.valueOf(df.format(cell.getNumericCellValue())); //数字
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN: //布尔型
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_BLANK: //空白
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_ERROR: //错误
                                cellValue = "错误";
                                break;
                            case Cell.CELL_TYPE_FORMULA: //公式
                                cellValue = "错误";
                                break;
                            default:
                                cellValue = "错误";
                        }
                        values += cellValue + " ";
                    }
                    map.put(rFlag, values);
                    rFlag++;
                    values = "";
                }
            }
//            map.put(rFlag + 1, errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    /**
     * 批量导入房主信息时调用检验方法
     * 第五列：联系方式
     * 第六列：身份证
     *
     * @param
     * @return
     */
    public String HouseOwnerCheck(int c, Cell cell, String errorMsg, int s, int r) {
        if (c == 5) {
            DecimalFormat df = new DecimalFormat("0");//使用DecimalFormat类对科学计数法格式的数字进行格式化
            String cellValue = String.valueOf(df.format(cell.getNumericCellValue())); //数字
            if (!phoneCheck(cellValue)) {
                errorMsg += "第" + (s + 1) + "个工作簿，第" + (r + 1) + "行，第F列格式错误，内容为：" + cellValue + "。\n";
            }
        } else if (c == 6) {
            String cellValue = cell.getStringCellValue();
            if (!identityCardCheck(cellValue)) {
                errorMsg += "第" + (s + 1) + "个工作簿，第" + (r + 1) + "行，第G列格式错误，内容为：" + cellValue + "。\n";
            }
        }

        return errorMsg;


    }

    /**
     * 验证手机号码
     * <p>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param s 受检验字符串
     * @return
     */
    public Boolean phoneCheck(String s) {
        String regEx = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2-3,5-9]))\\d{8}$"; //检验手机号正则表达式
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        return mat.find();
    }

    public Boolean identityCardCheck(String s) {
        String regEx = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$"; //检验身份证号（15位或18位）正则表达式
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        return mat.find();
    }

//
//    /**
//     * 测试
//     */
//    public void test(String str) {
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//
//        String errorMsg = "";
//
//        try {
//            //同时支持Excel 2003、2007
//            File excelFile = new File(str); //创建文件对象
//            FileInputStream is = new FileInputStream(excelFile); //文件流
//            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
//            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
//            String values = "";
//            String cellValue = null;
//            int rFlag = 1;//设置一个总行数的标记
//            //遍历每个Sheet
//            for (int s = 0; s < 1; s++) {
//                Sheet sheet = workbook.getSheetAt(s);
//                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
//                //遍历每一行
//                for (int r = 1; r < 31; r++) {
//                    Row row = sheet.getRow(r);
//                    Cell addr = row.getCell(5);
////                    DecimalFormat df = new DecimalFormat("0");//使用DecimalFormat类对科学计数法格式的数字进行格式化
//////                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
////                    Cell name = row.getCell(1);
////                    Cell idCard = row.getCell(2);
//////                    String sss = String.valueOf(df.format(idCard.getNumericCellValue()));
////                    Cell phone = row.getCell(4);
////
////                    cellValue = String.valueOf(df.format(phone.getNumericCellValue())); //数字
//
//                    //遍历每一列
////                    for (int c = 0; c < cellCount; c++) {
////                        Cell cell = row.getCell(c);
////                        //批量导入房主时需要检验电话和身份证信息
//////                        errorMsg = HouseOwnerCheck(c, cell, errorMsg, s, r);
////                        int cellType = cell.getCellType();
////                        switch (cellType) {
////                            case Cell.CELL_TYPE_STRING: //文本
////                                cellValue = cell.getStringCellValue();
////                                break;
////                            case Cell.CELL_TYPE_NUMERIC: //数字、日期
////                                if (DateUtil.isCellDateFormatted(cell)) {
////                                    cellValue = fmt.format(cell.getDateCellValue()); //日期型
////                                } else {
////                                    DecimalFormat df = new DecimalFormat("0");//使用DecimalFormat类对科学计数法格式的数字进行格式化
////                                    cellValue = String.valueOf(df.format(cell.getNumericCellValue())); //数字
////                                }
////                                break;
////                            case Cell.CELL_TYPE_BOOLEAN: //布尔型
////                                cellValue = String.valueOf(cell.getBooleanCellValue());
////                                break;
////                            case Cell.CELL_TYPE_BLANK: //空白
////                                cellValue = cell.getStringCellValue();
////                                break;
////                            case Cell.CELL_TYPE_ERROR: //错误
////                                cellValue = "错误";
////                                break;
////                            case Cell.CELL_TYPE_FORMULA: //公式
////                                cellValue = "错误";
////                                break;
////                            default:
////                                cellValue = "错误";
////                        }
////                        values += cellValue + " ";
////                    }
//                    //System.err.println("insert into house_owner (`name`,`credentials_number`,`phone`,`nation`) values('"+name+"','"+idCard+"','"+cellValue+"',1);");
//                    System.err.println(addr);
//                }
//            }
////            map.put(rFlag + 1, errorMsg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//    public static void main(String[] args) {
//
//        ReadExcel readExcel = new ReadExcel();
//        readExcel.test("G:\\玉龙村试点地址人员信息表.xls");
//
//
//
//    }
}