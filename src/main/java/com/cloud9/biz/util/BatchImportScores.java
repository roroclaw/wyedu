//package com.cloud9.biz.util;
//
//import com.roroclaw.base.utils.POIExcelUtil;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//
//import java.io.*;
//import java.text.DecimalFormat;
//
///**
// * Created by roroclaw on 2018/1/15.
// */
//public class BatchImportScores {
//
//    public static void importScores(){
//        //读取excel
//        String fileName = "17年期中分数导入表格（初三年级）";
//        String fileUrl = "D:\\期中分数导入系统\\"+fileName+".xlsx";
//        // 构建指定文件
//        File file = new File(fileUrl);
//        InputStream in = null;
//        try {
//            // 根据文件创建文件的输入流
//            in = new FileInputStream(file);
//            Sheet sheet = POIExcelUtil.getExcelSheet(fileName+".xlsx", in);
//
//            int begin = sheet.getFirstRowNum() + 1;
//            int end = sheet.getLastRowNum();
//
//            StringBuffer sb = new StringBuffer("");
//
//            for (int i = begin; i <= end; i++) {
//                Row row = sheet.getRow(i);
//                if (null != row) {
//                    if(isEmptyRow(row)){
//                        continue;
//                    }
//                }
//
//                DecimalFormat df = new DecimalFormat("0");
//                Cell stuNoCell = row.getCell(0);
//                String stuNo = df.format(stuNoCell.getNumericCellValue());
//                Cell courseOpenCell = row.getCell(1);
//                String courseOpenId = POIExcelUtil.getStringCellValue(courseOpenCell);
//                Cell classNameCell = row.getCell(2);
//                String className = POIExcelUtil.getStringCellValue(classNameCell);
//                Cell studyYearCell = row.getCell(3);
//                String studyYear = String.valueOf(Double.valueOf(POIExcelUtil.getStringCellValue(studyYearCell)).intValue());
//                Cell termCell = row.getCell(4);
//                String term = String.valueOf(Double.valueOf(POIExcelUtil.getStringCellValue(termCell)).intValue());
//                Cell scoreCell = row.getCell(5);
//                String score = POIExcelUtil.getStringCellValue(scoreCell);
//                Cell statusCell = row.getCell(6);
//                String status = String.valueOf(Double.valueOf(POIExcelUtil.getStringCellValue(statusCell)).intValue());
//                String id = BizConstants.generatorPid();
//                sb.append("insert into `sco_exam_scores_temp` " +
//                                "(`ID`, `COURSE_OPEN_ID`,`STU_NO`, `STU_ID`, `CLASS_ID`, " +
//                                "`SCORE`, `SCHOOL_YEAR`, `TERM`, `TEACHER_ID`, `CREATE_TIME`, `CREATOR`, `UPDATE_TIME`, `UPDATER`, " +
//                                "`TYPE`, `RECORDE_STATUS`, `STATUS`) " +
//                                "values('"+id+"','"+courseOpenId+"','" +stuNo+"',"+
//                                "(select id from arc_student_info a where a.STU_NUMBER = '"+stuNo+"')," +
//                                "(select id from tch_class_info a where a.name = '"+className+"'),"+score+",'"+studyYear+"'," +
//                                "'"+term+"',NULL," +
//                                "now(),'00',NULL,NULL,'1','1','"+status+"');\r\n");
//            }
//
//            try {
//                File newFile = new File("D:\\期中分数导入系统\\"+fileName+".txt");
//                if (newFile.exists())// 存在，则删除
//                    if (!newFile.delete())// 删除成功则创建
//                    {
//                        System.err.println("删除文件" + newFile + "失败");
//                    }
//                if (newFile.createNewFile()) {// 创建成功，则写入文件内容
//                    PrintWriter p = new PrintWriter(new FileOutputStream(newFile
//                            .getAbsolutePath()));
//                    p.write(sb.toString());
//                    p.close();
//                } else {
//                    System.err.println("创建文件：" + newFile + "失败");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 关闭输入流
//                in.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static boolean isEmptyRow(Row row){
//        boolean bol = false;
//        Cell realNameCell = row.getCell(1);
//        if(realNameCell == null || realNameCell.getCellType() == Cell.CELL_TYPE_BLANK){
//            bol = true;
//        }
//        return bol;
//    }
//
//    public static void main(String[] args) {
//        importScores();
//    }
//
//}
