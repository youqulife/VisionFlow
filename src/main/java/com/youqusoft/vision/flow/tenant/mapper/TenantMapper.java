package com.youqusoft.vision.flow.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.tenant.model.entity.Tenant;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.tenant.model.query.TenantQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户信息Mapper接口
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {

    /**
     * 获取租户信息分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<TenantVO>} 租户信息分页列表
     */
    Page<TenantVO> getTenantPage(Page<TenantVO> page, TenantQuery queryParams);

}
