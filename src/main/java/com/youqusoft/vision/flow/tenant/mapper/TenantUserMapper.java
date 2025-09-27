package com.youqusoft.vision.flow.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.tenant.model.entity.TenantUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.tenant.model.query.TenantUserQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantUserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户用户（多租户）Mapper接口
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Mapper
public interface TenantUserMapper extends BaseMapper<TenantUser> {

    /**
     * 获取租户用户（多租户）分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<TenantUserVO>} 租户用户（多租户）分页列表
     */
    Page<TenantUserVO> getTenantUserPage(Page<TenantUserVO> page, TenantUserQuery queryParams);

}
