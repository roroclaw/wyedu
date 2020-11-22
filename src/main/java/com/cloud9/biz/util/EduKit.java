package com.cloud9.biz.util;

import com.cloud9.biz.models.SysDictItem;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.handler.BizException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by dxz on 2017/5/8.
 * 教育类系统业务工具类
 */
public class EduKit {

    private static List<SysDictItem> STUDY_YEARS = null;
    private static int LAST_CAL_STUDY_YEARS = 0;

    private static Map<String,String> gradeMap ;

    public static Map<String, String> getGradeMap() {
        return gradeMap;
    }

    public static void setGradeMap(Map<String, String> gradeMap) {
        EduKit.gradeMap = gradeMap;
    }

    /**
     * 是否历史学年
     * @param studyYear
     * @return
     */
    public static boolean isHistoryStudyYear(String studyYear){
        boolean bol = false;
        String curStudyYear = getCurStudyYear();
        if(curStudyYear.compareTo(studyYear) > 0){
            bol = true;
        }
        return bol;
    }

    /**
     * 获取当前学年
     * @return
     */
    public static String getCurStudyYear(){
        String curStudyYear = "";
        int starYear = getCurStarYearByCurTime();
        int nextYear = starYear+1;
        curStudyYear = formatStudyYear(starYear,nextYear);
        return curStudyYear;
    }

    /**
     * 根据当前时间获取当前学年开始年份
     */
    public static int getCurStarYearByCurTime(){
        Calendar calendar = Calendar.getInstance();
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        int starYear = calendar.get(Calendar.YEAR);

        try {
            int schoolYearPointMonth = Integer.valueOf(MemoryCache.getSysConfigKey("schoolYear.pointMonth")).intValue();
            String schoolYearPointDay  = MemoryCache.getSysConfigKey("schoolYear.pointDay");

            if(curMonth < schoolYearPointMonth){ //通过配置参数判断学年跨度分割点
                starYear = starYear - 1;
            }else if(curMonth == schoolYearPointMonth){//比较月份
                if(schoolYearPointDay != null){
                    if(curDay < Integer.valueOf(schoolYearPointDay).intValue()){
                        starYear = starYear - 1;
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new BizException("学年分割点配置错误!");
        } catch (Exception e){
            throw new BizException("计算当前学年开始年份发生错误!");
        }
        return starYear;
    }

    /**
     * 获取学年集合
     * @param starYear
     * @return
     */
    public static List<SysDictItem> genrateStudyYears(int starYear){
        List<SysDictItem> items = null;
        //获取当前年份
        int endYear = getCurStarYearByCurTime();

        //判斷内存中是否已计算过
        if(endYear <= LAST_CAL_STUDY_YEARS){
            return STUDY_YEARS;
        }
        if(starYear <= endYear){
            items = new ArrayList<SysDictItem>();
            STUDY_YEARS = items;
            LAST_CAL_STUDY_YEARS = endYear;
        }
        while (starYear <= endYear){
            SysDictItem sysDictItem = new SysDictItem();
            int nextYear = endYear+1;
            String studyYear = formatStudyYear(endYear,nextYear);
            sysDictItem.setCode(studyYear);
            sysDictItem.setText(studyYear);
            endYear = endYear-1;
            items.add(sysDictItem);
        }
        return  items;
    }

    /**
     * 获取学年集合
     * @param starYear
     * @return
     */
    public static List<SysDictItem> genrateStudyYears(int starYear,int endYear){
        List<SysDictItem> items = null;
        if(starYear <= endYear){
            items = new ArrayList<SysDictItem>();
        }
        while (starYear <= endYear){
            SysDictItem sysDictItem = new SysDictItem();
            int nextYear = starYear+1;
            String studyYear = formatStudyYear(starYear,nextYear);
            sysDictItem.setCode(studyYear);
            sysDictItem.setText(studyYear);
            starYear = nextYear;
            items.add(sysDictItem);
        }
        return  items;
    }

    /**
     * 格式化学年
     * @param starYear
     * @param endYear
     * @return
     */
    private static String formatStudyYear(int starYear,int endYear){
        String studyYear = starYear+"-"+endYear;
        return studyYear;
    }

    /**
     * 拼接班级名称
     * @param grade
     * @param classSeq
     * @return
     */
    public static String joinClassName(String grade,String classSeq){
        String className = "";
        className = int2Chinese(grade)+"("+classSeq+")班";
        return className;
    }

    private static String int2Chinese(String numStr){
        return gradeMap.get(numStr);
    }

//    /**
//     * 数字转中文
//     */
//    private static String[]  s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
//    private static String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
//
//    private static String int2Chinese(String numStr){
//        String result = "";
//
//        int n = numStr.length();
//        for (int i = 0; i < n; i++) {
//            int num = numStr.charAt(i) - '0';
//            if (i != n - 1 && num != 0) {
//                result += s1[num] + s2[n - 2 - i];
//            } else {
//                result += s1[num];
//            }
//        }
//        return result;
//    }


    /**
     * 根据年级获取学段
     * @param grade
     * @return
     */
    public static String getPeriodByGrade(String grade){
            String period = null;
                int gNum = Integer.parseInt(grade);
                if(gNum <= 6){
                    period = BizConstants.PERIOD.PRIMARY_SCHOOL;
                }else if(gNum > 6 && gNum <= 12){
                    period = BizConstants.PERIOD.MIDDLE_SCHOOL;
                }
            return period;
    }

    /**
     * 根据年级获取学段
     * @param grade
     * @return
     */
    public static String getPeriodByGrade4Statistics(String grade){
            String period = null;
            int gNum = Integer.parseInt(grade);
            if(gNum <= 6){
                period = BizConstants.PERIOD.PRIMARY_SCHOOL;
            }else if(gNum > 6 && gNum <= 9){
                period = BizConstants.PERIOD.MIDDLE_SCHOOL;
            }else if(gNum > 9 && gNum <= 12){
                period = BizConstants.PERIOD.HIGH_SCHOOL;
            }
            return period;
    }
}
