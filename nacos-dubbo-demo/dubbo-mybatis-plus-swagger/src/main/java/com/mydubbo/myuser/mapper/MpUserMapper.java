package com.mydubbo.myuser.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mydubbo.myuser.entity.MpUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2019-05-11
 */
@Repository
public interface MpUserMapper extends BaseMapper<MpUser> {
	 /**
     * 自定义sql分页
     * @param page
     * @param queryWrapper 看这里看这里，如果自定义的方法中需要用到wrapper查询条件，需要这样写
     * @return
     */
    IPage<MpUser> selectMyPage(IPage<MpUser> page, @Param(Constants.WRAPPER) Wrapper<MpUser> queryWrapper);
    
    IPage<MpUser> selectSqlPage(IPage<MpUser> page,@Param("query")  Map<String,Object> params);

}
