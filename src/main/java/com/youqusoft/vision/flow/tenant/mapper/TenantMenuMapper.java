package com.youqusoft.vision.flow.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.system.model.entity.Menu;
import com.youqusoft.vision.flow.tenant.model.entity.TenantMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 菜单访问层
 *
 * @author Ray
 * @since 2022/1/24
 */

@Mapper
public interface TenantMenuMapper extends BaseMapper<TenantMenu> {

    /**
     * 获取菜单路由列表
     *
     * @param roleCodes 角色编码集合
     */
    List<TenantMenu> getMenusByRoleCodes(Set<String> roleCodes);

}
