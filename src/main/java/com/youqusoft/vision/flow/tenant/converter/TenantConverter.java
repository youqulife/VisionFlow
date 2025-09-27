package com.youqusoft.vision.flow.tenant.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.tenant.model.entity.Tenant;
import com.youqusoft.vision.flow.tenant.model.form.TenantForm;

/**
 * 租户信息对象转换器
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Mapper(componentModel = "spring")
public interface TenantConverter{

    TenantForm toForm(Tenant entity);

    Tenant toEntity(TenantForm formData);
}