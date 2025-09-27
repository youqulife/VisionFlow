package com.youqusoft.vision.flow.tenant.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.tenant.model.entity.TenantUser;
import com.youqusoft.vision.flow.tenant.model.form.TenantUserForm;

/**
 * 租户用户（多租户）对象转换器
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Mapper(componentModel = "spring")
public interface TenantUserConverter{

    TenantUserForm toForm(TenantUser entity);

    TenantUser toEntity(TenantUserForm formData);
}