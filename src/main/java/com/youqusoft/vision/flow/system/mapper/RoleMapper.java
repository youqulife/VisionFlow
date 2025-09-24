package com.youqusoft.vision.flow.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.system.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);
}
