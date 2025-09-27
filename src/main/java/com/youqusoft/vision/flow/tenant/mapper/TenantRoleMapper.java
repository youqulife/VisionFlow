package com.youqusoft.vision.flow.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.tenant.model.entity.TenantRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.tenant.model.query.TenantRoleQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantRoleVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户角色Mapper接口
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Mapper
public interface TenantRoleMapper extends BaseMapper<TenantRole> {

    /**
     * 获取租户角色分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<TenantRoleVO>} 租户角色分页列表
     */
    Page<TenantRoleVO> getTenantRolePage(Page<TenantRoleVO> page, TenantRoleQuery queryParams);

}
