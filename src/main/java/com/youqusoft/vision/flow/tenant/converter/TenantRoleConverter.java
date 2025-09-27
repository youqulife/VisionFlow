package com.youqusoft.vision.flow.tenant.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.tenant.model.entity.TenantRole;
import com.youqusoft.vision.flow.tenant.model.form.TenantRoleForm;

/**
 * 租户角色对象转换器
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Mapper(componentModel = "spring")
public interface TenantRoleConverter{

    TenantRoleForm toForm(TenantRole entity);

    TenantRole toEntity(TenantRoleForm formData);
}