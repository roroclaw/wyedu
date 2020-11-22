package com.cloud9.biz.util;

/**
 * Created by roroclaw on 2017/8/17.
 */
public class ImportKit {

    /**
     * 判断值是够为null 或者 ""
     * @param val
     * @param valDesc
     * @return
     */
    public static String isValEmpty(String val,String valDesc){
        String errMsg = "";
        if(val == null || "".equals(val)){
            errMsg = valDesc+"不可以为空!";
        }
        return errMsg;
    }
}
