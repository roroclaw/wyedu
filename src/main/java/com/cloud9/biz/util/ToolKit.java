package com.cloud9.biz.util;

import com.cloud9.biz.models.vo.ValidateDelVo;
import com.roroclaw.base.handler.BizException;

import java.util.List;

/**
 * Created by roroclaw on 2017/8/15.
 */
public class ToolKit {
    public static void validateDelFun(List<ValidateDelVo> validateDelVoList){
        String errMsg = "";
        for (int i=0;i < validateDelVoList.size(); i++){
            ValidateDelVo validateDelVo = validateDelVoList.get(i);
            if(validateDelVo.getNum() > 0){
                errMsg += validateDelVo.getMsg()+",";
            }
        }
        if(errMsg != null && !"".equals(errMsg)){
            throw new BizException(errMsg+"不可以删除!");
        }
    }
}
