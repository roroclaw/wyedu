package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysToken;
import com.cloud9.biz.models.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysTokenMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysToken record);

    int insertSelective(SysToken record);

    SysToken selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysToken record);

    int updateByPrimaryKey(SysToken record);

    SysToken selectByToken(@Param("accToken")String accToken);

    List<SysToken> selectByUserApp(@Param("userId")String userId,@Param("appId")String appId);

    void refreshToken(@Param("oldToken")String oldToken,@Param("newToken") String newToken);

    void refreshTokenTime(@Param("token")String token,@Param("tokenTime") Date tokenTime);
}