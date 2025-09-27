package com.youqusoft.vision.flow.tenant.converter;

import com.youqusoft.vision.flow.system.model.entity.Menu;
import com.youqusoft.vision.flow.system.model.form.MenuForm;
import com.youqusoft.vision.flow.system.model.vo.MenuVO;
import com.youqusoft.vision.flow.tenant.model.entity.TenantMenu;
import com.youqusoft.vision.flow.tenant.model.form.TenantMenuForm;
import com.youqusoft.vision.flow.tenant.model.vo.TenantMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单对象转换器
 *
 * @author Ray Hao
 * @since 2024/5/26
 */
@Mapper(componentModel = "spring")
public interface TenantMenuConverter {

    TenantMenuVO toVo(TenantMenu entity);

    @Mapping(target = "params", ignore = true)
    TenantMenuForm toForm(TenantMenu entity);

    @Mapping(target = "params", ignore = true)
    TenantMenu toEntity(TenantMenuForm menuForm);

}